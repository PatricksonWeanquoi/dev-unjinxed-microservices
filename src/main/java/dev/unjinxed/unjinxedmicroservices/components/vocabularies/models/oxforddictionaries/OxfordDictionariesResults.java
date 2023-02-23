package dev.unjinxed.unjinxedmicroservices.components.vocabularies.models.oxforddictionaries;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public class OxfordDictionariesResults {
    public String id;
    public String language;
    public List<OxfordDictionariesLexicalEntry> lexicalEntries;
    public String type;
    public String word;

    @Override
    public String toString() {
        return "OxfordDictionariesResults{" +
                "id='" + id + '\'' +
                ", language='" + language + '\'' +
                ", lexicalEntries=" + lexicalEntries +
                ", type='" + type + '\'' +
                ", word='" + word + '\'' +
                '}';
    }
}

