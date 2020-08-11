/**
 * Licensed to Jasig under one or more contributor license
 * agreements. See the NOTICE file distributed with this work
 * for additional information regarding copyright ownership.
 * Jasig licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a
 * copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.jasig.services.persondir.support.xml;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.core.io.Resource;
import org.springframework.util.Assert;

/**
 * Base logic for loading unmarshalling an XML document via JAXB and only reloading the cached object model when needed.
 * The class attempts to monitor the lastModified date of the {@link Resource} to determine when to reload. If that fails
 * the resource is reloaded periodically as specified by the {@link #setNoLastModifiedReloadPeriod(long)} property.
 * 
 * The class determines the return type and the base package to use for the {@link JAXBContext#newInstance(String)} call
 * via the loadedType parameter provided to the constructor.
 * 
 * @author Eric Dalquist
 * @version $Revision$
 */
public class CachingJaxbLoaderImpl<T> implements CachingJaxbLoader<T> {
    protected final Class<T> loadedType;
    
    protected long noLastModifiedReloadPeriod = 5 * 60 * 1000; //5 minute default
    protected Resource mappedXmlResource;
    
    protected T unmarshalledObject;
    protected long lastModifiedTime = Integer.MIN_VALUE;
    
    public CachingJaxbLoaderImpl(Class<T> loadedType) {
        Assert.notNull(loadedType, "loadedType can not be null");
        this.loadedType = loadedType;
    }
    
    
    public long getNoLastModifiedReloadPeriod() {
        return noLastModifiedReloadPeriod;
    }
    /**
     * Period between reloads if last-modified of the {@link Resource} cannot be determined
     */
    public void setNoLastModifiedReloadPeriod(long noLastModifiedReloadPeriod) {
        this.noLastModifiedReloadPeriod = noLastModifiedReloadPeriod;
    }

    public Resource getMappedXmlResource() {
        return mappedXmlResource;
    }
    /**
     * The XML resource to load.
     */
    public void setMappedXmlResource(Resource mappedXmlResource) {
        this.mappedXmlResource = mappedXmlResource;
    }


    /* (non-Javadoc)
     * @see org.jasig.services.persondir.support.xml.CachingJaxbLoader#getUnmarshalledObject()
     */
    public T getUnmarshalledObject() {
        return this.getUnmarshalledObject(null);
    }

    /* (non-Javadoc)
     * @see org.jasig.services.persondir.support.xml.CachingJaxbLoader#getUnmarshalledObject(org.jasig.services.persondir.support.xml.CachingJaxbLoader.UnmarshallingCallback)
     */
    public T getUnmarshalledObject(UnmarshallingCallback<T> callback) {
        //Only bother checking for a change if the object already exists
        Long lastModified = null;
        if (this.unmarshalledObject != null) {
            lastModified = this.getLastModified();
            
            //Return immediately if nothing has changed
            if (this.isCacheValid(lastModified)) {
                return this.unmarshalledObject;
            }
        }
        
        final InputStream xmlInputStream = this.getXmlInputStream();
        final JAXBContext jaxbContext = this.getJAXBContext();
        final Unmarshaller unmarshaller = this.getUnmarshaller(jaxbContext);
        final T unmarshalledObject = this.unmarshal(xmlInputStream, unmarshaller);
        
        if (callback != null) {
            callback.postProcessUnmarshalling(unmarshalledObject);
        }
        
        this.unmarshalledObject = unmarshalledObject;
        if (lastModified != null) {
            this.lastModifiedTime = lastModified;
        }
        else {
            this.lastModifiedTime = System.currentTimeMillis();
        }
        
        return this.unmarshalledObject;
    }

    /**
     * @return The last modified date for the XML file, null if it cannot be determined
     */
    protected Long getLastModified() {
        try {
            return this.mappedXmlResource.lastModified();
        }
        catch (IOException ioe) {
            return null;
        }
    }

    /**
     * Determines if the cached unmarshalled object is still valid
     * 
     * @param lastModified last modified timestamp of the resource, null if not known.
     * @return true if the cached object should be used
     */
    protected boolean isCacheValid(Long lastModified) {
        return (lastModified != null && lastModified <= this.lastModifiedTime) || 
                (lastModified == null && (this.lastModifiedTime + this.noLastModifiedReloadPeriod) <= System.currentTimeMillis());
    }

    /**
     * @return The InputStream to read the XML file from
     */
    protected InputStream getXmlInputStream() {
        final InputStream xmlInputStream;
        try {
            xmlInputStream = this.mappedXmlResource.getInputStream();
        }
        catch (IOException e) {
            throw new RuntimeException("Failed to open InputStream for Resource: " + this.mappedXmlResource, e);
        }
        return xmlInputStream;
    }

    /**
     * @return The JAXB context to parse the XML resource with
     */
    protected JAXBContext getJAXBContext() {
        final Package loadedPackage = this.loadedType.getPackage();
        final String filterDisplayPackage = loadedPackage.getName();
        try {
            return JAXBContext.newInstance(filterDisplayPackage);
        }
        catch (JAXBException e) {
            throw new RuntimeException("Failed to create " + JAXBContext.class + " to unmarshal " + this.loadedType, e);
        }
    }

    /**
     * @param jaxbContext The context to get an unmarshaller for
     * @return An unmarshaller to use to generate object from the XML
     */
    protected Unmarshaller getUnmarshaller(final JAXBContext jaxbContext) {
        try {
            return jaxbContext.createUnmarshaller();
        }
        catch (JAXBException e) {
            throw new RuntimeException("Failed to create " + Unmarshaller.class + " to unmarshal " + this.loadedType, e);
        }
    }

    /**
     * @param xmlInputStream InputStream to read the XML from
     * @param unmarshaller Unmarshaller to generate the object from the XML with
     * @return An unmarshalled object model of the XML
     */
    @SuppressWarnings("unchecked")
    protected T unmarshal(final InputStream xmlInputStream, final Unmarshaller unmarshaller) {
        try {
            return (T)unmarshaller.unmarshal(xmlInputStream);
        }
        catch (JAXBException e) {
            throw new RuntimeException("Unexpected JAXB error while unmarshalling  " + this.mappedXmlResource, e);
        }
    }
}
