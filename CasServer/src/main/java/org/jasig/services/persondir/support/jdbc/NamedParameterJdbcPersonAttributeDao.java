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

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.jasig.services.persondir.IPersonAttributes;
import org.jasig.services.persondir.support.AbstractDefaultAttributePersonAttributeDao;
import org.jasig.services.persondir.support.CaseInsensitiveNamedPersonImpl;
import org.jasig.services.persondir.support.IUsernameAttributeProvider;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.namedparam.AbstractSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

/**
 * Implementation of PersonAttributeDao based on Spring's {@link NamedParameterJdbcTemplate}.  
 * Specify any valid SQL, using named parameters as necessary.  Name the columns you want as 
 * attributes with the property 'userAttributeNames' (you can adjust names to taste using 
 * aliases in your SQL).  Supports multi-valued attributes through inner joins.
 * 
 * <p>Example SQL:  SELECT USER_ID FROM UP_USER WHERE USER_NAME = :username</p>
 * 
 * <p>Example Sprring Configuration:</p>
 * 
 * &lt;bean id="rolesUserSource" class="org.jasig.services.persondir.support.jdbc.NamedParameterJdbcPersonAttributeDao"&gt;
 *     &lt;property name="dataSource" ref="PersonDB" /&gt;
 *     &lt;property name="sql"&gt;
 *         &lt;value&gt;
 *             SELECT rolename AS scsRoleName
 *             from s_external_role
 *             where oprid = UPPER(:username)
 *         &lt;/value&gt;
 *     &lt;/property&gt;
 *     &lt;property name="usernameAttributeProvider" ref="usernameAttributeProvider" /&gt;
 *     &lt;property name="userAttributeNames"&gt;
 *         &lt;set&gt;
 *             &lt;value&gt;scsRoleName&lt;/value&gt;
 *         &lt;/set&gt;
 *     &lt;/property&gt;
 * &lt;/bean&gt;
 * 
 * @author drew wills
 */
public class NamedParameterJdbcPersonAttributeDao extends AbstractDefaultAttributePersonAttributeDao implements InitializingBean {
    
    // Instance Members
    private NamedParameterJdbcTemplate jdbcTemplate;
        
    /*
     * Spring-Configured Dependencies
     */

    private DataSource dataSource;
    private String sql;
    private IUsernameAttributeProvider usernameAttributeProvider;
    private Set<String> availableQueryAttributes = null;  // default
    private Set<String> userAttributeNames = null;  // default

    @Required
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Required
    public void setSql(String sql) {
        this.sql = sql;
    }
    
    @Required
    public void setUsernameAttributeProvider(IUsernameAttributeProvider usernameAttributeProvider) {
        this.usernameAttributeProvider = usernameAttributeProvider;
    }
    
    public void setAvailableQueryAttributes(Set<String> availableQueryAttributes) {
        this.availableQueryAttributes = Collections.unmodifiableSet(availableQueryAttributes);
    }
    
    @Required
    public void setUserAttributeNames(Set<String> userAttributeNames) {
        this.userAttributeNames = Collections.unmodifiableSet(userAttributeNames);
    }
    
    @Override
    public void afterPropertiesSet() throws Exception {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    /*
     * IPersonAttributeDao Implementation
     */

    @Override
    public Set<String> getAvailableQueryAttributes() {
        return availableQueryAttributes;
    }

    @Override
    public Set<IPersonAttributes> getPeopleWithMultivaluedAttributes(Map<String, List<Object>> queryParameters) {
        String username = usernameAttributeProvider.getUsernameFromQuery(queryParameters);
        RowCallbackHandlerImpl rslt = new RowCallbackHandlerImpl(username);
        jdbcTemplate.query(sql, new SqlParameterSourceImpl(queryParameters), rslt);
        return rslt.getResults();
    }

    @Override
    public Set<String> getPossibleUserAttributeNames() {
        return userAttributeNames;
    }
    
    /*
     * Nested Types
     */
    
    private static final class SqlParameterSourceImpl extends AbstractSqlParameterSource {
        
        // Instance Members.
        private final Map<String, List<Object>> queryParameters;
        
        public SqlParameterSourceImpl(Map<String, List<Object>> queryParameters) {
            this.queryParameters = queryParameters;
        }

        @Override
        public Object getValue(String paramName) throws IllegalArgumentException {
            // Use the first one
            List<Object> val = queryParameters.get(paramName);
            return val != null && val.size() != 0
                        ? val.get(0) 
                        : null;
        }

        @Override
        public boolean hasValue(String paramName) {
            List<Object> val = queryParameters.get(paramName);
            return val != null && val.size() != 0;
        }
        
    }
    
    private /* not static*/ final class RowCallbackHandlerImpl implements RowCallbackHandler {
        
        // Instance Members
        final String username;
        final Map<String,Set<Object>> attributes = new HashMap<String,Set<Object>>();
        
        public RowCallbackHandlerImpl(String username) {
            this.username = username;
        }

        @Override
        public void processRow(ResultSet rs) throws SQLException {

            for (String attrName : userAttributeNames) {
                Set<Object> values = attributes.get(attrName);
                if (values == null) {
                    values = new HashSet<Object>();
                    attributes.put(attrName, values);
                }
                Object val = rs.getObject(attrName);
                if (val != null) {
                    values.add(val);
                }
            }

        }
        
        public Set<IPersonAttributes> getResults() {
            Map<String,List<Object>> mapOfLists = new HashMap<String,List<Object>>();
            for (Map.Entry<String,Set<Object>> y : attributes.entrySet()) {
                mapOfLists.put(y.getKey(), new ArrayList<Object>(y.getValue()));
            }
            IPersonAttributes person = new CaseInsensitiveNamedPersonImpl(username, mapOfLists);
            return Collections.singleton(person);
        }
        
    }

}
