package chatbot.assignment.com.chatbot.chat.view;

import java.util.List;

import chatbot.assignment.com.chatbot.base.BaseViewContract;
import chatbot.assignment.com.chatbot.chat.model.ChatMessage;

/**
 * Created by Ajay on 15-01-2019.
 */
public interface ChatBotMvpView extends BaseViewContract {
     void onChatResponse(int result, String message, ChatMessage chatMessage);
     void getAllMessagesFromDb(List<ChatMessage> chatMessageList);
}
