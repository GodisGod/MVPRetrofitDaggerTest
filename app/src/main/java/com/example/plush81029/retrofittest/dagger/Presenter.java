package com.example.plush81029.retrofittest.dagger;

import android.util.Log;

import com.example.plush81029.retrofittest.PluNet;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by PLUSH81029 on 2017/7/27.
 */

public class Presenter {

    @Inject
    PluNet mPluNet;

    private UploadView mUploadView;

    @Inject
    public Presenter(UploadView uploadView) {
        mUploadView = uploadView;
    }

    public void getInfo() {
        mPluNet.uploadVideo(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccess()) {
                    Log.i("LHD", response.body().toString());
                    try {
                        String result = response.body().string();
                        mUploadView.success(result);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.i("LHD", "访问失败");
            }
        });
    }

}
