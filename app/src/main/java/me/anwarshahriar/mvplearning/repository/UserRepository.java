package me.anwarshahriar.mvplearning.repository;

import me.anwarshahriar.mvplearning.model.User;

public interface UserRepository {
    User getUser(int id);
    void save(User anUser);
}
