package ru.job4j.ood.tdd.template;

import org.junit.Ignore;
import org.junit.Test;

import java.util.Map;

public class TemplateGeneratorTest {
    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenTemplateIsNull() {
        new TemplateGenerator()
                .produce(null,
                        Map.of("name", "Petr Arsentev",
                                "subject", "you"
                        ));
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenMapIsNull() {
        new TemplateGenerator()
                .produce("I am a ${name}, Who are ${subject}? ", null);
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenTemplateIsEmpty() {
        new TemplateGenerator()
                .produce("",
                        Map.of("name", "Petr Arsentev",
                                "subject", "you"
                        ));
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenMapIsEmpty() {
        new TemplateGenerator()
                .produce("I am a ${name}, Who are ${subject}?", Map.of());
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenMapNotContainsKey() {
        new TemplateGenerator()
                .produce("I am a ${name}, Who are ${subject}?",
                        Map.of("name", "Petr Arsentev"));
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenMapContainsExtraKey() {
        new TemplateGenerator()
                .produce("I am a ${name}, Who are ${subject}?",
                        Map.of("name", "Petr Arsentev",
                                "subject", "you",
                                "id", "1234"
                        ));
    }
}