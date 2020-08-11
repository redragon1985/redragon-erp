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

package org.jasig.services.persondir.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jasig.services.persondir.IPersonAttributeDao;

/**
 * @author Eric Dalquist
 * @version $Revision$
 */
public class PatternHelper {
    /**
     * Converts a String using the {@link IPersonAttributeDao#WILDCARD} into a valid regular expression
     * {@link Pattern} with the {@link IPersonAttributeDao#WILDCARD} replaced by .* and the rest of the
     * string escaped using {@link Pattern#quote(String)}
     */
    public static Pattern compilePattern(String queryString) {
        final StringBuilder queryBuilder = new StringBuilder();
        
        final Matcher queryMatcher = IPersonAttributeDao.WILDCARD_PATTERN.matcher(queryString);
        
        if (!queryMatcher.find()) {
            return Pattern.compile(Pattern.quote(queryString));
        }
        
        int start = queryMatcher.start();
        int previousEnd = -1;
        if (start > 0) {
            final String queryPart = queryString.substring(0, start);
            final String quotedQueryPart = Pattern.quote(queryPart);
            queryBuilder.append(quotedQueryPart);
        }
        queryBuilder.append(".*");

        do {
            start = queryMatcher.start();
            
            if (previousEnd != -1) {
                final String queryPart = queryString.substring(previousEnd, start);
                final String quotedQueryPart = Pattern.quote(queryPart);
                queryBuilder.append(quotedQueryPart);
                queryBuilder.append(".*");
            }
            
            previousEnd = queryMatcher.end();
        } while (queryMatcher.find());
        
        if (previousEnd < queryString.length()) {
            final String queryPart = queryString.substring(previousEnd);
            final String quotedQueryPart = Pattern.quote(queryPart);
            queryBuilder.append(quotedQueryPart);
        }
        
        return Pattern.compile(queryBuilder.toString());
    }
}
