package com.example.plush81029.retrofittest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.plush81029.retrofittest.dagger.TestActivity;
import com.tencent.rtmp.ugc.TXRecordCommon;
import com.tencent.rtmp.ugc.TXUGCPublish;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 普通retrofit上传
 */
public class MainActivity extends AppCompatActivity {

    private TextView tvTime;
    private Button btnStart;
    private Button btnUpload;

    private PluNet mPluNet;

    TXUGCPublish mTXUGCPublish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        initView();
        initEvent();
    }

    private void init() {
        mTXUGCPublish = new TXUGCPublish(this);
        mPluNet = new PluNet();
    }

    private void initView() {
        tvTime = (TextView) findViewById(R.id.tv_time);
        btnStart = (Button) findViewById(R.id.btn_start);
        btnUpload = (Button) findViewById(R.id.btn_upload);
    }

    private void initEvent() {

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPluNet.uploadVideo(new Callback<ResponseBody>() {

                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccess()) {
                            Log.i("LHD", response.body().toString());
                            try {
                                String result = response.body().string();
                                tvTime.setText(result);
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
        });

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TXRecordCommon.TXPublishParam param = new TXRecordCommon.TXPublishParam();
                param.signature = "1OIehbaryYPanx8sOBBGQudbQaxzZWNyZXRJZD1BS0lER09IelhyUXRmRmdybVJheHB4cld3NzJqQlBQRnA3QTQmY3VycmVudFRpbWVTdGFtcD0xNTAxMTM5Mzc0JmV4cGlyZVRpbWU9MTUwMTIzOTM3NCZyYW5kb209MTA";
                param.videoPath = "/mnt/shared/Other/V_-1207322832_.mp4";
                param.coverPath = "/mnt/shared/Image/3bcc013b4fad97ea3b908c62e9d09567_d788d43f8794a4c22fe6ab9408f41bd5ac6e3943.jpg";
                mTXUGCPublish.publishVideo(param);
                tvTime.setText(param.videoPath + "\n" + param.coverPath);
            }
        });

        mTXUGCPublish.setListener(new TXRecordCommon.ITXVideoPublishListener() {
            @Override
            public void onPublishProgress(final long l, final long l1) {
                Log.i("LHD", "onPublishProgress  " + l + "   " + l1);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvTime.setText("onPublishProgress  " + l + "   " + l1);
                    }
                });
            }

            @Override
            public void onPublishComplete(final TXRecordCommon.TXPublishResult txPublishResult) {
                Log.i("LHD", "onPublishComplete  " + txPublishResult.videoURL);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvTime.setText("onPublishComplete  " +
                                "videoURL = " + txPublishResult.videoURL + "\n" +
                                "coverURL = " + txPublishResult.coverURL + "\n" +
                                "descMsg = " + txPublishResult.descMsg + "\n" +
                                "videoId = " + txPublishResult.videoId + "\n" +
                                "retCode = " + txPublishResult.retCode);
                    }
                });
            }
        });

        findViewById(R.id.btn_jump).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, TestActivity.class));
            }
        });
    }

}
