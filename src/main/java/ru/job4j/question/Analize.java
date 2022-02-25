package ru.job4j.question;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        int changed = 0;
        int deleted = 0;
        int added = 0;
        if (previous != null && current != null) {
            Map<Integer, String> currUsers = current.stream()
                    .collect(Collectors.toMap(User::getId, User::getName));
            for (User prevUsers : previous) {
                String value = currUsers.get(prevUsers.getId());
                if (value != null && value.equals(prevUsers.getName())) {
                    currUsers.remove(prevUsers.getId());
                }
                if (value != null && !value.equals(prevUsers.getName())) {
                    currUsers.remove(prevUsers.getId());
                    changed++;
                }
                if (value == null) {
                    deleted++;
                }
            }
            added = currUsers.size();
        }
        return new Info(added, changed, deleted);
    }
}
