package yangdevatca.com.torontoplaces.Views;

public interface LoginView {
    public void showErrorMessage(int resId);
    public void emptyInputFields();
    public void gotoNextActivity();
    public void loginInProgress(boolean inProgress);
}
