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

import java.io.Serializable;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang.Validate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jasig.services.persondir.IPersonAttributeDao;
import org.jasig.services.persondir.IPersonAttributes;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springmodules.cache.key.CacheKeyGenerator;

/**
 * A configurable caching implementation of {@link IPersonAttributeDao} 
 * which caches results from a wrapped IPersonAttributeDao. 
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
 *         <td align="right" valign="top">cachedPersonAttributesDao</td>
 *         <td>
 *             The {@link org.jasig.services.persondir.IPersonAttributeDao} to delegate
 *             queries to on cache misses.
 *         </td>
 *         <td valign="top">Yes</td>
 *         <td valign="top">null</td>
 *     </tr>
 *     <tr>
 *         <td align="right" valign="top">userInfoCache</td>
 *         <td>
 *             The {@link java.util.Map} to use for result caching. This class does no cache
 *             maintenence. It is assumed the underlying Map implementation will ensure the cache
 *             is in a good state at all times.
 *         </td>
 *         <td valign="top">Yes</td>
 *         <td valign="top">null</td>
 *     </tr>
 *     <tr>
 *         <td align="right" valign="top">cacheKeyAttributes</td>
 *         <td>
 *             A Set of attribute names to use when building the cache key. The default
 *             implementation generates the key as a Map of attributeNames to values retrieved
 *             from the seed for the query. Zero length sets are treaded as null.
 *         </td>
 *         <td valign="top">No</td>
 *         <td valign="top">null</td>
 *     </tr>
 *     <tr>
 *         <td align="right" valign="top">cacheNullResults</td>
 *         <td>
 *             If the wrapped IPersonAttributeDao returns null for the query should that null
 *             value be stored in the cache. 
 *         </td>
 *         <td valign="top">No</td>
 *         <td valign="top">false</td>
 *     </tr>
 *     <tr>
 *         <td align="right" valign="top">nullResultsObject</td>
 *         <td>
 *             If cacheNullResults is set to true this value is stored in the cache for any
 *             query that returns null. This is used as a flag so the same query will return
 *             null from the cache by seeing this value
 *         </td>
 *         <td valign="top">No</td>
 *         <td valign="top">{@link CachingPersonAttributeDaoImpl#NULL_RESULTS_OBJECT}</td>
 *     </tr>
 * </table>
 * 
 * 
 * @author dgrimwood@unicon.net
 * @author Eric Dalquist
 * @version $Id
 */
public class CachingPersonAttributeDaoImpl extends AbstractDefaultAttributePersonAttributeDao implements InitializingBean, BeanNameAware {
    protected static final Set<IPersonAttributes> NULL_RESULTS_OBJECT = Collections.singleton((IPersonAttributes)new SingletonPersonImpl());
            
    protected Log statsLogger = LogFactory.getLog(this.getClass().getName() + ".statistics");

    private long queries = 0;
    private long misses = 0;
    
    /*
     * The IPersonAttributeDao to delegate cache misses to.
     */
    private IPersonAttributeDao cachedPersonAttributesDao = null;
    
    /*
     * Used to generate cache keys for storing calls in the cache.
     */
    private CacheKeyGenerator cacheKeyGenerator = null;
    
    /*
     * The cache to store query results in.
     */
    private Map<Serializable, Set<IPersonAttributes>> userInfoCache = null; 
    
    /*
     * The set of attributes to use to generate the cache key.
     */
    private Set<String> cacheKeyAttributes = null;
    
    /*
     * If null resutls should be cached
     */
    private boolean cacheNullResults = false;
    
    /*
     * The Object that should be stored in the cache if cacheNullResults is true
     */
    private Set<IPersonAttributes> nullResultsObject = NULL_RESULTS_OBJECT;
    
    private String beanName;
    
    /**
     * @return Returns the cachedPersonAttributesDao.
     */
    public IPersonAttributeDao getCachedPersonAttributesDao() {
        return this.cachedPersonAttributesDao;
    }
    /**
     * The IPersonAttributeDao to cache results from.
     * 
     * @param cachedPersonAttributesDao The cachedPersonAttributesDao to set.
     */
    public void setCachedPersonAttributesDao(IPersonAttributeDao cachedPersonAttributesDao) {
        if (cachedPersonAttributesDao == null) {
            throw new IllegalArgumentException("cachedPersonAttributesDao may not be null");
        }

        this.cachedPersonAttributesDao = cachedPersonAttributesDao;
    }
    
    /**
     * @return Returns the cacheKeyAttributes.
     * @deprecated these should be retrieved from the provided {@link CacheKeyGenerator} if applicable
     */
    @Deprecated
    public Set<String> getCacheKeyAttributes() {
        return this.cacheKeyAttributes;
    }
    /**
     * @param cacheKeyAttributes The cacheKeyAttributes to set.
     * @deprecated these should be set on the provided {@link CacheKeyGenerator} if applicable
     */
    @Deprecated
    public void setCacheKeyAttributes(Set<String> cacheKeyAttributes) {
        this.cacheKeyAttributes = cacheKeyAttributes;
    }

