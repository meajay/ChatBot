package chatbot.assignment.com.chatbot.network.api;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONObject;

import chatbot.assignment.com.chatbot.constants.APIConstants;
import chatbot.assignment.com.chatbot.constants.AppConstants;
import chatbot.assignment.com.chatbot.network.response.ChatResponse;
import chatbot.assignment.com.chatbot.utils.GsonUtils;
import io.reactivex.Observable;

/**
 * Created by Ajay on 15-01-2019.
 */
public class ChatBotAPI {
    public Observable<ChatResponse> fetchChatData( String chatMessage) {
        return Observable.create(emitter -> {
            AndroidNetworking.get(APIConstants.FETCH_CHAT_URL)
                    .addHeaders("Content-Type", "application/x-www-form-urlencoded")
                    .addQueryParameter("message",chatMessage)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                emitter.onNext(GsonUtils.convertToObject(response.toString(), ChatResponse.class));
                                emitter.onComplete();
                            } catch (Exception e) {
                                emitter.onError(e);
                                emitter.onComplete();
                            }
                        }

                        @Override
                        public void onError(ANError error) {
                            // handle error
                        }
                    });
        });
    }

}
