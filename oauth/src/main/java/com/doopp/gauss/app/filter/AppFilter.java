package com.doopp.gauss.app.filter;

import com.doopp.kreactor.common.KReactorFilter;
import com.doopp.kreactor.common.RequestAttribute;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.netty.http.server.HttpServerRequest;
import reactor.netty.http.server.HttpServerResponse;


@Slf4j
public class AppFilter implements KReactorFilter {

    @Override
    public Mono<Object> doFilter(HttpServerRequest request, HttpServerResponse response, RequestAttribute requestAttribute) {
        return Mono.just(requestAttribute);
    }
}
