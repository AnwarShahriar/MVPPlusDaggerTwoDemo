package me.anwarshahriar.mvplearning;

import android.app.Application;

public class MVPApp extends Application {
    private AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerAppComponent.builder()
                        .appModule(new AppModule())
                        .build();
    }

    public AppComponent getComponent() {
        return component;
    }
}
