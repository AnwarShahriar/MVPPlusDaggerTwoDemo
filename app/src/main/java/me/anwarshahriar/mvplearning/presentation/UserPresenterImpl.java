package me.anwarshahriar.mvplearning.presentation;

import android.text.TextUtils;

import me.anwarshahriar.mvplearning.model.User;
import me.anwarshahriar.mvplearning.repository.UserRepository;
import me.anwarshahriar.mvplearning.view.UserView;

public class UserPresenterImpl implements UserPresenter {
    private UserRepository userRepository;
    private UserView view;
    private User u;

    public UserPresenterImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void loadUserDetails() {
        int id = view.getUserId();
        u = userRepository.getUser(id);
        if (u == null) {
            view.showUserNotFoundMessage();
        } else {
            view.displayFirstName(u.firstName());
            view.displayLastName(u.lastName());
        }
    }

    @Override
    public void setView(UserView view) {
        this.view = view;
    }

    @Override
    public void saveUser() {
        if (u != null) {
            if (TextUtils.isEmpty(view.getFirstName())
                    || TextUtils.isEmpty(view.getLastName())) {
                view.showUserNameIsRequired();
            } else {
                u = User.with(u.id(), view.getFirstName(), view.getLastName());
                userRepository.save(u);
                view.showUserSavedMessage();
            }
        }
    }

    @Override
    public void resume() {
        loadUserDetails();
    }

    @Override
    public void pause() {

    }
}
