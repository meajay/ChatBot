package chatbot.assignment.com.chatbot.network;

import android.support.annotation.NonNull;

import java.io.IOException;

import chatbot.assignment.com.chatbot.constants.AppConstants;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Ajay on 15-01-2019.
 */
public class AuthInterceptor implements Interceptor {
        @Override
        public Response intercept(@NonNull Chain chain) throws IOException {
            Request originalRequest = chain.request();

            HttpUrl originalHttpUrl = originalRequest.url();
            HttpUrl newHttpUrl = originalHttpUrl.newBuilder()
                    .build();

            Request newRequest = originalRequest.newBuilder()
                 //   .addHeader(AppConstants.AUTHORIZATION, AppConstants.TOKEN)
                    .addHeader(AppConstants.CONTENT_TYPE, AppConstants.APPLICATION_JSON)
                    .addHeader(AppConstants.REQUESTED_WITH, AppConstants.XML_HTTP_REQUEST)
                    .url(newHttpUrl)
                    .build();

            return chain.proceed(newRequest);
        }
}
