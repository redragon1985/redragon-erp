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

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.Validate;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.jasig.services.persondir.IPersonAttributes;

/**
 * Provides creation of attributes via a {@link MessageFormat} string using other user attributes as the arguments to
 * the format string.
 * <br>
 * <br>
 * Configuration:
 * <table border="1">
 *     <tr>
 *         <th align="left">Property</th>
 *         <th align="left">Description</th>
 *         <th align="left">Required</th>
 *         <th align="left">Default</th>
 *     </tr>
 *     <tr>
 *         <td align="right" valign="top">formatAttributes</td>
 *         <td>
 *             A {@link Set} of {@link FormatAttribute} objects that define the formatted user attributes to be
 *             generated. 
 *         </td>
 *         <td valign="top">Yes</td>
 *         <td valign="top">null</td>
 *     </tr>
 * </table>
 * 
 * @author Eric Dalquist
 * @version $Revision$
 */
public class MessageFormatPersonAttributeDao extends AbstractDefaultAttributePersonAttributeDao {
    private Set<FormatAttribute> formatAttributes;
    private Set<String> possibleUserAttributeNames;
    
    
    public Set<FormatAttribute> getFormatAttributes() {
        return formatAttributes;
    }
    /**
     * @param formatAttributes the formatAttributes to set
     */
    public void setFormatAttributes(Set<FormatAttribute> formatAttributes) {
        Validate.notNull(formatAttributes, "formatAttributes can not be null");
        
        final Set<String> possibleUserAttributeNames = new LinkedHashSet<String>();
        for (final FormatAttribute formatAttribute : formatAttributes) {
            possibleUserAttributeNames.addAll(formatAttribute.attributeNames);
        }
        
        this.possibleUserAttributeNames = possibleUserAttributeNames;
        this.formatAttributes = formatAttributes;
    }


    /* (non-Javadoc)
     * @see org.jasig.services.persondir.IPersonAttributeDao#getAvailableQueryAttributes()
     */
    public Set<String> getAvailableQueryAttributes() {
        return null;
    }

    /* (non-Javadoc)
     * @see org.jasig.services.persondir.IPersonAttributeDao#getPeopleWithMultivaluedAttributes(java.util.Map)
     */
    public Set<IPersonAttributes> getPeopleWithMultivaluedAttributes(Map<String, List<Object>> query) {
        final Map<String, List<Object>> formattedAttributes = new LinkedHashMap<String, List<Object>>();
        
        for (final FormatAttribute formatAttribute : this.formatAttributes) {
            final String format = formatAttribute.getFormat();
            final List<String> sourceAttributes = formatAttribute.getSourceAttributes();
            
            //If the query doesn't contain all source attributes skip the formats
            if (!query.keySet().containsAll(sourceAttributes)) {
                this.logger.debug("Query does not contain all source attributes, skipping FormatAttribute " + formatAttribute);
                continue;
            }
            
            //Add attribute values to list
            final List<Object> sourceValues = new ArrayList<Object>(sourceAttributes.size());
            for (final String sourceAttribute : sourceAttributes) {
                final List<Object> values = query.get(sourceAttribute);
                sourceValues.add(values == null || values.size() == 0 ? null : values.get(0));
            }

            //Format the attribute
            final String formattedAttribute = MessageFormat.format(format, sourceValues.toArray());
            
            //Add formatted attribute under each name
            for (final String attributeName : formatAttribute.getAttributeNames()) {
                formattedAttributes.put(attributeName, Collections.singletonList((Object)formattedAttribute));
            }
        }
        
        //If no attributes were formatted just return null
        if (formattedAttributes.size() == 0) {
            return null;
        }
        
        //Create the person attributes object to return
        final IPersonAttributes personAttributes;
        final IUsernameAttributeProvider usernameAttributeProvider = this.getUsernameAttributeProvider();
        final String usernameFromQuery = usernameAttributeProvider.getUsernameFromQuery(query);
        if (usernameFromQuery != null) {
            personAttributes = new NamedPersonImpl(usernameFromQuery, formattedAttributes);
        }
        else {
            final String usernameAttribute = usernameAttributeProvider.getUsernameAttribute();
            personAttributes = new AttributeNamedPersonImpl(usernameAttribute, formattedAttributes);
        }
        
        return Collections.singleton(personAttributes);
    }

    /* (non-Javadoc)
     * @see org.jasig.services.persondir.IPersonAttributeDao#getPossibleUserAttributeNames()
     */
    public Set<String> getPossibleUserAttributeNames() {
        return this.possibleUserAttributeNames;
    }

    
    

    /**
     * Sets up a formatted attribute
     */
    public static class FormatAttribute {
        private Set<String> attributeNames;
        private String format;
        private List<String> sourceAttributes;
        
        public FormatAttribute() {
        }
        
        public FormatAttribute(Set<String> attributeNames, String format, List<String> sourceAttributes) {
            this.attributeNames = attributeNames;
            this.format = format;
            this.sourceAttributes = sourceAttributes;
        }



        public Set<String> getAttributeNames() {
            return attributeNames;
        }
        /**
         * @param attributeNames The resulting attributes the formatted string should be returned under
         */
        public void setAttributeNames(Set<String> attributeNames) {
            this.attributeNames = attributeNames;
        }

        public String getFormat() {
            return format;
        }
        /**
         * @param format The {@link java.text.MessageFormat} string used to generate the attribute
         */
        public void setFormat(String format) {
            this.format = format;
        }

        public List<String> getSourceAttributes() {
            return sourceAttributes;
        }
        /**
         * @param sourceAttributes The attributes to pass as arguments to the {@link java.text.MessageFormat}
         */
        public void setSourceAttributes(List<String> sourceAttributes) {
            this.sourceAttributes = sourceAttributes;
        }
        
        
        /**
         * @see java.lang.Object#equals(Object)
         */
        @Override
        public boolean equals(Object object) {
            if (object == this) {
                return true;
            }
            if (!(object instanceof FormatAttribute)) {
                return false;
            }
            FormatAttribute rhs = (FormatAttribute) object;
            return new EqualsBuilder()
                .append(this.attributeNames, rhs.attributeNames)
                .append(this.format, rhs.format)
                .append(this.sourceAttributes, rhs.sourceAttributes)
                .isEquals();
        }
        /**
         * @see java.lang.Object#hashCode()
         */
        @Override
        public int hashCode() {
            return new HashCodeBuilder(-1421658045, 156936851)
                .append(this.attributeNames)
                .append(this.format)
                .append(this.sourceAttributes)
                .toHashCode();
        }
        /**
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
            return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("attributeNames", this.attributeNames)
                .append("format", this.format)
                .append("sourceAttributes", this.sourceAttributes)
                .toString();
        }
    }
}
