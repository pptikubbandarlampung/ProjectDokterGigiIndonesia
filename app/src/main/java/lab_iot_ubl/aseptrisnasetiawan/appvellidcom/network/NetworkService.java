package lab_iot_ubl.aseptrisnasetiawan.appvellidcom.network;


import java.util.List;
import java.util.Map;

import lab_iot_ubl.aseptrisnasetiawan.appvellidcom.Models.DefaultResponse;
import lab_iot_ubl.aseptrisnasetiawan.appvellidcom.Models.LoginResponse;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;

/**
 * Created by github.com/adip28 on 7/17/2018.
 */
public interface NetworkService {

    @FormUrlEncoded
    @POST("users/register_api")
    Call<DefaultResponse> createUser(
            @Field("username") String d_username,
            @Field("password") String d_pass,
            @Field("ktp") String d_ktp,
            @Field("nama_lengkap") String d_name,
            @Field("alamat_rumah") String d_addresshome,
            @Field("alamat_kerja") String d_addresswork,
            @Field("tempatlahir") String d_ttl,
            @Field("email") String d_mail,
            @Field("telp") String d_phone,
            @Field("no_kta") String no_kta,
            @Field("no_str") String d_str,
            @Field("no_npm") String d_npm,
            @Field("profesi") String tempjenis,
            @Field("organisasi") String temporgan,
            @Field("cabang") String tempcabang
    );

    @FormUrlEncoded
    @POST("users/api_login")
    Call<LoginResponse> userLogin(
            @Field("username") String email,
            @Field("password") String password
    );

}