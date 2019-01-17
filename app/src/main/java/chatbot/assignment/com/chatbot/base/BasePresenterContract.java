package chatbot.assignment.com.chatbot.base;

/**
 * Created by Ajay on 15-01-2019.
 */
public interface BasePresenterContract<V extends BaseViewContract> {

    void onAttach(V view);

    void onDetach();

    boolean isViewAttached();

    V getView();
}
