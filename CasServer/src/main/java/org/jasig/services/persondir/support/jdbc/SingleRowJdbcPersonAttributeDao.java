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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.jasig.services.persondir.IPersonAttributes;
import org.jasig.services.persondir.support.CaseInsensitiveAttributeNamedPersonImpl;
import org.jasig.services.persondir.support.CaseInsensitiveNamedPersonImpl;
import org.jasig.services.persondir.support.MultivaluedPersonAttributeUtils;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

/**
 * An {@link org.jasig.services.persondir.IPersonAttributeDao}
 * implementation that maps from column names in the result of a SQL query
 * to attribute names. <br>
 * You must set a Map from column names to attribute names and only column names
 * appearing as keys in that map will be used.
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
 *         <td align="right" valign="top">columnsToAttributes</td>
 *         <td>
 *             The {@link Map} of {@link String} columns names to {@link String} or {@link Set}s
 *             of {@link String}s to use as attribute names in the returned Map. If a column name
 *             is not in the map the column name will be used in as the returned attribute name.
 *         </td>
 *         <td valign="top">No</td>
 *         <td valign="top">{@link java.util.Collections#EMPTY_MAP}</td>
 *     </tr>
 * </table>
 * 
 * @author andrew.petro@yale.edu
 * @author Eric Dalquist
 * @version $Revision$ $Date$
 * @since uPortal 2.5
 */
public class SingleRowJdbcPersonAttributeDao extends AbstractJdbcPersonAttributeDao<Map<String, Object>> {
    private static final ParameterizedRowMapper<Map<String, Object>> MAPPER = new ColumnMapParameterizedRowMapper(true);

    /**
     * Creates a new MultiRowJdbcPersonAttributeDao specifying the DataSource and SQL to use.
     * 
     * @param ds The DataSource to get connections from for executing queries, may not be null.
     * @param attrList Sets the query attribute list
     * @param sql The SQL to execute for user attributes, may not be null.
     */
    public SingleRowJdbcPersonAttributeDao(DataSource ds, String sql) {
        super(ds, sql);
    }

    /* (non-Javadoc)
     * @see org.jasig.services.persondir.support.jdbc.AbstractJdbcPersonAttributeDao#getRowMapper()
     */
    @Override
    protected ParameterizedRowMapper<Map<String, Object>> getRowMapper() {
        return MAPPER;
    }

    
    /* (non-Javadoc)
     * @see org.jasig.services.persondir.support.jdbc.AbstractJdbcPersonAttributeDao#parseAttributeMapFromResults(java.util.List, java.lang.String)
     */
    @Override
    protected List<IPersonAttributes> parseAttributeMapFromResults(List<Map<String, Object>> queryResults, String queryUserName) {
        final List<IPersonAttributes> peopleAttributes = new ArrayList<IPersonAttributes>(queryResults.size());
        
        for (final Map<String, Object> queryResult : queryResults) {
            final Map<String, List<Object>> multivaluedQueryResult = MultivaluedPersonAttributeUtils.toMultivaluedMap(queryResult);
            
            final IPersonAttributes person;
            if (queryUserName != null) {
                person = new CaseInsensitiveNamedPersonImpl(queryUserName, multivaluedQueryResult);
            }
            else {
                //Create the IPersonAttributes doing a best-guess at a userName attribute
                final String userNameAttribute = this.getConfiguredUserNameAttribute();
                person = new CaseInsensitiveAttributeNamedPersonImpl(userNameAttribute, multivaluedQueryResult);
            }
            
            peopleAttributes.add(person);
        }
        
        return peopleAttributes;
    }
}