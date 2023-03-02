package dev.unjinxed.unjinxedmicroservices.components.vocabularies.models.oxforddictionaries;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OxfordDictionariesSenses {
    public List<String> definitions;
    public List<Object> domainClasses;
    public List<OxfordDictionariesExample> examples;
    public String id;
    public List<Object> semanticClasses;
    public List<String> shortDefinitions;
    public List<Object> subsenses;
    public List<Object> synonyms;
    public List<Object> thesaurusLinks;
    public List<Object> notes;
}

