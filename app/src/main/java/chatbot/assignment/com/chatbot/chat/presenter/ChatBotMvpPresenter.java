package chatbot.assignment.com.chatbot.chat.presenter;

import chatbot.assignment.com.chatbot.chat.model.ChatMessage;

/**
 * Created by Ajay on 15-01-2019.
 */
public interface ChatBotMvpPresenter {
 void fetchChatResponse(String inputText);
 void getAllDbMessages();
 void saveMessageToDb(ChatMessage chatMessage);
}
