package dev.unjinxed.unjinxedmicroservices.components.vocabularies.adapters.randomwordsadapter.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.unjinxed.unjinxedmicroservices.adaptors.httpclientbase.HttpClientBase;
import dev.unjinxed.unjinxedmicroservices.components.vocabularies.adapters.randomwordsadapter.RandomWordsAdapter;
import dev.unjinxed.unjinxedmicroservices.components.vocabularies.models.randomwords.RandomWordsResponse;
import dev.unjinxed.unjinxedmicroservices.exceptions.RequestEntityBuilderException;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscription;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.MultiValueMapAdapter;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.function.ServerResponse;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.Flow;

@Component
@Slf4j
public class RandomWordsAdapterImpl extends HttpClientBase implements RandomWordsAdapter {

    private String appHost;
    private String appKey;
    private MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();


    public RandomWordsAdapterImpl(@NotNull @Value("${X-RapidAPI-Host}")String appHost,
                                  @NotNull @Value("${X-RapidAPI-Key}") String appKey,
                                  @NotNull @Value("${X-RapidAPI-Domain}") String appDomain) {
        this.appHost = appHost;
        this.appKey = appKey;
        setDomain(appDomain);
        initializeHeaders();
    }


    private void initializeHeaders() {
        headers.add("X-RapidAPI-Host", this.appHost);
        headers.add("X-RapidAPI-Key", this.appKey);
    }

    public Mono<RandomWordsResponse> getRandomWord() {
        final String requestPath =  "/getRandom";
        log.info("getRandomWord(): entering... resource path: " + requestPath);
        try {
            RequestEntity<Void> requestEntity = this.generateRequestEntity(requestPath,null, headers, HttpMethod.GET);
            ParameterizedTypeReference returnType = new ParameterizedTypeReference<String>() {};
            return this.serviceCallOut(requestEntity, returnType)
                    .doOnSuccess(responseEntity -> log.info("getRandomWord(): response " + responseEntity))
                    .map(responseEntity -> responseEntity.getBody().toString())
                    .map(word -> RandomWordsResponse.builder().word(word).build());
        } catch (RequestEntityBuilderException requestEntityBuilderException) {
            log.error("getRandomWord(): requestEntityBuilderException " + requestEntityBuilderException.getMessage());
            return Mono.error(requestEntityBuilderException);
        }
    }
}
