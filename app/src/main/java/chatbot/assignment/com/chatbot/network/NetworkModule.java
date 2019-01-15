package chatbot.assignment.com.chatbot.network;

import android.content.Context;

import com.readystatesoftware.chuck.ChuckInterceptor;

import java.util.concurrent.TimeUnit;

import chatbot.assignment.com.chatbot.dagger.qualifier.ApplicationContext;
import chatbot.assignment.com.chatbot.dagger.scope.AppScope;
import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by Ajay on 15-01-2019.
 */
@Module
public class NetworkModule {
    private static final long CACHE_SIZE = 10 * 1024 * 1024;    // 10 MB
    private static final int CONNECT_TIMEOUT = 6;
    private static final int WRITE_TIMEOUT = 60;
    private static final int TIMEOUT = 15;


    @Provides
    @AppScope
    OkHttpClient providesOkHttpClient( @ApplicationContext Context context) {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .addInterceptor(new AuthInterceptor())
                .addInterceptor(new ChuckInterceptor(context));

        return builder.build();
    }
}
