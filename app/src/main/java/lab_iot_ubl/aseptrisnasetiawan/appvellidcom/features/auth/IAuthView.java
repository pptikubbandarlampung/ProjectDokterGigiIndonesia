package lab_iot_ubl.aseptrisnasetiawan.appvellidcom.features.auth;

import android.view.View;

/**
 * Created by omgimbot on 5/7/2019.
 */

public interface IAuthView {


    void onClick(View v);

    void register();

    void signin();


    void onSignInsucces();

    void onSignInfailled();

    void Signupberhasil();

    void Signupgagal();


}
