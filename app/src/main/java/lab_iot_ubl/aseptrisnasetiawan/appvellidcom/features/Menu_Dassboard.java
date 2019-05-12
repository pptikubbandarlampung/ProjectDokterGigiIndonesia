package lab_iot_ubl.aseptrisnasetiawan.appvellidcom.features;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;


import lab_iot_ubl.aseptrisnasetiawan.appvellidcom.R;
import lab_iot_ubl.aseptrisnasetiawan.appvellidcom.features.AdapterEvent;
import lab_iot_ubl.aseptrisnasetiawan.appvellidcom.features.auth.AuthActivity;
import lab_iot_ubl.aseptrisnasetiawan.appvellidcom.features.auth.SharedPrefManager;
import lab_iot_ubl.aseptrisnasetiawan.appvellidcom.network.ApiService;
import lab_iot_ubl.aseptrisnasetiawan.appvellidcom.network.InitRetrofit;
import lab_iot_ubl.aseptrisnasetiawan.appvellidcom.Models.EventItem;
import lab_iot_ubl.aseptrisnasetiawan.appvellidcom.Models.ResponseEvent;

import lab_iot_ubl.aseptrisnasetiawan.appvellidcom.Models.User;

import java.util.List;

import lab_iot_ubl.aseptrisnasetiawan.appvellidcom.ui.TopSnakbar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//import org.json.JSONObject;


public class Menu_Dassboard extends AppCompatActivity {
//    RMQ rmq = new RMQ();
    ImageView profil;

    private RecyclerView recyclerView;

    LinearLayout one, two;
    Animation uptodown, downtoup;
    private CoordinatorLayout coordinatorLayout;

    ImageButton notif,logout;
    boolean doubleBackToExitPressedOnce = false;
    //@BindView(R.id.progress_bar) ProgressBar progressBar;
    Thread subscribeThread ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu__dassboard);
//       subscribeThread = new Thread();


        User user= SharedPrefManager.getInstance(this).getUser();
//        TopSnakbar.showSuccess(Menu_Dassboard.this, "Login sukses");
        Toast.makeText(Menu_Dassboard.this,user.getUsername(),Toast.LENGTH_LONG).show();
//        Toast.makeText(MainActivity.this,user.getK()+user.getNama_lengkap(),Toast.LENGTH_LONG).show();
        coordinatorLayout = findViewById(R.id.MyMain);
        one = (LinearLayout) findViewById(R.id.one);
        two = (LinearLayout) findViewById(R.id.two);
        uptodown = AnimationUtils.loadAnimation(this,R.anim.uptodown);
        downtoup = AnimationUtils.loadAnimation(this,R.anim.downtoup);
        one.setAnimation(uptodown);
        two.setAnimation(downtoup);
        profil=(ImageView)findViewById(R.id.profil);

// Inisialisasi Widget
        recyclerView = (RecyclerView) findViewById(R.id.rvListBerita);
        // RecyclerView harus pakai Layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // Eksekusi method
        tampilBerita();
        profil.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                 //pindah intent menggunakan btnJava yang diinisialiasi di atas
                Intent intent = new Intent(Menu_Dassboard.this,
                        Menu_UserProfil.class);
                startActivity(intent);
            }
        });

//

//        notif = findViewById(R.id.notifyBtn);
//        notif.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                toNotif();
//            }
//        });


        logout=findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                keluar();
            }
        });
//
//        rmq.setupConnectionFactory();
//        subscribeNotification();
//
//
//        rmq.setupConnectionFactory();
//        subscribeNotification();
    }


//    @Override
//    public boolean onCreateOptionsMenu(android.view.Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_search, menu);
//        final MenuItem item = menu.findItem(R.id.action_search);
//        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
//        searchView.setQueryHint("Cari Event Berdasarkan kota");
//        searchView.setIconified(false);
//        searchView.setOnQueryTextListener((SearchView.OnQueryTextListener) this);
//        return true;
//    }

    private void keluar(){
        SharedPrefManager.getInstance(Menu_Dassboard.this).clear();
        Intent intent = new Intent(Menu_Dassboard.this,AuthActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }


//    public void toNotif(){
//        Intent sign = new Intent(this, Notify.class);
//        startActivity(sign);
//        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
//        finish();
//    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce){
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Tekan sekali lagi untuk keluar", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
                System.exit(0);
            }
        },2000);
    };

    private void tampilBerita() {
        ApiService api = InitRetrofit.getInstance();
        // Siapkan request
        Call<ResponseEvent> beritaCall = api.request_show_all_berita();
        // Kirim request
        beritaCall.enqueue(new Callback<ResponseEvent>() {
            @Override
            public void onResponse(Call<ResponseEvent> call, Response<ResponseEvent> response) {
                // Pasikan response Sukses
                if (response.isSuccessful()){
                    Log.d("response api", response.body().toString());
                    // tampung data response body ke variable
                    List<EventItem> data_berita = response.body().getBerita();
                    boolean status = response.body().isStatus();
                    // Kalau response status nya = true
//                    if (status){
//                       // Buat Adapter untuk recycler view
                    AdapterEvent adapter= new AdapterEvent(Menu_Dassboard.this, data_berita);
                    recyclerView.setAdapter(adapter);
//                    } else {
//                     kalau tidak true
//                    AdapterBerita gg = new AdapterBerita;
//                    Toast.makeText(MainActivity.this,AdapterBerita.urlG (),Toast.LENGTH_LONG).show();
//                        Toast.makeText(MainActivity.this, "Tidak ada berita untuk saat ini", Toast.LENGTH_SHORT).show();
//                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseEvent> call, Throwable t) {
                // print ke log jika Error
                t.printStackTrace();
            }
        });


    }



//    private void subscribeNotification() {
//        final Handler incomingMessageHandler = new Handler() {
//            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
//            @Override
//            public void handleMessage(Message msg) {
//                String title = "Seminar Baru";
//                String message = msg.getData().getString("msg");
//                Log.d("RMQMessage", message);
//
//                showNotification(title, message);
////                Toast.makeText(MainActivity.this,title,Toast.LENGTH_LONG).show();
//            }
//        };
//
//        //ini gua coba iseng kak
//        rmq.subscribe(incomingMessageHandler,subscribeThread);
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
//    public void showNotification(String title, String body) {
//        Context context = getApplicationContext();
//        Intent intent = getIntent();
//
//        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//        int notificationId = 1;
//        String channelId = "channel-01";
//        String channelName = "Channel Name";
//        int importance = NotificationManager.IMPORTANCE_HIGH;
//
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//            NotificationChannel mChannel = new NotificationChannel(
//                    channelId, channelName, importance);
//            notificationManager.createNotificationChannel(mChannel);
//        }
//
//        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, channelId)
//                .setSmallIcon(R.drawable.notify_ic)
//                .setContentTitle(title)
//                .setContentText(body);
//
//        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
//        stackBuilder.addNextIntent(intent);
//        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(
//                0,
//                PendingIntent.FLAG_UPDATE_CURRENT
//        );
//        mBuilder.setContentIntent(resultPendingIntent);
//
//        notificationManager.notify(notificationId, mBuilder.build());
//    }
}
