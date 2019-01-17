package chatbot.assignment.com.chatbot.chat.presenter;

import java.util.List;

import javax.inject.Inject;

import chatbot.assignment.com.chatbot.base.BasePresenter;
import chatbot.assignment.com.chatbot.chat.GetAllMessages;
import chatbot.assignment.com.chatbot.chat.model.ChatMessage;
import chatbot.assignment.com.chatbot.chat.view.ChatBotMvpView;
import chatbot.assignment.com.chatbot.constants.APIConstants;
import chatbot.assignment.com.chatbot.constants.AppConstants;
import chatbot.assignment.com.chatbot.db.DbService;
import chatbot.assignment.com.chatbot.network.api.ChatBotAPI;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Ajay on 15-01-2019.
 */
public class ChatBotPresenter extends BasePresenter<ChatBotMvpView> implements ChatBotMvpPresenter {


    private ChatBotAPI chatBotAPI;
    private DbService dbService;

    public ChatBotPresenter(ChatBotAPI chatBotAPI, DbService dbService) {
        this.chatBotAPI = chatBotAPI;
        this.dbService = dbService;
    }

    @Override
    public void fetchChatResponse(String inputText) {
        chatBotAPI.fetchChatData(APIConstants.FETCH_CHAT_URL, inputText).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(chatResponse -> {
                    ChatMessage chatMessage = chatResponse.getMessage();
                    chatMessage.setChatMessaqeId(System.currentTimeMillis());
                    chatMessage.setSender(false);
                    saveMessageToDb(chatMessage);
                    getView()
                            .onChatResponse(AppConstants.SUCCESS, "success", chatMessage);
                },
                throwable -> {
                    getView().onChatResponse(AppConstants.ERROR, "error", null);
                });
    }

    @Override
    public void getAllDbMessages() {
        dbService.getAllMessages(chatMessageList ->
                getView().getAllMessagesFromDb(chatMessageList));
    }

    @Override
    public void saveMessageToDb(ChatMessage chatMessage) {
        dbService.saveMessageToDb(chatMessage);
    }
}
