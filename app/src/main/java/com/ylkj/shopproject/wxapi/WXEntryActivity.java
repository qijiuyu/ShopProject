package com.ylkj.shopproject.wxapi;

import android.os.Bundle;

import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tencent.wxop.stat.event.EventType;
import com.ylkj.shopproject.eventbus.EventStatus;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.util.Constant;
import org.greenrobot.eventbus.EventBus;

public class WXEntryActivity extends BaseActivity implements IWXAPIEventHandler {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IWXAPI api = WXAPIFactory.createWXAPI(this, Constant.APP_ID);
        api.handleIntent(getIntent(), this);
    }

    //微信发送的请求将回调到onReq方法
    public void onReq(BaseReq req) {
    }

    //发送到微信请求的响应结果
    public void onResp(BaseResp resp) {
        switch (resp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                //发送成功
                SendAuth.Resp sendResp = (SendAuth.Resp) resp;
                if (sendResp != null) {
                    final String code = sendResp.code;
//                    EventBus.getDefault().post(new EventType(EventStatus.WX_AUTH,code));
                }
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                //发送取消
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                //发送被拒绝
                break;
            default:
                //发送返回
                break;
        }
    }

}
