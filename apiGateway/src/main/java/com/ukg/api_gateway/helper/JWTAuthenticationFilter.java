package com.ukg.api_gateway.helper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

//    public JWTAuthenticationFilter(){
//        super(Config.class);
//    }

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String bearerToken = request.getHeader("Authorization");
        String token = null;
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")){
            token = bearerToken.substring(7);
        }

        if(StringUtils.hasText(token) && jwtUtil.validateToken(token)){
            String email = jwtUtil.getEmail(token);

            UserDetails userDetails = userDetailsService.loadUserByUsername(email);

            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }

        filterChain.doFilter(request, response);
    }

//    @Override
//    public GatewayFilter apply(Config config){
//        return ((exchange, chain) -> {
//            if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){
//                throw new RuntimeException("Authorization headers not found");
//            }
//
//            String headers = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
//
//            if(headers == null || !headers.startsWith("Bearer ")){
//                throw new RuntimeException("Token not found");
//            }
//
//            String token = headers.substring(7);
//            if(!jwtUtil.validateToken(token)){
//                throw new RuntimeException("Unauthorized");
//            }
//
//            return chain.filter(exchange);
//        });
//    }

//    public static class Config{}

//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//
//        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
//        String jwt = resolveToken(httpServletRequest);
//
//        if(StringUtils.hasText(jwt)){
//            if(jwtUtil.validateToken(jwt)){
//                String email = jwtUtil.getEmail(jwt);
//                UserDetails userDetails = this.userDetailsService.loadUserByUsername(email);
//
//                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
//                SecurityContextHolder.getContext().setAuthentication(authToken);
//            }
//        }
//
//        filterChain.doFilter(servletRequest, servletResponse);
//    }
//
//    private String resolveToken(HttpServletRequest request) {
//
//        String bearerToken = request.getHeader("Authorization");
//
//        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
//            return bearerToken.substring(7);
//        }
//
//        return null;
//    }
}
