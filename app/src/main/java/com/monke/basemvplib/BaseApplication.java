package com.monke.basemvplib;

import android.app.Application;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cookie.store.MemoryCookieStore;
import com.lzy.okgo.cookie.store.PersistentCookieStore;

public class BaseApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();

        initOkGo();
    }

    private void initOkGo() {
        //OKGO初始化
        OkGo.init(this);

        //如果不想让框架管理cookie（或者叫session的保持）,以下不需要
        OkGo.getInstance()
//        .setCookieStore(new MemoryCookieStore())       //cookie使用内存缓存（app退出后，cookie消失）
                .setCookieStore(new PersistentCookieStore());  //cookie持久化存储，如果cookie不过期，则一直有效
    }
}
