package Vodagone.Jersey;

import org.glassfish.jersey.server.ResourceConfig;

/**
 * Created by Anders Egberts on 22/11/2016.
 */
public class RestConfig extends ResourceConfig {

    public RestConfig() {
        packages( "oose.dea.service.rest" );
        property( "jersey.config.server.packages" , "com.fasterxml.jackson.jaxrs.json;service" );
    }
}
