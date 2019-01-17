package chatbot.assignment.com.chatbot.chat.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;
import chatbot.assignment.com.chatbot.R;
import chatbot.assignment.com.chatbot.chat.model.ChatMessage;

class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {

    private List<ChatMessage> chatMessage = new ArrayList<>();
    private Context context;

    public ChatAdapter(Context context, List<ChatMessage> chatMessage) {
        this.chatMessage = chatMessage;
        this.context = context;
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ChatViewHolder(LayoutInflater.from(context).inflate(R.layout.chat_item, viewGroup,
                false));
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder chatViewHolder, int i) {
        chatViewHolder.setChatData(chatMessage.get(i));
    }

    @Override
    public int getItemCount() {
        return chatMessage.size();
    }

    public class ChatViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.send_text_message)
        TextView sentMessage;
        @BindView(R.id.receive_text_message)
        TextView receivedMessage;

        public ChatViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setChatData(ChatMessage chatMessage) {
            if (chatMessage.isSender()) {
                sentMessage.setVisibility(View.VISIBLE);
                receivedMessage.setVisibility(View.GONE);
                sentMessage.setText(chatMessage.getMessage());
            } else {
                sentMessage.setVisibility(View.GONE);
                receivedMessage.setVisibility(View.VISIBLE);
                receivedMessage.setText(chatMessage.getMessage());
            }
        }
    }
}
