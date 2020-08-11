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

package org.jasig.services.persondir.support.merger;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jasig.services.persondir.IPersonAttributes;

/**
 * Interface for merging attributes from sibling PersonAttributeDaos. 
 * 
 * @author andrew.petro@yale.edu
 * @version $Revision$ $Date$
 */
public interface IAttributeMerger {
    /**
     * Merge the results of a {@link org.jasig.services.persondir.search.IPersonLookupDao#getUserIds(java.util.Map)} call
     * and a compiled results map.
     * 
     * @param toModify The compiled results map, this will be modified based on the values in toConsider.
     * @param toConsider The query results map, this will not be modified.
     */
    public Set<IPersonAttributes> mergeResults(Set<IPersonAttributes> toModify, Set<IPersonAttributes> toConsider);

    /**
     * Modify the "toModify" argument in consideration of the "toConsider" argument. Return the resulting Set which may
     * or may not be the same reference as the "toModify" argument.
     * 
     * The modification performed is implementation-specific -- implementations of this interface exist to perform some
     * particular transformation on the toModify argument given the toConsider argument.
     * 
     * @param toModify Modify this set
     * @param toConsider In consideration of this set
     * @return The modified set
     * @throws IllegalArgumentException if either toModify or toConsider is null
     */
    public Set<String> mergePossibleUserAttributeNames(Set<String> toModify, Set<String> toConsider);
    
    /**
     * Modify the "toModify" argument in consideration of the "toConsider" argument. Return the resulting Set which may
     * or may not be the same reference as the "toModify" argument.
     * 
     * The modification performed is implementation-specific -- implementations of this interface exist to perform some
     * particular transformation on the toModify argument given the toConsider argument.
     * 
     * @param toModify Modify this set
     * @param toConsider In consideration of this set
     * @return The modified set
     * @throws IllegalArgumentException if either toModify or toConsider is null
     */
    public Set<String> mergeAvailableQueryAttributes(Set<String> toModify, Set<String> toConsider);
    

    /**
     * Modify the "toModify" argument in consideration of the "toConsider" 
     * argument.  Return the resulting Map, which may or may not be the same
     * reference as the "toModify" argument.
     * The modification performed is implementation-specific -- implementations
     * of this interface exist to perform some particular transformation on
     * the toModify argument given the toConsider argument.
     * 
     * @param toModify - modify this map
     * @param toConsider - in consideration of this map
     * @return the modified Map
     * @throws IllegalArgumentException if either toModify or toConsider is null
     */
    public Map<String, List<Object>> mergeAttributes(Map<String, List<Object>> toModify, Map<String, List<Object>> toConsider);
}