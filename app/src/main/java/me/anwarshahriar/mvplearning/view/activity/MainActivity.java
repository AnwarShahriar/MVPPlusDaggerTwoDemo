package me.anwarshahriar.mvplearning.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.anwarshahriar.mvplearning.MVPApp;
import me.anwarshahriar.mvplearning.R;
import me.anwarshahriar.mvplearning.presentation.UserPresenter;
import me.anwarshahriar.mvplearning.view.UserView;

public class MainActivity extends AppCompatActivity implements UserView {
    @Inject UserPresenter userPresenter;

    @BindView(R.id.user_first_name)
    EditText userFirstName;

    @BindView(R.id.user_last_name)
    EditText userLastName;

    private static final String USER_ID = "user_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ((MVPApp) getApplication()).getComponent().inject(this);

        userPresenter.setView(this);
    }

    @OnClick(R.id.user_save)
    public void saveUser() {
        userPresenter.saveUser();
    }

    @Override
    public int getUserId() {
        return getIntent().getIntExtra(USER_ID, 0);
    }

    @Override
    public void displayFirstName(String name) {
        userFirstName.setText(name);
    }

    @Override
    public void displayLastName(String name) {
        userLastName.setText(name);
    }

    @Override
    public void showUserNotFoundMessage() {
        Toast.makeText(this, "User not found", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showUserSavedMessage() {
        Toast.makeText(this, "User saved successfully", Toast.LENGTH_SHORT).show();
    }

    @Override
    public String getFirstName() {
        return userFirstName.getText().toString();
    }

    @Override
    public String getLastName() {
        return userLastName.getText().toString();
    }

    @Override
    public void showUserNameIsRequired() {
        Toast.makeText(this, "User name is required", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        userPresenter.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        userPresenter.pause();
    }
}
