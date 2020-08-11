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
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Map;

import org.apache.commons.collections.map.CaseInsensitiveMap;
import org.apache.commons.collections.map.ListOrderedMap;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.support.JdbcUtils;

/**
 * JDK5 clone of {@link org.springframework.jdbc.core.ColumnMapRowMapper}
 * 
 * @author Eric Dalquist
 * @version $Revision$
 */
public class ColumnMapParameterizedRowMapper implements ParameterizedRowMapper<Map<String, Object>> {
    private final boolean ignoreNull;
    
    public ColumnMapParameterizedRowMapper() {
        this(false);
    }
    
    public ColumnMapParameterizedRowMapper(boolean ignoreNull) {
        this.ignoreNull = ignoreNull;
    }
    
    
    /* (non-Javadoc)
     * @see org.springframework.jdbc.core.simple.ParameterizedRowMapper#mapRow(java.sql.ResultSet, int)
     */
    public final Map<String, Object> mapRow(ResultSet rs, int rowNum) throws SQLException {
        final ResultSetMetaData rsmd = rs.getMetaData();
        final int columnCount = rsmd.getColumnCount();
        final Map<String, Object> mapOfColValues = this.createColumnMap(columnCount);
        
        for (int i = 1; i <= columnCount; i++) {
            final String columnName = JdbcUtils.lookupColumnName(rsmd, i);
            final Object obj = this.getColumnValue(rs, i);
            if (!this.ignoreNull || obj != null) {
                final String key = this.getColumnKey(columnName);
                mapOfColValues.put(key, obj);
            }
        }

        return mapOfColValues;
    }

    /**
     * Create a Map instance to be used as column map.
     * <br/>
     * By default, a linked case-insensitive Map will be created
     * 
     * @param columnCount the column count, to be used as initial capacity for the Map
     * @return the new Map instance
     */
    @SuppressWarnings("unchecked")
    protected Map<String, Object> createColumnMap(int columnCount) {
        return ListOrderedMap.decorate(new CaseInsensitiveMap(columnCount > 0 ? columnCount : 1));
    }

    /**
     * Determine the key to use for the given column in the column Map.
     * 
     * @param columnName the column name as returned by the ResultSet
     * @return the column key to use
     * @see java.sql.ResultSetMetaData#getColumnName
     */
    protected String getColumnKey(String columnName) {
        return columnName;
    }

    /**
     * Retrieve a JDBC object value for the specified column.
     * <br/>
     * 
     * The default implementation uses the <code>getObject</code> method. Additionally, this implementation includes
     * a "hack" to get around Oracle returning a non standard object for their TIMESTAMP datatype.
     * 
     * @param rs is the ResultSet holding the data
     * @param index is the column index
     * @return the Object returned
     * @see org.springframework.jdbc.support.JdbcUtils#getResultSetValue
     */
    protected Object getColumnValue(ResultSet rs, int index) throws SQLException {
        return JdbcUtils.getResultSetValue(rs, index);
    }
}