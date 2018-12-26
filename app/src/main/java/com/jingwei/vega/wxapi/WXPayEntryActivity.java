package com.jingwei.vega.wxapi;

import com.jingwei.vega.base.BaseActivity;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;

public class WXPayEntryActivity extends BaseActivity implements IWXAPIEventHandler {
    @Override
    public int getContentView() {
        return 0;
    }

    @Override
    public void initTitleBar() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
        if (baseResp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            switch (baseResp.errCode) {
                case 0:
                    showToast("支付成功");
                    finish();
                    break;
                case -1:
                    showToast("支付取消");
                    finish();
                    break;
                case -2:
                    showToast("请求失败");
                    finish();
                    break;
            }
            finish();
        }
    }
}
