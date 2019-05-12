package lab_iot_ubl.aseptrisnasetiawan.appvellidcom.features;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import lab_iot_ubl.aseptrisnasetiawan.appvellidcom.R;
import lab_iot_ubl.aseptrisnasetiawan.appvellidcom.features.auth.AuthActivity;
import lab_iot_ubl.aseptrisnasetiawan.appvellidcom.network.ApiConfig;
import lab_iot_ubl.aseptrisnasetiawan.appvellidcom.network.ServerResponse;
import lab_iot_ubl.aseptrisnasetiawan.appvellidcom.network.InitRetrofit;

import java.io.File;

import lab_iot_ubl.aseptrisnasetiawan.appvellidcom.ui.SweetDialogs;
import lab_iot_ubl.aseptrisnasetiawan.appvellidcom.ui.TopSnakbar;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterGambarDokter extends AppCompatActivity {
    Intent intent;
    Uri fileUri;
    Button btn_choose_image;
    ImageView imageView;
    Bitmap bitmap, decoded;
    public final int REQUEST_CAMERA = 0;
    public final int SELECT_FILE = 1;

    int bitmap_size = 40; // image quality 1 - 100;
    int max_resolution_image = 800;

    EditText edUserName;
    Button uploadKtp, uploadKtm, uploadStr, uploadKta, uploadData,uploadstr;
    TextView txtKtp, txtKta, txtstr;

    String username;
    String profesi;

    String mediaPathKtp,mediaPathStr, mediaPathKta;

    String[] mediaColumns = {MediaStore.Video.Media._ID};
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_gambar_dokter);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Uploading...");

        edUserName  = findViewById(R.id.usernamedokter);
        uploadstr   = findViewById(R.id.uploadstrdokter);
        uploadKtp   = findViewById(R.id.uploadktpdokter);
        uploadKta   = findViewById(R.id.uploadKtadokter);

        uploadData  = findViewById(R.id.uploadImgdokter);

        txtKta      = findViewById(R.id.txtktadokter);
        txtKtp      = findViewById(R.id.txtktpdokter);
        txtstr      = findViewById(R.id.txtstrdokter);

        Intent a    = getIntent();
        username    = a.getStringExtra("username");
        profesi     = a.getStringExtra("profesi");
        edUserName.setText(username);

        edUserName.setEnabled(false);




        uploadKta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, 1);
            }
        });
        uploadKtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, 0);


            }
        });


        uploadstr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, 2);
            }
        });

        uploadData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtKta.getText().toString().equals("")
                        || txtKtp.getText().toString().equals("")|| txtstr.getText().toString().equals("")) {
                    TopSnakbar.showWarning(RegisterGambarDokter.this, "Kolom tidak boleh kosong,ulangi");
                } else

                progressDialog = ProgressDialog.show(RegisterGambarDokter.this,"Loading.....",null,true,true);
                uploadMultipleFiles();
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            // When an Image is picked
            if (requestCode == 0 && resultCode == RESULT_OK && null != data) {

                // Get the Image from data
                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};

                Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                assert cursor != null;
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                mediaPathKtp = cursor.getString(columnIndex);
                txtKtp.setText(mediaPathKtp);
                // Set the Image in ImageView for Previewing the Media
                //imgKtp.setImageBitmap(BitmapFactory.decodeFile(mediaPathKtp));
                cursor.close();

            } else if (requestCode == 1 && resultCode == RESULT_OK && null != data) {

                // Get the Video from data
                Uri selectedVideo = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};

                Cursor cursor = getContentResolver().query(selectedVideo, filePathColumn, null, null, null);
                assert cursor != null;
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);

                mediaPathKta = cursor.getString(columnIndex);
                txtKta.setText(mediaPathKta);
                // Set the Video Thumb in ImageView Previewing the Media
                //imgKtm.setImageBitmap(BitmapFactory.decodeFile(mediaPathKtm));
                cursor.close();
            }else if (requestCode == 2 && resultCode == RESULT_OK && null != data) {

                // Get the Video from data
                Uri selectedVideo = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};

                Cursor cursor = getContentResolver().query(selectedVideo, filePathColumn, null, null, null);
                assert cursor != null;
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);

                mediaPathStr = cursor.getString(columnIndex);
                txtstr.setText(mediaPathStr);
                // Set the Video Thumb in ImageView Previewing the Media
                //imgKtm.setImageBitmap(BitmapFactory.decodeFile(mediaPathKtm));
                cursor.close();


            }else {
                Toast.makeText(this, "You haven't picked Image/Video", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
        }
    }

    // Uploading Image/Video
    private void uploadMultipleFiles() {
        progressDialog.show();

        // Map is used to multipart the file using okhttp3.RequestBody
        File ktp = new File(mediaPathKtp);
        File kta = new File(mediaPathKta);
        File str = new File(mediaPathStr);

        // Parsing any Media type file
        RequestBody requestBody1 = RequestBody.create(MediaType.parse("image/jpeg"), ktp);
        RequestBody requestBody4 = RequestBody.create(MediaType.parse("image/jpeg"), str);
        RequestBody requestBody5 = RequestBody.create(MediaType.parse("image/jpeg"), kta);

        MultipartBody.Part fileToUpload1 = MultipartBody.Part.createFormData("file", ktp.getName(), requestBody1);
        MultipartBody.Part fileToUpload4 = MultipartBody.Part.createFormData("file", str.getName(), requestBody4);
        MultipartBody.Part fileToUpload5 = MultipartBody.Part.createFormData("file", kta.getName(), requestBody4);

        RequestBody profesis = RequestBody.create(MultipartBody.FORM, profesi);
        RequestBody user = RequestBody.create(MultipartBody.FORM, username);






        ApiConfig getResponse = InitRetrofit.getRetrofit().create(ApiConfig.class);
        Call<ServerResponse> call = getResponse.uploadMulFile(fileToUpload1, fileToUpload4,fileToUpload5, profesis, user);
        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                ServerResponse serverResponse = response.body();
                if (serverResponse != null) {
                    if (serverResponse.getSuccess() == false) {
                        progressDialog.dismiss();
//                        SweetDialogs.commonSuccess(RegisterGambarDokter.this, response.message());


                        Toast.makeText(getApplicationContext(), serverResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        Intent a = new Intent(RegisterGambarDokter.this, AuthActivity.class);
                        startActivity(a);
                        finish();
                        Toast.makeText(getApplicationContext(), serverResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), serverResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    assert serverResponse != null;
                    Log.v("Response", serverResponse.toString());
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(RegisterGambarDokter.this, "Register Gagal", Toast.LENGTH_SHORT).show();

            }
        });
    }


}
