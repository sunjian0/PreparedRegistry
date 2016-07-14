package com.example.administrator.preparedregistry.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.preparedregistry.R;

/**
 * Created by IO on 2016/7/11.
 */
public class RegisterCodeDialog extends Dialog {
    public RegisterCodeDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    public static class Builder {

        private Context context;
        private String registerCode;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setRegisterCode(String registerCode) {
            this.registerCode = registerCode;
            return this;
        }

        public TimeTipsDialog create() {
            LayoutInflater layoutInflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            //dialog theme

            final TimeTipsDialog timeTipsDialog = new
                    TimeTipsDialog(context, R.style.Dialog);
            View dialoglayout = layoutInflater.inflate(R.layout.dialog_register_code, null);
            timeTipsDialog.addContentView(dialoglayout, new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

            ((TextView) dialoglayout.findViewById(R.id.tv_register_code)).setText(registerCode);

            timeTipsDialog.setContentView(dialoglayout);
            return timeTipsDialog;
        }
    }
}
