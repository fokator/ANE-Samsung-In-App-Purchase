package sk.krusty.ane.samsung.inapppurchase.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.samsung.android.sdk.iap.lib.helper.SamsungIapHelper;
import com.samsung.android.sdk.iap.lib.listener.OnPaymentListener;
import com.samsung.android.sdk.iap.lib.vo.ErrorVo;
import com.samsung.android.sdk.iap.lib.vo.PurchaseVo;
import sk.krusty.ane.samsung.inapppurchase.SamsungInAppPurchaseExtension;

public class StartPaymentActivity extends Activity implements OnPaymentListener {

    public static final String PRODUCT_STORE_ID = "PRODUCT_STORE_ID";
    public static final String IAP_MODE = "IAP_MODE";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SamsungInAppPurchaseExtension.logToAS(":: " + getClass().getCanonicalName() + " onCreate");

        Intent intent = getIntent();
        String itemId = intent.getStringExtra(PRODUCT_STORE_ID);

        SamsungIapHelper mIapHelper = SamsungIapHelper.getInstance(this, SamsungInAppPurchaseExtension.getIapMode());
        mIapHelper.startPayment(itemId, true, this);

        SamsungInAppPurchaseExtension.logToAS("DONE .. StartPaymentActivity.onCreate");
    }

    @Override
    public void onPayment(ErrorVo _errorVO, PurchaseVo _purchaseVo) {
        SamsungInAppPurchaseExtension.logToAS("     onPayment _purchaseVo:" + _purchaseVo + " _errorVO:" + _errorVO);

        if (_errorVO != null) {
            SamsungInAppPurchaseExtension.logToAS("     ErrorCode:" + _errorVO.getErrorCode());
            if (_errorVO.getErrorCode() == SamsungIapHelper.IAP_ERROR_NONE) {
                // all is ok
                SamsungInAppPurchaseExtension.onPayment(_purchaseVo);
            } else {
                // ERROR
                SamsungInAppPurchaseExtension.onPaymentError(_errorVO);
            }
        } else {
            // errorVO is null!
            SamsungInAppPurchaseExtension.onError("onPayment: ErrorVO is null!");
        }

        finish();
    }
}
