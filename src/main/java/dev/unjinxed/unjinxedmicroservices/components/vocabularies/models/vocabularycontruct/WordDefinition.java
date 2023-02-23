package dev.unjinxed.unjinxedmicroservices.components.vocabularies.models.vocabularycontruct;

import dev.unjinxed.unjinxedmicroservices.components.vocabularies.models.oxforddictionaries.OxfordDictionariesExample;
import dev.unjinxed.unjinxedmicroservices.components.vocabularies.models.oxforddictionaries.OxfordDictionarieslexicalCategory;
import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder
public class WordDefinition {
    String word;
    List<String> definitions;
    List<OxfordDictionariesExample> examples;
    List<OxfordDictionarieslexicalCategory> lexicalCategories;
}
