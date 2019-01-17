package chatbot.assignment.com.chatbot.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import chatbot.assignment.com.chatbot.chat.model.ChatMessage;
import chatbot.assignment.com.chatbot.constants.AppConstants;
import chatbot.assignment.com.chatbot.db.Dao.ChatMessageDao;

@Database(entities = {ChatMessage.class}, version = AppConstants.DB_VERSION)
public abstract class ChatBotDataBase extends RoomDatabase {
    public static ChatBotDataBase chatBotDataBase;

    public abstract ChatMessageDao chatMessageDao();

    public static DbService getDataBase(Context context) {
        if (chatBotDataBase == null) {
            chatBotDataBase = Room.databaseBuilder(context.getApplicationContext(),
                    ChatBotDataBase.class, "chatbot_db")
                    .build();
        }
        return DbService.getInstance(chatBotDataBase, context);
    }
}
