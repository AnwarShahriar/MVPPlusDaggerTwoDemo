package me.anwarshahriar.mvplearning;

import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import me.anwarshahriar.mvplearning.presentation.UserPresenter;
import me.anwarshahriar.mvplearning.presentation.UserPresenterImpl;
import me.anwarshahriar.mvplearning.repository.InMemoryUserRepositoryImpl;
import me.anwarshahriar.mvplearning.repository.UserRepository;

@Module
public class AppModule {

    @Provides @Singleton
    public UserRepository provideUserRepository() {
        return new InMemoryUserRepositoryImpl();
    }

    @Provides
    public UserPresenter provideUserPresenter(UserRepository userRepository) {
        return new UserPresenterImpl(userRepository);
    }
}
