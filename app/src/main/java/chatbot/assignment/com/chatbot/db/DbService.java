package chatbot.assignment.com.chatbot.db;

import android.content.Context;

import java.util.List;

import chatbot.assignment.com.chatbot.chat.model.ChatMessage;
import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Flowable;
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

    public Flowable<List<ChatMessage>> getAllMessages() {
        return chatBotDataBase.chatMessageDao().getAll();
    }


}
