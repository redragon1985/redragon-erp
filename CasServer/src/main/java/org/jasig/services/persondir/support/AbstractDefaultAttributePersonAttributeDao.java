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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.Validate;
import org.jasig.services.persondir.IPersonAttributes;
import org.springframework.dao.support.DataAccessUtils;


/**
 * Abstract class implementing the IPersonAttributeDao method  {@link org.jasig.services.persondir.IPersonAttributeDao#getPerson(String)}
 * by delegation to {@link org.jasig.services.persondir.IPersonAttributeDao#getPeopleWithMultivaluedAttributes(Map)} using a configurable
 * default attribute name. If {@link org.jasig.services.persondir.IPersonAttributeDao#getPeopleWithMultivaluedAttributes(Map)} returnes
 * more than one {@link IPersonAttributes} is returned {@link org.springframework.dao.IncorrectResultSizeDataAccessException} is thrown.
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
 *         <td align="right" valign="top">usernameAttributeProvider</td>
 *         <td>
 *             The provider used to determine the username attribute to use when no attribute is specified in the query. This
 *             is primarily used for calls to {@link #getPerson(String)}.
 *         </td>
 *         <td valign="top">No</td>
 *         <td valign="top">{@link SimpleUsernameAttributeProvider}</td>
 *     </tr>
 * </table>
 * 
 * @author Eric Dalquist
 * @version $Revision$ $Date$
 * @since uPortal 2.5
 */
public abstract class AbstractDefaultAttributePersonAttributeDao extends AbstractFlatteningPersonAttributeDao {
    private IUsernameAttributeProvider usernameAttributeProvider = new SimpleUsernameAttributeProvider();

    /**
     * @see org.jasig.services.persondir.IPersonAttributeDao#getPerson(java.lang.String)
     * @throws org.springframework.dao.IncorrectResultSizeDataAccessException if more than one matching {@link IPersonAttributes} is found.
     */
    public IPersonAttributes getPerson(String str) {
        Validate.notNull(str, "uid may not be null.");
        //Generate the seed map for the uid
        String uid=str;
        Map<String, List<Object>> seed=new HashMap<String, List<Object>>();
        if (str.indexOf(",")>0) {
        	uid=str.split(",")[0];
        	String orgcode=str.split(",")[1];
        	seed = this.toSeedMapOrg(uid,orgcode);
		}else{
			seed = this.toSeedMap(uid);
		}
        
        //Run the query using the seed
        final Set<IPersonAttributes> people = this.getPeopleWithMultivaluedAttributes(seed);
        
        //Ensure a single result is returned
        IPersonAttributes person = (IPersonAttributes)DataAccessUtils.singleResult(people);
        if (person == null) {
            return null;
        }
        
        //Force set the name of the returned IPersonAttributes if it isn't provided in the return object
        if (person.getName() == null) {
            person = new NamedPersonImpl(uid, person.getAttributes());
        }
        
        return person;
    }

    /**
     * Converts the uid to a multi-valued seed Map using the value from {@link #getDefaultAttributeName()}
     * as the key.
     */
    protected Map<String, List<Object>> toSeedMap(String uid) {
        final List<Object> values = Collections.singletonList((Object)uid);
        final String usernameAttribute = this.usernameAttributeProvider.getUsernameAttribute();
        
        final Map<String, List<Object>> seed = Collections.singletonMap(usernameAttribute, values);  
        if (this.logger.isDebugEnabled()) {
            this.logger.debug("Created seed map='" + seed + "' for uid='" + uid + "'");
        }
        return seed;
    }
    
    protected Map<String, List<Object>> toSeedMapOrg(String uid,String orgcode) {
        final List<Object> username = Collections.singletonList((Object)uid);
        final List<Object> orgcodeL = Collections.singletonList((Object)orgcode);
        final String usernameAttribute = this.usernameAttributeProvider.getUsernameAttribute();
        final Map<String, List<Object>> seed = toSeedMap0(usernameAttribute, username,orgcodeL);  
        if (this.logger.isDebugEnabled()) {
            this.logger.debug("Created seed map='" + seed + "' for uid='" + uid + "'");
        }
        return seed;
    }


    protected Map<String, List<Object>> toSeedMap0(String usernameAttributes, List<Object> username,List<Object> orgcode) {  
        Map<String, List<Object>> seed = new HashMap<String, List<Object>>(3);  
        String[] split = usernameAttributes.split(",");  
        for (int i = 0; i < split.length; i++) {
        	if ("username".equals(split[i])) {
        		seed.put(split[i],username);  
			}else {
				seed.put(split[i],orgcode);  
			}
        	
		}
        return seed;  
    }  

    public IUsernameAttributeProvider getUsernameAttributeProvider() {
        return this.usernameAttributeProvider;
    }
    /**
     * The {@link IUsernameAttributeProvider} to use for determining the username attribute
     * to use when none is provided. The provider is used when calls are made to {@link #getPerson(String)}
     * to build a query Map and then call {@link #getPeopleWithMultivaluedAttributes(Map)}
     * 
     * @param usernameAttributeProvider the usernameAttributeProvider to set
     */
    public void setUsernameAttributeProvider(IUsernameAttributeProvider usernameAttributeProvider) {
        Validate.notNull(usernameAttributeProvider);
        this.usernameAttributeProvider = usernameAttributeProvider;
    }
}
