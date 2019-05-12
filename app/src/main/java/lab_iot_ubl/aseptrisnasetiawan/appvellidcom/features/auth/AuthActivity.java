package lab_iot_ubl.aseptrisnasetiawan.appvellidcom.features.auth;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import lab_iot_ubl.aseptrisnasetiawan.appvellidcom.Models.LoginResponse;
import lab_iot_ubl.aseptrisnasetiawan.appvellidcom.features.RegisterGambarDokter;
import lab_iot_ubl.aseptrisnasetiawan.appvellidcom.features.RegisterGambarMahasiswa;
import lab_iot_ubl.aseptrisnasetiawan.appvellidcom.network.RestService;
import lab_iot_ubl.aseptrisnasetiawan.appvellidcom.ui.TopSnakbar;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lab_iot_ubl.aseptrisnasetiawan.appvellidcom.R;
import lab_iot_ubl.aseptrisnasetiawan.appvellidcom.features.Menu_Dassboard;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthActivity extends AppCompatActivity implements IAuthView,AdapterView.OnItemSelectedListener {
//    String Patterns = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    private TextView date;



    private Button btDatePicker;
    AuthPresenter presenter;
    CoordinatorLayout coordinatorLayout, coordinatorLayoutt;
    EditText Usernamelogin, Passwordlogin;
    LinearLayout one, two, onee, twoo;
    Animation uptodown, downtoup;
    Button in;
    ProgressDialog progressDialog;

    private boolean isSigninScreen = true;
    private final String TAG = "Auth Activity";
    private int currentStep = 0;
    private String signupType = "4";


    TextView mSignpInvokerText;
    TextView mSignInInvokerText;


    LinearLayout mSigninLayout;
    LinearLayout mSignupLayout;


    //form register
    TextView masuk, daftar;
    LinearLayout lnrkta, lnrsta, lnrktm;
    private EditText usernamee, passwordd, ktp,  fullName, phoneNumber,
            alamat_rumah, alamat_korespondensi,email, profesi, no_kta, no_str, no_npm, passwordagain;

    private String[] arrMonth = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    RadioGroup rg;
    Button TombolRegis;
    Bitmap bitmap;
    int hour, minute, mYear, mMonth, mDay;
    static final int TIME_DIALOG_ID = 0;
    static final int DATE_DIALOG_ID = 1;

    private static final int IMAGE = 100;
    String[] jenis = {"", "Dokter", "Dokter Spesialis", "Dokter Gigi", "Dokter Gigi Spesialis", "Mahasiswa Kedokteran", "Mahasiswa Kedokteran Gigi"};
    String tempJenis;
    Spinner spinner;

    String[] organisasi = {"", "PDGI", "IDI", "MAHASISWA   "};
    String temporgan;
    Spinner spinner1;

    String[] cabang = {"", "Aceh", "Sumatra Utara", "Sumatra Barat", "Riau", "Kepulaan Riau",
            "Jambi", "Sumtra Selatan", "Bangka Belitung", "Bengkulu",
            "Lampung", "Dki Jakarta", "Jawa Barat", "Banten", "Jawa Tengah", "DI Yogyakarta",
            "Jawa Timur", "Bali", "NTB", "NTT", "Kalimantan Selatan", "Kalimantan Tengah",
            "Kalimantan Timur", "Kalimantan Utara", "Kalimantan Barat", "Sulawesi Utara",
            "Sulawesi barat", "Sulawesi Tengah", "Sulawesi Tenggara", "Sulawesi Selatan",
            "Gorontalo", "Sulawesi Tengah", "Sulawesi Tenggara", "Sulawesi Selatan", "Gorontalo",
            "Maluku", "Maluku Utara", "Papua Barat", "Papua"};
    String tempcabang;
    Spinner spinner2;

    @Override
    protected void onStart() {
        super.onStart();

        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
            Intent intent = new Intent(this, Menu_Dassboard.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        } else {
//            Toast.makeText(SignIn.this,"Dettacare-Event",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ImageView gambarkta, gambarktm, gambarstr;
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_auth);
        presenter = new AuthPresenter(this);
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);


        coordinatorLayoutt = findViewById(R.id.myauth);
        coordinatorLayout = findViewById(R.id.MySignIn);
        one = (LinearLayout) findViewById(R.id.one);
        two = (LinearLayout) findViewById(R.id.two);
        uptodown = AnimationUtils.loadAnimation(this, R.anim.uptodown);
        downtoup = AnimationUtils.loadAnimation(this, R.anim.downtoup);
        one.setAnimation(uptodown);
        two.setAnimation(downtoup);


        //setting layout
        mSignpInvokerText = (TextView) findViewById(R.id.signup_invoker_text);
        mSignInInvokerText = (TextView) findViewById(R.id.signin_invoker_text);


        mSigninLayout = (LinearLayout) findViewById(R.id.signin_layout);
        mSignupLayout = (LinearLayout) findViewById(R.id.signin_layout);


        mSignInInvokerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isSigninScreen = false;
                showSigninForm();

            }
        });


        mSignpInvokerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isSigninScreen = true;
                showSignupForm();
            }
        });


        //form Login
        Usernamelogin = (EditText) findViewById(R.id.usrlogin);
        Passwordlogin = (EditText) findViewById(R.id.pwlogin);
        in = (Button) findViewById(R.id.login);
        masuk = (Button) findViewById(R.id.tekssignin);
        daftar = (Button) findViewById(R.id.tekssignup);
        btDatePicker=(Button)findViewById(R.id.btntanggal);


        btDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog();
            }
        });
        in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String.valueOf(Usernamelogin.getText());
                if (Usernamelogin.getText().toString().equals("")) {
                    TopSnakbar.showWarning(AuthActivity.this, "Kolom username tidak boleh kosong");
                } else if (Passwordlogin.getText().toString().equals("")) {
//                        ||
                    TopSnakbar.showWarning(AuthActivity.this, "Kolom password tidak boleh kosong");
                } else if (Usernamelogin.getText().toString().equals("") || Passwordlogin.getText().toString().equals("")) {
                    TopSnakbar.showWarning(AuthActivity.this, "Kolom Username dan Password tidak boleh kosong");

                } else
                    signin();
            }

        });

        masuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSigninForm();
            }
        });
        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSignupForm();
            }
        });

        //form register

        usernamee = (EditText) findViewById(R.id.username);
        passwordd = (EditText) findViewById(R.id.password);
        passwordagain = findViewById(R.id.ulangipassword);
        ktp = findViewById(R.id.idktp);
        phoneNumber = findViewById(R.id.phonewa);
        gambarkta = (ImageView) findViewById(R.id.ktagambar);
        gambarktm = (ImageView) findViewById(R.id.npmgambar);
        gambarstr = (ImageView) findViewById(R.id.strgambar);
        date = findViewById(R.id.txtDate);
        fullName = findViewById(R.id.name);
        email = findViewById(R.id.mail);
        alamat_rumah = findViewById(R.id.alamat);
        alamat_korespondensi = findViewById(R.id.korespondensi);
        no_kta = findViewById(R.id.nokta);
        no_str = findViewById(R.id.str);
        no_npm = findViewById(R.id.npm);
        lnrktm = (LinearLayout) findViewById(R.id.linearktm);
        lnrkta = (LinearLayout) findViewById(R.id.linearkta);
        lnrsta = (LinearLayout) findViewById(R.id.linearstr);
