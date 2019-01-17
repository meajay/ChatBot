package chatbot.assignment.com.chatbot.chat.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import chatbot.assignment.com.chatbot.ChatBotApp;
import chatbot.assignment.com.chatbot.R;
import chatbot.assignment.com.chatbot.chat.model.ChatMessage;
import chatbot.assignment.com.chatbot.chat.presenter.ChatBotPresenter;
import chatbot.assignment.com.chatbot.constants.AppConstants;

public class ChatActivity extends AppCompatActivity implements ChatBotMvpView {

    @Inject
    ChatBotPresenter chatBotPresenter;

    @BindView(R.id.chat_message_et)
    EditText chatMessageET;
    @BindView(R.id.recycler_chat)
    RecyclerView recyclerChatView;
    @BindView(R.id.no_data_text)
    TextView noDataTV;
    @BindView(R.id.progress_circular)
    ProgressBar progressBar;

    private ChatAdapter adapter;
    private List<ChatMessage> chatMessageList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);
        initDependencies();
        chatBotPresenter.onAttach(this);
        setUpRecyclerView();
        setUpTextUI();

    }

    private void setUpTextUI() {
        if (chatMessageList.size() > 0) {
            noDataTV.setVisibility(View.GONE);
        } else {
            noDataTV.setVisibility(View.VISIBLE);
        }
    }

    private void setUpRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                this, LinearLayoutManager.VERTICAL, false);
        linearLayoutManager.setStackFromEnd(true);
        recyclerChatView.setLayoutManager(linearLayoutManager);
        adapter = new ChatAdapter(this, chatMessageList);
        recyclerChatView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        chatBotPresenter.getAllDbMessages();
    }

    private void initDependencies() {
        ((ChatBotApp) getApplication()).getAppComponent().inject(this);
    }

    @Override
    public void onChatResponse(int result, String message, ChatMessage chatMessage) {
        if (AppConstants.SUCCESS == result) {
            progressBar.setVisibility(View.GONE);
            updateReclycerView(chatMessage);
        } else {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getAllMessagesFromDb(List<ChatMessage> chatMessageList) {

        updateReclycerViewByList(chatMessageList);
    }

    private void updateReclycerViewByList(List<ChatMessage> chatMessageList) {
        this.chatMessageList.clear();
        this.chatMessageList.addAll(chatMessageList);
        adapter.notifyDataSetChanged();
        setUpTextUI();
        recyclerChatView.smoothScrollToPosition(chatMessageList.size());
    }

    public void sendChatMessage(View view) {
        if (chatMessageET.getText().toString().trim().length() == 0)
            return;
        progressBar.setVisibility(View.VISIBLE);
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setChatMessaqeId(System.currentTimeMillis());
        chatMessage.setMessage(chatMessageET.getText().toString().trim());
        chatMessage.setSender(true);
        updateReclycerView(chatMessage);
        chatBotPresenter.saveMessageToDb(chatMessage);
        chatBotPresenter.fetchChatResponse("Bot : " + chatMessageET.getText().toString().trim());
        chatMessageET.getText().clear();
    }

    private void updateReclycerView(ChatMessage chatMessage) {
        chatMessageList.add(chatMessage);
        adapter.notifyDataSetChanged();
        setUpTextUI();
        recyclerChatView.smoothScrollToPosition(chatMessageList.size());
    }


}
