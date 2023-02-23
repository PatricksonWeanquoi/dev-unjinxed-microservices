package dev.unjinxed.unjinxedmicroservices.components.vocabularies.models.oxforddictionaries;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
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

    @Override
    public String toString() {
        return "OxfordDictionariesSenses{" +
                "definitions=" + definitions +
                ", domainClasses=" + domainClasses +
                ", examples=" + examples +
                ", id='" + id + '\'' +
                ", semanticClasses=" + semanticClasses +
                ", shortDefinitions=" + shortDefinitions +
                ", subsenses=" + subsenses +
                ", synonyms=" + synonyms +
                ", thesaurusLinks=" + thesaurusLinks +
                ", notes=" + notes +
                '}';
    }
}

