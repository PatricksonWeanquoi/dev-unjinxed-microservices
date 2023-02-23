package dev.unjinxed.unjinxedmicroservices.components.vocabularies.schedulers;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class VocabularyScheduler {
    @Scheduled(cron = "${cron.interval}")
    public void exe() {
        System.out.println("Hello World");
    }
}
