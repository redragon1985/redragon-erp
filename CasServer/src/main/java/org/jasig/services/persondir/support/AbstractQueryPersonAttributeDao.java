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

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.Validate;
import org.jasig.services.persondir.IPersonAttributes;

/**
 * Provides common functionality for DAOs using a set of attribute values from the seed to
 * perform a query. Ensures the nessesary attributes to run the query exist on the seed and
 * organizes the values into an argument array.
 * 
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
 *         <td align="right" valign="top">queryAttributeMapping</td>
 *         <td>
 *             A {@link Map} from attribute names used in the query {@link Map} to attribute names to use in the SQL.
 *             The values can be either {@link String} or {@link Collection<String>} to use a single Map attribute under
 *             multiple names as in the SQL. If set only {@link Map} attributes listed will be used in the SQL. If not
 *             set all {@link Map} attributes are used as-is in the SQL.
 *         </td>
 *         <td valign="top">No</td>
 *         <td valign="top">null</td>
 *     </tr>
 *     <tr>
 *         <td align="right" valign="top">resultAttributeMapping</td>
 *         <td>
 *             A {@link Map} from SQL result names to returned attribute names. The values can be either {@link String} 
 *             or {@link Collection<String>} to use a single SQL result under multiple returned attributes. If set only
 *             SQL attributes listed will be returned. If not set all SQL attributes will be returned.
 *         </td>
 *         <td valign="top">No</td>
 *         <td valign="top">null</td>
 *     </tr>
 *     <tr>
 *         <td align="right" valign="top">requireAllQueryAttributes</td>
 *         <td>
 *             If the SQL should only be run if all attributes listed in the queryAttributeMapping exist in the query
 *             {@link Map}. Ignored if queryAttributeMapping is null
 *         </td>
 *         <td valign="top">No</td>
 *         <td valign="top">false</td>
 *     </tr>
 *     <tr>
 *         <td align="right" valign="top">unmappedUsernameAttribute</td>
 *         <td>
 *             The unmapped username attribute returned by the query. If null the value returned by the configured
 *             {@link IUsernameAttributeProvider} is used.
 *         </td>
 *         <td valign="top">No</td>
 *         <td valign="top">null</td>
 *     </tr>
 * </table>
 * 
 * @author Eric Dalquist 
 * @version $Revision$
 */
public abstract class AbstractQueryPersonAttributeDao<QB> extends AbstractDefaultAttributePersonAttributeDao {
    private Map<String, Set<String>> queryAttributeMapping;
    private Map<String, Set<String>> resultAttributeMapping;
    private Set<String> possibleUserAttributes;
    private boolean requireAllQueryAttributes = false;
    private boolean useAllQueryAttributes = true;
    private String unmappedUsernameAttribute = null;
    

    public boolean isUseAllQueryAttributes() {
        return this.useAllQueryAttributes;
    }
    /**
     * If {@link #setQueryAttributeMapping(Map)} is null this determines if no parameters should be specified 
     * or if all query attributes should be used as parameters. Defaults to true.
     */
    public void setUseAllQueryAttributes(boolean useAllQueryAttributes) {
        this.useAllQueryAttributes = useAllQueryAttributes;
    }
    
    
    /**
     * @return the queryAttributeMapping
     */
    public Map<String, Set<String>> getQueryAttributeMapping() {
        return queryAttributeMapping;
    }
    /**
     * Map from query attribute names to data-layer attribute names to use when building the query. If an ordered Map is
     * passed in the order of the attributes will be honored when building the query.
     *  
     * If not set query attributes will be used directly from the query Map.
     * 
     * @param queryAttributeMapping the queryAttributeMapping to set
     */
    public void setQueryAttributeMapping(final Map<String, ?> queryAttributeMapping) {
        final Map<String, Set<String>> parsedQueryAttributeMapping = MultivaluedPersonAttributeUtils.parseAttributeToAttributeMapping(queryAttributeMapping);
        
        if (parsedQueryAttributeMapping.containsKey("")) {
            throw new IllegalArgumentException("The map from attribute names to attributes must not have any empty keys.");
        }
        
        this.queryAttributeMapping = parsedQueryAttributeMapping;
    }

