package com.olegsagenadatrytwo.olegmcnamara_androidcodechallenge.model.remote;

import com.olegsagenadatrytwo.olegmcnamara_androidcodechallenge.entities.Posts;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;


class RetrofitHelper {
    private static final String BASE_URL = "http://www.reddit.com/";

    private static Retrofit create(){

        //create a logging interceptor
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        //create a custom client to add the interceptor
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit;
    }

    static Call<Posts> getPostsCall(String query){
        Retrofit retrofit = create();
        PostService eventService = retrofit.create(PostService.class);
        return eventService.getPostsData(query);
    }

    interface PostService {

        @GET("r/{query}/.json")
        Call<Posts> getPostsData(@Path(value = "query") String query);
    }

}
