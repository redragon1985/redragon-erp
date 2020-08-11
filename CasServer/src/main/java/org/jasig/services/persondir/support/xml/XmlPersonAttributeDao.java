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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.jasig.services.persondir.IPersonAttributes;
import org.jasig.services.persondir.support.AbstractDefaultAttributePersonAttributeDao;
import org.jasig.services.persondir.support.IUsernameAttributeProvider;
import org.jasig.services.persondir.support.NamedPersonImpl;
import org.jasig.services.persondir.support.xml.CachingJaxbLoader.UnmarshallingCallback;
import org.jasig.services.persondir.support.xml.om.Attribute;
import org.jasig.services.persondir.support.xml.om.Person;
import org.jasig.services.persondir.support.xml.om.PersonData;
import org.jasig.services.persondir.util.PatternHelper;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;


/**
 * XML backed person attribute DAO that supports wildcard searching. The XML files provided must conform to the
 * PersonData.xsd which resides at the root of the classpath.
 * 
 * @author Eric Dalquist
 * @version $Revision$
 */
public class XmlPersonAttributeDao extends AbstractDefaultAttributePersonAttributeDao implements InitializingBean {
    private final AttributeLoader attributeLoader = new AttributeLoader();
    
    //Set of all attribute names across all available IPersonAttributes 
    private Set<String> attributesCache = null;
    //Map from attribute name to the Set of IPersonAttributes that have that attribute
    private Map<String, Set<IPersonAttributes>> personByAttributeCache = null;
    //Map from person name to IPersonAttributes
    private Map<String, IPersonAttributes> personByNameCache = null;
    
    
    private CachingJaxbLoader<PersonData> jaxbLoader;
    private Resource mappedXmlResource;
    
    public CachingJaxbLoader<PersonData> getJaxbLoader() {
        return jaxbLoader;
    }
    /**
     * The {@link CachingJaxbLoader} to use to load the {@link PersonData}, if set the mappedXmlResource property is
     * ignored.
     */
    public void setJaxbLoader(CachingJaxbLoader<PersonData> jaxbLoader) {
        this.jaxbLoader = jaxbLoader;
    }

    public Resource getMappedXmlResource() {
        return mappedXmlResource;
    }
    /**
     * The XML {@link Resource} to load the {@link PersonData} from, required if the jaxbLoader property is not set.
     */
    public void setMappedXmlResource(Resource mappedXmlResource) {
        this.mappedXmlResource = mappedXmlResource;
    }
    
    /* (non-Javadoc)
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    public void afterPropertiesSet() throws Exception {
        if (this.jaxbLoader == null && this.mappedXmlResource == null) {
            throw new BeanCreationException("Either the 'jaxbLoader' property or the 'mappedXmlResource' property needs to be set");
        }
        
        if (this.jaxbLoader == null) {
            this.jaxbLoader = new CachingJaxbLoaderImpl<PersonData>(PersonData.class);
            ((CachingJaxbLoaderImpl<PersonData>)this.jaxbLoader).setMappedXmlResource(this.mappedXmlResource);
        }
    }
    
    
    /* (non-Javadoc)
     * @see org.jasig.services.persondir.IPersonAttributeDao#getAvailableQueryAttributes()
     */
    public Set<String> getAvailableQueryAttributes() {
        this.jaxbLoader.getUnmarshalledObject(this.attributeLoader);
        return this.attributesCache;
    }

    /* (non-Javadoc)
     * @see org.jasig.services.persondir.IPersonAttributeDao#getPossibleUserAttributeNames()
     */
    public Set<String> getPossibleUserAttributeNames() {
        this.jaxbLoader.getUnmarshalledObject(this.attributeLoader);
        return this.attributesCache;
    }

    /* (non-Javadoc)
     * @see org.jasig.services.persondir.support.AbstractDefaultAttributePersonAttributeDao#getPerson(java.lang.String)
     */
    @Override
    public IPersonAttributes getPerson(String uid) {
        this.jaxbLoader.getUnmarshalledObject(this.attributeLoader);
        return this.personByNameCache.get(uid);
    }

