package dev.unjinxed.unjinxedmicroservices.adaptors.httpclientbase.utils;

import dev.unjinxed.unjinxedmicroservices.exceptions.RequestEntityBuilderException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.constraints.NotNull;
import java.net.URI;
import java.net.URL;

@Data
@Slf4j
public class RequestEntityBuilder {

    String sessionID;
    String domain;

    public RequestEntity generateRequestEntity(@NotNull String resource,
                                               @NotNull Object body,
                                               @NotNull MultiValueMap<String,String> headers,
                                               @NotNull HttpMethod method) throws RequestEntityBuilderException {
        log.info("generateRequestEntity(): entering... " +
                "resource[" + resource + "], " +
                "body[" + body + "], " +
                "headers[" + headers + "], " +
                "method[" + method +"]");
        try {
            URI uri = UriComponentsBuilder
                    .fromUriString(domain + resource)
                    .build()
                    .toUri();
            var requestEntity = new RequestEntity<>(body, headers, method, uri);
            log.info("generateRequestEntity(): requestEntity: " + requestEntity);
            return requestEntity;
        } catch (RuntimeException runtimeException) {
            log.error("generateRequestEntity(): error generating request entity " + runtimeException);
            throw new RequestEntityBuilderException(runtimeException.getMessage());
        }
    }
}
