package com.ttn.core.core.services.impl;

import com.ttn.core.core.services.CacheService;
import com.ttn.core.core.services.ocd.CacheConfig;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.Optional;

@Component(service = CacheService.class, immediate = true)
@Designate(ocd = CacheConfig.class)
public class EhCacheServiceImpl implements CacheService {

    private static final String cacheName = "aem-ehCache";
    private final Logger LOG = LoggerFactory.getLogger(getClass());
    private CacheManager cacheManager;

    @Activate
    protected void activate(CacheConfig config) {
        if (config != null) {
            boolean isEnabled = config.cacheEnabled();
            cacheManager = CacheManager.create();
            cacheManager.addCacheIfAbsent(cacheName);
            LOG.debug("EhCacheServiceImpl - enabled set to [{}] by OSGi configuration", isEnabled);
            if (!isEnabled) {
                cacheManager.clearAll();
            }
        }
    }

    @Override
    public void addToCache(String key, Object item) {
        // Do not save any null key or item
        if (Objects.nonNull(key) && item != null) {
            Ehcache cache = cacheManager.getEhcache(cacheName);
            if (cache != null) {
                Element element = new Element(key, item);
                cache.put(element);
            }
        }
    }

    @Override
    public Object getFromCache(String itemId) {
        Ehcache cache = cacheManager.getEhcache(cacheName);
        Object response = null;
        if (cache != null) {
            Element element = cache.get(itemId);
            if (element != null) {
                response = element.getObjectValue();
            }
        }
        return response;
    }

    @Override
    public <T> Optional<T> getFromCache(Class<T> expectedType, String id) {
        Object item = getFromCache(id);
        return Optional.of(item).filter(expectedType::isInstance).map(expectedType::cast);
    }

    @Override
    public void cleanAllCache() {
        Ehcache cache = cacheManager.getEhcache(cacheName);
        cache.removeAll();
    }

    @Override
    public boolean isCacheExist(String key) {
        Ehcache ehcache = cacheManager.getEhcache(cacheName);
        return ehcache.isKeyInCache(key);
    }

    @Deactivate
    protected void deActivate() {
        if (cacheManager != null) {
            cacheManager.shutdown();
            cacheManager = null;
        }
    }
}
