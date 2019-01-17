package chatbot.assignment.com.chatbot.db;

import android.content.Context;

import java.util.List;

import chatbot.assignment.com.chatbot.chat.GetAllMessages;
import chatbot.assignment.com.chatbot.chat.model.ChatMessage;
import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Scheduler;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

public class DbService {

    private ChatBotDataBase chatBotDataBase;
    private Context context;
    private CompositeDisposable compositeDisposable;

    public DbService(ChatBotDataBase nTeacherDataBase, Context context) {
        compositeDisposable = new CompositeDisposable();
        this.context = context;
        this.chatBotDataBase = nTeacherDataBase;
    }

    public static DbService getInstance(ChatBotDataBase chatBotDataBase, Context context) {
        return new DbService(chatBotDataBase, context);
    }

    public void saveMessageToDb(ChatMessage chatMessage) {
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                chatBotDataBase.chatMessageDao().insert(chatMessage);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    public void getAllMessages(GetAllMessages getAllMessages) {
        chatBotDataBase.chatMessageDao().findMessages().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<ChatMessage>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(List<ChatMessage> chatMessages) {
                        if (chatMessages.size() > 0) {
                            getAllMessages.getAllMessages(chatMessages);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }


}
