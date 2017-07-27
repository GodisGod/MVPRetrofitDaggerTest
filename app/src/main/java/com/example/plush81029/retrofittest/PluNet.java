package com.example.plush81029.retrofittest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

/**
 * Created by PLUSH81029 on 2017/7/27.
 */

public class PluNet implements PluInterface, PluInterface.ActionInterface {

    @Override
    public Call<String> uploadVideo(Callback callback) {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(ApiConfigure.URL_BASE)
                .build();

        PluInterface.NetInterface netInterface = retrofit.create(PluInterface.NetInterface.class);
        Call call = netInterface.uploadVideo();
        call.enqueue(callback);
        return call;
    }


}
