package com.example.plush81029.retrofittest;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;

/**
 * Created by PLUSH81029 on 2017/7/26.
 */

public interface PluInterface {
    /**
     * 暴露给外部访问的请求接口，传值等由这里定义
     */
    interface ActionInterface<T> {
        Call<String> uploadVideo(Callback<String> callback);
    }

    /**
     * API-PATH
     * 自身请求网络的retrofit接口
     */
    interface NetInterface{
        @GET("basil2style")
        Call<ResponseBody> uploadVideo();
    }


}
