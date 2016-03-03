package com.example.levent_j.dotamin_.net;

import com.example.levent_j.dotamin_.pojo.User;
import com.example.levent_j.dotamin_.utils.Util;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by levent_j on 16-3-1.
 */
public class Api {
    //http://api.steampowered.com/ISteamUser/GetPlayerSummaries/v0002/?key=A7CAFA33562B6310ACDC0C3864B9DC1B&steamids=76561198089905448
    private static final String URL = "http://api.steampowered.com/";

    //我的steam web api专属key
    private static final String KEY = "A7CAFA33562B6310ACDC0C3864B9DC1B";

    private ApiService apiService;

    private static volatile Api instance;

    public final Gson gson = new GsonBuilder().create();

    private Api(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(URL)
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    public static Api getInstance(){
        if (instance == null){
            synchronized (Api.class){
                if (instance == null){
                    instance = new Api();
                }
            }
        }
        return instance;
    }

    //有毒
    public void getUsers(String id, Observer<User> observer){
        apiService.getUsers(KEY,id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public interface ApiService{
        //ISteamUser/GetPlayerSummaries/v0002/?key=A7CAFA33562B6310ACDC0C3864B9DC1B&steamid=76561198089905448
        @GET("ISteamUser/GetPlayerSummaries/v0002/")
        Observable<User> getUsers(@Query("key") String key,
                                  @Query("steamids") String id);
    }
}
