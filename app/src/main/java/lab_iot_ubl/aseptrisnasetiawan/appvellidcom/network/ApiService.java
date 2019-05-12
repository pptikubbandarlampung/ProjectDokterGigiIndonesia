package lab_iot_ubl.aseptrisnasetiawan.appvellidcom.network;

import lab_iot_ubl.aseptrisnasetiawan.appvellidcom.Models.ResponseEvent;
import lab_iot_ubl.aseptrisnasetiawan.appvellidcom.Models.ResponseEvent;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    //@TIPEMETHOD("API_END_POINT")
    @GET("event/get_event_api")
    Call<ResponseEvent> request_show_all_berita();
    // <ModelData> nama_method()



}
