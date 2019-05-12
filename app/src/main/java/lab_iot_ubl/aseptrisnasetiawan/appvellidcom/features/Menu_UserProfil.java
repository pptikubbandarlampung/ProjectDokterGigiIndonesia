package lab_iot_ubl.aseptrisnasetiawan.appvellidcom.features;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import lab_iot_ubl.aseptrisnasetiawan.appvellidcom.Models.User;
import lab_iot_ubl.aseptrisnasetiawan.appvellidcom.R;
import lab_iot_ubl.aseptrisnasetiawan.appvellidcom.features.auth.SharedPrefManager;

public class Menu_UserProfil extends AppCompatActivity {
    TextView nama,alamat,username,telpon,organisasi,email,prof;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu__user_profil);

        nama=findViewById(R.id.nama_lengkap);
        prof=findViewById(R.id.profesi);
        username=findViewById(R.id.user);
        telpon=findViewById(R.id.no_tlp);

        User user= SharedPrefManager.getInstance(this).getUser();
        username.setText(user.getUsername());
        nama.setText(user.getNama_lengkap());
        prof.setText(user.getProfesi());
        telpon.setText(user.getNo_telp());







    }
}
