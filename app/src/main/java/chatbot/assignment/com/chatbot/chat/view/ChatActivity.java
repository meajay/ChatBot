package chatbot.assignment.com.chatbot.chat.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import chatbot.assignment.com.chatbot.R;
import chatbot.assignment.com.chatbot.chat.view.ChatBotMvpView;

public class ChatActivity extends AppCompatActivity implements ChatBotMvpView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
    }

    @Override
    public void onChatResponse(int result, String message, String chatMessage) {

    }
}
