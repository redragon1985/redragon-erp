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
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jasig.services.persondir.IPersonAttributes;


/**
 * Simply returns the seed it is passed.
 * 
 * @author Eric Dalquist
 * @version $Revision$ $Date$
 * @since uPortal 2.5
 */
public class EchoPersonAttributeDaoImpl extends AbstractDefaultAttributePersonAttributeDao {

    /**
     * Returns a duplicate of the seed it is passed.
     * @return a Map equal to but not the same reference as the seed.
     * @see org.jasig.services.persondir.IPersonAttributeDao#getPeopleWithMultivaluedAttributes(java.util.Map)
     */
    public Set<IPersonAttributes> getPeopleWithMultivaluedAttributes(Map<String, List<Object>> query) {
        if (query == null) {
            throw new IllegalArgumentException("seed may not be null");
        }

        return Collections.singleton((IPersonAttributes)new AttributeNamedPersonImpl(query));
    }

    /**
     * Possible attributes are unknown; will always return <code>null</code>.
     * @return null
     * @see org.jasig.services.persondir.IPersonAttributeDao#getPossibleUserAttributeNames()
     */
    public Set<String> getPossibleUserAttributeNames() {
        return null;
    }

    /* (non-Javadoc)
     * @see org.jasig.services.persondir.IPersonAttributeDao#getAvailableQueryAttributes()
     */
    public Set<String> getAvailableQueryAttributes() {
        return null;
    }
}
