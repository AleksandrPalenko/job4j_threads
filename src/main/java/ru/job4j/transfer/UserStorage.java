package ru.job4j.transfer;

import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public final class UserStorage {

    private final Map<Integer, User> users = new HashMap<>();

    synchronized boolean add(User user) {
        boolean rsl = true;
        if (!users.containsValue(user)) {
            add(user);
            return rsl;
        }
        return false;
    }

    synchronized boolean update(User user) {
        boolean rsl = true;
        if (users.containsValue(user)) {
            delete(user);
            update(user);
            return rsl;
        }
        return false;
    }

    synchronized boolean delete(User user) {
        boolean rsl = true;
        if (users.containsKey(user.getId())) {
            delete(user);
            return rsl;
        }
        return false;
    }

    public synchronized void transfer(int fromId, int toId, int amount) {
        User userFrom = users.values().stream().filter(u -> fromId == u.getId()).findFirst().orElse(null);
        User userTo = users.values().stream().filter(u -> toId == u.getId()).findFirst().orElse(null);
        if (userFrom != null && userTo != null) {
            /* userFrom.setAmount(userFrom.getAmount() - amount); */
            userTo.setAmount(userTo.getAmount() + amount);
        }
    }
}
