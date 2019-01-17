package chatbot.assignment.com.chatbot.db.Dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import chatbot.assignment.com.chatbot.chat.model.ChatMessage;
import io.reactivex.Single;

@Dao
public interface ChatMessageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ChatMessage chatMessage);

    @Query("SELECT * FROM chat_message")
    Single<List<ChatMessage>> findMessages();
}
