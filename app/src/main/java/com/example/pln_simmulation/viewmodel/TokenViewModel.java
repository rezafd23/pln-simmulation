package com.example.pln_simmulation.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pln_simmulation.repositories.TokenRepositories;
import com.example.pln_simmulation.response.TokenResponse;

public class TokenViewModel extends ViewModel {

    private MutableLiveData<TokenResponse> mutableLiveData;
    private TokenRepositories tokenRepositories;

    public void init(){
        if (mutableLiveData!=null){
            return;
        }
        tokenRepositories = TokenRepositories.getInstance();
    }

    public LiveData<TokenResponse> getUserData(String no_pelanggan){
        if (mutableLiveData==null){
            tokenRepositories=TokenRepositories.getInstance();
        }
        mutableLiveData=tokenRepositories.getCustomer(no_pelanggan);
        return mutableLiveData;
    }

    public LiveData<TokenResponse> getActiveToken(String no_pelanggan){
        if (mutableLiveData==null){
            tokenRepositories=TokenRepositories.getInstance();
        }
        mutableLiveData=tokenRepositories.getActive(no_pelanggan);
        return mutableLiveData;
    }

    public LiveData<TokenResponse> redeemToken(String token){
        if (mutableLiveData==null){
            tokenRepositories=TokenRepositories.getInstance();
        }
        mutableLiveData=tokenRepositories.redeemToken(token);
        return mutableLiveData;
    }

}
