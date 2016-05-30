package yangdevatca.com.torontoplaces;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import yangdevatca.com.torontoplaces.Presenters.LoginPresenter;
import yangdevatca.com.torontoplaces.Presenters.UserLoginPresenter;
import yangdevatca.com.torontoplaces.Views.LoginView;

public class LoginActivity extends AppCompatActivity implements LoginView{
    private static final String STATE_ERROR_MSG = "ERROR_MSG";
    private static final String TAG = "TOTAPP";
    private EditText editTextUserName;
    private EditText editTextPassword;
    private TextView textViewMessage;
    private Button buttonLogin;
    private LoginPresenter loginPresenter;
    private ProgressBar progressBar;
    private boolean isConfigurationChange = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextUserName = (EditText) findViewById(R.id.editTextName);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        textViewMessage = (TextView) findViewById(R.id.loginErrorMessage);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        loginPresenter = UserLoginPresenter.getInstance();
        loginPresenter.setLoginView(this);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginPresenter.validateCredentials(editTextUserName.getText().toString(), editTextPassword.getText().toString());
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        isConfigurationChange = true;
        outState.putString(STATE_ERROR_MSG, textViewMessage.getText().toString());
        super.onSaveInstanceState(outState);
        Log.d(TAG, "LoginActivity, onSaveInstanceState, msg=" + textViewMessage.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        isConfigurationChange = false;
        super.onRestoreInstanceState(savedInstanceState);
        textViewMessage.setText(savedInstanceState.getString(STATE_ERROR_MSG));
        loginInProgress(loginPresenter.isInProgress());
        Log.d(TAG, "LoginActivity, onRestoreInstanceState, msg=" + savedInstanceState.getString(STATE_ERROR_MSG));
    }

    @Override
    protected void onDestroy() {
        if(!isConfigurationChange) {
            loginPresenter.onDestroy();
        }
        super.onDestroy();
    }

    /**
     * the methods implementing interface LoginView
     */
    @Override
    public void showErrorMessage(int resId) {
        if(resId == -1){
            textViewMessage.setText("");
        }else {
            textViewMessage.setText(resId);
        }
    }

    @Override
    public void emptyInputFields() {
        editTextUserName.setText("");
        editTextPassword.setText("");
    }

    @Override
    public void gotoNextActivity() {
        startActivity(new Intent(this, PlaceListActivity.class));
        finish();
    }

    @Override
    public void loginInProgress(boolean inProgress) {
        buttonLogin.setVisibility(inProgress ? View.GONE : View.VISIBLE);
        progressBar.setVisibility(inProgress ? View.VISIBLE : View.GONE);
    }
}
