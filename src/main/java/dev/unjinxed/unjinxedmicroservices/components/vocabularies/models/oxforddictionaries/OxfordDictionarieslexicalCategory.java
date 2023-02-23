package dev.unjinxed.unjinxedmicroservices.components.vocabularies.models.oxforddictionaries;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OxfordDictionarieslexicalCategory {
    public String id;
    public String text;

    @Override
    public String toString() {
        return "OxfordDictionarieslexicalCategory{" +
                "id='" + id + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
