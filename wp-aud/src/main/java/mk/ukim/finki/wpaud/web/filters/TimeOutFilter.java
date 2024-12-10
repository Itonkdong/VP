package mk.ukim.finki.wpaud.web.filters;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wpaud.web.servlet.LongProcessingServlet;

import java.io.IOException;
import java.io.PrintWriter;

@WebFilter(filterName = "timeOutFilter",
        urlPatterns = "/servlet/long-processing",
        initParams = {@WebInitParam(name="timeoutLimit", value = "1000"),},
        dispatcherTypes = DispatcherType.REQUEST
)
public class TimeOutFilter implements Filter
{

    private FilterConfig filterConfig;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {
        this.filterConfig = filterConfig;
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException
    {
        int timeoutLimit = Integer.parseInt(this.filterConfig.getInitParameter("timeoutLimit"));
        long start = System.currentTimeMillis();
        filterChain.doFilter(servletRequest,servletResponse);
        long end = System.currentTimeMillis();
        long timeTakenToProcessRequest = end - start;

        if (timeTakenToProcessRequest > timeoutLimit)
        {
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            PrintWriter writer = response.getWriter();
            writer.printf("Processing took too long");
        }
    }
}
