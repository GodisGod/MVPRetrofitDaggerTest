package com.example.plush81029.retrofittest.dagger;

/**
 * Created by PLUSH81029 on 2017/7/27.
 */


@dagger.Component(modules = TestModule.class)
public interface Component {
    void inject(TestActivity activity);
}
