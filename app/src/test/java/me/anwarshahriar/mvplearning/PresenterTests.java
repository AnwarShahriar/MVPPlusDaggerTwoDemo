package me.anwarshahriar.mvplearning;

import me.anwarshahriar.mvplearning.model.User;
import me.anwarshahriar.mvplearning.presentation.UserPresenter;
import me.anwarshahriar.mvplearning.presentation.UserPresenterImpl;
import me.anwarshahriar.mvplearning.repository.UserRepository;
import me.anwarshahriar.mvplearning.view.UserView;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

public class PresenterTests {
  UserRepository mockUserRepository;
  UserView mockUserView;
  UserPresenter presenter;
  User user;

  @Before
  public void setup() {
    mockUserRepository = mock(UserRepository.class);

    user = User.with(1, "Mighty", "Mouse");
    when(mockUserRepository.getUser(anyInt())).thenReturn(user);

    mockUserView = mock(UserView.class);

    presenter = new UserPresenterImpl(mockUserRepository);
    presenter.setView(mockUserView);
  }

  @Test
  public void noInteractionsWithViewShouldTakePlaceIfUserIsNull() {
    presenter.saveUser();

    verifyZeroInteractions(mockUserView);
  }

  @Test
  public void shouldBeAbleToLoadTheUserFromTheRepositoryWhenValiduserisPresent() {
    when(mockUserView.getUserId()).thenReturn(1);

    presenter.loadUserDetails();

    verify(mockUserRepository, times(1)).getUser(anyInt());
    verify(mockUserView, times(1)).getUserId();
    verify(mockUserView, times(1)).displayFirstName("Mighty");
    verify(mockUserView, times(1)).displayLastName("Mouse");
    verify(mockUserView, never()).showUserNotFoundMessage();
  }

  @Test
  public void shouldShowErrorMessageOnViewWhenUserIsNotPresent() {
    when(mockUserView.getUserId()).thenReturn(1);
    when(mockUserRepository.getUser(anyInt())).thenReturn(null);

    presenter.loadUserDetails();

    verify(mockUserRepository, times(1)).getUser(anyInt());
    verify(mockUserView, times(1)).getUserId();
    verify(mockUserView, times(1)).showUserNotFoundMessage();
    verify(mockUserView, never()).displayFirstName("Mighty");
    verify(mockUserView, never()).displayLastName("Mouse");
  }
}
