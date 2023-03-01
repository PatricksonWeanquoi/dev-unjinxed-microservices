package dev.unjinxed.unjinxedmicroservices.components.vocabularies.adapters.oxforddictionaries.impl;

import dev.unjinxed.unjinxedmicroservices.adaptors.httpclientbase.HttpClientBase;
import dev.unjinxed.unjinxedmicroservices.components.vocabularies.adapters.oxforddictionaries.OxfordDictionariesAdapter;
import dev.unjinxed.unjinxedmicroservices.components.vocabularies.models.oxforddictionaries.OxfordDictionariesResponse;
import dev.unjinxed.unjinxedmicroservices.constants.GLOBALCONSTANTS;
import dev.unjinxed.unjinxedmicroservices.exceptions.RequestEntityBuilderException;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;

@Component
@Slf4j
@EqualsAndHashCode
public class OxfordDictionariesAdapterImpl extends HttpClientBase implements OxfordDictionariesAdapter {

    private String url;
    private String appId;
    private String appKey;
    private final MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();

    public OxfordDictionariesAdapterImpl(@NotNull @Value("${oxfordDictionaries.url}") String url,
                                         @NotNull @Value("${oxfordDictionaries.app_id}") String appId,
                                         @NotNull @Value("${oxfordDictionaries.app_key}") String appKey) {
        this.url = url;
        this.appId = appId;
        this.appKey = appKey;
        setDomain(this.url);
        initializeHeaders();
    }

    private void initializeHeaders() {
        headers.add(GLOBALCONSTANTS.OXFORD_APP_ID_HEADER, this.appId);
        headers.add(GLOBALCONSTANTS.OXFORD_APP_KEY_HEADER, this.appKey);
    }

    public Mono<OxfordDictionariesResponse> getWordDefinition(String word) {
        log.info("getWordDefinition(): Entering... word = " + word);
        final String resourcePath = "/entries/en-us/" + word;
        log.info("getWordDefinition(): resource path: " + resourcePath);
        try {
            RequestEntity<Void> requestEntityBuilder = this.generateRequestEntity(resourcePath, null, this.headers, HttpMethod.GET);
            return this.serviceCallOut(requestEntityBuilder, new ParameterizedTypeReference<OxfordDictionariesResponse>(){})
                    .doOnSuccess(responseEntity -> log.info("getWordDefinition(): response " + responseEntity))
                    .map(ResponseEntity::getBody);
        } catch (RequestEntityBuilderException requestEntityBuilderException) {
            log.info("getWordDefinition(): failed to build request builder: " + requestEntityBuilderException);
            return Mono.error(requestEntityBuilderException);
        }
    }
}
