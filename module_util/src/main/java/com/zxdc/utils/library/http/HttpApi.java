package com.zxdc.utils.library.http;

import java.util.Map;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface HttpApi {

    @FormUrlEncoded
    @POST(HttpConstant.LOGIN)
    Call<ResponseBody> login(@FieldMap Map<String, String> map);


}
