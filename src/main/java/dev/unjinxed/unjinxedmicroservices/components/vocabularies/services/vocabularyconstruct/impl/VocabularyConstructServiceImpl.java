package dev.unjinxed.unjinxedmicroservices.components.vocabularies.services.vocabularyconstruct.impl;

import dev.unjinxed.unjinxedmicroservices.components.vocabularies.models.oxforddictionaries.OxfordDictionariesResponse;
import dev.unjinxed.unjinxedmicroservices.components.vocabularies.models.oxforddictionaries.OxfordDictionariesSenses;
import dev.unjinxed.unjinxedmicroservices.components.vocabularies.models.randomwords.RandomWordsResponse;
import dev.unjinxed.unjinxedmicroservices.components.vocabularies.models.vocabularycontruct.WordDefinition;
import dev.unjinxed.unjinxedmicroservices.components.vocabularies.services.oxforddictionaries.OxfordDictionariesService;
import dev.unjinxed.unjinxedmicroservices.components.vocabularies.services.randomwords.RandomWordsService;
import dev.unjinxed.unjinxedmicroservices.components.vocabularies.services.vocabularyconstruct.VocabularyConstructService;
import dev.unjinxed.unjinxedmicroservices.exceptions.RequestEntityBuilderException;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.function.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collector;
import java.util.stream.Collectors;
@Service
@Slf4j
public class VocabularyConstructServiceImpl implements VocabularyConstructService {

    RandomWordsService randomWordsService;
    OxfordDictionariesService oxfordDictionariesService;

    public VocabularyConstructServiceImpl(@Autowired RandomWordsService randomWordsService,
                                          @Autowired OxfordDictionariesService oxfordDictionariesService) {
        this.randomWordsService = randomWordsService;
        this.oxfordDictionariesService = oxfordDictionariesService;
    }
    @Override
    public Mono<WordDefinition> getRandomWordDefinition() {
        return this.composeDefinition();
    }

    @Override
    public Mono<ServerResponse> getRandomWordDefinitionServerResponse() {
        return this.getRandomWordDefinition()
                .map(wordDefinition -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(Mono.just(wordDefinition), new ParameterizedTypeReference<>(){}))
                .onErrorResume(HttpClientErrorException.class, httpClientErrorException -> Mono.just(ServerResponse
                        .status(httpClientErrorException.getStatusCode().value())
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(Mono.just(httpClientErrorException.getResponseBodyAsString()), new ParameterizedTypeReference<Mono<Object>>(){}))
                ).onErrorResume(RuntimeException.class, e -> Mono.just(ServerResponse
                        .status(500)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(Mono.just(e.getMessage()), new ParameterizedTypeReference<Mono<Object>>(){})));

    }

    private Mono<WordDefinition> composeDefinition() {
        return this.randomWordsService.getRandomWord()
                .map(RandomWordsResponse::getWord)
                .flatMap(this.oxfordDictionariesService::getWordDefinition)
                .map(this::buildWordDefinition);
    }

    private WordDefinition buildWordDefinition(OxfordDictionariesResponse oxfordDictionariesResponse){
        final WordDefinition wordDefinition = WordDefinition.builder()
                .lexicalCategories(new ArrayList<>())
                .build();
        if(oxfordDictionariesResponse != null) {
            if(!StringUtil.isNullOrEmpty(oxfordDictionariesResponse.word)) {
                wordDefinition.setWord(oxfordDictionariesResponse.word);
            }
            OxfordDictionariesSenses dictionariesSenses = new OxfordDictionariesSenses();
            dictionariesSenses.definitions = new ArrayList<>();
            dictionariesSenses.examples = new ArrayList<>();
            dictionariesSenses = oxfordDictionariesResponse.results
                .stream()
                .map(oxfordDictionariesResults -> oxfordDictionariesResults.lexicalEntries)
                .flatMap(Collection::stream)
                .filter(oxfordDictionariesLexicalEntry -> oxfordDictionariesLexicalEntry.lexicalCategory != null)
                .map(oxfordDictionariesLexicalEntry -> {
                    wordDefinition.getLexicalCategories().add(oxfordDictionariesLexicalEntry.lexicalCategory);
                    log.info("oxfordDictionariesLexicalEntry.lexicalCategory: " + oxfordDictionariesLexicalEntry.lexicalCategory);
                    return oxfordDictionariesLexicalEntry;
                })
                .flatMap(oxfordDictionariesLexicalEntry -> oxfordDictionariesLexicalEntry.entries.stream())
                .flatMap(oxfordDictionariesEntry -> oxfordDictionariesEntry.senses.stream())
                .filter(oxfordDictionariesSenses -> oxfordDictionariesSenses.definitions != null)
                .filter(oxfordDictionariesSenses -> oxfordDictionariesSenses.examples != null)
                .reduce(dictionariesSenses, (oxfordDictionariesSenseFinal, oxfordDictionariesSenses) -> {
                    oxfordDictionariesSenseFinal.definitions.addAll(oxfordDictionariesSenses.definitions);
                    oxfordDictionariesSenseFinal.examples.addAll(oxfordDictionariesSenses.examples);
                    return oxfordDictionariesSenseFinal;
                });
            wordDefinition.setDefinitions(dictionariesSenses.definitions);
            wordDefinition.setExamples(dictionariesSenses.examples);
            return wordDefinition;
        }
        return null;
    }
}
