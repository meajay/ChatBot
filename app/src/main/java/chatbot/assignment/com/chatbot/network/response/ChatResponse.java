package chatbot.assignment.com.chatbot.network.response;

import com.google.gson.annotations.SerializedName;

import chatbot.assignment.com.chatbot.chat.model.ChatMessage;

/**
 * Created by Ajay on 15-01-2019.
 */
public class ChatResponse {
    @SerializedName("success")
    private int success;
    @SerializedName("errorMessage")
    private String errorMessage;
    @SerializedName("message")
    private ChatMessage message;

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public ChatMessage getMessage() {
        return message;
    }

    public void setMessage(ChatMessage message) {
        this.message = message;
    }
}
