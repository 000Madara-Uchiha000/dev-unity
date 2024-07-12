package uz.pdp.devunity.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

//@Component
//@Order(0)
public class CorsFilter
//        extends HttpFilter
{

//    @Override
//    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
//            throws IOException, ServletException {
//
//        response.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:5500");
//        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
//        response.setHeader("Access-Control-Allow-Headers", "*");
//        response.setHeader("Access-Control-Allow-Credentials", "true");
//
//        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
//            response.setStatus(HttpServletResponse.SC_OK);
//        } else {
//            chain.doFilter(request, response);
//        }
//    }
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        // Initialization logic if needed
//    }
//
//    @Override
//    public void destroy() {
//        // Cleanup logic if needed
//    }
}