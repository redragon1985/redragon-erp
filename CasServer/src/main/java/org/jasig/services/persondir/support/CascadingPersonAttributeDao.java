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

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jasig.services.persondir.IPersonAttributes;
import org.jasig.services.persondir.IPersonAttributeDao;
import org.jasig.services.persondir.support.merger.ReplacingAttributeAdder;


/**
 * This {@link org.jasig.services.persondir.IPersonAttributeDao}
 * implementation iterates through an ordered {@link java.util.List} of
 * {@link org.jasig.services.persondir.IPersonAttributeDao} impls
 * when getting user attributes.
 * <br>
 * The first DAO is queried using the seed {@link Map} passed to this class. The results
 * of the query are merged into a general result map. After the first DAO this general
 * result map used as the query seed for each DAO and each DAOs results are merged into it.
 * <br>
 * This behavior allows a DAO lower on the list to rely on attributes returned by a DAO
 * higher on the list.
 * <br>
 * The default merger for the general result set is {@link ReplacingAttributeAdder}.
 * <br>
 * Note that most DAOs expect a Map of String->String. Some of the DAOs return a Map of
 * String->Object or String->List. This may cause problems in the DAO if the key for an
 * attribute with a non String value matches a key needed by the DAO for the query it is
 * running.
 * <br>
 * It is <u>highly</u> recomended that the first DAO on the list for this class is
 * the {@link org.jasig.services.persondir.support.EchoPersonAttributeDaoImpl}
 * to ensure the seed gets placed into the general result map.
 * 
 * @author Eric Dalquist
 * @version $Revision$ $Date$
 * @since uPortal 2.5
 */
public class CascadingPersonAttributeDao extends AbstractAggregatingDefaultQueryPersonAttributeDao {
    public CascadingPersonAttributeDao() {
        this.attrMerger = new ReplacingAttributeAdder();
    }
    
    

    /**
     * If this is the first call or there are no results in the resultPeople Set the seed map is used. If not the
     * attributes of the first user in the resultPeople Set are used.
     *  
     * @see org.jasig.services.persondir.support.AbstractAggregatingDefaultQueryPersonAttributeDao#getAttributesFromDao(java.util.Map, boolean, org.jasig.services.persondir.IPersonAttributeDao, java.util.Set)
     */
    @Override
    protected Set<IPersonAttributes> getAttributesFromDao(Map<String, List<Object>> seed, boolean isFirstQuery, IPersonAttributeDao currentlyConsidering, Set<IPersonAttributes> resultPeople) {
        if (isFirstQuery || resultPeople == null || resultPeople.size() == 0) {
            return currentlyConsidering.getPeopleWithMultivaluedAttributes(seed);
        }
        
        Set<IPersonAttributes> mergedPeopleResults = null;
        for (final IPersonAttributes person : resultPeople) {
            final Map<String, List<Object>> queryAttributes = new LinkedHashMap<String, List<Object>>();
            
            //Add the userName into the query map
            final String userName = person.getName();
            if (userName != null) {
                final Map<String, List<Object>> userNameMap = this.toSeedMap(userName);
                queryAttributes.putAll(userNameMap);
            }
            
            //Add the rest of the attributes into the query map
            final Map<String, List<Object>> personAttributes = person.getAttributes();
            queryAttributes.putAll(personAttributes);
            
            final Set<IPersonAttributes> newResults = currentlyConsidering.getPeopleWithMultivaluedAttributes(queryAttributes);
            if (newResults != null) {
                if (mergedPeopleResults == null) {
                    //If this is the first valid result set just use it.
                    mergedPeopleResults = new LinkedHashSet<IPersonAttributes>(newResults);
                }
                else {
                    //Merge the Sets of IPersons
                    mergedPeopleResults = this.attrMerger.mergeResults(mergedPeopleResults, newResults);
                }
            }
        }
        
        return mergedPeopleResults;
    }
}