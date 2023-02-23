package dev.unjinxed.unjinxedmicroservices.components.vocabularies.adapters.randomwordsadapter;

import dev.unjinxed.unjinxedmicroservices.components.vocabularies.models.randomwords.RandomWordsResponse;
import org.springframework.web.servlet.function.ServerResponse;
import reactor.core.publisher.Mono;

public interface RandomWordsAdapter {
    public Mono<RandomWordsResponse> getRandomWord();
}
