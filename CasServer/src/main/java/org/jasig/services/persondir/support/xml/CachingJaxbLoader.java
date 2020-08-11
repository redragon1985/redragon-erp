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

/**
 * JAXB Object unmarshalling and caching service
 * 
 * @author Eric Dalquist
 * @version $Revision$
 * @param <T>
 */
public interface CachingJaxbLoader<T> {

    /**
     * @param callback Callback that will be executed after the object is unmarshalled (if it needs to be) but before it is returned
     * @see #getUnmarshalledObject() 
     */
    public T getUnmarshalledObject(UnmarshallingCallback<T> callback);
    
    /**
     * Loads and unmarshalls the XML as needed, returning the unmarshalled object
     */
    public T getUnmarshalledObject();

    public interface UnmarshallingCallback<T> {
        /**
         * Allow for specific handling of of the unmarshalled object before it is returned by a call to
         * {@link JaxbLoader#getUnmarshalledObject(UnmarshallingCallback)} that triggered a reload. If
         * this method throws an exception the loaded object will not be cached and the exception will
         * be propegated to the caller of {@link JaxbLoader#getUnmarshalledObject(UnmarshallingCallback)}.
         */
        public void postProcessUnmarshalling(T unmarshalledObject);
    }
}