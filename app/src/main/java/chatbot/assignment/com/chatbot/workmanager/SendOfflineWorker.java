package chatbot.assignment.com.chatbot.workmanager;

import android.content.Context;
import android.provider.SyncStateContract;
import android.support.annotation.NonNull;

import com.google.common.util.concurrent.ListenableFuture;


import androidx.work.ListenableWorker;
import androidx.work.WorkerParameters;
import chatbot.assignment.com.chatbot.constants.AppConstants;

public class SendOfflineWorker extends ListenableWorker {

    public SendOfflineWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @Override
    public ListenableFuture<Result> startWork() {
        Context applicationContext = getApplicationContext();
        WorkerUtils workerUtils = new WorkerUtils();
        workerUtils.initDependencies(applicationContext);
        // ADD THIS LINE
        String message = getInputData().getString(AppConstants.CHAT_MESSAGE);
                workerUtils.sendOfflineMessage(message);

            return null;
    }
}
