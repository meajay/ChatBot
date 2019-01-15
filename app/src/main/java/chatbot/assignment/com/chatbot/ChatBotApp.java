package chatbot.assignment.com.chatbot;

import android.app.Application;

import com.androidnetworking.AndroidNetworking;

import javax.inject.Inject;

import chatbot.assignment.com.chatbot.dagger.AppComponent;
import chatbot.assignment.com.chatbot.dagger.AppModule;
import chatbot.assignment.com.chatbot.dagger.DaggerAppComponent;
import chatbot.assignment.com.chatbot.network.NetworkModule;
import okhttp3.OkHttpClient;

/**
 * Created by Ajay on 15-01-2019.
 */
public class ChatBotApp extends Application {

    @Inject
    OkHttpClient okHttpClient;


    public AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initAppComponent().inject(this);
        AndroidNetworking.initialize(this, okHttpClient);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    public AppComponent initAppComponent() {
        if (appComponent == null) {
            appComponent = DaggerAppComponent
                    .builder().appModule(new AppModule(this))
                    .networkModule(new NetworkModule())
                    .build();
        }
        return appComponent;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
