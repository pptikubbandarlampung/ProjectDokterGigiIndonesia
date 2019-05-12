package lab_iot_ubl.aseptrisnasetiawan.appvellidcom.network;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestService {

    private static final String BASE_URL = "http://167.205.7.18:5000/";
    public static Retrofit getNetworkService;
    private static RestService mInstance;
    private Retrofit retrofit;
    //    http://167.205.7.18:5000/event/get_event_api
    private RestService(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized RestService getInstance(){
        if (mInstance == null ){
            mInstance = new RestService ();
        }
        return mInstance;
    }


    public NetworkService getNetworkService(){
        return retrofit.create(NetworkService.class);
    }

}
