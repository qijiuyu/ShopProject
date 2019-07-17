package com.zxdc.utils.library.http.base;

import android.text.TextUtils;

import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.base.BaseApplication;
import com.zxdc.utils.library.util.LogUtils;
import com.zxdc.utils.library.util.SPUtil;

import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
/**
 * HTTP拦截器
 * Created by lyn on 2017/4/13.
 */
public class LogInterceptor implements Interceptor {

    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        long t1 = System.nanoTime();
        if (request.method().equals("POST")) {
            request = addParameter(request);
        }
        Response response = chain.proceed(request);
        long t2 = System.nanoTime();
        String body = response.body().string();
        try {
            if(!TextUtils.isEmpty(body)){
                JSONObject jsonObject=new JSONObject(body);
                if(!jsonObject.isNull("data") && jsonObject.getString("data").equals("")){
                    jsonObject.put("data",null);
                    body=jsonObject.toString();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        LogUtils.e(String.format("response %s in %.1fms%n%s", response.request().url(), (t2 - t1) / 1e6d, body));
        return response.newBuilder().body(ResponseBody.create(response.body().contentType(), body)).build();
    }


    /***
     * 添加公共参数
     */
    public Request addParameter(Request request) throws IOException {
        FormBody.Builder bodyBuilder = new FormBody.Builder();
        FormBody formBody;
        Map<String, String> requstMap = new HashMap<>();
        //添加token参数
        requstMap.put("token", SPUtil.getInstance(BaseApplication.getContext()).getString(SPUtil.TOKEN));
        LogUtils.e("token="+ SPUtil.getInstance(BaseApplication.getContext()).getString(SPUtil.TOKEN));
        if (request.body().contentLength() > 0 && request.body() instanceof FormBody) {
            formBody = (FormBody) request.body();
            //把原来的参数添加到新的构造器，（因为没找到直接添加，所以就new新的）
            for (int i = 0; i < formBody.size(); i++) {
                  requstMap.put(formBody.name(i), formBody.value(i));
                  LogUtils.e(request.url() + "参数:" + formBody.name(i) + "=" + formBody.value(i));
            }
        }
        //添加公共参数
        for (String key : requstMap.keySet()) {
            bodyBuilder.addEncoded(key, requstMap.get(key));
        }
        formBody = bodyBuilder.build();
        request = request.newBuilder().post(formBody).build();
        return request;
    }

}
