package haoran.bwie.com.lianxi.util;

import android.os.Handler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import haoran.bwie.com.lianxi.callback.MyCallback;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class OkhttpUtil {
    private Handler handler = new Handler();
    private final OkHttpClient okHttpClient;
    private static OkhttpUtil mInstance;
    private static OkhttpUtil okhttpUtil;

    public OkhttpUtil() {
        okHttpClient = new OkHttpClient.Builder().readTimeout(5, TimeUnit.SECONDS).build();

    }

    public static OkhttpUtil getInstance() {
        if (mInstance == null) {
            synchronized (OkhttpUtil.class) {
                if (mInstance == null) {
                    okhttpUtil = new OkhttpUtil();
                }
            }
        }
        return okhttpUtil;
    }

    public void doPost(String lujing, HashMap<String, String> params, final MyCallback myCallback) {
        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String, String> p : params.entrySet()) {
            builder.add(p.getKey(), p.getValue());
        }
        RequestBody build = builder.build();
        Request build1 = new Request.Builder().url(lujing).post(build).build();
        okHttpClient.newCall(build1).enqueue(new Callback() {



            //失败时
            @Override
            public void onFailure(Call call, IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        myCallback.falure("错误");
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                //通过handler从子线程跳转到主线程
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        myCallback.success(string);
                    }
                });
            }
        });

    }
}
