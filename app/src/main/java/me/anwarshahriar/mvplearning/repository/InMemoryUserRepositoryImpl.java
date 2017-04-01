package me.anwarshahriar.mvplearning.repository;

import java.util.HashSet;
import java.util.Set;
import me.anwarshahriar.mvplearning.model.User;

public class InMemoryUserRepositoryImpl implements UserRepository {
    private Set<User> users = new HashSet<>();

    @Override
    public User getUser(int id) {
        for (User current : users) {
            if (current.id() == id) {
                return current;
            }
        }

        User newUser = User.with(id, "John", "Doe");
        save(newUser);

        return newUser;
    }

    @Override
    public void save(User anUser) {
        users.add(anUser);
    }
}