    /**
     * @return Returns the userInfoCache.
     */
    public Map<Serializable, Set<IPersonAttributes>> getUserInfoCache() {
        return this.userInfoCache;
    }
    /**
     * The Map to use for caching results. Only get, put and remove are used so the Map may be backed by a real caching
     * implementation.
     * 
     * @param userInfoCache The userInfoCache to set.
     */
    public void setUserInfoCache(Map<Serializable, Set<IPersonAttributes>> userInfoCache) {
        if (userInfoCache == null) {
            throw new IllegalArgumentException("userInfoCache may not be null");
        }

        this.userInfoCache = userInfoCache;
    }
    
    /**
     * @return the cacheNullResults
     */
    public boolean isCacheNullResults() {
        return this.cacheNullResults;
    }
    /**
     * If null results should be cached to avoid repeating failed lookups. Defaults to false.
     * 
     * @param cacheNullResults the cacheNullResults to set
     */
    public void setCacheNullResults(boolean cacheNullResults) {
        this.cacheNullResults = cacheNullResults;
    }
    
    /**
     * @return the nullResultsObject
     */
    public Set<IPersonAttributes> getNullResultsObject() {
        return this.nullResultsObject;
    }
    /**
     * Used to specify the placeholder object to put in the cache for null results. Defaults to a minimal Set. Most
     * installations will not need to set this.
     * 
     * @param nullResultsObject the nullResultsObject to set
     */
    public void setNullResultsObject(Set<IPersonAttributes> nullResultsObject) {
        if (nullResultsObject == null) {
            throw new IllegalArgumentException("nullResultsObject may not be null");
        }

        this.nullResultsObject = nullResultsObject;
    }
    