    /**
     * @return the resultAttributeMapping
     */
    public Map<String, Set<String>> getResultAttributeMapping() {
        return resultAttributeMapping;
    }
    /**
     * Set the {@link Map} to use for mapping from a data layer name to an attribute name or {@link Set} of attribute
     * names. Data layer names that are specified but have null mappings will use the column name for the attribute
     * name. Data layer names that are not specified as keys in this {@link Map} will be ignored.
     * <br>
     * The passed {@link Map} must have keys of type {@link String} and values of type {@link String} or a {@link Set} 
     * of {@link String}.
     * 
     * @param resultAttributeMapping {@link Map} from column names to attribute names, may not be null.
     * @throws IllegalArgumentException If the {@link Map} doesn't follow the rules stated above.
     * @see MultivaluedPersonAttributeUtils#parseAttributeToAttributeMapping(Map)
     */
    public void setResultAttributeMapping(Map<String, ?> resultAttributeMapping) {
        final Map<String, Set<String>> parsedResultAttributeMapping = MultivaluedPersonAttributeUtils.parseAttributeToAttributeMapping(resultAttributeMapping);
        
        if (parsedResultAttributeMapping.containsKey("")) {
            throw new IllegalArgumentException("The map from attribute names to attributes must not have any empty keys.");
        }
        
        final Collection<String> userAttributes = MultivaluedPersonAttributeUtils.flattenCollection(parsedResultAttributeMapping.values());
        
        this.resultAttributeMapping = parsedResultAttributeMapping;
        this.possibleUserAttributes = Collections.unmodifiableSet(new LinkedHashSet<String>(userAttributes));
    }
    
    /**
     * @return the requireAllQueryAttributes
     */
    public boolean isRequireAllQueryAttributes() {
        return requireAllQueryAttributes;
    }
    /**
     * If all attributes specified in the queryAttributeMapping keySet must be present to actually run the query
     * 
     * @param requireAllQueryAttributes the requireAllQueryAttributes to set
     */
    public void setRequireAllQueryAttributes(boolean requireAllQueryAttributes) {
        this.requireAllQueryAttributes = requireAllQueryAttributes;
    }
    
    /**
     * @return the userNameAttribute
     */
    public String getUnmappedUsernameAttribute() {
        return unmappedUsernameAttribute;
    }
    /**
     * The returned attribute to use as the userName for the mapped IPersons. If null the {@link #setDefaultAttributeName(String)}
     * value will be used and if that is null the {@link AttributeNamedPersonImpl#DEFAULT_USER_NAME_ATTRIBUTE} value is
     * used. 
     * 
     * @param userNameAttribute the userNameAttribute to set
     */
    public void setUnmappedUsernameAttribute(String userNameAttribute) {
        this.unmappedUsernameAttribute = userNameAttribute;
    }
    
    /* (non-Javadoc)
     * @see org.jasig.services.persondir.IPersonAttributeDao#getPeopleWithMultivaluedAttributes(java.util.Map)
     */
    public final Set<IPersonAttributes> getPeopleWithMultivaluedAttributes(Map<String, List<Object>> query) {
        Validate.notNull(query, "query may not be null.");
        
        //Generate the query to pass to the subclass
        final QB queryBuilder = this.generateQuery(query);
        if (queryBuilder == null && (this.queryAttributeMapping != null || this.useAllQueryAttributes == true)) {
            this.logger.debug("No queryBuilder was generated for query " + query + ", null will be returned");
            
            return null;
        }
        
        //Get the username from the query, if specified
        final IUsernameAttributeProvider usernameAttributeProvider = this.getUsernameAttributeProvider();
        final String username = usernameAttributeProvider.getUsernameFromQuery(query);
        
        //Execute the query in the subclass
        final List<IPersonAttributes> unmappedPeople = this.getPeopleForQuery(queryBuilder, username);
        if (unmappedPeople == null) {
            return null;
        }

        //Map the attributes of the found people according to resultAttributeMapping if it is set
        final Set<IPersonAttributes> mappedPeople = new LinkedHashSet<IPersonAttributes>();
        for (final IPersonAttributes unmappedPerson : unmappedPeople) {
            final IPersonAttributes mappedPerson = this.mapPersonAttributes(unmappedPerson);
            mappedPeople.add(mappedPerson);
        }
        
        return Collections.unmodifiableSet(mappedPeople);
    }
    
