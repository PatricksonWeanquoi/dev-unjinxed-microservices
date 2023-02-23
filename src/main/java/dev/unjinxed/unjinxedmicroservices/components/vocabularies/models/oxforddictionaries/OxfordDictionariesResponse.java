package dev.unjinxed.unjinxedmicroservices.components.vocabularies.models.oxforddictionaries;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public class OxfordDictionariesResponse {
    public String id;
    public String word;
    public List<OxfordDictionariesResults> results;
    public Object metadata;

    @Override
    public String toString() {
        return "OxfordDictionariesResponse{" +
                "id='" + id + '\'' +
                ", word='" + word + '\'' +
                ", results=" + results +
                ", metadata=" + metadata +
                '}';
    }
}
