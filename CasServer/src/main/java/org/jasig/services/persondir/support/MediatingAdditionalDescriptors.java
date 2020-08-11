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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.Validate;

/**
 * Uses a List of {@link IAdditionalDescriptors} objects to delegate method calls to. For set/add/remove
 * operations all delegates are called. For get operations the first delegate to return a non-null/empty
 * result is used.
 * 
 * @author Eric Dalquist
 * @version $Revision$
 */
public class MediatingAdditionalDescriptors implements IAdditionalDescriptors {
    private static final long serialVersionUID = 1L;
    
    private List<IAdditionalDescriptors> delegateDescriptors = Collections.emptyList();
    

    public void setDelegateDescriptors(List<IAdditionalDescriptors> delegateDescriptors) {
        Validate.noNullElements(delegateDescriptors, "delegateDescriptors List cannot be null or contain null attributes");
        this.delegateDescriptors = new ArrayList<IAdditionalDescriptors>(delegateDescriptors);
    }

    /* (non-Javadoc)
     * @see org.jasig.services.persondir.support.IAdditionalDescriptors#addAttributes(java.util.Map)
     */
    public void addAttributes(Map<String, List<Object>> attributes) {
        for (final IAdditionalDescriptors additionalDescriptors : this.delegateDescriptors) {
            additionalDescriptors.addAttributes(attributes);
        }
    }

    /**
     * Returns list of all removed values
     * 
     * @see org.jasig.services.persondir.support.IAdditionalDescriptors#removeAttribute(java.lang.String)
     */
    public List<Object> removeAttribute(String name) {
        List<Object> removedValues = null;
        
        for (final IAdditionalDescriptors additionalDescriptors : this.delegateDescriptors) {
            final List<Object> values = additionalDescriptors.removeAttribute(name);
            if (values != null) {
                if (removedValues == null) {
                    removedValues = new ArrayList<Object>(values);
                }
                else {
                    removedValues.addAll(values);
                }
            }
        }
        
        return removedValues;
    }

    /**
     * Returns list of all replaced values
     * 
     * @see org.jasig.services.persondir.support.IAdditionalDescriptors#setAttributeValues(java.lang.String, java.util.List)
     */
    public List<Object> setAttributeValues(String name, List<Object> values) {
        List<Object> replacedValues = null;
        
        for (final IAdditionalDescriptors additionalDescriptors : delegateDescriptors) {
            final List<Object> oldValues = additionalDescriptors.setAttributeValues(name, values);
            if (oldValues != null) {
                if (replacedValues == null) {
                    replacedValues = new ArrayList<Object>(oldValues);
                }
                else {
                    replacedValues.addAll(oldValues);
                }
            }
        }
        
        return replacedValues;
    }

    /* (non-Javadoc)
     * @see org.jasig.services.persondir.support.IAdditionalDescriptors#setAttributes(java.util.Map)
     */
    public void setAttributes(Map<String, List<Object>> attributes) {
        for (final IAdditionalDescriptors additionalDescriptors : delegateDescriptors) {
            additionalDescriptors.setAttributes(attributes);
        }
    }

    /* (non-Javadoc)
     * @see org.jasig.services.persondir.support.IAdditionalDescriptors#setName(java.lang.String)
     */
    public void setName(String name) {
        for (final IAdditionalDescriptors additionalDescriptors : delegateDescriptors) {
            additionalDescriptors.setName(name);
        }
    }

    /* (non-Javadoc)
     * @see org.jasig.services.persondir.IPersonAttributes#getAttributeValue(java.lang.String)
     */
    public Object getAttributeValue(String name) {
        for (final IAdditionalDescriptors additionalDescriptors : delegateDescriptors) {
            final Map<String, List<Object>> attributes = additionalDescriptors.getAttributes();
            if (attributes != null && attributes.containsKey(name)) {
                return additionalDescriptors.getAttributeValue(name);
            }
        }
        
        return null;
    }

    /* (non-Javadoc)
     * @see org.jasig.services.persondir.IPersonAttributes#getAttributeValues(java.lang.String)
     */
    public List<Object> getAttributeValues(String name) {
        for (final IAdditionalDescriptors additionalDescriptors : delegateDescriptors) {
            final Map<String, List<Object>> attributes = additionalDescriptors.getAttributes();
            if (attributes != null && attributes.containsKey(name)) {
                return additionalDescriptors.getAttributeValues(name);
            }
        }
        
        return null;
    }

    /* (non-Javadoc)
     * @see org.jasig.services.persondir.IPersonAttributes#getAttributes()
     */
    public Map<String, List<Object>> getAttributes() {
        for (final IAdditionalDescriptors additionalDescriptors : delegateDescriptors) {
            final Map<String, List<Object>> attributes = additionalDescriptors.getAttributes();
            if (attributes != null && !attributes.isEmpty()) {
                return attributes;
            }
        }
        
        return Collections.emptyMap();
    }

    /* (non-Javadoc)
     * @see java.security.Principal#getName()
     */
    public String getName() {
        for (final IAdditionalDescriptors additionalDescriptors : delegateDescriptors) {
            final String name = additionalDescriptors.getName();
            if (name != null) {
                return name;
            }
        }
        
        return null;
    }
}
