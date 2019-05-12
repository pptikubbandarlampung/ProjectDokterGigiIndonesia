package lab_iot_ubl.aseptrisnasetiawan.appvellidcom.features.auth;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import lab_iot_ubl.aseptrisnasetiawan.appvellidcom.Models.DefaultResponse;
import lab_iot_ubl.aseptrisnasetiawan.appvellidcom.features.Menu_Dassboard;
import lab_iot_ubl.aseptrisnasetiawan.appvellidcom.network.RestService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import lab_iot_ubl.aseptrisnasetiawan.appvellidcom.Models.LoginResponse;

/**
 * Created by omgimbot on 5/7/2019.
 */

public class AuthPresenter  {

    final IAuthView view;
    private final RestService restService;
    private final String TAG = "Auth Presenter";

    AuthPresenter(IAuthView view) {
        this.view = view;
        restService = RestService.getInstance();
    }



    void login(String email, String password) {
        retrofit2.Call<LoginResponse> call = RestService
                .getInstance()
                .getNetworkService()
                .userLogin(email, password);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(retrofit2.Call<LoginResponse> call, Response<LoginResponse> response) {
//                LoginResponse loginResponse = response.body();
//                SharedPrefManager.getInstance(AuthPresenter.this,AuthActivity.class)
//                        .saveUser(loginResponse
//                                .getUser());
                          view.onSignInsucces();
            }

            @Override
            public void onFailure(retrofit2.Call<LoginResponse> call, Throwable t) {
                view.onSignInfailled();

            }

        });

    }



        public void signup(String d_username, String password, String d_ktp, String d_name, String d_ttl, String d_mail, String d_phone,
                           String d_addresshome, String d_addresswork, String d_kta, String d_str, String d_npm, String tempJenis, String temporgan,
                           String tempcabang) {
            retrofit2.Call<DefaultResponse> call = RestService
                    .getInstance()
                    .getNetworkService()
                    .createUser(d_username, password, d_ktp, d_name, d_ttl, d_mail, d_phone, d_addresshome, d_addresswork,
                            d_kta, d_str, d_npm, tempJenis, temporgan, tempcabang);
            call.enqueue(new Callback<DefaultResponse>() {
                @Override
                public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                    DefaultResponse df = response.body();
                    view.Signupberhasil();
                }

                @Override
                public void onFailure(Call<DefaultResponse> call, Throwable t) {
                    view.Signupgagal();

                }
            });
        }
}




