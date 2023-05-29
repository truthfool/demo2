package com.example.demo;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Iterator;


public class ApiOriginFilter implements  Filter {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final String[] HEADERS_TO_TRY = {
            "X-Forwarded-For",
            "X-Forwarded-Host",
            "X-Forwarded-Port",
            "X-Forwarded-Proto",
            "X-Forwarded-Scheme",
            "X-Original-Forwarded-For",
            "Proxy-Client-IP",
            "WL-Proxy-Client-IP",
            "HTTP_X_FORWARDED_FOR",
            "HTTP_X_FORWARDED",
            "HTTP_X_CLUSTER_CLIENT_IP",
            "HTTP_CLIENT_IP",
            "HTTP_FORWARDED_FOR",
            "HTTP_FORWARDED",
            "HTTP_VIA",
            "REMOTE_ADDR"
    };

    private void getClientIpAddress(HttpServletRequest request) {
        for (String header : HEADERS_TO_TRY) {
            String val = request.getHeader(header);
            logger.info("Header Name : {}",header);
            logger.info("Header Value : {}",val);
        }

        logger.info("Request remote address: {}",request.getRemoteAddr());
        logger.info("Remote Host: {}",request.getRemoteHost());
        logger.info("Header names: {}",request.getHeaderNames().toString());
        logger.info("Header names: ");
        Iterator<String> headersList= request.getHeaderNames().asIterator();
        while(headersList.hasNext()){
            logger.info(headersList.next());
        }


    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletResponse res = (HttpServletResponse) response;
        HttpServletRequest req = (HttpServletRequest) request;
        getClientIpAddress(req);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
}
