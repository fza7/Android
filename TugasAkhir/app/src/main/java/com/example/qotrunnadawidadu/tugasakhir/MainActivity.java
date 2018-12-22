package com.example.qotrunnadawidadu.tugasakhir;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    //inisial variabel email dan password
    EditText email, password;
    //inisial variable button login
    Button login, register;
    //inisial variabel string email dan password
    String str_email, str_password;
    //variabel global untuk autentikasi
    private FirebaseAuth mAuth;
    //inisialisasi string untuk menyimpan  pesan dari log
    String TAG = "Ini Kelas Main Activity";


    GoogleApiClient mGoogleApiClient;
    SignInButton googleBtn;
    int RC_SIGN_IN = 9001;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //menghubungkan variable email, password, dan button login ke XML
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.loginButton);
        register = (Button) findViewById(R.id.toRegisterButton);
        googleBtn = (SignInButton) findViewById(R.id.googlebutton22);
        googleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginGoogle();
            }
        });

        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();


        mAuth = FirebaseAuth.getInstance();
        //memberikan fungsi pada button register
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent z = new Intent(getApplicationContext(),Main3Activity.class);
                startActivity(z);
            }
        });

        //memberikan fungsi pada button login
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Mengambil data pada form di email dan password untuk dimasukkan ke dlm variable str_email, dan str_password
                str_email = email.getText().toString().trim();
                str_password = password.getText().toString().trim();
                //memanggil method signIn
                signIn(str_email,str_password);
            }
        });
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        // FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }

    private void updateUI(FirebaseUser currentUser) {
        if( currentUser != null){
            Intent i = new Intent(this, Main2Activity.class);
            startActivity(i);
        }else {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }
    }

    private void signIn(String str_email, String str_password){
        //untuk melakukan SignIn dengan parameter str_email, str_password
        mAuth.signInWithEmailAndPassword(str_email, str_password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            Log.d(TAG, "signInWithEmail:success");
                            //iniasisasi variable user yang berisi data dari user
                            Toast.makeText(MainActivity.this, " " + user.getEmail(),
                                    Toast.LENGTH_SHORT).show();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // ...
                    }
                });
    }

    private void loginGoogle() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int request_code, int result_kode, Intent data){
        super.onActivityResult(request_code,result_kode,data);
        if(request_code == RC_SIGN_IN){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if(result.isSuccess()){
            FirebaseUser user = mAuth.getCurrentUser();
            Toast.makeText(MainActivity.this, "Login Success.",
                    Toast.LENGTH_SHORT).show();
            updateUI(user);
        }else{
            Toast.makeText(MainActivity.this, "Login Gagal.",
                    Toast.LENGTH_SHORT).show();
            updateUI(null);
        }
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(TAG,"onConnectionFailed" + connectionResult);
    }
}

