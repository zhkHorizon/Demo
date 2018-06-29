package com.example.stu.loginmvpdemo.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.stu.loginmvpdemo.R;


public class LoginFragment extends Fragment implements LoginContract.View, View.OnClickListener,CompoundButton.OnCheckedChangeListener {

    private EditText muserNameEdit;
    private EditText mpassWordEdit;
    private ImageView muserNameClear;
    private ImageView mpassWordClear;
    private CheckBox mremenberPass;
    private Button mloginButton;
    private LoginContract.Presenter mPresenter;

    public LoginFragment(){
        // Requires empty public constructor
    }

    public static LoginFragment newInstance(){
        return new LoginFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_login,container,false);

        //set up view
        muserNameEdit = (EditText) root.findViewById(R.id.lg_userNameEdit);
        mpassWordEdit = (EditText) root.findViewById(R.id.lg_passWordEdit);
        muserNameClear = (ImageView) root.findViewById(R.id.lg_userNameClear);
        mpassWordClear = (ImageView)root.findViewById(R.id.lg_passWordClear);
        mremenberPass = (CheckBox) root.findViewById(R.id.lg_remember);
        mloginButton = (Button)root.findViewById(R.id.lg_loginButt);

        mpassWordClear.setOnClickListener(this);
        muserNameClear.setOnClickListener(this);
        mloginButton.setOnClickListener(this);
        mremenberPass.setOnCheckedChangeListener(this);
        if(mPresenter.checkInfo()==1){
            mPresenter.setInfo();
            mremenberPass.setChecked(true);
       }


        return root;
    }

    @Override
    public String getUserName() {
        return this.muserNameEdit.getText().toString();
    }

    @Override
    public String getPassWord() {
        return this.mpassWordEdit.getText().toString();
    }

    @Override
    public void setUserName(String s) {
        this.muserNameEdit.setText(s);
    }

    @Override
    public void setPassWord(String s) {
        this.mpassWordEdit.setText(s);
    }

    @Override
    public void showSuccessMsg(String Msg) {
        Toast.makeText(this.getActivity(),"登陆成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFailMsg(String Msg) {
        Toast.makeText(this.getActivity(),"登陆失败",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void clearName() {
        muserNameEdit.setText("");
    }

    @Override
    public void clearPWord() {
        mpassWordEdit.setText("");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.lg_loginButt:
                mPresenter.login();
                break;
            case R.id.lg_userNameClear:
                mPresenter.userNameClear();
                break;
            case R.id.lg_passWordClear:
                mPresenter.passWordClear();
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked){
            mPresenter.rememberInfo();
        }else{
            mPresenter.clearInfo();
        }
    }
}
