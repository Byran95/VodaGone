package Guice;

import RESTService.*;
import com.google.inject.servlet.ServletModule;

/**
 * Created by Anders Egberts on 22/11/2016.
 */
public class AppBinding extends ServletModule {
    @Override
    protected void configureServlets() {
        super.configureServlets();
        serve("login").with(LoginServlet.class);
        bind(IAbonnementService.class).to(AbonnementService.class);
        bind(IAbonneeService.class).to(AbonneeService.class);
        bind(IDienstService.class).to(IDienstService.class);
    }
}
