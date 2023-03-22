package subject.blog;

import com.github.benmanes.caffeine.cache.Caffeine;
import java.util.Arrays;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheSetting extends CachingConfigurerSupport {

    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();

        ConcurrentMapCache blogCache = new ConcurrentMapCache("blogs", getBlogsCacheMap(),
            false);
        ConcurrentMapCache hotKeyCache = new ConcurrentMapCache("hotKeys", getHotKeyCacheMap(),
            false);
        cacheManager.setCaches(Arrays.asList(blogCache, hotKeyCache));

        return cacheManager;
    }

    private ConcurrentMap<Object, Object> getHotKeyCacheMap() {
        return Caffeine.newBuilder()
            .expireAfterWrite(20, TimeUnit.SECONDS)
            .maximumSize(1)
            .build()
            .asMap();
    }

    private ConcurrentMap<Object, Object> getBlogsCacheMap() {
        return Caffeine.newBuilder()
            .expireAfterWrite(30, TimeUnit.SECONDS)
            .maximumSize(300)
            .build()
            .asMap();
    }
}
