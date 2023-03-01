package dev.unjinxed.unjinxedmicroservices.components.vocabularies.adapters.oxforddictionaries;

import dev.unjinxed.unjinxedmicroservices.adaptors.httpclientbase.HttpClientBase;
import dev.unjinxed.unjinxedmicroservices.components.vocabularies.adapters.oxforddictionaries.impl.OxfordDictionariesAdapterImpl;
import dev.unjinxed.unjinxedmicroservices.components.vocabularies.models.oxforddictionaries.OxfordDictionariesResponse;
import dev.unjinxed.unjinxedmicroservices.exceptions.RequestEntityBuilderException;
import dev.unjinxed.unjinxedmicroservices.utils.MockitoInit;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

//import static org.hamcrest.Matctchers.any;

import java.util.Map;

import static org.assertj.core.api.Fail.fail;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static reactor.core.publisher.Mono.when;
public class OxfordDictionariesAdapterTest extends MockitoInit {
    @InjectMocks
    OxfordDictionariesAdapterImpl oxfordDictionariesAdapter;

    @Test
    void getWordDefinitionGetSuccessDefinitionTest() throws RequestEntityBuilderException {
//        this.oxfordDictionariesAdapter.setRestTemplate(new RestTemplate());
//        RequestEntity requestEntity = this.oxfordDictionariesAdapter.generateRequestEntity("", null, new LinkedMultiValueMap<>(), HttpMethod.GET);
//
//        Mockito.when(this.oxfordDictionariesAdapter.getRestTemplate().exchange(requestEntity, new ParameterizedTypeReference<Object>() {
//        }))
//                .thenReturn(Mono.just(new ResponseEntity("Hello Word", new LinkedMultiValueMap<>(), 200)));
//        Mono<OxfordDictionariesResponse> oxfordDictionariesResponseMono = this.oxfordDictionariesAdapter.getWordDefinition("test");
//
//        StepVerifier.create(oxfordDictionariesResponseMono)
//                .assertNext(response -> {
//                    assertNotNull(response, "serviceCallOutSuccessGetCall(): Response not null");
//                }).verifyComplete();
        fail("TODO: add unit test");
    }
}
