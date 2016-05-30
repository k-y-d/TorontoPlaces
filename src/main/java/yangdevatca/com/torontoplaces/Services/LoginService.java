package yangdevatca.com.torontoplaces.Services;

import rx.Observable;
import rx.Subscriber;

public interface LoginService {
    public Observable<Boolean> validateCredentials(String userName, String password);
}
