package com.example.pln_simmulation.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.pln_simmulation.R;
import com.example.pln_simmulation.viewmodel.TokenViewModel;
import com.xw.repo.XEditText;

import org.json.JSONObject;

public class MeteranActivity extends SingleActivity {

    private AppCompatTextView tv_owner,tv_token;
    private XEditText et_token;
    private AppCompatButton btn_redeem;
    private TokenViewModel tokenViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meteran);
        initView();

        setupView();

        btn_redeem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redeemToken(et_token.getText().toString());
            }
        });
    }

    private void initView(){
        et_token=findViewById(R.id.et_token);
        tv_owner=findViewById(R.id.tv_owner);
        tv_token=findViewById(R.id.tv_token);
        btn_redeem=findViewById(R.id.btn_redeem);
        tokenViewModel = ViewModelProviders.of(this).get(TokenViewModel.class);
    }

    public static void navigate(Activity activity,String data) {
        Intent intent = new Intent(activity, MeteranActivity.class);
        intent.putExtra("data",data);
        activity.startActivity(intent);
    }

    private void setupView(){

        try {
            JSONObject obj = new JSONObject(getIntent().getStringExtra("data"));
            getActiveToken(obj.getString("no_pelanggan"));
            tv_owner.setText(obj.getString("nama"));
        } catch (Throwable t) {
            Log.e("My App", "Could not parse malformed JSON:");
        }
    }

    private void getActiveToken(String no_pelanggan){
        showProgressDialog();
        tokenViewModel.getActiveToken(no_pelanggan)
                .observe(this, userResponse -> {
                    dismissProgressDialog();
                    Log.v("isiResponse: ", userResponse.getResponse());
                    try {
                        if (userResponse.getResponse().equals("200")) {
                            Log.v("isiResponse: ", userResponse.getPayload().toString());
                            tv_token.setText(userResponse.getPayload().getString("token"));
                        } else {
                            Toast.makeText(getApplicationContext(), "Terjadi Kesalahan, Mohon Ulangi", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        Log.v("isiError: ", e.getMessage());
                    }

                });
    }

    private void redeemToken(String token){
        showProgressDialog();
        tokenViewModel.redeemToken(token)
                .observe(this, userResponse -> {
                    dismissProgressDialog();
                    Log.v("isiResponse: ", userResponse.getResponse());
                    try {
                        if (userResponse.getResponse().equals("200")) {
                            setupView();
                            et_token.setText("");
                        } else {
                            Toast.makeText(getApplicationContext(), "Terjadi Kesalahan, Mohon Ulangi", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        Log.v("isiError: ", e.getMessage());
                    }

                });
    }






}