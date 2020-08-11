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

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.Validate;
import org.jasig.services.persondir.IPersonAttributeDao;
import org.jasig.services.persondir.IPersonAttributes;

/**
 * This DAO wraps another DAO and only executes the wrapped DAO if the data in the seed matches
 * criteria set out by the configured <code>patterns</code> {@link Map}. Multiple seed attributes
 * can be tested by specifying the attribute name as the key of the <code>patterns</code> {@link Map}
 * and the regular expression pattern as the value.
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
 *         <td align="right" valign="top">patterns</td>
 *         <td>
 *             A {@link Map} of {@link String} attribute names to {@link String} regular
 *             expression patterns.
 *         </td>
 *         <td valign="top">Yes</td>
 *         <td valign="top">null</td>
 *     </tr>
 *     <tr>
 *         <td align="right" valign="top">targetPersonAttributeDao</td>
 *         <td>
 *             A the {@link IPersonAttributeDao} to delegate the call to if the pattern matching
 *             criteria is met.
 *         </td>
 *         <td valign="top">Yes</td>
 *         <td valign="top">null</td>
 *     </tr>
 *     <tr>
 *         <td align="right" valign="top">matchAllPatterns</td>
 *         <td>
 *             If true all patterns in the <code>patterns</code> map must past the value mapping
 *             criteria for the <code>targetPersonAttributeDao</code> to be delegated to. If false
 *             only one of the patterns needs to pass the value mapping criteria.
 *         </td>
 *         <td valign="top">No</td>
 *         <td valign="top">false</td>
 *     </tr>
 *     <tr>
 *         <td align="right" valign="top">matchAllValues</td>
 *         <td>
 *             If true all values for the attribute being tested must match the pattern testing
 *             it for the criteria to be met. If false only one of the values needs to match
 *             the pattern for the criteria to be met.
 *         </td>
 *         <td valign="top">No</td>
 *         <td valign="top">false</td>
 *     </tr>
 * </table>
 */
public final class RegexGatewayPersonAttributeDao extends AbstractDefaultAttributePersonAttributeDao {
    private boolean matchAllPatterns = false;
    private boolean matchAllValues = false;
    private Map<String, Pattern> patterns = null;
    private IPersonAttributeDao targetPersonAttributeDao = null;
    
    /**
     * Default constructor, {@link #setPatterns(Map)} and {@link #setTargetPersonAttributeDao(IPersonAttributeDao)}
     * will need to be called if this is used.
     */
    public RegexGatewayPersonAttributeDao() {
    }

    /**
     * Creates a RegexGatewayPersonAttributeDao that will test a single attribute. The specified
     * attribute is also configured as the {@link #setDefaultAttributeName(String)}.
     * 
     * @param attributeName The attribute to test, is also set as the defaultAttributeName.
     * @param pattern The pattern to test the specified attribute with.
     * @param enclosed The IPersonAttributeDao to delegate to if the pattern matches.
     */
    public RegexGatewayPersonAttributeDao(String attributeName, String pattern, IPersonAttributeDao enclosed) {
        // Instance Members.
        final IUsernameAttributeProvider usernameAttributeProvider = new SimpleUsernameAttributeProvider(attributeName);
        this.setUsernameAttributeProvider(usernameAttributeProvider);
        
        this.setPatterns(Collections.singletonMap(attributeName, pattern));
        this.setTargetPersonAttributeDao(enclosed);

        // PersonDirectory won't stop for anything... we need decent logging.
        if (logger.isDebugEnabled()) {
            logger.debug("Created RegexGatewayPersonAttributeDao with defaultAttributeName='" + attributeName + "' and patterns=" + this.patterns);
        }
    }
    