    /* (non-Javadoc)
     * @see org.jasig.services.persondir.IPersonAttributeDao#getAvailableQueryAttributes()
     */
    public Set<String> getAvailableQueryAttributes() {
        if (this.queryAttributeMapping == null) {
            return Collections.emptySet();
        }
        
        return Collections.unmodifiableSet(this.queryAttributeMapping.keySet());
    }
    
    /* (non-Javadoc)
     * @see org.jasig.services.persondir.IPersonAttributeDao#getPossibleUserAttributeNames()
     */
    public Set<String> getPossibleUserAttributeNames() {
        return this.possibleUserAttributes;
    }

    
    /**
     * Executes the query for the generated queryBuilder object and returns a list where each entry is a Map of
     * attributes for a single IPersonAttributes.
     * 
     * @param queryBuilder The query generated by calls to {@link #appendAttributeToQuery(Object, String, List)}
     * @param queryUserName The username passed in the query map, if no username attribute existed in the query Map null is provided.
     * @return The list of IPersons found by the query. The user attributes should be using the raw names from the data layer.
     */
    protected abstract List<IPersonAttributes> getPeopleForQuery(QB queryBuilder, String queryUserName);

    /**
     * Append the attribute and value to the queryBuilder.
     * 
     * @param queryBuilder The sub-class specific query builder object
     * @param dataAttribute The full attribute name to append
     * @param queryValues The values for the data attribute
     * @return An updated queryBuiler
     */
    protected abstract QB appendAttributeToQuery(QB queryBuilder, String dataAttribute, List<Object> queryValues);
    
    /**
     * Generates a query using the queryBuilder object passed by the subclass. Attribute/Value pairs are added to the
     * queryBuilder by calling {@link #appendAttributeToQuery(Object, String, String)}. Attributes are only added if
     * there is an attributed mapped in the queryAttributeMapping.
     * 
     * @param queryBuilder The sub-class specific object to pass to {@link #appendAttributeToQuery(Object, String, String)} when building the query
     * @param query The query Map to populate the queryBuilder with.
     * @return The fully populated query builder.
     */
    protected final QB generateQuery(Map<String, List<Object>> query) {
        QB queryBuilder = null;

        if (this.queryAttributeMapping != null) {
            for (final Map.Entry<String, Set<String>> queryAttrEntry : this.queryAttributeMapping.entrySet()) {
                String queryAttr = queryAttrEntry.getKey();
                //dongbin 2017.8.14 update
            	//当key为带#时，只取#号前的字符串，并取到值
                if(queryAttr.contains("#")){
                	queryAttr = queryAttr.substring(0, queryAttr.indexOf("#"));
                }
                final List<Object> queryValues = query.get(queryAttr);
                
                System.out.println("==================="+queryAttr);
                System.out.println("==================="+queryValues.toString());
                
                if (queryValues != null ) {
                    final Set<String> dataAttributes = queryAttrEntry.getValue();
                    
                    System.out.println("==================="+dataAttributes.toString());
                    
                    if (dataAttributes == null) {
                        if (this.logger.isDebugEnabled()) {
                            this.logger.debug("Adding attribute '" + queryAttr + "' with value '" + queryValues + "' to query builder '" + queryBuilder + "'");
                        }
                        
                        queryBuilder = this.appendAttributeToQuery(queryBuilder, null, queryValues); 
                    }
                    else {
                        for (final String dataAttribute : dataAttributes) {
                            if (this.logger.isDebugEnabled()) {
                                this.logger.debug("Adding attribute '" + dataAttribute + "' with value '" + queryValues + "' to query builder '" + queryBuilder + "'");
                            }
                            
                            queryBuilder = this.appendAttributeToQuery(queryBuilder, dataAttribute, queryValues); 
                        }
                    }
                }
                else if (this.requireAllQueryAttributes) {
                    this.logger.debug("Query " + query + " does not contain all nessesary attributes as specified by queryAttributeMapping " + this.queryAttributeMapping + ", null will be returned for the queryBuilder");
                    return null;
                }
            }
        }
        
        else if (this.useAllQueryAttributes) {
            for (final Map.Entry<String, List<Object>> queryAttrEntry : query.entrySet()) {
                final String queryKey = queryAttrEntry.getKey();
                final List<Object> queryValues = queryAttrEntry.getValue();
                
                if (this.logger.isDebugEnabled()) {
                    this.logger.debug("Adding attribute '" + queryKey + "' with value '" + queryValues + "' to query builder '" + queryBuilder + "'");
                }
                System.out.println("!!!==============="+queryKey+"===="+queryValues.toString());
                queryBuilder = this.appendAttributeToQuery(queryBuilder, queryKey, queryValues); 
            }
        }
        
        if (this.logger.isDebugEnabled()) {
            this.logger.debug("Generated query builder '" + queryBuilder + "' from query Map " + query + ".");
        }
        
        return queryBuilder;
    }
    