//        in = (Button) findViewById(R.id.signin);
        TombolRegis = (Button) findViewById(R.id.Register);
        onee = (LinearLayout) findViewById(R.id.onee);
        twoo = (LinearLayout) findViewById(R.id.twoo);
        uptodown = AnimationUtils.loadAnimation(this, R.anim.uptodown);
        downtoup = AnimationUtils.loadAnimation(this, R.anim.downtoup);
        onee.setAnimation(uptodown);
        twoo.setAnimation(downtoup);


        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> Ajenis = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, jenis);
        Ajenis.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(Ajenis);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position,
                                       long id) {
                // TODO Auto-generated method stub
                tempJenis = jenis[position];

                if (position == 6) {
                    no_npm.setVisibility(View.VISIBLE);
                    no_kta.setVisibility(View.GONE);
                    no_str.setVisibility(View.GONE);
                    gambarkta.setVisibility(View.GONE);
                    gambarstr.setVisibility(View.GONE);
                    gambarktm.setVisibility(View.VISIBLE);
                    lnrktm.setVisibility(View.VISIBLE);
                    lnrsta.setVisibility(View.GONE);
                    lnrkta.setVisibility(View.GONE);



                } else if (position == 5) {
                    no_npm.setVisibility(View.VISIBLE);
                    no_kta.setVisibility(View.GONE);
                    no_str.setVisibility(View.GONE);
                    gambarkta.setVisibility(View.GONE);
                    gambarstr.setVisibility(View.GONE);
                    gambarktm.setVisibility(View.VISIBLE);
                    lnrktm.setVisibility(View.VISIBLE);
                    lnrsta.setVisibility(View.GONE);
                    lnrkta.setVisibility(View.GONE);



                } else if (position == 0) {
                    no_npm.setVisibility(View.GONE);
                    no_kta.setVisibility(View.GONE);
                    no_str.setVisibility(View.GONE);
                    gambarkta.setVisibility(View.GONE);
                    gambarstr.setVisibility(View.GONE);
                    gambarktm.setVisibility(View.GONE);
                    lnrktm.setVisibility(View.GONE);
                    lnrsta.setVisibility(View.GONE);
                    lnrkta.setVisibility(View.GONE);



                } else {
                    no_npm.setVisibility(View.GONE);
                    gambarktm.setVisibility(View.GONE);
                    no_kta.setVisibility(View.VISIBLE);
                    no_str.setVisibility(View.VISIBLE);
                    gambarkta.setVisibility(View.VISIBLE);
                    gambarstr.setVisibility(View.VISIBLE);
                    lnrktm.setVisibility(View.GONE);
                    lnrsta.setVisibility(View.VISIBLE);
                    lnrkta.setVisibility(View.VISIBLE);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });
        spinner1 = (Spinner) findViewById(R.id.spinner1);


        ArrayAdapter<String> jenis = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, organisasi);
        Ajenis.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(jenis);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position,
                                       long id) {
                // TODO Auto-generated method stub
                temporgan = organisasi[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }

        });


        spinner2 = (Spinner) findViewById(R.id.spinner2);


        ArrayAdapter<String> cbg = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, cabang);
        Ajenis.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(cbg);

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position,
                                       long id) {
                // TODO Auto-generated method stub
                tempcabang = cabang[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }

        });


