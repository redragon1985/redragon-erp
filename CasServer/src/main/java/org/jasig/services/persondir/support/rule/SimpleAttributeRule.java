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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jasig.services.persondir.IPersonAttributes;
import org.jasig.services.persondir.support.NamedPersonImpl;

/**
 * Sets a specified attribute to a specified value when a specified seed value 
 * matches a specified pattern.
 */
public final class SimpleAttributeRule implements AttributeRule {

    // Instance Members.
    private final String whenKey;
    private final String whenPattern;
    private final String setUserName;
    private final String setKey;
    private final String setValue;
    private final Set<String> possibleAttributeNames;

    /*
     * Public API.
     */

    public SimpleAttributeRule(String whenKey, String whenPattern, String setUserName,
                            String setKey, String setValue) {

        // Assertions.
        if (whenKey == null) {
            String msg = "Argument 'whenKey' cannot be null.";
            throw new IllegalArgumentException(msg);
        }
        if (whenPattern == null) {
            String msg = "Argument 'whenPattern' cannot be null.";
            throw new IllegalArgumentException(msg);
        }
        if (setKey == null) {
            String msg = "Argument 'setKey' cannot be null.";
            throw new IllegalArgumentException(msg);
        }
        if (setUserName == null) {
            String msg = "Argument 'setUserName' cannot be null.";
            throw new IllegalArgumentException(msg);
        }
        if (setValue == null) {
            String msg = "Argument 'setValue' cannot be null.";
            throw new IllegalArgumentException(msg);
        }

        // Instance Members.
        this.whenKey = whenKey;
        this.whenPattern = whenPattern;
        this.setUserName = setUserName;
        this.setKey = setKey;
        this.setValue = setValue;
        
        this.possibleAttributeNames = Collections.singleton(this.setKey);
    }

    public boolean appliesTo(Map<String, List<Object>> userInfo) {

        // Assertions.
        if (userInfo == null) {
            String msg = "Argument 'userInfo' cannot be null.";
            throw new IllegalArgumentException(msg);
        }

        final List<Object> value = userInfo.get(whenKey);
        if (value == null) {
            // No problem... but we certainly don't apply in this case.
            return false;
        }

        // Figure out what to look at.
        String[] compare = null;
        try {
            compare = value.toArray(new String[value.size()]);
        } catch (ClassCastException cce) {
            String msg = "List values may contain only String instances.";
            throw new RuntimeException(msg, cce);
        }

        boolean rslt = false;   // default...
        for (int i=0; i < compare.length; i++) {
            if (compare[i].matches(whenPattern)) {
                rslt = true;
                break;
            }
        }

        return rslt;

    }

    public Set<IPersonAttributes> evaluate(Map<String, List<Object>> userInfo) {

        // Assertions.
        if (userInfo == null) {
            String msg = "Argument 'userInfo' cannot be null.";
            throw new IllegalArgumentException(msg);
        }
        if (!appliesTo(userInfo)) {
            String msg = "May not evaluate.  This rule does not apply.";
            throw new IllegalArgumentException(msg);
        }
        
        Map<String, List<Object>> rslt = new LinkedHashMap<String, List<Object>>();
        List<Object> value = new ArrayList<Object>(1);
        value.add(setValue);
        rslt.put(setKey, value);
        
        final IPersonAttributes person = new NamedPersonImpl(this.setUserName, rslt);
        return Collections.singleton(person);
    }

    public Set<String> getPossibleUserAttributeNames() {
        return this.possibleAttributeNames;
    }

    public Set<String> getAvailableQueryAttributes() {
        return Collections.singleton(this.whenKey);
    }
}
