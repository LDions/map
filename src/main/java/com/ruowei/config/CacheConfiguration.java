package com.ruowei.config;

import java.time.Duration;
import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;
import org.hibernate.cache.jcache.ConfigSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.info.GitProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.*;
import tech.jhipster.config.JHipsterProperties;
import tech.jhipster.config.cache.PrefixedKeyGenerator;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private GitProperties gitProperties;
    private BuildProperties buildProperties;
    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache = jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration =
            Eh107Configuration.fromEhcacheCacheConfiguration(
                CacheConfigurationBuilder
                    .newCacheConfigurationBuilder(Object.class, Object.class, ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                    .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                    .build()
            );
    }

    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(javax.cache.CacheManager cacheManager) {
        return hibernateProperties -> hibernateProperties.put(ConfigSettings.CACHE_MANAGER, cacheManager);
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            createCache(cm, com.ruowei.domain.User.class.getName());
            createCache(cm, com.ruowei.domain.Role.class.getName());
            createCache(cm, com.ruowei.domain.UserRole.class.getName());
            createCache(cm, com.ruowei.domain.Draft.class.getName());
            createCache(cm, com.ruowei.domain.Dict.class.getName());
            createCache(cm, com.ruowei.domain.EmiFactor.class.getName());
            createCache(cm, com.ruowei.domain.EmiData.class.getName());
            createCache(cm, com.ruowei.domain.SewEmi.class.getName());
            createCache(cm, com.ruowei.domain.SewPot.class.getName());
            createCache(cm, com.ruowei.domain.SewProcess.class.getName());
            createCache(cm, com.ruowei.domain.SewSlu.class.getName());
            createCache(cm, com.ruowei.domain.District.class.getName());
            createCache(cm, com.ruowei.domain.Enterprise.class.getName());
            createCache(cm, com.ruowei.domain.Menu.class.getName());
            createCache(cm, com.ruowei.domain.RoleMenu.class.getName());
            createCache(cm, com.ruowei.domain.OtherIndex.class.getName());
            createCache(cm, com.ruowei.domain.Group.class.getName());
            createCache(cm, com.ruowei.domain.Platform.class.getName());
            createCache(cm, com.ruowei.domain.SewMeter.class.getName());
            createCache(cm, com.ruowei.domain.Craft.class.getName());
            createCache(cm, com.ruowei.domain.EntCraftData.class.getName());
            // jhipster-needle-ehcache-add-entry
        };
    }

    private void createCache(javax.cache.CacheManager cm, String cacheName) {
        javax.cache.Cache<Object, Object> cache = cm.getCache(cacheName);
        if (cache != null) {
            cache.clear();
        } else {
            cm.createCache(cacheName, jcacheConfiguration);
        }
    }

    @Autowired(required = false)
    public void setGitProperties(GitProperties gitProperties) {
        this.gitProperties = gitProperties;
    }

    @Autowired(required = false)
    public void setBuildProperties(BuildProperties buildProperties) {
        this.buildProperties = buildProperties;
    }

    @Bean
    public KeyGenerator keyGenerator() {
        return new PrefixedKeyGenerator(this.gitProperties, this.buildProperties);
    }
}
