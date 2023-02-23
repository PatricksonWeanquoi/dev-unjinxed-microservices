package dev.unjinxed.unjinxedmicroservices.components.vocabularies.adapters.oxforddictionaries;

import dev.unjinxed.unjinxedmicroservices.components.vocabularies.models.oxforddictionaries.OxfordDictionariesResponse;
import reactor.core.publisher.Mono;

public interface OxfordDictionariesAdapter {
    public Mono<OxfordDictionariesResponse> getWordDefinition(String word);
}
