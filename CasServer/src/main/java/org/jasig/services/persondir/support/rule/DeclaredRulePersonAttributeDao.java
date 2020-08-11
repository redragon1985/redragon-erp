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

package org.jasig.services.persondir.support.rule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.Validate;
import org.jasig.services.persondir.IPersonAttributes;
import org.jasig.services.persondir.support.AbstractDefaultAttributePersonAttributeDao;
import org.jasig.services.persondir.support.IUsernameAttributeProvider;
import org.jasig.services.persondir.support.SimpleUsernameAttributeProvider;

/**
 * Implementation of uPortal's <code>IPersonAttributeDao</code> that evaluates
 * person directory information based on configurable rules.  You may chain as 
 * many rules as you like, but this DAO will apply <b>at most</b> one rule, the
 * first that triggers.
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
 *         <td align="right" valign="top">rules</td>
 *         <td>
 *             The array of {@link AttributeRule}s to use when 
 *         </td>
 *         <td valign="top">Yes</td>
 *         <td valign="top">null</td>
 *     </tr>
 * </table>
 */
public final class DeclaredRulePersonAttributeDao extends AbstractDefaultAttributePersonAttributeDao {

    /**
     * List of {@link AttributeRule} objects.
     */
    private List<AttributeRule> rules;


    /**
     * Creates a new DeclaredRulePersonAttributeDao specifying the attributeName to pass to
     * {@link #setDefaultAttributeName(String)} and the {@link List} of {@link AttributeRule}s
     * to pass to {@link #setRules(List)}
     * 
     * @param attributeName
     * @param rules
     */
    public DeclaredRulePersonAttributeDao(String attributeName, List<AttributeRule> rules) {
        // PersonDirectory won't stop for anything... we need decent logging.
        if (logger.isDebugEnabled()) {
            logger.debug("Creating DeclaredRulePersonAttributeDao with attributeName='" + attributeName + "' and rules='" + rules + "'");
        }

        // Instance Members.
        final IUsernameAttributeProvider usernameAttributeProvider = new SimpleUsernameAttributeProvider(attributeName);
        this.setUsernameAttributeProvider(usernameAttributeProvider);
        this.setRules(rules);

        // PersonDirectory won't stop for anything... we need decent logging.
        if (logger.isDebugEnabled()) {
            logger.debug("Created DeclaredRulePersonAttributeDao with attributeName='" + attributeName + "' and rules='" + rules + "'");
        }
    }
    
    /**
     * @return the rules
     */
    public List<AttributeRule> getRules() {
        return this.rules;
    }
    /**
     * @param rules the rules to set
     */
    public void setRules(List<AttributeRule> rules) {
        Validate.notEmpty(rules, "Argument 'rules' cannot be null or empty.");

        this.rules = Collections.unmodifiableList(new ArrayList<AttributeRule>(rules));
    }

    /* (non-Javadoc)
     * @see org.jasig.services.persondir.IPersonAttributeDao#getPeopleWithMultivaluedAttributes(java.util.Map)
     */
    public Set<IPersonAttributes> getPeopleWithMultivaluedAttributes(Map<String, List<Object>> seed) {
        Validate.notNull(seed, "Argument 'seed' cannot be null.");

        for (final AttributeRule rule : this.rules) {
            if (rule.appliesTo(seed)) {
                if (logger.isDebugEnabled()) {
                    logger.debug("Evaluating rule='" + rule + "' from the rules List");
                }

            	return rule.evaluate(seed);
            }
        }
        
        return null;
    }

    /**
     * Aggregates the results of calling {@link AttributeRule#getPossibleUserAttributeNames()}
     * on each {@link AttributeRule} instance in the rules array.
     * 
     * @see org.jasig.services.persondir.IPersonAttributeDao#getPossibleUserAttributeNames()
     */
    public Set<String> getPossibleUserAttributeNames() {
        Set<String> rslt = new LinkedHashSet<String>();

        for (final AttributeRule rule : this.rules) {
            final Set<String> possibleUserAttributeNames = rule.getPossibleUserAttributeNames();
            rslt.addAll(possibleUserAttributeNames);
        }

        return rslt;
    }

    /* (non-Javadoc)
     * @see org.jasig.services.persondir.IPersonAttributeDao#getAvailableQueryAttributes()
     */
    public Set<String> getAvailableQueryAttributes() {
        Set<String> rslt = new LinkedHashSet<String>();

        for (final AttributeRule rule : this.rules) {
            final Set<String> possibleUserAttributeNames = rule.getAvailableQueryAttributes();
            rslt.addAll(possibleUserAttributeNames);
        }

        return rslt;
    }
}
