package dev.unjinxed.unjinxedmicroservices.components.vocabularies.services.randomwords;

import dev.unjinxed.unjinxedmicroservices.components.vocabularies.models.randomwords.RandomWordsResponse;
import org.springframework.web.servlet.function.ServerResponse;
import reactor.core.publisher.Mono;

public interface RandomWordsService {

    public Mono<RandomWordsResponse> getRandomWord();
    public Mono<ServerResponse> getRandomWordServerResponse();
}
