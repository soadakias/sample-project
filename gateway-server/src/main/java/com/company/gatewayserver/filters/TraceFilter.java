package com.company.gatewayserver.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import static com.company.gatewayserver.utils.FilterUtils.*;

@Component
public class TraceFilter implements GlobalFilter {

    private static final Logger LOG = LoggerFactory.getLogger(TraceFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        HttpHeaders requestHeaders = exchange.getRequest().getHeaders();
        if(traceIdExists(requestHeaders)) {
            LOG.debug("X-Trace-Id: {} [existing]", getTraceIdFromHeaders(requestHeaders));
        } else {
            String traceId = generateTraceId();
            exchange = setRequestHeader(exchange, TRACE_ID, traceId);
            LOG.debug("X-Trace-Id: {} [generated]", traceId);
        }
        return chain.filter(exchange);
    }
}
