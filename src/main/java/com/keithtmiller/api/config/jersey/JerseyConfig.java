package com.keithtmiller.api.config.jersey;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.info.BuildProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.ws.rs.ApplicationPath;

@Component
@ApplicationPath("/api")
public class JerseyConfig extends ResourceConfig {
    private static final String packageName = "com.keithtmiller.api.routes";

    private BuildProperties buildProperties;
    private int port;

    @Autowired
    public JerseyConfig(BuildProperties buildProperties,
                        @Value("${server.port}") int port) {
        this.buildProperties = buildProperties;
        this.port = port;

        packages(packageName);
        property(ServletProperties.FILTER_FORWARD_ON_404, true);
    }

    @PostConstruct
    public void init() {
        configureSwagger();
    }

    private void configureSwagger() {
        register(ApiListingResource.class);
        register(SwaggerSerializers.class);
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion(buildProperties.getVersion());
        beanConfig.setSchemes(new String[]{"http", "https"});
        beanConfig.setHost(String.format("localhost:%d", port));
        beanConfig.setBasePath("/api");
        beanConfig.setResourcePackage(packageName);
        beanConfig.setPrettyPrint(true);
        beanConfig.setScan(true);
    }
}
