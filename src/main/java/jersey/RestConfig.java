package main.java.jersey;

import org.glassfish.jersey.server.ResourceConfig;

/**
 * Created by Bryan on 22-11-2016.
 */
public class RestConfig extends ResourceConfig {

    public RestConfig () {
        packages("oose.dea.services.rest");
        property("jersey.config.server.provider.packages", "com.fasterxml.jackson.jaxrs.json;service");
    }
}
