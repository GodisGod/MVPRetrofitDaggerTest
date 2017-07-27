package com.example.plush81029.retrofittest.dagger;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.plush81029.retrofittest.PluNet;
import com.example.plush81029.retrofittest.R;

import javax.inject.Inject;

public class TestActivity extends AppCompatActivity implements UploadView {

    private TextView tvResult;
    private Button btnStart;

    @Inject
    Presenter mPresenter;

//    PluNet mPluNet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger2_test);

        DaggerComponent.builder()
                .testModule(new TestModule(new PluNet(), this))
                .build()
                .inject(this);

        initView();
        initEvent();
    }

    private void initView() {
        tvResult = (TextView) findViewById(R.id.tv_result);
        btnStart = (Button) findViewById(R.id.btn_start);
    }

    private void initEvent() {
//        mPluNet = new PluNet();
//        mPresenter = new Presenter(mPluNet, this);
        findViewById(R.id.btn_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.getInfo();
            }
        });
    }


    @Override
    public void success(String result) {
        tvResult.setText(result);
    }
}
