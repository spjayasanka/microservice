package com.tutorial.apigateway.filter;

import com.tutorial.apigateway.config.JwtUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component
public class JwtRequestFilter extends AbstractGatewayFilterFactory<JwtRequestFilter.Config> {

    @Autowired
    private RouteValidator routeValidator;

    @Autowired
    private JwtUtil jwtUtil;

    public JwtRequestFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();

            if (routeValidator.isSecured.test(request)) {
                if (!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw new RuntimeException("Authorization header not present");
                }

                String token = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

                if (StringUtils.isNotEmpty(token) && token.startsWith("Bearer ")) {
                    token = token.substring(7);
                }

                try {
                    jwtUtil.validateToken(token);
                } catch (Exception e) {
                    throw new RuntimeException(e.getMessage());
                }
            }

            return chain.filter(exchange);
        };
    }

    public static class Config {

    }
}
