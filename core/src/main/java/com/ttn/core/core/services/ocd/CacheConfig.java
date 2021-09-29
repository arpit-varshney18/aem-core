package com.ttn.core.core.services.ocd;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "HTTP Cache - In-Memory Cache Store", description = "Http Cache Store Service Configurations")
public @interface CacheConfig {

    @AttributeDefinition(name="Enabled", description = "Enable to activate in-memory cache", type = AttributeType.BOOLEAN)
    boolean cacheEnabled() default true;

}
