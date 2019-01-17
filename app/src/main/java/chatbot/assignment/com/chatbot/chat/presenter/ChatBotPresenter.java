package chatbot.assignment.com.chatbot.chat.presenter;

import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import chatbot.assignment.com.chatbot.base.BasePresenter;
import chatbot.assignment.com.chatbot.chat.model.ChatMessage;
import chatbot.assignment.com.chatbot.chat.view.ChatBotMvpView;
import chatbot.assignment.com.chatbot.constants.AppConstants;
import chatbot.assignment.com.chatbot.db.DbService;
import chatbot.assignment.com.chatbot.network.api.ChatBotAPI;
import chatbot.assignment.com.chatbot.workmanager.SendOfflineWorker;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Ajay on 15-01-2019.
 */
public class ChatBotPresenter extends BasePresenter<ChatBotMvpView> implements ChatBotMvpPresenter {


    private ChatBotAPI chatBotAPI;
    private DbService dbService;
    private WorkManager workManager;

    public ChatBotPresenter(ChatBotAPI chatBotAPI, DbService dbService, WorkManager workManager) {
        this.chatBotAPI = chatBotAPI;
        this.dbService = dbService;
        this.workManager = workManager;
    }

    @Override
    public void fetchChatResponse(String inputText) {
        chatBotAPI.fetchChatData(inputText).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(chatResponse -> {
                    ChatMessage chatMessage = chatResponse.getMessage();
                    chatMessage.setChatMessaqeId(System.currentTimeMillis());
                    chatMessage.setSender(false);
                    saveMessageToDb(chatMessage);
                    getView()
                            .onChatResponse(AppConstants.SUCCESS, "success", chatMessage);
                },
                throwable -> {
                    addToWorkManager(inputText);
                    getView().onChatResponse(AppConstants.ERROR, "error", null);
                });
    }

    @Override
    public void getAllDbMessages() {
        dbService.getAllMessages().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(chatMessageList -> {
                    getView().getAllMessagesFromDb(chatMessageList);
                });
    }

    @Override
    public void saveMessageToDb(ChatMessage chatMessage) {
        dbService.saveMessageToDb(chatMessage);
    }

    private Data createInputDataForWorkManager(String chatMessage) {
        Data.Builder builder = new Data.Builder();
        builder.putString(AppConstants.CHAT_MESSAGE, chatMessage);
        return builder.build();
    }

    @Override
    public void addToWorkManager(String chatMessage) {
        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build();
        OneTimeWorkRequest request = new OneTimeWorkRequest.Builder(SendOfflineWorker.class)
                .setInputData(createInputDataForWorkManager(chatMessage))
                .setConstraints(constraints)
                .build();
        workManager.enqueue(request);
    }
}