//        public boolean isPasswordLayoutReady() {
//            boolean valid = false;
//            String email = email.getText().toString();
//            String password = mPasswordSignupEdit.getText().toString();
//            String passwordRepeat = mPasswordRepeatSignupEdit.getText().toString();
//            if (Utils.validateEmail(email)) {
//                if (password.length() > 5 && passwordRepeat.length() > 5) {
//                    if (password.equals(passwordRepeat)) {
//                        valid = true;
//                    } else TopSnakbar.showWarning(this, "kata sandi tidak cocok");
//                } else TopSnakbar.showWarning(this, "Password minimal 6 karakter");
//            } else TopSnakbar.showWarning(this,
//                    "mohon isikan email dengan benar");
//        }

        TombolRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String pss1= passwordd.getText().toString();
                final String  pss2= passwordagain.getText().toString();
                final String  gmail= passwordagain.getText().toString();
                progressDialog = ProgressDialog.show(AuthActivity.this, "Loading.....", null, true, true);
                if (usernamee.getText().toString().equals("") ||
                        passwordd.getText().toString().equals("") ||
                        passwordagain.getText().toString().equals("") ||
                        ktp.getText().toString().equals("") ||
                        fullName.getText().toString().equals("") ||
                        date.getText().toString().equals("") ||
                        email.getText().toString().equals("") ||
                        phoneNumber.getText().toString().equals("") ||
                        alamat_rumah.getText().toString().equals("") ||
                        alamat_korespondensi.getText().toString().equals("") ||
//                                spinner.toString().equals("")||
                        tempJenis.equals("") ||
//                                no_kta.getText().toString().equals("")||
//                                no_str.getText().toString().equals("")||
//                                no_npm.getText().toString().equals("")||
                        tempcabang.equals("") ||
                        temporgan.equals("")) {
                    usernamee.setFocusable(false);
                    passwordd.setFocusable(false);
                    passwordagain.setFocusable(false);
                    ktp.setFocusable(false);
                    fullName.setFocusable(false);
                    date.setFocusable(false);
                    email.setFocusable(false);
                    phoneNumber.setFocusable(false);
                    date.setFocusable(false);
                    alamat_rumah.setFocusable(false);
                    alamat_korespondensi.setFocusable(false);
                    spinner.setFocusable(false);
                    no_kta.setFocusable(false);
                    no_str.setFocusable(false);
                    no_npm.setFocusable(false);
                    spinner1.setFocusable(false);
                    spinner2.setFocusable(false);
//                    showSnackbarsignin();
                    progressDialog.dismiss();
                    TopSnakbar.showWarning(AuthActivity.this, "Kolom tidak boleh kosong,Ulangi Kembali");
                    showSignupForm();
                    usernamee.setFocusableInTouchMode(true);
                    passwordd.setFocusableInTouchMode(true);
                    passwordagain.setFocusableInTouchMode(true);
                    ktp.setFocusableInTouchMode(true);
                    fullName.setFocusableInTouchMode(true);
                    date.setFocusableInTouchMode(true);
                    email.setFocusableInTouchMode(true);
                    phoneNumber.setFocusableInTouchMode(true);
                    date.setFocusableInTouchMode(true);
                    alamat_rumah.setFocusableInTouchMode(true);
                    alamat_korespondensi.setFocusableInTouchMode(true);
                    spinner.setFocusableInTouchMode(true);
                        no_kta.setFocusableInTouchMode(true);
                        no_str.setFocusableInTouchMode(true);
                        no_npm.setFocusableInTouchMode(true);
                    spinner1.setFocusableInTouchMode(true);
                    spinner2.setFocusableInTouchMode(true);
                }else  if (passwordd.length() < 6 && passwordagain.length() < 6) {
                    progressDialog.dismiss();
                    TopSnakbar.showWarning(AuthActivity.this, "Kata sandi minamal 6 karatkter,Ulangi kembali");
                    showSignupForm();
                    passwordd.setFocusableInTouchMode(true);
                    passwordagain.setFocusableInTouchMode(true);
                } else if (!isMatch(pss1,pss2)) {
                    progressDialog.dismiss();
                    TopSnakbar.showWarning(AuthActivity.this, "Kata sandi tidak cocok");
                    showSignupForm();
                    passwordd.setFocusableInTouchMode(true);
                    passwordagain.setFocusableInTouchMode(true);
//                }else if (!isValidEmail(gmail)){
//                    progressDialog.dismiss();
//                    TopSnakbar.showWarning(AuthActivity.this, "Invalid Email");
//                    showSignupForm();
//                    email.setError("Invalid Email");
//                    email.setFocusable(true);
                } else register();
            }
        });
    }



    @Override
    public void onClick(View v) {

    }

    @Override
    public void register() {
        presenter.signup(
                usernamee.getText().toString(),
                passwordd.getText().toString(),
                //passwordagain.getText().toString(),
                ktp.getText().toString(),
                fullName.getText().toString(),
                date.getText().toString(),
                email.getText().toString(),
                phoneNumber.getText().toString(),
                alamat_rumah.getText().toString(),
                alamat_korespondensi.getText().toString(),
                tempJenis.toString(),
                no_kta.getText().toString(),
                no_str.getText().toString(),
                no_npm.getText().toString(),
                tempcabang.toString(),
                temporgan.toString()

        );

    }


    @Override
    public void signin() {
        progressDialog = ProgressDialog.show(AuthActivity.this, "Loading.....", null, true, true);
        String user = String.valueOf(Usernamelogin.getText());
        String pas = String.valueOf(Passwordlogin.getText());
        if (user.equals("")) {
            showSnackbar();
        } else if (pas.equals("")) {
            showSnackbar();
        } else {
            // Intent sign = new Intent(this, MainActivity.class);
            //startActivity(sign);
            //overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            //finish();
            retrofit2.Call<LoginResponse> call = RestService
                    .getInstance()
                    .getNetworkService()
                    .userLogin(user, pas);

            call.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(retrofit2.Call<LoginResponse> call, Response<LoginResponse> response) {
                    LoginResponse loginResponse = response.body();
                    if (!loginResponse.isError()) {
                        //save user and open profile
                        SharedPrefManager.getInstance(AuthActivity.this)
                                .saveUser(loginResponse
                                        .getUser());
//                        Toast.makeText(getApplicationContext(), LoginResponse.getMessage(), Toast.LENGTH_SHORT).show();
//                        TopSnakbar.showWarning(AuthActivity.this, "Login Gagal,Ulangi Kembali");
//                        Toast.makeText(AuthActivity.this, "Sukses", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(AuthActivity.this, Menu_Dassboard.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        TopSnakbar.showSuccess(AuthActivity.this, "Login sukses");

                    } else {
                        progressDialog.dismiss();
                        TopSnakbar.showWarning(AuthActivity.this, "Login Gagal,Ulangi Kembali");
                    }
                }

                @Override
                public void onFailure(retrofit2.Call<LoginResponse> call, Throwable t) {
                    progressDialog.dismiss();
//                    TopSnakbar.showSuccess(AuthActivity.this, "Login sukses");
                    TopSnakbar.showWarning(AuthActivity.this, "Login Gagal,Ulangi Kembali");
                }
            });
        }
    }
