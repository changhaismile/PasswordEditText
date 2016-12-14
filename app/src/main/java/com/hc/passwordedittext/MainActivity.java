package com.hc.passwordedittext;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements CustomerKeyboard.CustomerKeyboardClickListener, PasswordEditText.PasswordFullListener {

    private PasswordEditText mPasswordEt;
    private CustomerKeyboard mCustomerKeyboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPasswordEt = (PasswordEditText) findViewById(R.id.password_et);
        mCustomerKeyboard = (CustomerKeyboard) findViewById(R.id.custom_keyboard);
        mCustomerKeyboard.setOnCustomerKeyboardClickListener(this);
        mPasswordEt.setOnPasswordFullListener(this);
    }

    @Override
    public void click(String number) {
        // Toast.makeText(this, number, Toast.LENGTH_SHORT).show();
        mPasswordEt.addPassword(number);
    }

    @Override
    public void delete() {
        // Toast.makeText(this, "delete", Toast.LENGTH_SHORT).show();
        mPasswordEt.deleteLastPassword();
    }

    @Override
    public void passwordFull(String password) {
        Toast.makeText(this, "密码填充完毕：" + password, Toast.LENGTH_SHORT).show();
    }
}
