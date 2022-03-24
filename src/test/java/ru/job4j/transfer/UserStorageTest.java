package ru.job4j.transfer;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserStorageTest {

    @Test
    public void userAdd() {
        Storage storage =  new UserStorage();
        User user = new User(1, 300);
        assertTrue(storage.add(user));
    }

    @Test
    public void userUpdate() {
        UserStorage storage =  new UserStorage();
        User user = new User(1, 200);
        User user2 = new User(2, 100);
        assertTrue(storage.add(user));
        assertTrue(storage.add(user2));
        assertTrue(storage.update(new User(1, 300)));
        assertTrue(storage.transfer(1, 2, 300));
    }

    @Test
    public void userRemove() {
        Storage storage =  new UserStorage();
        User user = new User(1, 100);
        User user2 = new User(2, 50);
        assertTrue(storage.add(user));
        assertTrue(storage.delete(user));
        assertFalse(storage.delete(user2));
    }

}