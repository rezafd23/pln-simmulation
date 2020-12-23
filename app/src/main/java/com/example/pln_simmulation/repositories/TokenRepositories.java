package com.example.pln_simmulation.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.pln_simmulation.response.TokenResponse;

import org.json.JSONObject;

public class TokenRepositories {

    private static TokenRepositories tokenRepositories;

    public static TokenRepositories getInstance() {
        if (tokenRepositories == null) {
            tokenRepositories = new TokenRepositories();
        }
        return tokenRepositories;
    }

    public MutableLiveData<TokenResponse> getCustomer(String no_pelanggan) {
        MutableLiveData<TokenResponse> userData = new MutableLiveData<>();

        try {
            final TokenResponse res = new TokenResponse();
            AndroidNetworking.get("http://192.168.18.5:8082/api/customer/{no_pelanggan}")
                    .addPathParameter("no_pelanggan",no_pelanggan)
                    .addHeaders("apikey","1001")
                    .setTag("getUser")
                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                if (response.getString("response").equals("200")) {
                                    res.setResponse(response.getString("response"));
                                    res.setPayload(response.getJSONObject("payload"));
                                } else {
                                    res.setResponse(response.getString("response"));
                                }
                                userData.setValue(res);
                            } catch (Exception e) {
                                userData.setValue(null);
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onError(ANError anError) {
                            userData.setValue(null);
                            Log.v("Cek Error", "onError: " + anError);
                            anError.printStackTrace();
                        }
                    });
        } catch (Exception e) {
            userData.setValue(null);
            e.printStackTrace();
        }
        return userData;
    }

    public MutableLiveData<TokenResponse> getActive(String no_pelanggan) {
        MutableLiveData<TokenResponse> userData = new MutableLiveData<>();

        try {
            final TokenResponse res = new TokenResponse();
            AndroidNetworking.get("http://192.168.18.5:8082/api/token/active/{no_pelanggan}")
                    .addPathParameter("no_pelanggan",no_pelanggan)
                    .addHeaders("apikey","1001")
                    .setTag("getActiveToken")
                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                if (response.getString("response").equals("200")) {
                                    res.setResponse(response.getString("response"));
                                    res.setPayload(response.getJSONObject("payload"));
                                } else {
                                    res.setResponse(response.getString("response"));
                                }
                                userData.setValue(res);
                            } catch (Exception e) {
                                userData.setValue(null);
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onError(ANError anError) {
                            userData.setValue(null);
                            Log.v("Cek Error", "onError: " + anError);
                            anError.printStackTrace();
                        }
                    });
        } catch (Exception e) {
            userData.setValue(null);
            e.printStackTrace();
        }
        return userData;
    }

    public MutableLiveData<TokenResponse> redeemToken(String no_token) {
        MutableLiveData<TokenResponse> userData = new MutableLiveData<>();

        try {
            final TokenResponse res = new TokenResponse();
            AndroidNetworking.put("http://192.168.18.5:8082/api/token/redeem/{no_token}")
                    .addPathParameter("no_token",no_token)
                    .addHeaders("apikey","1001")
                    .setTag("getActiveToken")
                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                if (response.getString("response").equals("200")) {
                                    res.setResponse(response.getString("response"));
                                } else {
                                    res.setResponse(response.getString("response"));
                                }
                                userData.setValue(res);
                            } catch (Exception e) {
                                userData.setValue(null);
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onError(ANError anError) {
                            userData.setValue(null);
                            Log.v("Cek Error", "onError: " + anError);
                            anError.printStackTrace();
                        }
                    });
        } catch (Exception e) {
            userData.setValue(null);
            e.printStackTrace();
        }
        return userData;
    }

}
