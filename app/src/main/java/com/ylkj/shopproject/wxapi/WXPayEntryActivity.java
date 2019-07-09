package com.ylkj.shopproject.wxapi;

import android.content.Intent;
import android.os.Bundle;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.ylkj.shopproject.R;
import com.ylkj.shopproject.eventbus.EventBusType;
import com.ylkj.shopproject.eventbus.EventStatus;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.util.Constant;
import com.zxdc.utils.library.util.ToastUtil;
import org.greenrobot.eventbus.EventBus;
public class WXPayEntryActivity extends BaseActivity implements IWXAPIEventHandler {

    private IWXAPI api;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    	api = WXAPIFactory.createWXAPI(this, Constant.APP_ID);
        api.handleIntent(getIntent(), this);
    }

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		setIntent(intent);
        api.handleIntent(intent, this);
	}

	@Override
	public void onReq(BaseReq req) {
	}

	@Override
	public void onResp(BaseResp resp) {
		if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX ) {
			if (resp.errCode==0) {
				EventBus.getDefault().post(new EventBusType(EventStatus.WX_RECHARGE));
				ToastUtil.showLong(getString(R.string.payment_success));
			}else if(resp.errCode==-1) {
				ToastUtil.showLong(getString(R.string.payment_failed));
			}else{
				ToastUtil.showLong(getString(R.string.payment_has_been_cancelled));
			}
			finish();
		}
	}
}