    /**
     * @return the patterns
     */
    public Map<String, String> getPatterns() {
        if (this.patterns == null) {
            return null;
        }
        
        final Map<String, String> toReturn = new LinkedHashMap<String, String>(this.patterns.size());
        
        for (final Map.Entry<String, Pattern> patternEntry : this.patterns.entrySet()) {
            final String attribute = patternEntry.getKey();
            final Pattern pattern = patternEntry.getValue();
            toReturn.put(attribute, pattern.pattern());
        }
        
        return Collections.unmodifiableMap(toReturn);
    }
    /**
     * @param patterns the patterns to set
     */
    public void setPatterns(Map<String, String> patterns) {
        Validate.notEmpty(patterns, "patterns Map may not be null and must contain at least 1 mapping.");
        
        final Map<String, Pattern> newPatterns = new LinkedHashMap<String, Pattern>(patterns.size());
        
        //Pre-compile patterns for performance
        for (final Map.Entry<String, String> patternEntry : patterns.entrySet()) {
            final String attribute = patternEntry.getKey();
            
            final String pattern = patternEntry.getValue();
            Validate.notNull(pattern, "pattern can not be null. attribute=" + attribute);

            final Pattern compiledPattern = Pattern.compile(pattern);
            
            newPatterns.put(attribute, compiledPattern);
        }
        
        this.patterns = Collections.unmodifiableMap(newPatterns);
    }

    /**
     * @return the targetPersonAttributeDao
     */
    public IPersonAttributeDao getTargetPersonAttributeDao() {
        return this.targetPersonAttributeDao;
    }
    /**
     * @param targetPersonAttributeDao the targetPersonAttributeDao to set
     */
    public void setTargetPersonAttributeDao(IPersonAttributeDao targetPersonAttributeDao) {
        Validate.notNull(targetPersonAttributeDao, "targetPersonAttributeDao may not be null");

        this.targetPersonAttributeDao = targetPersonAttributeDao;
    }
    
    /**
     * @return the matchAllPatterns
     */
    public boolean isMatchAllPatterns() {
        return this.matchAllPatterns;
    }
    /**
     * @param matchAllPatterns the matchAllPatterns to set
     */
    public void setMatchAllPatterns(boolean matchAllPatterns) {
        this.matchAllPatterns = matchAllPatterns;
    }

    /**
     * @return the matchAllValues
     */
    public boolean isMatchAllValues() {
        return this.matchAllValues;
    }
    /**
     * @param matchAllValues the matchAllValues to set
     */
    public void setMatchAllValues(boolean matchAllValues) {
        this.matchAllValues = matchAllValues;
    }

