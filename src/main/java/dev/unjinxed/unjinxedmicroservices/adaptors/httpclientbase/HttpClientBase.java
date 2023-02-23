package dev.unjinxed.unjinxedmicroservices.adaptors.httpclientbase;


import dev.unjinxed.unjinxedmicroservices.adaptors.httpclientbase.utils.RequestEntityBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.server.WebServerException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClientException;
import reactor.core.publisher.Mono;


public class HttpClientBase extends RequestEntityBuilder {
    @Autowired
    @Qualifier("restTemplate")
    RestTemplate restTemplate;


    public <T> Mono<ResponseEntity<T>> serviceCallOut(RequestEntity request, ParameterizedTypeReference returnType) {
        try {
            return Mono.just(this.restTemplate.exchange(request, returnType));
        } catch (HttpClientErrorException httpClientErrorException) {
            return Mono.error(httpClientErrorException);
        }
    }
}
