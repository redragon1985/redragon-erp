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

package org.jasig.services.persondir.support.ldap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.naming.directory.SearchControls;

import org.apache.commons.lang.StringUtils;
import org.jasig.services.persondir.IPersonAttributes;
import org.jasig.services.persondir.support.AbstractQueryPersonAttributeDao;
import org.jasig.services.persondir.support.CaseInsensitiveAttributeNamedPersonImpl;
import org.jasig.services.persondir.support.CaseInsensitiveNamedPersonImpl;
import org.jasig.services.persondir.support.QueryType;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.ContextSource;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.ldap.filter.Filter;
import org.springframework.ldap.filter.LikeFilter;
import org.springframework.util.Assert;

/**
 * LDAP implementation of {@link org.jasig.services.persondir.IPersonAttributeDao}.
 * 
 * In the case of multi valued attributes a {@link java.util.List} is set as the value.
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
 *         <td align="right" valign="top">searchControls</td>
 *         <td>
 *             Set the {@link SearchControls} used for executing the LDAP query.
 *         </td>
 *         <td valign="top">No</td>
 *         <td valign="top">Default instance with SUBTREE scope.</td>
 *     </tr>
 *     <tr>
 *         <td align="right" valign="top">baseDN</td>
 *         <td>
 *             The base DistinguishedName to use when executing the query filter.
 *         </td>
 *         <td valign="top">No</td>
 *         <td valign="top">""</td>
 *     </tr>
 *     <tr>
 *         <td align="right" valign="top">contextSource</td>
 *         <td>
 *             A {@link ContextSource} from the Spring-LDAP framework. Provides a DataSource
 *             style object that this DAO can retrieve LDAP connections from.
 *         </td>
 *         <td valign="top">Yes</td>
 *         <td valign="top">null</td>
 *     </tr>
 *     <tr>
 *         <td align="right" valign="top">setReturningAttributes</td>
 *         <td>
 *             If the ldap attributes set in the ldapAttributesToPortalAttributes Map should be copied
 *             into the {@link SearchControls#setReturningAttributes(String[])}. Setting this helps reduce
 *             wire traffic of ldap queries.
 *         </td>
 *         <td valign="top">No</td>
 *         <td valign="top">true</td>
 *     </tr>
 *     <tr>
 *         <td align="right" valign="top">queryType</td>
 *         <td>
 *             How multiple attributes in a query should be concatenated together. The other option is OR.
 *         </td>
 *         <td valign="top">No</td>
 *         <td valign="top">AND</td>
 *     </tr>
 * </table>
 * 
 * @author andrew.petro@yale.edu
 * @author Eric Dalquist
 * @version $Revision$ $Date$
 * @since uPortal 2.5
 */
public class LdapPersonAttributeDao extends AbstractQueryPersonAttributeDao<LogicalFilterWrapper> implements InitializingBean {
    private static final Pattern QUERY_PLACEHOLDER = Pattern.compile("\\{0\\}");
    private final static AttributesMapper MAPPER = new AttributeMapAttributesMapper();

    /**
     * The LdapTemplate to use to execute queries on the DirContext
     */
    private LdapTemplate ldapTemplate = null;

    private String baseDN = "";
    private String queryTemplate = null;
    private ContextSource contextSource = null;
    private SearchControls searchControls = new SearchControls();
    private boolean setReturningAttributes = true;
    private QueryType queryType = QueryType.AND;
    
    
    public LdapPersonAttributeDao() {
        this.searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        this.searchControls.setReturningObjFlag(false);
    }
    
    /* (non-Javadoc)
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    public void afterPropertiesSet() throws Exception {
        final Map<String, Set<String>> resultAttributeMapping = this.getResultAttributeMapping();
        if (this.setReturningAttributes && resultAttributeMapping != null) {
            this.searchControls.setReturningAttributes(resultAttributeMapping.keySet().toArray(new String[resultAttributeMapping.size()]));
        }
        
        if (this.contextSource == null) {
            throw new BeanCreationException("contextSource must be set");
        }
    }

    
    /* (non-Javadoc)
     * @see org.jasig.services.persondir.support.AbstractQueryPersonAttributeDao#appendAttributeToQuery(java.lang.Object, java.lang.String, java.util.List)
     */
    @Override
    protected LogicalFilterWrapper appendAttributeToQuery(LogicalFilterWrapper queryBuilder, String dataAttribute, List<Object> queryValues) {
        if (queryBuilder == null) {
            queryBuilder = new LogicalFilterWrapper(this.queryType);
        }
        
        for (final Object queryValue : queryValues) {
            final String queryValueString = queryValue == null ? null : queryValue.toString();
            
            if (StringUtils.isNotBlank(queryValueString)) {
                final Filter filter;
                if (!queryValueString.contains("*")) {
                    filter = new EqualsFilter(dataAttribute, queryValueString);
                }
                else {
                    filter = new LikeFilter(dataAttribute, queryValueString);
                }
                
                queryBuilder.append(filter);
            }
        }
        
        return queryBuilder;
    }

