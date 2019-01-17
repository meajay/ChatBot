package chatbot.assignment.com.chatbot.chat.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;


/**
 * Created by Ajay on 15-01-2019.
 */
@Entity(tableName = "chat_message")
public class ChatMessage {
    @SerializedName("message")
    @ColumnInfo(name = "message")
    private String message;
    @ColumnInfo(name = "is_sender")
    private  boolean isSender;

    @PrimaryKey
    @ColumnInfo(name = "id_message")
    private  long chatMessaqeId;
    @ColumnInfo(name= "is_message_sent")
    private boolean isMessageSent;

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }


    public boolean isSender() {
        return isSender;
    }

    public void setSender(boolean sender) {
        isSender = sender;
    }

    public long getChatMessaqeId() {
        return chatMessaqeId;
    }

    public void setChatMessaqeId(long chatMessaqeId) {
        this.chatMessaqeId = chatMessaqeId;
    }

    public boolean isMessageSent() {
        return isMessageSent;
    }

    public void setMessageSent(boolean messageSent) {
        isMessageSent = messageSent;
    }
}
