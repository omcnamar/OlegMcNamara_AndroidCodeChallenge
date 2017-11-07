package com.olegsagenadatrytwo.olegmcnamara_androidcodechallenge.model.remote;


import android.support.annotation.NonNull;

import com.olegsagenadatrytwo.olegmcnamara_androidcodechallenge.entities.Posts;

public class Remote {

    private IRemote iremote;

    public Remote(IRemote iremote){
        this.iremote = iremote;
    }

    public void getPosts(String query){
        retrofit2.Call<Posts> postsDataCall = RetrofitHelper.getPostsCall(query);
        postsDataCall.enqueue(new retrofit2.Callback<Posts>() {
            @Override
            public void onResponse(@NonNull retrofit2.Call<Posts> call, @NonNull retrofit2.Response<Posts> response) {
                Posts posts = response.body();
                iremote.sendData(posts);
            }

            @Override
            public void onFailure(@NonNull retrofit2.Call<Posts> call, @NonNull Throwable t) {
                iremote.sendData(null);
            }
        });
    }

}
