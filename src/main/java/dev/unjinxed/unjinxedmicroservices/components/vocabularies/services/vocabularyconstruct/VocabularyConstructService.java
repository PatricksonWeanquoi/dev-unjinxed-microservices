package dev.unjinxed.unjinxedmicroservices.components.vocabularies.services.vocabularyconstruct;

import dev.unjinxed.unjinxedmicroservices.components.vocabularies.models.vocabularycontruct.WordDefinition;
import org.springframework.web.servlet.function.ServerResponse;
import reactor.core.publisher.Mono;

public interface VocabularyConstructService {

    public Mono<WordDefinition> getRandomWordDefinition();

    public Mono<ServerResponse> getRandomWordDefinitionServerResponse();
}
