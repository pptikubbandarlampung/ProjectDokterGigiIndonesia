package lab_iot_ubl.aseptrisnasetiawan.appvellidcom.network;

import com.google.gson.annotations.SerializedName;

public class ServerResponse {

    // variable name should be same as in the json response from php
    @SerializedName("error")
    boolean success;
    @SerializedName("msg")
    String message;

    public String getMessage() {
        return message;
    }

    public boolean getSuccess() {
        return success;
    }

}
