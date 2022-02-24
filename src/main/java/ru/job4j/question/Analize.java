package ru.job4j.question;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        Info info = new Info(0, 0, 0);
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
                    info.setChanged(1);
                }
                if (value == null) {
                    info.setDeleted(1);
                }
            }
            info.setAdded(currUsers.size());
        }
        return info;
    }
}
