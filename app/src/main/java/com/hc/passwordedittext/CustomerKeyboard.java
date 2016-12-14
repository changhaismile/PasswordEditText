package com.hc.passwordedittext;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Darren on 2016/12/14.
 * Email: 240336124@qq.com
 * Description:
 */

public class CustomerKeyboard extends LinearLayout implements View.OnClickListener {
    private CustomerKeyboardClickListener mListener;

    public CustomerKeyboard(Context context) {
        this(context, null);
    }

    public CustomerKeyboard(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomerKeyboard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, R.layout.ui_customer_keyboard, this);
        setChildViewOnclick(this);
    }

    /**
     * 设置键盘子View的点击事件
     */
    private void setChildViewOnclick(ViewGroup parent) {
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = parent.getChildAt(i);
            if (view instanceof ViewGroup) {
                setChildViewOnclick((ViewGroup) view);
                continue;
            }
            view.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        View clickView = v;
        if (clickView instanceof TextView) {
            String number = ((TextView) clickView).getText().toString();
            if (!TextUtils.isEmpty(number)) {
                if (mListener != null) {
                    mListener.click(number);
                }
            }
        } else if (clickView instanceof ImageView) {
            if (mListener != null) {
                mListener.delete();
            }
        }
    }

    public void setOnCustomerKeyboardClickListener(CustomerKeyboardClickListener listener) {
        this.mListener = listener;
    }

    public interface CustomerKeyboardClickListener {
        public void click(String number);
        public void delete();
    }
}
