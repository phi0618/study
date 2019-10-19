package com.iusername.base.base;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.iusername.base.R;
import com.iusername.base.utils.UnitUtils;

public abstract class ActivityBase extends AppCompatActivity {
    protected Context mContext;
    private Toast toast;
    private TextView toastView;

    @Override
    protected void onStart() {
        super.onStart();
        mContext = this;
    }

    public void showMessage(String message) {
        if (toast == null) {
            toast = new Toast(this);
            toastView = new TextView(this);
            toastView.setPadding(0, 2 * UnitUtils.dip2px(10), 0, UnitUtils.dip2px(10));
            toastView.setBackgroundColor(getResources().getColor(R.color.colorSuccess));
            toastView.setGravity(Gravity.CENTER);
            toastView.setTextColor(Color.WHITE);
            toast.setView(toastView);
            toast.setGravity(Gravity.FILL_HORIZONTAL | Gravity.TOP, 0, 0);
        }
        toastView.setText(message);
        toast.show();
    }
}
