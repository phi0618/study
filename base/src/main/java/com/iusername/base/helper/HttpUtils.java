package com.iusername.base.helper;
import java.io.IOException;

import com.iusername.base.listener.HttpCallBack;
import com.iusername.base.utils.JsonUtils;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpUtils {
    private HttpUtils() {
    }

    public void post(String url, Object params, HttpCallBack callBack) {
        OkHttpClient client = new OkHttpClient.Builder().build();
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), JsonUtils.toJson(params));
        Request request =new Request.Builder().url(url).post(body).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
    }
}
