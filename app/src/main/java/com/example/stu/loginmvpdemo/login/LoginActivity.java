package com.example.stu.loginmvpdemo.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.stu.loginmvpdemo.R;
import com.example.stu.loginmvpdemo.data.UserRepository;
import com.example.stu.loginmvpdemo.util.ActivityUtils;

public class LoginActivity extends AppCompatActivity {
    private LoginPresenter mLoginPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        LoginFragment loginFragment =
                (LoginFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if(loginFragment == null){
            loginFragment = LoginFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),loginFragment,R.id.contentFrame);
        }

        mLoginPresenter = new LoginPresenter(getApplicationContext(),new UserRepository(),loginFragment);
    }
}
