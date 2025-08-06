package com.company.gatewayserver.utils;

import lombok.experimental.UtilityClass;
import org.springframework.http.HttpHeaders;
import org.springframework.web.server.ServerWebExchange;

import java.util.Objects;
import java.util.UUID;

@UtilityClass
public class FilterUtils {

    public static String TRACE_ID = "X-Trace-Id";

    public static String getTraceIdFromHeaders(HttpHeaders requestHeaders) {
        if(requestHeaders.containsKey(TRACE_ID)) {
            return Objects.requireNonNull(requestHeaders.get("X-Trace-Id")).stream().findFirst().orElse(null);
        }
        return null;
    }

    public static boolean traceIdExists(HttpHeaders requestHeaders) {
        return Objects.nonNull(getTraceIdFromHeaders(requestHeaders));
    }

    public static String generateTraceId() {
        return UUID.randomUUID().toString();
    }

    public ServerWebExchange setRequestHeader(ServerWebExchange exchange, String headerName, String headerValue) {
        return exchange.mutate().request(exchange.getRequest().mutate().header(headerName, headerValue).build()).build();
    }
}
