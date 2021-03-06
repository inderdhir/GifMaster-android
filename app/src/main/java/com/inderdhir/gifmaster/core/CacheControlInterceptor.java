package com.inderdhir.gifmaster.core;

import android.content.Context;

import com.inderdhir.gifmaster.util.Utils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


public class CacheControlInterceptor implements Interceptor {

    private static final int CACHE_MAX_AGE_IN_SECONDS = 60;
    private static final int CACHE_MAX_STALE_IN_SECONDS = 900; // 15 mins

    private Context mContext;

    public CacheControlInterceptor(Context context) {
        mContext = context;
    }

    @Override
    public Response intercept(final Chain chain) throws IOException {
        Request original = chain.request();
        if (Utils.isNetworkAvailable(mContext)) {
            original = original.newBuilder()
                    .addHeader("Cache-Control", "public, max-age=" + CACHE_MAX_AGE_IN_SECONDS)
                    .build();
        } else {
            original = original.newBuilder()
                    .addHeader("Cache-Control", "public, only-if-cached, max-stale=" +
                            CACHE_MAX_STALE_IN_SECONDS)
                    .build();
        }
        return chain.proceed(original);
    }
}
