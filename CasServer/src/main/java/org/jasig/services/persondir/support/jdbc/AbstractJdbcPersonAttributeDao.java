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

package org.jasig.services.persondir.support.jdbc;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.jasig.services.persondir.IPersonAttributeDao;
import org.jasig.services.persondir.IPersonAttributes;
import org.jasig.services.persondir.support.AbstractQueryPersonAttributeDao;
import org.jasig.services.persondir.support.QueryType;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

/**
 * Provides common logic for executing a JDBC based query including building the WHERE clause SQL string.
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
 *         <td align="right" valign="top">queryType</td>
 *         <td>
 *             How multiple attributes in a query should be concatenated together. The other option is OR.
 *         </td>
 *         <td valign="top">No</td>
 *         <td valign="top">AND</td>
 *     </tr>
 * </table>
 * 
 * @author Eric Dalquist 
 * @version $Revision$
 */
public abstract class AbstractJdbcPersonAttributeDao<R> extends AbstractQueryPersonAttributeDao<PartialWhereClause> {
    private static final Pattern WHERE_PLACEHOLDER = Pattern.compile("\\{0\\}");
    
    private final SimpleJdbcTemplate simpleJdbcTemplate;
    private final String queryTemplate;
    private QueryType queryType = QueryType.AND;
    
    /**
     * @param ds The DataSource to use for queries
     * @param queryTemplate Template to use for SQL query generation. Use {0} as the placeholder for where the generated portion of the WHERE clause should be inserted. 
     */
    public AbstractJdbcPersonAttributeDao(DataSource ds, String queryTemplate) {
        Validate.notNull(ds, "DataSource can not be null");
        Validate.notNull(queryTemplate, "queryTemplate can not be null");
        
        this.simpleJdbcTemplate = new SimpleJdbcTemplate(ds);
        this.queryTemplate = queryTemplate;
    }
    
    /**
     * @return the queryTemplate
     */
    public String getQueryTemplate() {
        return queryTemplate;
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


    /**
     * Takes the {@link List} from the query and parses it into the {@link List} of {@link IPersonAttributes} attributes to be returned.
     * 
     * @param queryResults Results from the query.
     * @param queryUserName The username passed in the query map, if no username attribute existed in the query Map null is provided.
     * @return The results of the query 
     */
    protected abstract List<IPersonAttributes> parseAttributeMapFromResults(final List<R> queryResults, String queryUserName);
    
    /**
     * @return The ParameterizedRowMapper to handle the results of the SQL query.
     */
    protected abstract ParameterizedRowMapper<R> getRowMapper();
    
    /* (non-Javadoc)
     * @see org.jasig.services.persondir.support.AbstractQueryPersonAttributeDao#appendAttributeToQuery(java.lang.Object, java.lang.String, java.util.List)
     */
    @Override
    protected PartialWhereClause appendAttributeToQuery(PartialWhereClause queryBuilder, String dataAttribute, List<Object> queryValues) {
        for (final Object queryValue : queryValues) {
            final String queryString = queryValue != null ? queryValue.toString() : null;
            if (StringUtils.isNotBlank(queryString)) {
            	//dongbin 2017.8.14 update
            	//当value为null字符串时，不组成查询字符串，只把值放入参数数组中
                if(!dataAttribute.equals("null")){
                	if (queryBuilder == null) {
                        queryBuilder = new PartialWhereClause();
                    }
                    else if (queryBuilder.sql.length() > 0) {
                        queryBuilder.sql.append(" ").append(this.queryType.toString()).append(" ");
                    }
                }

                //Convert to SQL wildcard
                final Matcher queryValueMatcher = IPersonAttributeDao.WILDCARD_PATTERN.matcher(queryString);
                final String formattedQueryValue = queryValueMatcher.replaceAll("%");
                
                System.out.println("%%%%%%%%%%%%%%"+formattedQueryValue);
                
                queryBuilder.arguments.add(formattedQueryValue);
                //dongbin 2017.8.14 update
            	//当value为null字符串时，不组成查询字符串，只把值放入参数数组中
                if(!dataAttribute.equals("null")){
                	if (dataAttribute != null) {
                        queryBuilder.sql.append(dataAttribute);
                        if (formattedQueryValue.equals(queryString)) {
                            queryBuilder.sql.append(" = ");
                        }
                        else {
                            queryBuilder.sql.append(" LIKE ");
                        }
                    }
                    queryBuilder.sql.append("?");
                }
                
            }
        }
        
        return queryBuilder;
    }

    
    /* (non-Javadoc)
     * @see org.jasig.services.persondir.support.AbstractQueryPersonAttributeDao#getPeopleForQuery(java.lang.Object, java.lang.String)
     */
    @Override
    protected List<IPersonAttributes> getPeopleForQuery(PartialWhereClause queryBuilder, String queryUserName) {
        //Execute the query
        final ParameterizedRowMapper<R> rowMapper = this.getRowMapper();
        
        final List<R> results;
        if (queryBuilder != null) {
            //Merge the generated SQL with the base query template
            final StringBuilder partialSqlWhere = queryBuilder.sql;
            final Matcher queryMatcher = WHERE_PLACEHOLDER.matcher(this.queryTemplate);
            final String querySQL = queryMatcher.replaceAll(partialSqlWhere.toString());
            
            System.out.println("#########################"+partialSqlWhere.toString());
            System.out.println("#########################"+queryTemplate);
            System.out.println("#########################"+querySQL);
            System.out.println("#########################"+queryBuilder.arguments.toString());
            
            results = this.simpleJdbcTemplate.query(querySQL, rowMapper, queryBuilder.arguments.toArray());
            
            if (this.logger.isDebugEnabled()) {
                this.logger.debug("Executed '" + this.queryTemplate + "' with arguments " + queryBuilder.arguments + " and got results " + results);
            }
        }
        else {
            results = this.simpleJdbcTemplate.query(this.queryTemplate, rowMapper);
            
            if (this.logger.isDebugEnabled()) {
                this.logger.debug("Executed '" + this.queryTemplate + "' and got results " + results);
            }
        }

        return this.parseAttributeMapFromResults(results, queryUserName);
    }
}
