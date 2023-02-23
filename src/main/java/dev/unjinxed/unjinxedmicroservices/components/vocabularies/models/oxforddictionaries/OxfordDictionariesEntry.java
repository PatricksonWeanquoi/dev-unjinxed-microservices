package dev.unjinxed.unjinxedmicroservices.components.vocabularies.models.oxforddictionaries;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public class OxfordDictionariesEntry {
    public List<String> etymologies;
    public String homographNumber;
    public List<Object> pronunciations;
    public List<OxfordDictionariesSenses> senses;

    @Override
    public String toString() {
        return "OxfordDictionariesEntry{" +
                "etymologies=" + etymologies +
                ", homographNumber='" + homographNumber + '\'' +
                ", pronunciations=" + pronunciations +
                ", senses=" + senses +
                '}';
    }
}

