package com.ruiyi.askandanswer.data.net;

import android.support.annotation.NonNull;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.http.HttpHeaders;

public class LoggerInterceptor implements Interceptor {

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        // 拦截请求，获取到该次请求的request
        Request request = chain.request();
        // 执行本次网络请求操作，返回response信息
        Response response = chain.proceed(request);
        ResponseBody responseBody = response.body();

        if (HttpHeaders.hasBody(response) && responseBody != null) {
            BufferedReader bufferedReader = new BufferedReader(new
                    InputStreamReader(responseBody.byteStream(), "utf-8"));
            String result;
            while ((result = bufferedReader.readLine()) != null) {
                Log.e("OkHttp", "response: " + result);
            }
            // 测试代码
            responseBody.string();
        }

        // 注意，这样写，等于重新创建Request，获取新的Response，避免在执行以上代码时，
        // 调用了responseBody.string()而不能在返回体中再次调用。
        return response.newBuilder().build();
    }

}