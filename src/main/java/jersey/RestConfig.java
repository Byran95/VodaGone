package jersey;

import org.glassfish.jersey.server.ResourceConfig;

/**
 * Created by Bryan on 22-11-2016.
 */
public class RestConfig extends ResourceConfig {

    public RestConfig () {
        packages("DomainApplication");
        property("jersey.config.server.provider.packages", "com.fasterxml.jackson.jaxrs.json;service");
    }
}
