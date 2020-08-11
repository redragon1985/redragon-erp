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

package org.jasig.services.persondir.support.rule;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jasig.services.persondir.IPersonAttributes;

/**
 * Defines the contract for a person directory user attribute rule for use with
 * the <code>DeclaredRulePersonAttributeDao</code>.
 */
public interface AttributeRule {

    /**
     * Indicates whether the rule applies to the user described by the specified
     * information.  Implementations of {@link AttributeRule} <strong>must
     * not change the input <code>Map</code></strong>. Implementations dictate
     * the expected types for the Keys and Values of the Map.
     * 
     * @param userInfo immutable Map of attributes to values for the implementation to determine if this rule applies, must not be null.
     * @return TRUE if this rule applies to the Map data, FALSE if not.
     * @throws IllegalArgumentException If <code>userInfo</code> is <code>null.</code>
     */
    public abstract boolean appliesTo(Map<String, List<Object>> userInfo);

    /**
     * Applies the embodied rule to the user described by the specified
     * information and returns the result.
     * 
     * This method follows the same contract as {@link org.jasig.services.persondir.IPersonAttributeDao#getPeopleWithMultivaluedAttributes(Map)}
     */
    public abstract Set<IPersonAttributes> evaluate(Map<String, List<Object>> userInfo);

    /**
     * Indicates the complete set of user attribute names that <em>may</em> be
     * returned by a call to <code>evaluate</code>.
     * 
     * This method follows the same contract as {@link org.jasig.services.persondir.IPersonAttributeDao#getPossibleUserAttributeNames()}
     */
    public abstract Set<String> getPossibleUserAttributeNames();
    
    /**
     * @see IPersonAttributeDao#getAvailableQueryAttributes()
     */
    public abstract Set<String> getAvailableQueryAttributes();
}
