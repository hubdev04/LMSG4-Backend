package com.lms.apiGateway.filter;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    public AuthenticationFilter(){
        super(Config.class);
    }

    @Autowired
    private RouteValidator routeValidator;

    @Autowired
    private  RestTemplate restTemplate;

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            if(routeValidator.isSecured.test(exchange.getRequest())){

                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if(authHeader == null || !authHeader.startsWith("Bearer ")){
                    throw new RuntimeException("Token is missing in headers");
                }

                String token = authHeader.substring(7);

                try{
                    HttpHeaders headers = new HttpHeaders();

                    headers.setContentType(MediaType.APPLICATION_JSON);
                    headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
                    headers.setBearerAuth(token);

                    HttpEntity request = new HttpEntity(headers);
                    restTemplate.exchange("http://localhost:8081/api/auth/validateToken", HttpMethod.GET, request, String.class);
                } catch (Exception e){
                    throw new RuntimeException("Unauthorized access");
                }
            }

            return chain.filter(exchange);
        });
    }

    public static class Config{

    }
}
