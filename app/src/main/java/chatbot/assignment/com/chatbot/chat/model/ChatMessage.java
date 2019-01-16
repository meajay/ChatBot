package chatbot.assignment.com.chatbot.chat.model;

import com.google.gson.annotations.SerializedName;

import nl.qbusict.cupboard.annotation.Ignore;

/**
 * Created by Ajay on 15-01-2019.
 */
public class ChatMessage {
    @SerializedName("chatBotName")
    private String chatBotName;
    @SerializedName("chatBotID")
    private int chatBotID;
    @SerializedName("message")
    private String message;
    @SerializedName("emotion")
    private String emotion;
    private transient boolean isSender;
    private transient long chatMessaqeId;

    public String getChatBotName() {
        return chatBotName;
    }

    public void setChatBotName(String chatBotName) {
        this.chatBotName = chatBotName;
    }

    public int getChatBotID() {
        return chatBotID;
    }

    public void setChatBotID(int chatBotID) {
        this.chatBotID = chatBotID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getEmotion() {
        return emotion;
    }

    public void setEmotion(String emotion) {
        this.emotion = emotion;
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
}
