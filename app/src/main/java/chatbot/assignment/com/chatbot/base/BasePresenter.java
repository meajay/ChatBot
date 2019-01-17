package chatbot.assignment.com.chatbot.base;

/**
 * Created by Ajay on 15-01-2019.
 */
public class BasePresenter<V extends BaseViewContract> implements BasePresenterContract<V> {
    protected final String TAG = getClass().getSimpleName();
    protected V view;

    @Override
    public void onAttach(V view) {
        this.view = view;
    }

    @Override
    public void onDetach() {
        this.view = null;
    }

    @Override
    public boolean isViewAttached() {
        return view != null;
    }

    @Override
    public V getView() {
        return view;
    }
}
