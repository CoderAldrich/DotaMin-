package com.example.levent_j.dotamin_.net;

import com.example.levent_j.dotamin_.model.FriendResult;
import com.example.levent_j.dotamin_.model.Item;
import com.example.levent_j.dotamin_.model.Match;
import com.example.levent_j.dotamin_.model.MatchesHistory;
import com.example.levent_j.dotamin_.model.User;
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

    private static final String RELATION = "friend";
    private static final String LANGUAGE = "zh";

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

    public void getFriends(String id,Observer<FriendResult> observer){
        apiService.getFriends(KEY, id, RELATION)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getMatchesHistory(String id,String num,Observer<MatchesHistory> observer){
        apiService.getMatchesHistory(KEY, id, num)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getMatchDeatials(String id,Observer<Match> observer){
        apiService.getMatchDetails(KEY, id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getItems(Observer<Item> observer){
        apiService.getItems(KEY,LANGUAGE)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public interface ApiService{
        //ISteamUser/GetPlayerSummaries/v0002/?key=A7CAFA33562B6310ACDC0C3864B9DC1B&steamid=76561198089905448
        @GET("ISteamUser/GetPlayerSummaries/v0002/")
        Observable<User> getUsers(@Query("key") String key,
                                  @Query("steamids") String id);
        //ISteamUser/GetFriendList/v1/?key=A7CAFA33562B6310ACDC0C3864B9DC1B&steamid=76561198089905448&relationship=friend
        @GET("ISteamUser/GetFriendList/v1/")
        Observable<FriendResult> getFriends(@Query("key") String key,
                                            @Query("steamid") String id,
                                            @Query("relationship") String relationship);
        //IDOTA2Match_570/GetMatchHistory/v001/?key=A7CAFA33562B6310ACDC0C3864B9DC1B&account_id=129639720&matches_requested=3
        @GET("IDOTA2Match_570/GetMatchHistory/v001/")
        Observable<MatchesHistory> getMatchesHistory(@Query("key") String key,
                                                     @Query("account_id") String id,
                                                     @Query("matches_requested") String num);

        //IDOTA2Match_570/GetMatchDetails/V001/?key=A7CAFA33562B6310ACDC0C3864B9DC1B&match_id=2199956955
        @GET("IDOTA2Match_570/GetMatchDetails/V001/")
        Observable<Match> getMatchDetails(@Query("key") String key,
                                          @Query("match_id") String id);

        //IEconDOTA2_570/GetGameItems/V001/?key=A7CAFA33562B6310ACDC0C3864B9DC1B&language=zh
        @GET("IEconDOTA2_570/GetGameItems/V001/")
        Observable<Item> getItems(@Query("key") String key,
                                  @Query("language") String zh);
    }
}
