package dev.unjinxed.unjinxedmicroservices.components.vocabularies.models.oxforddictionaries;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OxfordDictionariesExample {
    public String text;
    public List<Object> notes;

    @Override
    public String toString() {
        return "OxfordDictionariesExample{" +
                "text='" + text + '\'' +
                ", notes=" + notes +
                '}';
    }
}
