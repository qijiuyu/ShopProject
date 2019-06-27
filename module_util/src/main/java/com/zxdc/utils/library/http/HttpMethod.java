package com.zxdc.utils.library.http;

import android.os.Handler;
import com.zxdc.utils.library.http.base.BaseRequst;
import com.zxdc.utils.library.http.base.Http;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class HttpMethod extends BaseRequst {

    /**
     * 获取最新的accessToken
     */
    public static void login(String mobile,String code, final Handler handler) {
        Map<String, String> map = new HashMap<>();
        map.put("mobile", mobile);
        map.put("smscode", code);
        Http.getRetrofit().create(HttpApi.class).login(map).enqueue(new Callback<ResponseBody>() {
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    BaseRequst.sendMessage(handler, HandlerConstant.GET_ACCESS_TOKEN_SUCCESS, response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                BaseRequst.sendMessage(handler, HandlerConstant.REQUST_ERROR, null);
            }
        });
    }



}
