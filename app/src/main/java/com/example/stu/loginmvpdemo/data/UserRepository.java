package com.example.stu.loginmvpdemo.data;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class UserRepository implements UserDataSource {
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private User user;

    @Override
    public void login(final String username, final String password, final OnLoginListener onLoginListener) {
        new Thread(){
            @Override
            public void run() {
                super.run();
                try{
                    Thread.sleep(1000);
                    if(username.equals("testname") && password.equals("123456")){
                        user =  new User(username,password);
                        onLoginListener.loginSuccess(user);
                    }else{
                        onLoginListener.loginFailed("用户名或密码错误");
                    }
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @Override
    public void remberTempInfo(String username, String password, Context context) {
        sp = context.getSharedPreferences("info",Context.MODE_PRIVATE);
        editor = sp.edit();
        editor.putString("username",username);
        editor.putString("password",password);
        editor.putInt("isRemember",1);
        editor.commit();
    }

    @Override
    public void clearTempInfo(Context context) {
        sp = context.getSharedPreferences("info",Context.MODE_PRIVATE);
        editor = sp.edit();
        editor.clear();
        editor.putInt("isRemember",0);
        editor.commit();
    }

    @Override
    public int checkTempInfo(Context context) {
        sp = context.getSharedPreferences("info",Context.MODE_PRIVATE);
        int test = sp.getInt("isRem",-1);
        return sp.getInt("isRem",-1);
    }

    @Override
    public String getTempInfo(String key,Context context) {
        sp = context.getSharedPreferences("info",Context.MODE_PRIVATE);
        return sp.getString(key,"");
    }
}
