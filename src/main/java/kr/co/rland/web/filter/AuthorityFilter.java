package kr.co.rland.web.filter;

import jakarta.servlet.*;

import java.io.IOException;

public class AuthorityFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {

        System.out.println("필터가 실행되긴 하는건가?");
        chain.doFilter(req,resp);
    }
}
