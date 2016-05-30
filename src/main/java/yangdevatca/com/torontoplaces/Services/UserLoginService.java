package yangdevatca.com.torontoplaces.Services;

import android.util.Log;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class UserLoginService implements LoginService {
    private static final String TAG = "TOTAPP";
    private String mUserName, mPassword;
    private Observable<Boolean> mObservable;

    public UserLoginService(){
        mObservable = Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                //simulate a long running request for showing the progress bar
                // and for testing configuration change such as rotating device
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    // error
                }

                Log.d(TAG, "Observable, thread id = " + Thread.currentThread().getId());
                Log.d(TAG, "Observable, username = " + mUserName);
                Log.d(TAG, "Observable, password = " + mPassword);

                //hardcoded credentials for demo purpose.
                //could be replaced by real backend user authentication service
                if(mUserName.equals("demo") && mPassword.equals("demo")){
                    subscriber.onNext(true);
                    subscriber.onCompleted();
                }else{
                    subscriber.onNext(false);
                    subscriber.onCompleted();
                }
            }
        });
    }

    @Override
    public Observable<Boolean> validateCredentials(String userName, String password) {
        mUserName = userName;
        mPassword = password;
        return mObservable;
    }
}