    /* (non-Javadoc)
     * @see org.jasig.services.persondir.IPersonAttributeDao#getPeopleWithMultivaluedAttributes(java.util.Map)
     */
    public Set<IPersonAttributes> getPeopleWithMultivaluedAttributes(Map<String, List<Object>> seed) {
        Validate.notNull(seed, "Argument 'seed' cannot be null.");

        if (patterns == null || patterns.size() < 1) {
            throw new IllegalStateException("patterns Map may not be null and must contain at least 1 mapping.");
        }
        if (targetPersonAttributeDao == null) {
            throw new IllegalStateException("targetPersonAttributeDao may not be null");
        }

        //Flag for patterns that match
        boolean matchedPatterns = false;
        
        //Iterate through all attributeName/pattern pairs
        for (final Map.Entry<String, Pattern> patternEntry : this.patterns.entrySet()) {
            final String attributeName = patternEntry.getKey();
            final List<Object> attributeValues = seed.get(attributeName);
            
            //Check if the value exists
            if (attributeValues == null) {
                if (this.matchAllPatterns) {
                    //Need to match ALL patters, if the attribute isn't in the seed it can't be matched, return null
                    if (this.logger.isInfoEnabled()) {
                        this.logger.info("All patterns must match and attribute='" + attributeName + "' does not exist in the seed, returning null.");
                    }

                    return null;
                }

                //Don't need to match all, just go to the next attribute and see if it exists
                continue;
            }
            
            //The pattern to test the attribute's value(s) with
            final Pattern compiledPattern = patternEntry.getValue();
            if (compiledPattern == null) {
                throw new IllegalStateException("Attribute '" + attributeName + "' has a null pattern");
            }
            
            //Flag for matching the pattern on the values
            boolean matchedValues = false;
            
            //Iterate over the values for the attribute, testing each against the pattern
            for (final Object valueObj : attributeValues) {
                final String value;
                try {
                    value = (String)valueObj;
                }
                catch (ClassCastException cce) {
                    final IllegalArgumentException iae = new IllegalArgumentException("RegexGatewayPersonAttributeDao can only accept seeds who's values are String or List of String. Attribute '" + attributeName + "' has a non-String value.");
                    iae.initCause(cce);
                    throw iae;
                }
                
                //Check if the value matches the pattern
                final Matcher valueMatcher = compiledPattern.matcher(value);
                matchedValues = valueMatcher.matches();
                
                //Only one value needs to be matched, this one matched so no need to test the rest, break out of the loop
                if (matchedValues && !this.matchAllValues) {
                    if (this.logger.isDebugEnabled()) {
                        this.logger.debug("value='" + value + "' matched pattern='" + compiledPattern + "' and only one value match is needed, leaving value matching loop.");
                    }

                    break;
                }
                //Need to match all values, this one failed so no need to test the rest, break out of the loop
                else if (!matchedValues && this.matchAllValues) {
                    if (this.logger.isDebugEnabled()) {
                        this.logger.debug("value='" + value + "' did not match pattern='" + compiledPattern + "' and all values need to match, leaving value matching loop.");
                    }
                    
                    break;
                }
                //Extra logging
                else if (this.logger.isDebugEnabled()) {
                    if (matchedValues) {
                        this.logger.debug("value='" + value + "' matched pattern='" + compiledPattern + "' and all values need to match, continuing value matching loop.");
                    }
                    else {
                        this.logger.debug("value='" + value + "' did not match pattern='" + compiledPattern + "' and only one value match is needed, continuing value matching loop.");
                    }
                }
            }
            
            matchedPatterns = matchedValues;
            
            //Only one pattern needs to be matched, this one matched so no need to test the rest, break out of the loop
            if (matchedPatterns && !this.matchAllPatterns) {
                if (this.logger.isDebugEnabled()) {
                    this.logger.debug("pattern='" + compiledPattern + "' found a match and only one pattern match is needed, leaving pattern matching loop.");
                }

                break;
            }
            //Need to match all patterns, this one failed so no need to test the rest, break out of the loop
            else if (!matchedPatterns && this.matchAllPatterns) {
                if (this.logger.isDebugEnabled()) {
                    this.logger.debug("pattern='" + compiledPattern + "' did not find a match and all patterns need to match, leaving pattern matching loop.");
                }

                break;
            }
            //Extra logging
            else if (this.logger.isDebugEnabled()) {
                if (matchedPatterns) {
                    this.logger.debug("pattern='" + compiledPattern + "' found a match and all patterns need to match, continuing pattern matching loop.");
                }
                else {
                    this.logger.debug("pattern='" + compiledPattern + "' did not find a match and only one pattern match is needed, continuing pattern matching loop.");
                }
            }
        }
        
        //Execute the wrapped DAO if the match criteria was met
        if (matchedPatterns) {
            if (this.logger.isInfoEnabled()) {
                this.logger.info("Matching criteria '" + this.patterns + "' was met for query '" + seed + "', delegating call to the targetPersonAttributeDao='" + this.targetPersonAttributeDao + "'");
            }
            
            return this.targetPersonAttributeDao.getPeopleWithMultivaluedAttributes(seed);
        }

        if (this.logger.isInfoEnabled()) {
            this.logger.info("Matching criteria '" + this.patterns + "' was not met for query '" + seed + "', return null");
        }
        
        return null;
    }

    /*
     * @see org.jasig.services.persondir.IPersonAttributeDao#getPossibleUserAttributeNames()
     */
    public Set<String> getPossibleUserAttributeNames() {
        return targetPersonAttributeDao.getPossibleUserAttributeNames();
    }

    /* (non-Javadoc)
     * @see org.jasig.services.persondir.IPersonAttributeDao#getAvailableQueryAttributes()
     */
    public Set<String> getAvailableQueryAttributes() {
        return this.targetPersonAttributeDao.getAvailableQueryAttributes();
    }
}
