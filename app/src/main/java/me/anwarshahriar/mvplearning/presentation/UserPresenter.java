package me.anwarshahriar.mvplearning.presentation;

import me.anwarshahriar.mvplearning.view.UserView;

public interface UserPresenter extends LifecyclePresenter {
    void loadUserDetails();
    void setView(UserView view);
    void saveUser();
}
