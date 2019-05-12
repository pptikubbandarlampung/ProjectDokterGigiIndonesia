package lab_iot_ubl.aseptrisnasetiawan.appvellidcom.Models;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseEvent{

    @SerializedName("data")
    private List<EventItem> berita;

    @SerializedName("error")
    private boolean status;

    public void setBerita(List<EventItem> berita){
        this.berita = berita;
    }

    public List<EventItem> getBerita(){
        return berita;
    }

    public void setStatus(boolean status){
        this.status = status;
    }

    public boolean isStatus(){
        return status;
    }

    @Override
    public String toString(){
        return
                "ResponseEvent{" +
                        "data = '" + berita + '\'' +
                        ",error = '" + status + '\'' +
                        "}";
    }
}