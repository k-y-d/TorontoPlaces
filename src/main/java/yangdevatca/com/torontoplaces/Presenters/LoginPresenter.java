package yangdevatca.com.torontoplaces.Presenters;

import rx.Observable;
import yangdevatca.com.torontoplaces.Views.LoginView;

public interface LoginPresenter {
    public void setLoginView(LoginView view);
    public void validateCredentials(String userName, String password);
    public boolean isInProgress();
    public void onDestroy();
}
