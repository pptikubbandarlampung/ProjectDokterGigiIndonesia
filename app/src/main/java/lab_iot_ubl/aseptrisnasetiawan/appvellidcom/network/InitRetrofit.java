package lab_iot_ubl.aseptrisnasetiawan.appvellidcom.network;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Rizal Hilman on 12/02/18.
 * www.khilman.com
 */

public class InitRetrofit {
    // URL Server API
    public static String API_URL = "http://167.205.7.18:5000/";

    public static Retrofit setInit() {
        return new Retrofit.Builder().baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static ApiService getInstance() {
        return setInit().create(ApiService.class);
    }

    public static Retrofit getRetrofit() {

        return new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
