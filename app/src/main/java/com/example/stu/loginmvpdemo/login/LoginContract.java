package com.example.stu.loginmvpdemo.login;

import android.widget.EditText;

import com.example.stu.loginmvpdemo.BasePresenter;
import com.example.stu.loginmvpdemo.BaseView;

/**
 * Created by 16zhchen on 2018/6/26.
 */

public interface LoginContract {
    interface Presenter extends BasePresenter{
        int login();

        void rememberInfo();
        void clearInfo();
        int checkInfo();
        void setInfo();

        void userNameClear();
        void passWordClear();
    }
    interface View extends BaseView<Presenter>{
        String getUserName();
        void setUserName(String s);
        String getPassWord();
        void setPassWord(String s);
        void showSuccessMsg(String Msg);
        void showFailMsg(String Msg);
        void clearName();
        void clearPWord();
    }
}
