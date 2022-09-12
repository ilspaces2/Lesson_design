package ru.job4j.ood.tdd.template;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TemplateGeneratorTest {

    @Test()
    public void whenTemplateIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new TemplateGenerator()
                .produce(null,
                        Map.of("name", "Petr Arsentev",
                                "subject", "you"))
        );
    }

    @Test()
    public void whenMapIsNull() {
        assertThrows(IllegalArgumentException.class, () ->
                new TemplateGenerator()
                        .produce("I am a ${name}, Who are ${subject}? ", null)
        );
    }

    @Test()
    public void whenTemplateIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new TemplateGenerator()
                .produce("",
                        Map.of("name", "Petr Arsentev",
                                "subject", "you"))
        );
    }

    @Test()
    public void whenMapIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new TemplateGenerator()
                .produce("I am a ${name}, Who are ${subject}?", Map.of())
        );
    }

    @Test()
    public void whenMapNotContainsKey() {
        assertThrows(IllegalArgumentException.class, () -> new TemplateGenerator()
                .produce("I am a ${name}, Who are ${subject}?",
                        Map.of("name", "Petr Arsentev"))
        );
    }

    @Test()
    public void whenMapContainsExtraKey() {
        assertThrows(IllegalArgumentException.class, () -> new TemplateGenerator()
                .produce("I am a ${name}, Who are ${subject}?",
                        Map.of("name", "Petr Arsentev",
                                "subject", "you",
                                "id", "1234"))
        );
    }

    @Test
    public void whenTemplateGenerationIsDone() {
        String expected = new TemplateGenerator()
                .produce("I am a ${name}, Who are ${subject}?",
                        Map.of("name", "Petr Arsentev",
                                "subject", "you"
                        ));
        String actual = "I am a Petr Arsentev, Who are you?";
        assertEquals(expected, actual);
    }
}