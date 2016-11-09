package sk.krusty.ane.samsung.inapppurchase.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.samsung.android.sdk.iap.lib.helper.SamsungIapHelper;
import sk.krusty.ane.samsung.inapppurchase.SamsungInAppPurchaseExtension;

public class InitializeActivity extends Activity {

    public static final String IAP_MODE = "IAP_MODE";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SamsungInAppPurchaseExtension.logToAS(":: " + getClass().getCanonicalName() + " onCreate");

        Intent intent = getIntent();
        int iapMode = intent.getIntExtra(IAP_MODE, SamsungIapHelper.IAP_MODE_TEST_SUCCESS);

        SamsungInAppPurchaseExtension.setIapMode(iapMode);

        SamsungInAppPurchaseExtension.logToAS("DONE .. StartPaymentActivity.onCreate");

        SamsungInAppPurchaseExtension.onInitialized();

        finish();
    }
}
