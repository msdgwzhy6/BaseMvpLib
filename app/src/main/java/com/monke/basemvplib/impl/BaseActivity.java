package com.monke.basemvplib.impl;

import android.os.Bundle;
import com.monke.basemvplib.AppActivityManager;
import com.monke.basemvplib.IPresenter;
import com.monke.basemvplib.IView;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

public abstract class BaseActivity<T extends IPresenter> extends RxAppCompatActivity implements IView{
    protected Bundle savedInstanceState;
    private T mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.savedInstanceState = savedInstanceState;
        AppActivityManager.getInstance().add(this);
        initSDK();
        onCreateActivity();
        mPresenter = initInjector();
        attachView();
        initData();
        bindView();
        bindEvent();
        firstRequest();
    }

    /**
     * 首次逻辑操作
     */
    protected void firstRequest(){

    }

    /**
     * 事件触发绑定
     */
    protected void bindEvent() {

    }

    /**
     * 控件绑定
     */
    protected void bindView() {

    }

    /**
     * P层绑定V层
     */
    private void attachView() {
        if(null != mPresenter){
            mPresenter.attachView(this);
        }
    }

    /**
     * P层解绑V层
     */
    private void detachView(){
        if(null != mPresenter){
            mPresenter.detachView();
        }
    }

    /**
     * SDK初始化
     */
    protected void initSDK(){

    }

    /**
     * P层绑定   若无则返回null;
     * @return
     */
    protected abstract T initInjector();

    /**
     * 布局载入  setContentView()
     */
    protected abstract void onCreateActivity();

    /**
     * 数据初始化
     */
    protected abstract void initData();
    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        detachView();
        AppActivityManager.getInstance().remove(this);
    }
}
