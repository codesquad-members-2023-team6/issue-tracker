package com.team6.issue_tracker.global.auth.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Service
@Slf4j
public class JwtFilter extends GenericFilterBean {
    private JwtService jwtService;
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final int START_TOKEN = 7;

    public JwtFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String jwt = resolveToken(httpServletRequest);
        String responseURI = httpServletRequest.getRequestURI();
        if(StringUtils.hasText(jwt) && jwtService.validateToken(jwt)) {
            Authentication authentication = jwtService.getAuthentication(jwt);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            log.info("Security에 인증 정보 저장 완료 : " + authentication.getName() + responseURI);
        } else {
            log.info("유효한 토큰이 없습니다. : " + responseURI);
        }
        chain.doFilter(request, response);
    }

    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(START_TOKEN);
        }
        return null;
    }
}
