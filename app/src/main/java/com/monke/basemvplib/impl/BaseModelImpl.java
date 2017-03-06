package com.monke.basemvplib.impl;

import android.support.annotation.NonNull;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cookie.store.CookieStore;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;
import java.util.List;
import java.util.Map;
import okhttp3.Cookie;
import okhttp3.HttpUrl;

public class BaseModelImpl {

    //////////////////////////网络请求公共参数///////////////////////
    public static void addCommonParams(HttpParams httpParams) {
        OkGo.getInstance().addCommonParams(httpParams);
    }

    public static void putCommonParams(String key, String value) {
        OkGo.getInstance().getCommonParams().put(key, value);
    }

    @NonNull
    public static void removeCommonParams(String... keys) {
        for (String key : keys) {
            OkGo.getInstance().getCommonParams().remove(key);
        }
    }

    public static void clearCommonParams() {
        OkGo.getInstance().getCommonParams().clear();
    }

    ////////////////////网络请求公共头//////////////////////////////
    public static void addPublicHeader(HttpHeaders httpHeaders) {
        OkGo.getInstance().addCommonHeaders(httpHeaders);
    }

    public static void putPublicHeader(String key, String value) {
        OkGo.getInstance().getCommonHeaders().put(key, value);
    }

    @NonNull
    public static void removePublicHeader(String... keys) {
        for (String key : keys) {
            OkGo.getInstance().getCommonHeaders().remove(key);
        }
    }

    public static void clearPublicHeader(){
        OkGo.getInstance().getCommonHeaders().clear();
    }

    /**
     * 取消当前tag请求
     */
    public void cancelRequest() {
        OkGo.getInstance().cancelTag(this);
    }

    /**
     * 取消所有请求
     */
    public static void cancelRequestAll(){
        OkGo.getInstance().cancelAll();
    }

    //////////////////////////网络请求Cookies//////////////////////
    public static List<Cookie> getAllCookies(){
        CookieStore cookieStore = OkGo.getInstance().getCookieJar().getCookieStore();
        return cookieStore.getAllCookie();
    }

    public static void putCookies(String url,Map<String,Object> map){
        HttpUrl httpUrl = HttpUrl.parse(url);
        CookieStore cookieStore = OkGo.getInstance().getCookieJar().getCookieStore();

        Cookie.Builder builder = new Cookie.Builder();
        if(null != map && map.size() > 0){
            for(Map.Entry<String,Object> entry:map.entrySet()){
                cookieStore.saveCookie(httpUrl, builder.name(entry.getKey()).value(null==entry.getValue()?"":entry.getValue().toString()).domain(httpUrl.host()).build());
            }
        }
    }

    public static void removeCookies(String url){
        HttpUrl httpUrl = HttpUrl.parse(url);
        CookieStore cookieStore = OkGo.getInstance().getCookieJar().getCookieStore();
        cookieStore.removeCookie(httpUrl);
    }
}