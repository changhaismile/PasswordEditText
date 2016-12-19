package com.hc.passwordedittext;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.hc.passwordedittext.dialog.CommonDialog;

public class MainActivity extends Activity implements CustomerKeyboard.CustomerKeyboardClickListener,
        PasswordEditText.PasswordFullListener, View.OnClickListener {

    private ImageView mImageIv;
    CustomerKeyboard mCustomerKeyboard;
    PasswordEditText mPasswordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImageIv = (ImageView) findViewById(R.id.image_iv);
        mImageIv.setOnClickListener(this);
    }

    @Override
    public void click(String number) {
        mPasswordEditText.addPassword(number);
    }

    @Override
    public void delete() {
        mPasswordEditText.deleteLastPassword();
    }

    @Override
    public void passwordFull(String password) {
        Toast.makeText(this, "密码填充完毕：" + password, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        final CommonDialog.Builder builder = new CommonDialog.Builder(this).fullWidth().fromBottom()
                .setView(R.layout.dialog_customer_keyboard);
        builder.setOnClickListener(R.id.delete_dialog, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.dismiss();
            }
        });
        builder.create().show();
        mCustomerKeyboard = builder.getView(R.id.custom_key_board);
        mCustomerKeyboard.setOnCustomerKeyboardClickListener(this);
        mPasswordEditText =  builder.getView(R.id.password_edit_text);
        mPasswordEditText.setOnPasswordFullListener(this);
    }
}
