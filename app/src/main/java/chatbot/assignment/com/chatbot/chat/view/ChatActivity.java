package chatbot.assignment.com.chatbot.chat.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import javax.inject.Inject;

import butterknife.BindView;
import chatbot.assignment.com.chatbot.ChatBotApp;
import chatbot.assignment.com.chatbot.R;
import chatbot.assignment.com.chatbot.chat.model.ChatMessage;
import chatbot.assignment.com.chatbot.chat.presenter.ChatBotPresenter;

public class ChatActivity extends AppCompatActivity implements ChatBotMvpView {

    @Inject
    ChatBotPresenter chatBotPresenter;

    @BindView(R.id.chat_message_et)
    EditText chatMessage;
    @BindView(R.id.recycler_chat)
    RecyclerView recyclerChatView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        initDependencies();
        chatBotPresenter.onAttach(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        chatBotPresenter.fetchChatResponse("Hello");
    }

    private void initDependencies() {
        ((ChatBotApp) getApplication()).getAppComponent().inject(this);
    }

    @Override
    public void onChatResponse(int result, String message, ChatMessage chatMessage) {

    }

    public void sendChatMessage(View view) {
        chatBotPresenter.fetchChatResponse(chatMessage.getText().toString().trim());
    }
}