    /**
     * Uses resultAttributeMapping to return a copy of the IPersonAttributes with only the attributes specified in
     * resultAttributeMapping mapped to their result attribute names.
     * 
     * @param person The IPersonAttributes to map attributes for
     * @return A copy of the IPersonAttributes with mapped attributes, the original IPersonAttributes if resultAttributeMapping is null.
     */
    protected final IPersonAttributes mapPersonAttributes(final IPersonAttributes person) {
        final Map<String, List<Object>> personAttributes = person.getAttributes();
        
        final Map<String, List<Object>> mappedAttributes;
        //If no mapping just use the attributes as-is
        if (this.resultAttributeMapping == null) {
            mappedAttributes = personAttributes;
        }
        //Map the attribute names via the resultAttributeMapping
        else {
            mappedAttributes = new LinkedHashMap<String, List<Object>>(); 
            
            for (final Map.Entry<String, Set<String>> resultAttrEntry : this.resultAttributeMapping.entrySet()) {
                final String dataKey = resultAttrEntry.getKey();
                
                //Only map found data attributes
                if (personAttributes.containsKey(dataKey)) {
                    Set<String> resultKeys = resultAttrEntry.getValue();
                    
                    //If dataKey has no mapped resultKeys just use the dataKey
                    if (resultKeys == null) {
                        resultKeys = Collections.singleton(dataKey);
                    }
                    
                    //Add the value to the mapped attributes for each mapped key
                    final List<Object> value = personAttributes.get(dataKey);
                    for (final String resultKey : resultKeys) {
                        if (resultKey == null) {
                            //TODO is this possible?
                            mappedAttributes.put(dataKey, value);
                        }
                        else {
                            mappedAttributes.put(resultKey, value);
                        }
                    }
                }
            }
        }
        
        final IPersonAttributes newPerson;
        
        final String name = person.getName();
        if (name != null) {
            newPerson = new NamedPersonImpl(name, mappedAttributes);
        }
        else {
            final String userNameAttribute = this.getConfiguredUserNameAttribute();
            newPerson = new AttributeNamedPersonImpl(userNameAttribute, mappedAttributes);
        }
        
        return newPerson;
    }
    
    /**
     * @return The appropriate attribute to user for the user name. Since {@link #getDefaultAttributeName()} should
     * never return null this method should never return null either.
     */
    protected String getConfiguredUserNameAttribute() {
        //If configured explicitly use it
        if (this.unmappedUsernameAttribute != null) {
            return this.unmappedUsernameAttribute;
        }
        
        final IUsernameAttributeProvider usernameAttributeProvider = this.getUsernameAttributeProvider();
        return usernameAttributeProvider.getUsernameAttribute();
    }
}
