package com.example.pln_simmulation.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.pln_simmulation.R;
import com.example.pln_simmulation.viewmodel.TokenViewModel;
import com.xw.repo.XEditText;

public class MainActivity extends SingleActivity {

    private XEditText et_pelanggan;
    private AppCompatButton btnLogin;
    private TokenViewModel tokenViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPelanggan(et_pelanggan.getText().toString());
            }
        });
    }

    private void initView(){
        et_pelanggan=findViewById(R.id.et_pelanggan);
        btnLogin=findViewById(R.id.btnLogin);
        tokenViewModel = ViewModelProviders.of(this).get(TokenViewModel.class);
    }
    private void getPelanggan(String no_pelanggan){
        showProgressDialog();
        tokenViewModel.getUserData(no_pelanggan)
                .observe(this, userResponse -> {
                    dismissProgressDialog();
                    Log.v("isiResponse: ", userResponse.getResponse());
                    try {
                        if (userResponse.getResponse().equals("200")) {
                            Log.v("isiResponse: ", userResponse.getPayload().toString());
                            MeteranActivity.navigate(MainActivity.this,userResponse.getPayload().toString());
                        } else {
                            Toast.makeText(getApplicationContext(), "Terjadi Kesalahan, Mohon Ulangi", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        Log.v("isiError: ", e.getMessage());
                    }

                });
    }
}