    /**
     * @return the cacheKeyGenerator
     */
    public CacheKeyGenerator getCacheKeyGenerator() {
        return cacheKeyGenerator;
    }
    /**
     * The CacheKeyGenerator to use for generating cache keys.
     * 
     * @param cacheKeyGenerator the cacheKeyGenerator to set
     */
    public void setCacheKeyGenerator(CacheKeyGenerator cacheKeyGenerator) {
        this.cacheKeyGenerator = cacheKeyGenerator;
    }
    
    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }
    
    /* (non-Javadoc)
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    public void afterPropertiesSet() throws Exception {
        if (this.cacheKeyGenerator == null) {
            final AttributeBasedCacheKeyGenerator cacheKeyGenerator = new AttributeBasedCacheKeyGenerator();
            cacheKeyGenerator.setCacheKeyAttributes(this.cacheKeyAttributes);
            
            final IUsernameAttributeProvider usernameAttributeProvider = this.getUsernameAttributeProvider();
            final String usernameAttribute = usernameAttributeProvider.getUsernameAttribute();
            cacheKeyGenerator.setDefaultAttributeName(usernameAttribute);
            this.cacheKeyGenerator = cacheKeyGenerator;
        }
    }
    
    /**
     * @return Returns the number of cache misses.
     */
    public long getMisses() {
        return this.misses;
    }
    
    /**
     * @return Returns the number of queries.
     */
    public long getQueries() {
        return this.queries;
    }
    
    /**
     * Wraps the call to the specified cachedPersonAttributesDao IPersonAttributeDao delegate with
     * a caching layer. Results are cached using keys generated by {@link #getCacheKey(Map)}.
     * 
     * @see org.jasig.services.persondir.IPersonAttributeDao#getPeopleWithMultivaluedAttributes(java.util.Map)
     */
    public Set<IPersonAttributes> getPeopleWithMultivaluedAttributes(Map<String, List<Object>> seed) {
        //Ensure the arguments and state are valid
        if (seed == null) {
            throw new IllegalArgumentException("The query seed Map cannot be null.");
        }
        
        if (this.cachedPersonAttributesDao == null) {
            throw new IllegalStateException("No 'cachedPersonAttributesDao' has been specified.");
        }
        if (this.userInfoCache == null) {
            throw new IllegalStateException("No 'userInfoCache' has been specified.");
        }
        
        //Get the cache key
        final MethodInvocation methodInvocation = new PersonAttributeDaoMethodInvocation(seed);
        final Serializable cacheKey = this.cacheKeyGenerator.generateKey(methodInvocation);

        if (cacheKey != null) {
            Set<IPersonAttributes> cacheResults = this.userInfoCache.get(cacheKey);
            if (cacheResults != null) {
                //If the returned object is the null results object, set the cache results to null
                if (this.nullResultsObject.equals(cacheResults)) {
                    cacheResults = null;
                }
                
                if (logger.isDebugEnabled()) {
                    logger.debug("Retrieved query from cache for " + beanName + ". key='" + cacheKey + "', results='" + cacheResults + "'");
                }
                    
                this.queries++;
                if (statsLogger.isDebugEnabled()) {
                    statsLogger.debug("Cache Stats " + beanName + ": queries=" + this.queries + ", hits=" + (this.queries - this.misses) + ", misses=" + this.misses);
                }
                
                return cacheResults;
            }
        }
    
        final Set<IPersonAttributes> queryResults = this.cachedPersonAttributesDao.getPeopleWithMultivaluedAttributes(seed);
    
        if (cacheKey != null) {
            if (queryResults != null) {
                this.userInfoCache.put(cacheKey, queryResults);
            }
            else if (this.cacheNullResults) {
                this.userInfoCache.put(cacheKey, this.nullResultsObject);
            }
            
            if (logger.isDebugEnabled()) {
                logger.debug("Retrieved query from wrapped IPersonAttributeDao and stored in cache for " + beanName + ". key='" + cacheKey + "', results='" + queryResults + "'");
            }
            
            this.queries++;
            this.misses++;
            if (statsLogger.isDebugEnabled()) {
                statsLogger.debug("Cache Stats " + beanName + ": queries=" + this.queries + ", hits=" + (this.queries - this.misses) + ", misses=" + this.misses);
            }
        }

        return queryResults;
    }
    
    public void removeUserAttributes(String uid) {
        Validate.notNull(uid, "uid may not be null.");
        final Map<String, List<Object>> seed = this.toSeedMap(uid);
        this.removeUserAttributesMultivaluedSeed(seed);
    }
    
    public void removeUserAttributes(Map<String, Object> seed) {
        final Map<String, List<Object>> multiSeed = MultivaluedPersonAttributeUtils.toMultivaluedMap(seed);
        this.removeUserAttributesMultivaluedSeed(multiSeed);
    }
    
    public void removeUserAttributesMultivaluedSeed(Map<String, List<Object>> seed) {
        final MethodInvocation methodInvocation = new PersonAttributeDaoMethodInvocation(seed);
        final Serializable cacheKey = this.cacheKeyGenerator.generateKey(methodInvocation);
        this.userInfoCache.remove(cacheKey);
    }

    /* (non-Javadoc)
     * @see org.jasig.services.persondir.IPersonAttributeDao#getPossibleUserAttributeNames()
     */
    public Set<String> getPossibleUserAttributeNames() {
        return this.cachedPersonAttributesDao.getPossibleUserAttributeNames();
    }
    
    /* (non-Javadoc)
     * @see org.jasig.services.persondir.IPersonAttributeDao#getAvailableQueryAttributes()
     */
    public Set<String> getAvailableQueryAttributes() {
        return this.cachedPersonAttributesDao.getAvailableQueryAttributes();
    }
    
    private static class PersonAttributeDaoMethodInvocation implements MethodInvocation {
        private final static Method getPeopleWithMultivaluedAttributesMethod;
        static {
            try {
                getPeopleWithMultivaluedAttributesMethod = IPersonAttributeDao.class.getMethod("getPeopleWithMultivaluedAttributes", Map.class);
            }
            catch (SecurityException e) {
                final NoSuchMethodError nsme = new NoSuchMethodError("The 'getPeopleWithMultivaluedAttributes(" + Map.class + ")' method on the '" + IPersonAttributeDao.class + "' is not accessible due to a security policy.");
                nsme.initCause(e);
                throw nsme;
            }
            catch (NoSuchMethodException e) {
                final NoSuchMethodError nsme = new NoSuchMethodError("The 'getPeopleWithMultivaluedAttributes(" + Map.class + ")' method on the '" + IPersonAttributeDao.class + "' does not exist.");
                nsme.initCause(e);
                throw nsme;
            }
        }
        
        private final Object[] args;
        
        public PersonAttributeDaoMethodInvocation(Object... args) {
            this.args = args;
        }
        
        /* (non-Javadoc)
         * @see org.aopalliance.intercept.MethodInvocation#getMethod()
         */
        public Method getMethod() {
            return getPeopleWithMultivaluedAttributesMethod;
        }

        /* (non-Javadoc)
         * @see org.aopalliance.intercept.Invocation#getArguments()
         */
        public Object[] getArguments() {
            return this.args;
        }

        /* (non-Javadoc)
         * @see org.aopalliance.intercept.Joinpoint#getStaticPart()
         */
        public AccessibleObject getStaticPart() {
            throw new UnsupportedOperationException("This is a fake MethodInvocation, getStaticPart() is not supported.");
        }

        /* (non-Javadoc)
         * @see org.aopalliance.intercept.Joinpoint#getThis()
         */
        public Object getThis() {
            throw new UnsupportedOperationException("This is a fake MethodInvocation, getThis() is not supported.");
        }

        /* (non-Javadoc)
         * @see org.aopalliance.intercept.Joinpoint#proceed()
         */
        public Object proceed() throws Throwable {
            throw new UnsupportedOperationException("This is a fake MethodInvocation, proceed() is not supported.");
        }
    }
    
    private static final class SingletonPersonImpl extends BasePersonImpl {
        private static final long serialVersionUID = 1L;

        @SuppressWarnings("unchecked")
        public SingletonPersonImpl() {
            super(Collections.EMPTY_MAP);
        }

        /* (non-Javadoc)
         * @see java.security.Principal#getName()
         */
        public String getName() {
            return CachingPersonAttributeDaoImpl.class.getName() + "UNIQUE_NULL_RESULTS";
        }
    }
}
