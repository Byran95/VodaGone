package Vodagone.Guice;

import com.google.inject.servlet.GuiceFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * Created by Anders Egberts on 22/11/2016.
 */
@WebFilter("/*")
public class GuiceWebFilter extends GuiceFilter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println( "Did Filter stuff" );
        super.doFilter(servletRequest, servletResponse, filterChain);
    }
}
