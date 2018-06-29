package com.example.stu.loginmvpdemo.login;

import android.content.Context;
import android.os.Handler;

import com.example.stu.loginmvpdemo.data.User;
import com.example.stu.loginmvpdemo.data.UserDataSource;
import com.example.stu.loginmvpdemo.data.UserRepository;



/**
 * Created by 16zhchen on 2018/6/29.
 */

public class LoginPresenter implements LoginContract.Presenter {

    private UserRepository mUserRepository;
    private LoginContract.View mLoginView;
    private Handler mHandler;
    private Context mContext;

    public LoginPresenter(Context context, UserRepository userRepository,LoginContract.View loginView){
        this.mContext = context;
        this.mUserRepository = userRepository;
        this.mLoginView = loginView;
        mHandler = new Handler();
        mLoginView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public int login() {
        mUserRepository.login(mLoginView.getUserName(), mLoginView.getPassWord(), new UserDataSource.OnLoginListener() {
            @Override
            public void loginSuccess(final User user) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mLoginView.showSuccessMsg(user.getUsername()+"登陆成功");
                    }
                });
            }

            @Override
            public void loginFailed(final String s) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mLoginView.showFailMsg(s);
                    }
                });
            }
        });
        return 0;
    }

    @Override
    public void rememberInfo() {
        mUserRepository.remberTempInfo(mLoginView.getUserName(), mLoginView.getPassWord(),mContext);
    }

    @Override
    public void clearInfo() {
        mUserRepository.clearTempInfo(mContext);
    }

    @Override
    public int checkInfo() {
        return mUserRepository.checkTempInfo(mContext);
    }

    @Override
    public void userNameClear() {
        mLoginView.clearName();
    }

    @Override
    public void passWordClear() {
        mLoginView.clearPWord();
    }

    @Override
    public void setInfo() {
        String test = mUserRepository.getTempInfo("username",mContext);
        mLoginView.setUserName(mUserRepository.getTempInfo("username",mContext));
        mLoginView.setPassWord(mUserRepository.getTempInfo("username",mContext));
    }
}
