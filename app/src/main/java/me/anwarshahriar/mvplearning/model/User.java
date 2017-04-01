package me.anwarshahriar.mvplearning.model;

import com.google.auto.value.AutoValue;

@AutoValue public abstract class User {
    public abstract int id();
    public abstract String firstName();
    public abstract String lastName();

    public static User with(int id, String firstName, String lastName) {
        return new AutoValue_User(id, firstName, lastName);
    }
}