//        progressDialog = ProgressDialog.show(AuthActivity.this, "Loading.....", null, true, true);
//        String user = String.valueOf(Usernamelogin.getText());
//        String pas = String.valueOf(Passwordlogin.getText());
//        if (Usernamelogin.equals("")) {
//            showSnackbar();
//        } else if (Passwordlogin.equals("")) {
//            showSnackbar();
//        } else {
//            presenter.login(
//                    Usernamelogin.getText().toString(),
//                    Passwordlogin.getText().toString()
//            );
//        }

    @Override
    public void onSignInsucces() {
        progressDialog.dismiss();
//        Toast.makeText(this, "Login Sukses", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(AuthActivity.this, Menu_Dassboard.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);


//        }else {
//            progressDialog.dismiss();
//            Toast.makeText(AuthActivity.this, "Login Gagal", Toast.LENGTH_LONG).show();
//        }
    }


    @Override
    public void onSignInfailled() {
        TopSnakbar.showWarning(AuthActivity.this, "Login Gagal");
        progressDialog.dismiss();
//        Toast.makeText(mContext, error_message, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "Login Gagal Ulangi Kembali", Toast.LENGTH_SHORT).show();
//        Intent intent = new Intent(AuthActivity.this, AuthActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        startActivity(intent);
    }

    @Override
    public void Signupberhasil() {
        progressDialog.dismiss();
//        Toast.makeText(this, "Register Berhasil", Toast.LENGTH_SHORT).show();
        TopSnakbar.showSuccess(this, "Kolom sudah semua terisi, silahkan melanjutkan pendaftaran");

        if (tempJenis.equals("Dokter") || tempJenis.equals("Dokter Spesialis") ||
                tempJenis.equals("Dokter Gigi") || tempJenis.equals("Dokter Gigi Spesialis")) {

            Intent intent = new Intent(AuthActivity.this, RegisterGambarDokter.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            Intent sign = new Intent(this, RegisterGambarMahasiswa.class);
            intent.putExtra("username", usernamee.getText().toString());
            intent.putExtra("profesi", spinner.getSelectedItem().toString());
            startActivity(intent);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            finish();
        } else {
            Intent sign = new Intent(AuthActivity.this, RegisterGambarMahasiswa.class);
            sign.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            //Intent sign = new Intent(this, RegisterGambarMahasiswa.class);
            sign.putExtra("username", usernamee.getText().toString());
            sign.putExtra("profesi", spinner.getSelectedItem().toString());
            startActivity(sign);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            finish();
        }

    }


    @Override
    public void Signupgagal() {
        progressDialog.dismiss();
        TopSnakbar.showWarning(AuthActivity.this, "Register Gagal");
//    Toast.makeText(this, "Register Gagal, Ulangi Kemabali", Toast.LENGTH_SHORT).show();
        showSignupForm();

//    sign.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//    //Intent sign = new Intent(this, RegisterGambarMahasiswa.class);
////        sign.putExtra("username", usernamee.getText().toString());
////        sign.putExtra("profesi", spinner.getSelectedItem().toString());
//    startActivity(sign);
//    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
//    finish(); Intent sign = new Intent(AuthActivity.this,Splas_Screen.class);
////

    }

    public void showSignupForm() {
        ((LinearLayout.LayoutParams) mSigninLayout.getLayoutParams()).weight = 0.25f;
//                0.15f;
        mSigninLayout.requestLayout();
        ((LinearLayout.LayoutParams) mSignupLayout.getLayoutParams()).weight = 0.60f;
//                0.85f;
        mSignupLayout.requestLayout();
        mSignpInvokerText.setVisibility(View.GONE);
        mSignInInvokerText.setVisibility(View.VISIBLE);
        Animation translate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.translate_right_to_left);
        mSignupLayout.startAnimation(translate);
        Animation clockwise = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_right_to_left);
        TombolRegis.startAnimation(clockwise);
        one.setAnimation(uptodown);
        two.setAnimation(downtoup);
        onee.setAnimation(uptodown);
        twoo.setAnimation(downtoup);


    }

    private void showDateDialog(){

        /**
         * Calendar untuk mendapatkan tanggal sekarang
         */
        Calendar newCalendar = Calendar.getInstance();

        /**
         * Initiate DatePicker dialog
         */
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                /**
                 * Method ini dipanggil saat kita selesai memilih tanggal di DatePicker
                 */

                /**
                 * Set Calendar untuk menampung tanggal yang dipilih
                 */
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                /**
                 * Update TextView dengan tanggal yang kita pilih
                 */
                date.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        /**
         * Tampilkan DatePicker dialog
         */
        datePickerDialog.show();
    }


    public void showSigninForm() {
        ((LinearLayout.LayoutParams) mSigninLayout.getLayoutParams()).weight = 0.25f;
        mSigninLayout.requestLayout();
        ((LinearLayout.LayoutParams) mSignupLayout.getLayoutParams()).weight = 18f;
        mSignupLayout.requestLayout();
        Animation translate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.translate_left_to_right);
        mSigninLayout.startAnimation(translate);
        mSignpInvokerText.setVisibility(View.VISIBLE);
        mSignInInvokerText.setVisibility(View.GONE);
        Animation clockwise = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_left_to_right);
        in.startAnimation(clockwise);
        one.setAnimation(uptodown);
        two.setAnimation(downtoup);
        onee.setAnimation(uptodown);
        twoo.setAnimation(downtoup);

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    private void showSnackbar() {
        Snackbar snackbar = Snackbar.make(coordinatorLayout, "Userneme dan Password Tidak boleh kosong", Snackbar.LENGTH_INDEFINITE)
                .setAction("Ulangi", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Snackbar snackbar1 = Snackbar.make(coordinatorLayout, "Silahkan ulangi", Snackbar.LENGTH_SHORT);
                        snackbar1.show();
                        Usernamelogin.setFocusableInTouchMode(true);
                        Passwordlogin.setFocusableInTouchMode(true);
                    }
                });
        snackbar.show();
    }

    private boolean isMatch(String password, String password1){
        return password.equals(password1);
    }
    private boolean isValidEmail(String gmail) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(gmail);
        return matcher.matches();
    }
}






