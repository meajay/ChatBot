package chatbot.assignment.com.chatbot.chat.model;

import com.google.gson.annotations.SerializedName;

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
}
