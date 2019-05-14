package com.doopp.gauss.app.handle;

import io.netty.buffer.ByteBuf;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Path("/test")
public class TestHandle {

    @GET
    @Path("/template")
    public Mono<String> template() {
        return Mono.just("template");
    }

    @GET
    @Path("/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Mono<Map<String, String>> json() {
        return Mono
            .just(new HashMap<String, String>())
            .map(m -> {
                m.put("hi", "five girl");
                return m;
            });
    }

    @GET
    @Path("/jpeg")
    @Produces("image/jpeg")
    public Mono<ByteBuf> jpeg() {
        return HttpClient.create()
            .get()
            .uri("https://static.cnbetacdn.com/article/2019/0402/6398390c491f650.jpg")
            .responseContent()
            .aggregate()
            .map(ByteBuf::retain);
    }
}
