package chatbot.assignment.com.chatbot.workmanager;

import android.content.Context;
import android.nfc.Tag;
import android.util.Log;

import javax.inject.Inject;

import chatbot.assignment.com.chatbot.ChatBotApp;
import chatbot.assignment.com.chatbot.chat.model.ChatMessage;
import chatbot.assignment.com.chatbot.db.DbService;
import chatbot.assignment.com.chatbot.network.api.ChatBotAPI;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class WorkerUtils {

    public static final String TAG = WorkerUtils.class.getSimpleName();

    @Inject
    DbService dbService;

    @Inject
    ChatBotAPI chatBotAPI;

    public void initDependencies(Context context) {
        ((ChatBotApp) context).getAppComponent().inject(this);
    }

    public void sendOfflineMessage(String chatMessage) {
        chatBotAPI.fetchChatData(chatMessage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(chatResponse -> {
                    ChatMessage message = chatResponse.getMessage();
                    message.setChatMessaqeId(System.currentTimeMillis());
                    message.setSender(false);
                    dbService.saveMessageToDb(message);
                }, throwable -> {
                    Log.d(TAG, throwable.getMessage());
                });
    }
}
