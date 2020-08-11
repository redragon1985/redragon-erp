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

package org.jasig.services.persondir.support;

import java.util.List;
import java.util.Map;

import org.jasig.services.persondir.IPersonAttributes;

/**
 * Interface that describes what is essentially a mutable {@link IPersonAttributes} object
 * 
 * @author Eric Dalquist
 * @version $Revision$
 */
public interface IAdditionalDescriptors extends IPersonAttributes {

    /**
     * @param name The user name for the attributes
     */
    public void setName(String name);

    /**
     * @param attributes Attributes to add to the existing attribute Map
     */
    public void addAttributes(Map<String, List<Object>> attributes);

    /**
     * This should be atomic to the view of other methods on this interface.
     * 
     * @param attributes Replace all existing attributes witht he specified Map
     */
    public void setAttributes(Map<String, List<Object>> attributes);

    /**
     * Sets the specified attribute values
     * 
     * @param name Name of the attribute, must not be null
     * @param values Values for the attribute, may be null
     * @return The previous values for the attribute if they existed
     */
    public List<Object> setAttributeValues(String name, List<Object> values);

    /**
     * @param name Removes the specified attribute, must not be null
     * @return The removed values for the attribute if they existed
     */
    public List<Object> removeAttribute(String name);
}