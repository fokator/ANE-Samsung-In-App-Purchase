package sk.krusty.ane.samsung.inapppurchase.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.samsung.android.sdk.iap.lib.helper.SamsungIapHelper;
import com.samsung.android.sdk.iap.lib.listener.OnGetInboxListener;
import com.samsung.android.sdk.iap.lib.vo.ErrorVo;
import com.samsung.android.sdk.iap.lib.vo.InboxVo;
import sk.krusty.ane.samsung.inapppurchase.SamsungInAppPurchaseExtension;

import java.util.ArrayList;

public class GetItemsInboxActivity extends Activity implements OnGetInboxListener {

    public static final String ITEM_IDS = "ITEMI_DS";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SamsungInAppPurchaseExtension.logToAS(":: " + getClass().getCanonicalName() + " onCreate");

        Intent intent = getIntent();
        String _itemIds = intent.getStringExtra(ITEM_IDS);

        SamsungIapHelper mIapHelper = SamsungIapHelper.getInstance(this, SamsungInAppPurchaseExtension.getIapMode());
        mIapHelper.getItemInboxList(_itemIds, this);
    }

    @Override
    public void onGetItemInbox(ErrorVo _errorVO, ArrayList<InboxVo> _inboxList) {

        SamsungInAppPurchaseExtension.logToAS("     onGetItemInbox _inboxList:" + _inboxList + " _errorVO:" + _errorVO);

        if (_errorVO != null) {
            SamsungInAppPurchaseExtension.logToAS("     ErrorCode:" + _errorVO.getErrorCode());
            if (_errorVO.getErrorCode() == SamsungIapHelper.IAP_ERROR_NONE) {
                // all is ok
                SamsungInAppPurchaseExtension.onGetItemsInbox(_inboxList);
            } else {
                // ERROR
                SamsungInAppPurchaseExtension.onGetItemsInboxError(_errorVO);
            }
        } else {
            // errorVO is null!
            SamsungInAppPurchaseExtension.onError("onGetItemsInbox: ErrorVO is null!");
        }

        finish();
    }
}
