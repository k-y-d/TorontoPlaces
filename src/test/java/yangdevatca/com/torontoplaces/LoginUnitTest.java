package yangdevatca.com.torontoplaces;

import android.util.Log;

import org.junit.Test;

import java.util.List;

import rx.BackpressureOverflow;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action1;
import rx.observers.TestSubscriber;
import rx.schedulers.Schedulers;
import yangdevatca.com.torontoplaces.Presenters.LoginPresenter;
import yangdevatca.com.torontoplaces.Presenters.UserLoginPresenter;
import yangdevatca.com.torontoplaces.Services.LoginService;
import yangdevatca.com.torontoplaces.Services.UserLoginService;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class LoginUnitTest {
    @Test
    public void validate_login_credential(){
        LoginService service = new UserLoginService();
        Observable<Boolean> observable = service.validateCredentials("", "empty");
        observable.toBlocking().subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                assertEquals("Empty Input", aBoolean, false);
            }
        });

        observable = service.validateCredentials("demo", "demo");
        observable.toBlocking().subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                assertEquals("Valid login", aBoolean, true);
            }
        });

    }

}