package com.doopp.gauss.oauth.handle;

import com.doopp.gauss.oauth.service.OAuthService;
import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import javax.ws.rs.*;
import java.util.Map;

@Slf4j
@Path("/oauth/api")
public class OAuthHandle {

    @Inject
    private OAuthService oauthService;

    @POST
    @Path("/login")
    public Mono<Map<String, String>> login(@BeanParam OAuthRequest<LoginRequest> commonRequest) {
        return oauthService.userLogin(commonRequest);
    }

    @POST
    @Path("/login")
    public Mono<Map<String, String>> register(@BeanParam OAuthRequest<RegisterRequest> commonRequest) {
        return oauthService.userRegister(commonRequest);
    }
}
