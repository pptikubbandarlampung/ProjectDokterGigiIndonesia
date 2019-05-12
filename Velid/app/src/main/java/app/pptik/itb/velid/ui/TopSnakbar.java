package app.pptik.itb.velid.ui;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;
import com.androidadvance.topsnackbar.TSnackbar;

import app.pptik.itb.velid.R;


/**
 * Created by hynra [github.com/hynra] on 09/02/2018.
 */

public class TopSnakbar {
    public static void showWarning(Activity activity, String message){
        TSnackbar snackbar = TSnackbar.make(activity.findViewById(android.R.id.content), message, TSnackbar.LENGTH_LONG);
        snackbar.setActionTextColor(Color.WHITE);
        View snackbarView = snackbar.getView();

        snackbarView.setBackgroundColor(activity.getResources().getColor(
                R.color.redCrimson));
        TextView textView = snackbarView.findViewById(com.androidadvance.topsnackbar.R.id.snackbar_text);
        textView.setTextColor(activity.getResources().getColor(R.color.white));
        snackbar.show();
    }

    public static void showSuccess(Activity activity, String message){
        TSnackbar snackbar = TSnackbar.make(activity.findViewById(android.R.id.content), message, TSnackbar.LENGTH_LONG);
        snackbar.setActionTextColor(Color.WHITE);
        View snackbarView = snackbar.getView();

        snackbarView.setBackgroundColor(activity.getResources().getColor(R.color.material_green_800));
        TextView textView = snackbarView.findViewById(com.androidadvance.topsnackbar.R.id.snackbar_text);
        textView.setTextColor(activity.getResources().getColor(R.color.white));
        snackbar.show();
    }

}
