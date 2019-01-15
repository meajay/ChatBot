package chatbot.assignment.com.chatbot.chat.view;

import chatbot.assignment.com.chatbot.base.BaseViewContract;

/**
 * Created by Ajay on 15-01-2019.
 */
public interface ChatBotMvpView extends BaseViewContract {
     void onChatResponse(int result, String message, String chatMessage);
}
