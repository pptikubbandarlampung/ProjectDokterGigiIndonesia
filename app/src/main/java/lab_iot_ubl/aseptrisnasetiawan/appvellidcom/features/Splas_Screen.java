package lab_iot_ubl.aseptrisnasetiawan.appvellidcom.features;



import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import lab_iot_ubl.aseptrisnasetiawan.appvellidcom.R;
import lab_iot_ubl.aseptrisnasetiawan.appvellidcom.features.auth.AuthActivity;

//import com.wang.avi.AVLoadingIndicatorView;

public class Splas_Screen extends AppCompatActivity {

    Animation uptodown, downtoup;
    ImageView up;
    TextView up1;

    private static int splashInterval = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splas__screen);

        up = (ImageView) findViewById(R.id.one);
        up1 = (TextView) findViewById(R.id.ss);

        uptodown = AnimationUtils.loadAnimation(this, R.anim.to_left);
        downtoup = AnimationUtils.loadAnimation(this, R.anim.to_right);

        up.setAnimation(downtoup);
        up1.setAnimation(uptodown);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
                Intent i = new Intent(Splas_Screen.this, AuthActivity.class);
                startActivity(i);
                finish();



            }


        },3000);


    }

}