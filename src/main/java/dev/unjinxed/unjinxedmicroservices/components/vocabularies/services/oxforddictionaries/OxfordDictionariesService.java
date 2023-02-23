package dev.unjinxed.unjinxedmicroservices.components.vocabularies.services.oxforddictionaries;

import dev.unjinxed.unjinxedmicroservices.components.vocabularies.models.oxforddictionaries.OxfordDictionariesResponse;
import org.springframework.web.servlet.function.ServerResponse;
import reactor.core.publisher.Mono;

public interface OxfordDictionariesService {
    public Mono<OxfordDictionariesResponse> getWordDefinition(String word);
    public Mono<ServerResponse> getWordDefinitionServerResponse(String word);
}
