package ru.job4j.transfer;

import net.jcip.annotations.ThreadSafe;

import java.util.HashSet;

@ThreadSafe
public final class UserStorage {

    private final HashSet<User> users = new HashSet<>();

    boolean add(User user) {
        return users.add(user);
    }

    boolean update(User user) {
        return false;
    }

    boolean delete(User user) {
        return users.remove(user);
    }

    public synchronized void transfer(int fromId, int toId, int amount) {
        User userFrom = users.stream().filter(u -> fromId == u.getId()).findFirst().orElse(null);
        User userTo = users.stream().filter(u -> toId == u.getId()).findFirst().orElse(null);
        if (userFrom != null && userTo != null) {
           /* userFrom.setAmount(userFrom.getAmount() - amount); */
            userTo.setAmount(userTo.getAmount() + amount);
        }
    }
}