    /* (non-Javadoc)
     * @see org.jasig.services.persondir.support.AbstractQueryPersonAttributeDao#getPeopleForQuery(java.lang.Object, java.lang.String)
     */
    @Override
    protected List<IPersonAttributes> getPeopleForQuery(LogicalFilterWrapper queryBuilder, String queryUserName) {
        final String generatedLdapQuery = queryBuilder.encode();

        //If no query is generated return null since the query cannot be run
        if (StringUtils.isBlank(generatedLdapQuery)) {
            return null;
        }
        
        //Insert the generated query into the template if it is configured
        final String ldapQuery;
        if (this.queryTemplate == null) {
            ldapQuery = generatedLdapQuery;
        }
        else {
            final Matcher queryMatcher = QUERY_PLACEHOLDER.matcher(this.queryTemplate);
            ldapQuery = queryMatcher.replaceAll(generatedLdapQuery);
        }
        
        //Execute the query
        @SuppressWarnings("unchecked")
        final List<Map<String, List<Object>>> queryResults = this.ldapTemplate.search(this.baseDN, ldapQuery, this.searchControls, MAPPER);
        
        final List<IPersonAttributes> peopleAttributes = new ArrayList<IPersonAttributes>(queryResults.size());
        for (final Map<String, List<Object>> queryResult : queryResults) {
            final IPersonAttributes person;
            if (queryUserName != null) {
                person = new CaseInsensitiveNamedPersonImpl(queryUserName, queryResult);
            }
            else {
                //Create the IPersonAttributes doing a best-guess at a userName attribute
                final String userNameAttribute = this.getConfiguredUserNameAttribute();
                person = new CaseInsensitiveAttributeNamedPersonImpl(userNameAttribute, queryResult);
            }
            
            peopleAttributes.add(person);
        }
        
        return peopleAttributes;
    }

    /**
     * @see javax.naming.directory.SearchControls#getTimeLimit()
     * @deprecated Set the property on the {@link SearchControls} and set that via {@link #setSearchControls(SearchControls)}
     */
    @Deprecated
    public int getTimeLimit() {
        return this.searchControls.getTimeLimit();
    }

    /**
     * @see javax.naming.directory.SearchControls#setTimeLimit(int)
     * @deprecated
     */
    @Deprecated
    public void setTimeLimit(int ms) {
        this.searchControls.setTimeLimit(ms);
    }
    
    /**
     * @return The base distinguished name to use for queries.
     */
    public String getBaseDN() {
        return this.baseDN;
    }

    /**
     * @param baseDN The base distinguished name to use for queries.
     */
    public void setBaseDN(String baseDN) {
        if (baseDN == null) {
            baseDN = "";
        }

        this.baseDN = baseDN;
    }

    /**
     * @return The ContextSource to get DirContext objects for queries from.
     */
    public ContextSource getContextSource() {
        return this.contextSource;
    }
    
    /**
     * @param contextSource The ContextSource to get DirContext objects for queries from.
     */
    public synchronized void setContextSource(final ContextSource contextSource) {
        Assert.notNull(contextSource, "contextSource can not be null");
        this.contextSource = contextSource;
        this.ldapTemplate = new LdapTemplate(this.contextSource);
    }

    /**
     * Sets the LdapTemplate, and thus the ContextSource (implicitly).
     *
     * @param ldapTemplate the LdapTemplate to query the LDAP server from.  CANNOT be NULL.
     */
    public synchronized void setLdapTemplate(final LdapTemplate ldapTemplate) {
        Assert.notNull(ldapTemplate, "ldapTemplate cannot be null");
        this.ldapTemplate = ldapTemplate;
        this.contextSource = this.ldapTemplate.getContextSource();
    }

    /**
     * @return Search controls to use for LDAP queries
     */
    public SearchControls getSearchControls() {
        return this.searchControls;
    }
    /**
     * @param searchControls Search controls to use for LDAP queries
     */
    public void setSearchControls(SearchControls searchControls) {
        Assert.notNull(searchControls, "searchControls can not be null");
        this.searchControls = searchControls;
    }

    /**
     * @return the queryType
     */
    public QueryType getQueryType() {
        return queryType;
    }
    /**
     * Type of logical operator to use when joining WHERE clause components
     * 
     * @param queryType the queryType to set
     */
    public void setQueryType(QueryType queryType) {
        this.queryType = queryType;
    }

    public String getQueryTemplate() {
        return this.queryTemplate;
    }
    /**
     * Optional wrapper template for the generated part of the query. Use {0} as a placeholder for where the generated query should be inserted.
     */
    public void setQueryTemplate(String queryTemplate) {
        this.queryTemplate = queryTemplate;
    }
}
