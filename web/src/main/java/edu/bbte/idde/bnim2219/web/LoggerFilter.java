package edu.bbte.idde.bnim2219.web;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

// logs every request and the response status
@WebFilter("/*")
@Slf4j
public class LoggerFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        super.doFilter(req, res, chain);
        chain.doFilter(req, res);
        log.info("{} {} {}", req.getMethod(), req.getRequestURI(), res.getStatus());
    }
}
