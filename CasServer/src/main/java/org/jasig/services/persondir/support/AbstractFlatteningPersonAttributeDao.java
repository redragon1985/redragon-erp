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

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jasig.services.persondir.IPersonAttributes;


/**
 * Maps calls to {@link org.jasig.services.persondir.IPersonAttributeDao#getPeople(Map)} to
 * {@link org.jasig.services.persondir.IPersonAttributeDao#getPeopleWithMultivaluedAttributes(Map)}
 * 
 * @author Eric Dalquist
 * @version $Revision$
 */
public abstract class AbstractFlatteningPersonAttributeDao extends BasePersonAttributeDao {

    /* (non-Javadoc)
     * @see org.jasig.services.persondir.IPersonAttributeDao#getPeople(java.util.Map)
     */
    public final Set<IPersonAttributes> getPeople(Map<String, Object> query) {
        final Map<String, List<Object>> multivaluedSeed = MultivaluedPersonAttributeUtils.toMultivaluedMap(query);
        return this.getPeopleWithMultivaluedAttributes(multivaluedSeed);
    }
    
    /**
     * @deprecated Use {@link MultivaluedPersonAttributeUtils#toMultivaluedMap(Map)} instead. This will be removed in 1.6
     */
    @Deprecated
    protected Map<String, List<Object>> toMultivaluedSeed(Map<String, Object> seed) {
        return MultivaluedPersonAttributeUtils.toMultivaluedMap(seed);
    }
}
