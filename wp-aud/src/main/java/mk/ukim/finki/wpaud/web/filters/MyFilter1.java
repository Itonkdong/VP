package mk.ukim.finki.wpaud.web.filters;

import jakarta.servlet.*;
import jakarta.servlet.Filter;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.http.HttpFilter;
import org.springframework.context.annotation.ComponentScan;

import javax.xml.crypto.dsig.spec.XPathType;
import java.io.IOException;

@WebFilter (filterName = "My Filter",
        initParams = {@WebInitParam(name = "Value",value = "Viktor")},
        urlPatterns = "/servlet/filter",
        dispatcherTypes = {DispatcherType.INCLUDE}
)
public class MyFilter1 implements Filter
{

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException
    {
        System.out.println("Logging Before MyFilter");
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("Logging After MyFilter");
    }
}
