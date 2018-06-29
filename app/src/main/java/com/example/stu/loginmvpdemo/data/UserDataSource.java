package com.example.stu.loginmvpdemo.data;

import android.content.Context;

/**
 * Created by 16zhchen on 2018/6/26.
 */

public interface UserDataSource {
    void login(String username, String password,OnLoginListener onLoginListener);

    void remberTempInfo(String username, String password,Context context);
    void clearTempInfo(Context context);
    int checkTempInfo(Context context);
    String getTempInfo(String key,Context context);

    interface OnLoginListener{
        void loginSuccess(User user);
        void loginFailed(String s);
    }
}
