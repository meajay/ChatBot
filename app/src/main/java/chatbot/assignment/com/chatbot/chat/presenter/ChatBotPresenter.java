package chatbot.assignment.com.chatbot.chat.presenter;

import javax.inject.Inject;

import chatbot.assignment.com.chatbot.base.BasePresenter;
import chatbot.assignment.com.chatbot.chat.model.ChatMessage;
import chatbot.assignment.com.chatbot.chat.view.ChatBotMvpView;
import chatbot.assignment.com.chatbot.constants.APIConstants;
import chatbot.assignment.com.chatbot.constants.AppConstants;
import chatbot.assignment.com.chatbot.network.api.ChatBotAPI;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Ajay on 15-01-2019.
 */
public class ChatBotPresenter extends BasePresenter<ChatBotMvpView> implements ChatBotMvpPresenter {


    private ChatBotAPI chatBotAPI;

    public ChatBotPresenter(ChatBotAPI chatBotAPI) {
        this.chatBotAPI = chatBotAPI;
    }

    @Override
    public void fetchChatResponse(String inputText) {
        chatBotAPI.fetchChatData(APIConstants.FETCH_CHAT_URL, inputText).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(chatResponse -> {
                    ChatMessage chatMessage = chatResponse.getMessage();
                    chatMessage.setChatMessaqeId(System.currentTimeMillis());
                    chatMessage.setSender(false);
                    getView()
                            .onChatResponse(AppConstants.SUCCESS, "success",chatMessage);
                },
                throwable -> {
                    getView().onChatResponse(AppConstants.ERROR, "error", null);
                });
    }
}
