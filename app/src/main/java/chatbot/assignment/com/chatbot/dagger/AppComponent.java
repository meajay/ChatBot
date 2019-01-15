package chatbot.assignment.com.chatbot.dagger;

import chatbot.assignment.com.chatbot.chat.view.ChatActivity;
import chatbot.assignment.com.chatbot.ChatBotApp;
import chatbot.assignment.com.chatbot.dagger.scope.AppScope;
import chatbot.assignment.com.chatbot.network.NetworkModule;
import dagger.Component;

/**
 * Created by Ajay on 15-01-2019.
 */
@AppScope
@Component(modules = {AppModule.class, NetworkModule.class})
public interface AppComponent {
    void inject(ChatBotApp chatBotApp);

    void inject(ChatActivity chatActivity);

}
