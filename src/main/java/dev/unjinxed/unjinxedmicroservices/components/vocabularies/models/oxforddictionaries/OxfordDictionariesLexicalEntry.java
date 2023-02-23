package dev.unjinxed.unjinxedmicroservices.components.vocabularies.models.oxforddictionaries;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public class OxfordDictionariesLexicalEntry {
    public List<OxfordDictionariesEntry> entries;
    public OxfordDictionarieslexicalCategory lexicalCategory;
    public List<Object> phrases;
    public String text;

    @Override
    public String toString() {
        return "OxfordDictionariesLexicalEntry{" +
                "entries=" + entries +
                ", lexicalCategory=" + lexicalCategory +
                ", phrases=" + phrases +
                ", text='" + text + '\'' +
                '}';
    }
}

