package ru.job4j.transfer;

import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public final class UserStorage implements Storage, Transfer {

    private final Map<Integer, User> users = new HashMap<>();

    @Override
    public synchronized boolean add(User user) {
       /*
        boolean rsl = true;
        if (!users.containsKey(user.getId())) {
            users.put(user.getId(), user);
            return rsl;
        } else {
            users.get(user.getId());
        }
        return false;
        */
        return users.putIfAbsent(user.getId(), user) == null;
    }

    @Override
    public synchronized boolean update(User user) {
        /*
        boolean rsl = true;
        if (users.containsKey(user.getId())) {
            users.replace(user.getId(), user);
            return rsl;
        }

         */
        return users.replace(user.getId(), user) != null;
    }

    @Override
    public synchronized boolean delete(User user) {
        return users.remove(user.getId(), user);
    }

    @Override
    public synchronized boolean transfer(int fromId, int toId, int amount) {
        /* User userFrom = users.values().stream().filter(u -> fromId == u.getId()).findFirst().orElse(null); */
        /* User userTo = users.values().stream().filter(u -> toId == u.getId()).findFirst().orElse(null); */
        boolean rsl = true;
        User userFrom = users.get(fromId);
        User userTo = users.get(toId);
        if (userFrom != null && userTo != null && userFrom.getAmount() >= amount) {
            userFrom.setAmount(userFrom.getAmount() - amount);
            userTo.setAmount(userTo.getAmount() + amount);
            return rsl;
        }
        return false;
    }
}
