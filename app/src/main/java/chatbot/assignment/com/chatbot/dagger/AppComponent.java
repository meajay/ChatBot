package chatbot.assignment.com.chatbot.dagger;

import chatbot.assignment.com.chatbot.chat.view.ChatActivity;
import chatbot.assignment.com.chatbot.ChatBotApp;
import chatbot.assignment.com.chatbot.dagger.scope.AppScope;
import chatbot.assignment.com.chatbot.db.DbService;
import chatbot.assignment.com.chatbot.network.NetworkModule;
import chatbot.assignment.com.chatbot.network.api.ChatBotAPI;
import chatbot.assignment.com.chatbot.workmanager.WorkerUtils;
import dagger.Component;

/**
 * Created by Ajay on 15-01-2019.
 */
@AppScope
@Component(modules = {AppModule.class, NetworkModule.class})
public interface AppComponent {
    void inject(ChatBotApp chatBotApp);

    void inject(ChatActivity chatActivity);

    void inject(WorkerUtils workerUtils);

    ChatBotAPI getChatBotAPI();

    DbService getDbService();

}

