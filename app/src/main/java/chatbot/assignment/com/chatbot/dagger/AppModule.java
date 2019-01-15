package chatbot.assignment.com.chatbot.dagger;

import android.app.Application;
import android.content.Context;

import chatbot.assignment.com.chatbot.dagger.qualifier.ApplicationContext;
import chatbot.assignment.com.chatbot.dagger.scope.AppScope;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Ajay on 15-01-2019.
 */
@Module
public class AppModule {
    private Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @AppScope
    public Application providesApplication() {
        return application;
    }

    @Provides
    @ApplicationContext
    @AppScope
    Context provideContext() {
        return application.getBaseContext();
    }
}
