package me.anwarshahriar.mvplearning;

import javax.inject.Singleton;
import dagger.Component;
import me.anwarshahriar.mvplearning.view.activity.MainActivity;

@Component(modules = {AppModule.class})
@Singleton
public interface AppComponent {
    void inject(MainActivity target);
}
