package yangdevatca.com.torontoplaces.Presenters;

import android.util.Log;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import yangdevatca.com.torontoplaces.R;
import yangdevatca.com.torontoplaces.Services.LoginService;
import yangdevatca.com.torontoplaces.Services.UserLoginService;
import yangdevatca.com.torontoplaces.Views.LoginView;

public class UserLoginPresenter implements LoginPresenter {
    private static UserLoginPresenter instance;
    private static final String TAG = "TOTAPP";
    private LoginView mLoginView;
    private LoginService mLoginService;
    private Subscriber<Boolean> mSubscriber;
    private Subscription mSubscription;
    private boolean mInProcess = false;

    private UserLoginPresenter(){
    }

    public static UserLoginPresenter getInstance(){
        if(instance == null)
        {
            instance = new UserLoginPresenter();
        }
        return instance;
    }

    @Override
    public void onDestroy(){
        if(mSubscription != null && !mSubscription.isUnsubscribed()){
            mSubscription.unsubscribe();
        }

        instance = null;
    }

    @Override
    public void setLoginView(LoginView view){
        mLoginView = view;
    }

    @Override
    public void validateCredentials(String userName, String password) {
        userName = userName.trim();
        password = password.trim();

        if(userName.isEmpty() || password.isEmpty()){
            mLoginView.showErrorMessage(R.string.invalid_inputs);
            return;
        }

        if(mLoginService == null){
            mLoginService = new UserLoginService();
        }

        mSubscriber = new Subscriber<Boolean>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "Subscriber, onCompleted");
                mInProcess = false;
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onNext(Boolean aBoolean) {
                Log.d(TAG, "Subscriber, thread id = " + Thread.currentThread().getId());
                if(aBoolean){
                    mLoginView.gotoNextActivity();
                }else {
                    mLoginView.showErrorMessage(R.string.login_failed);
                    mLoginView.emptyInputFields();
                    mLoginView.loginInProgress(false);
                }

            }
        };

        mInProcess = true;
        mLoginView.loginInProgress(true);
        mLoginView.showErrorMessage(-1);  // empty error message
        mSubscription = mLoginService.validateCredentials(userName, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mSubscriber);
    }

    @Override
    public boolean isInProgress(){
        return mInProcess;
    }
}
