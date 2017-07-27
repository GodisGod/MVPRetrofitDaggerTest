package com.example.plush81029.retrofittest.dagger;

import com.example.plush81029.retrofittest.PluNet;

import dagger.Module;
import dagger.Provides;

/**
 * Created by PLUSH81029 on 2017/7/27.
 */
@Module
public class TestModule {

    private PluNet mPluNet;
    private UploadView mUploadView;

    public TestModule(PluNet pluNet, UploadView uploadView) {
        mPluNet = pluNet;
        mUploadView = uploadView;
    }

    @Provides
    UploadView provideUploadView(){
        return mUploadView;
    }

    @Provides
    PluNet providePluNet(){
        return mPluNet;
    }

}
