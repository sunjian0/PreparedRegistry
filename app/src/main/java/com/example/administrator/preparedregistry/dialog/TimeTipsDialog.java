package com.example.administrator.preparedregistry.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.preparedregistry.R;

/**
 * Created by moonny on 16-7-11.
 */
public class TimeTipsDialog extends Dialog {
    public TimeTipsDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    public static class Builder {

        private Context context;
        private String dialogmessage;


        private String beginningTime;
        private String endingTime;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setDialogmessage(String dialogmessage) {
            this.dialogmessage = dialogmessage;
            return this;
        }

        public Builder setEndingTime(String endingTime) {
            this.endingTime = endingTime;
            return this;
        }

        public Builder setBeginningTime(String beginningTime) {
            this.beginningTime = beginningTime;
            return this;
        }

        public TimeTipsDialog create() {
            LayoutInflater layoutInflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            //dialog theme

            final TimeTipsDialog timeTipsDialog = new
                    TimeTipsDialog(context, R.style.Dialog);
            View dialoglayout = layoutInflater.inflate(R.layout.dialog_time_tips, null);
            timeTipsDialog.addContentView(dialoglayout, new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

            //set begin and end time
            ((TextView) dialoglayout.findViewById(R.id.tv_dialog)).setText(dialogmessage);
            ((TextView) dialoglayout.findViewById(R.id.tv_begin_time)).setText(beginningTime);
            ((TextView) dialoglayout.findViewById(R.id.tv_end_time)).setText(endingTime);

            timeTipsDialog.setContentView(dialoglayout);
            return timeTipsDialog;
        }
    }

}