    /* (non-Javadoc)
     * @see org.jasig.services.persondir.IPersonAttributeDao#getPeopleWithMultivaluedAttributes(java.util.Map)
     */
    public Set<IPersonAttributes> getPeopleWithMultivaluedAttributes(Map<String, List<Object>> query) {
        this.jaxbLoader.getUnmarshalledObject(this.attributeLoader);
        
        //Tracks persons that could match the query
        final Map<String, IPersonAttributes> canidatePersons = new LinkedHashMap<String, IPersonAttributes>();
        
        boolean firstAttribute = true;
        for (final Map.Entry<String, List<Object>> queryEntry : query.entrySet()) {
            final String entryKey = queryEntry.getKey();

            //Skip attributes that no people contain
            if (!this.attributesCache.contains(entryKey)) {
                continue;
            }
            
            //Build list of non-blank attribute values
            final List<String> entryValues = new LinkedList<String>();
            for (final Object entryValue : queryEntry.getValue()) {
                //Skip null and blank values
                final String entry;
                if (entryValue == null || StringUtils.isBlank(entry = entryValue.toString())) {
                    continue;
                }
                
                entryValues.add(entry);
            }
            
            //Skip attributes that have no non-blank values
            if (entryValues.size() == 0) {
                continue;
            }
            
            //Build Set of Persons that have the current attribute
            final Set<IPersonAttributes> personsForAttribute = this.personByAttributeCache.get(entryKey);
            for (final Iterator<IPersonAttributes> canidateItr = canidatePersons.values().iterator(); canidateItr.hasNext();) {
                final IPersonAttributes canidate = canidateItr.next();
                if (!personsForAttribute.contains(canidate)) {
                    canidateItr.remove();
                }
            }
            
            final Map<String, IPersonAttributes> attributeCanidatePersons = new LinkedHashMap<String, IPersonAttributes>();
            final Set<String> attributeMissPersons = new HashSet<String>();
            
            //Iterate over each possible value for an attribute checking for a match in the personsForAttribute 
            for (final String queryString : entryValues) {
                //Convert the query value into a regular expression pattern
                final Pattern queryPattern = PatternHelper.compilePattern(queryString);
                
                //Look for the attribute value in each potential IPersonAttributes
                for (final IPersonAttributes person : personsForAttribute) {
                    //If this isn't the first attribute and this person isn't a canidate skip them
                    final String name = person.getName();
                    if (!firstAttribute && !canidatePersons.containsKey(name)) {
                        continue;
                    }

                    //Track if any of the person's attributes match
                    boolean foundMatch = false;
                    
                    //Compare the query pattern with the person's attribute values
                    final List<Object> personAttributeValues = person.getAttributeValues(entryKey);
                    if (personAttributeValues != null) {
                        for (final Object personAttributeValue : personAttributeValues) {
                            //Skip null values
                            if (personAttributeValue == null) {
                                continue;
                            }
                            
                            final String personAttributeString = personAttributeValue.toString();
                            final Matcher personAttributeMatcher = queryPattern.matcher(personAttributeString);
                            if (personAttributeMatcher.matches()) {
                                //If the attribute matches put the person in the canidate map and break out of the personAttributeValues loop
                                attributeCanidatePersons.put(name, person);
                                attributeMissPersons.remove(name);
                                foundMatch = true;
                                break;
                            }
                        }
                    }
                    
                    //No match for this attribute, remove the person from the canidate map if they are there
                    if (!foundMatch && !attributeCanidatePersons.containsKey(name)) {
                        attributeMissPersons.add(name);
                    }
                }
            }
            
            canidatePersons.putAll(attributeCanidatePersons);
            for (final String userName : attributeMissPersons) {
                canidatePersons.remove(userName);
            }

            //Ran through a query entry completely
            firstAttribute = false;
            
            //If no potential matches are left give up since canidatePersons never grows after the first pass
            if (canidatePersons.size() == 0) {
                break;
            }
        }
        
        return new LinkedHashSet<IPersonAttributes>(canidatePersons.values());
    }
    
    
    /**
     * Internal loader that takes care of parsing out the loaded data from the XML file into some
     * maps that are easier to search
     */
    private class AttributeLoader implements UnmarshallingCallback<PersonData> {

        /* (non-Javadoc)
         * @see org.jasig.services.persondir.support.xml.CachingJaxbLoader.UnmarshallingCallback#postProcessUnmarshalling(java.lang.Object)
         */
        public synchronized void postProcessUnmarshalling(PersonData unmarshalledObject) {
            final Set<String> attributeNames = new LinkedHashSet<String>();
            final Map<String, Set<IPersonAttributes>> personByAttributeCache = new LinkedHashMap<String, Set<IPersonAttributes>>();
            final Map<String, IPersonAttributes> personByNameCache = new LinkedHashMap<String, IPersonAttributes>();

            final IUsernameAttributeProvider usernameAttributeProvider = XmlPersonAttributeDao.this.getUsernameAttributeProvider();
            final String usernameAttribute = usernameAttributeProvider.getUsernameAttribute();
            attributeNames.add(usernameAttribute);
            
            for (final Person xmlPerson : unmarshalledObject.getPerson()) {
                final Map<String, List<Object>> attributes = new LinkedHashMap<String, List<Object>>();
                
                for (final Attribute xmlAttribute : xmlPerson.getAttribute()) {
                    final String key = xmlAttribute.getKey();
                    attributeNames.add(key);
                    
                    attributes.put(key, new ArrayList<Object>(xmlAttribute.getValue()));
                }
                
                final IPersonAttributes personAttributes = new NamedPersonImpl(xmlPerson.getName(), attributes);
                personByNameCache.put(personAttributes.getName(), personAttributes);
                
                for (final String key : personAttributes.getAttributes().keySet()) {
                    Set<IPersonAttributes> personsForAttribute = personByAttributeCache.get(key);
                    if (personsForAttribute == null) {
                        personsForAttribute = new LinkedHashSet<IPersonAttributes>();
                        personByAttributeCache.put(key, personsForAttribute);
                    }
                    personsForAttribute.add(personAttributes);
                }
            }
            
            XmlPersonAttributeDao.this.attributesCache = Collections.unmodifiableSet(attributeNames);
            XmlPersonAttributeDao.this.personByAttributeCache = Collections.unmodifiableMap(personByAttributeCache);
            XmlPersonAttributeDao.this.personByNameCache = Collections.unmodifiableMap(personByNameCache);
        }
    }
}
