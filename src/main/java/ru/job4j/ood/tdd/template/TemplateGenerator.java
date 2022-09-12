package ru.job4j.ood.tdd.template;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class TemplateGenerator implements Generator {

    private final Pattern pattern = Pattern.compile("\\$\\{.+?\\}");

    @Override
    public String produce(String template, Map<String, String> args) {
        List<String> wordsTemplate = validateInput(template, args);
        String formatString = template.replaceAll(pattern.toString(), "%s");
        return String.format(formatString, wordsTemplate.stream().map(args::get).toArray());
    }

    private List<String> validateInput(String template, Map<String, String> args) {
        if (template == null || template.isBlank() || args == null || args.isEmpty()) {
            throw new IllegalArgumentException("Шаблон или мапа не корректны");
        }
        var matcher = pattern.matcher(template);
        int count = 0;
        List<String> wordsFromTemplate = new ArrayList<>();
        while (matcher.find()) {
            var word = template.substring(matcher.start() + 2, matcher.end() - 1);
            count++;
            if (!args.containsKey(word)) {
                throw new IllegalArgumentException("Недостаточно ключей в мапе");
            }
            wordsFromTemplate.add(word);
        }
        if (count != args.size()) {
            throw new IllegalArgumentException("Недостаточно параметров в шаблоне");
        }
        return wordsFromTemplate;
    }
}
