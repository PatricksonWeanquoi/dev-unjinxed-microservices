package dev.unjinxed.unjinxedmicroservices.components.vocabularies.models.randomwords;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class RandomWordsResponse {
    String word;
}
