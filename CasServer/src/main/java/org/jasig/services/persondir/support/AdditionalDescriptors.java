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
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.Validate;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.jasig.services.persondir.IPersonAttributes;

/**
 * Implementation of {@link IPersonAttributes} for use with 
 * {@link AdditionalDescriptorsPersonAttributeDao}.  Unlike most other 
 * implementations, <code>AdditionalDescriptors</code> is mutable.  An instance 
 * of this class typically lives for the duration of a session or application, 
 * and external components are expected to add attributes to the collection 
 * after creation. 
 * 
 * @author awills
 */
public class AdditionalDescriptors implements IAdditionalDescriptors {

    // Static Members.
    private static final long serialVersionUID = 1L;
    
    // Private Members.
    private String name = null;
    private Map<String,List<Object>> attributes = new ConcurrentHashMap<String,List<Object>>();

    /*
     * Public API.
     */

    public Object getAttributeValue(String name) {
        List<Object> values = attributes.get(name);
        return values == null || values.size() == 0 ? null : values.get(0);
    }

    public List<Object> getAttributeValues(String name) {
        final List<Object> values = this.attributes.get(name);
        if (values == null) {
            return null;
        }

        return Collections.unmodifiableList(values);
    }

    public Map<String, List<Object>> getAttributes() {
        return Collections.unmodifiableMap(this.attributes);
    }

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void addAttributes(Map<String, List<Object>> attributes) {
        for (final Map.Entry<String, List<Object>> newAttribute : attributes.entrySet()) {
            final String name = newAttribute.getKey();
            final List<Object> values = newAttribute.getValue();
            
            if (values == null) {
                this.attributes.put(name, null);
            }
            else {
                this.attributes.put(name, new ArrayList<Object>(values));
            }
        }
    }
    
    public void setAttributes(Map<String, List<Object>> attributes) {
        Validate.notNull(attributes, "Argument 'attributes' cannot be null");
        final Map<String, List<Object>> newAttributes = new ConcurrentHashMap<String, List<Object>>();
        
        for (final Map.Entry<String, List<Object>> newAttribute : attributes.entrySet()) {
            final String name = newAttribute.getKey();
            final List<Object> values = newAttribute.getValue();
            
            if (values == null) {
                newAttributes.put(name, null);
            }
            else {
                newAttributes.put(name, new ArrayList<Object>(values));
            }
        }
        
        this.attributes = newAttributes;
    }
    
    public List<Object> setAttributeValues(String name, List<Object> values) {
        // Assertions.
        if (name == null) {
            String msg = "Argument 'name' cannot be null.";
            throw new IllegalArgumentException(msg);
        }

        if (values == null) {
            return this.attributes.put(name, null);
        }

        return this.attributes.put(name, new ArrayList<Object>(values));
    }
    
    public List<Object> removeAttribute(String name) {
        Validate.notNull(name, "Argument 'name' cannot be null");
        return this.attributes.remove(name);
    }

    
    /**
     * @see java.lang.Object#equals(Object)
     */
    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof IPersonAttributes)) {
            return false;
        }
        IPersonAttributes rhs = (IPersonAttributes) object;
        return new EqualsBuilder()
            .append(this.name, rhs.getName())
            .isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder(1574945487, 827742191)
            .append(this.name)
            .toHashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
            .append("name", this.name)
            .append("attributes", this.attributes)
            .toString();
    }
}
