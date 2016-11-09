package sk.krusty.ane.samsung.inapppurchase.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.samsung.android.sdk.iap.lib.helper.SamsungIapHelper;
import com.samsung.android.sdk.iap.lib.listener.OnGetItemListener;
import com.samsung.android.sdk.iap.lib.vo.ErrorVo;
import com.samsung.android.sdk.iap.lib.vo.ItemVo;
import sk.krusty.ane.samsung.inapppurchase.SamsungInAppPurchaseExtension;

import java.util.ArrayList;

public class GetItemsActivity extends Activity implements OnGetItemListener {

    public static final String START_NUM = "START_NUM";
    public static final String END_NUM = "END_NUM";
    public static final String ITEM_TYPE = "ITEM_TYPE";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SamsungInAppPurchaseExtension.logToAS(":: " + getClass().getCanonicalName() + " onCreate");

        Intent intent = getIntent();
        int startNum = intent.getIntExtra(START_NUM, 1);
        int endNum = intent.getIntExtra(END_NUM, 2);
        String itemType = intent.getStringExtra(ITEM_TYPE);

        SamsungIapHelper mIapHelper = SamsungIapHelper.getInstance(this, SamsungInAppPurchaseExtension.getIapMode());
        mIapHelper.getItemList(startNum, endNum, itemType, SamsungInAppPurchaseExtension.getIapMode(), this);
    }

    @Override
    public void onGetItem(ErrorVo _errorVO, ArrayList<ItemVo> _itemList) {
        SamsungInAppPurchaseExtension.logToAS("     onGetItem _inboxList:" + _itemList + " _errorVO:" + _errorVO);

        if (_errorVO != null) {
            SamsungInAppPurchaseExtension.logToAS("     ErrorCode:" + _errorVO.getErrorCode());
            if (_errorVO.getErrorCode() == SamsungIapHelper.IAP_ERROR_NONE) {
                // all is ok
                SamsungInAppPurchaseExtension.onGetItems(_itemList);
            } else {
                // ERROR
                SamsungInAppPurchaseExtension.onGetItemsError(_errorVO);
            }
        } else {
            // errorVO is null!
            SamsungInAppPurchaseExtension.onError("onGetItemsError: ErrorVO is null!");
        }

        finish();
    }
}
