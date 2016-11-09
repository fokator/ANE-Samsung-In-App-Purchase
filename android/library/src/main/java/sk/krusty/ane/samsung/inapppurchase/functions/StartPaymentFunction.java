package sk.krusty.ane.samsung.inapppurchase.functions;

import android.content.Intent;
import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import sk.krusty.ane.samsung.inapppurchase.SamsungInAppPurchaseExtension;
import sk.krusty.ane.samsung.inapppurchase.activities.StartPaymentActivity;
import sk.krusty.ane.samsung.inapppurchase.utils.FREConversionUtil;

public class StartPaymentFunction implements FREFunction {

    @Override
    public FREObject call(FREContext context, FREObject[] args) {
        SamsungInAppPurchaseExtension.logToAS(":: " + getClass().getCanonicalName());

        String storeId = FREConversionUtil.toString(args[0]);

        SamsungInAppPurchaseExtension.logToAS("     storeId:" + storeId);

        Intent intent = new Intent(context.getActivity(), StartPaymentActivity.class);
        intent.putExtra(StartPaymentActivity.PRODUCT_STORE_ID, storeId);
        context.getActivity().startActivity(intent);

        return null;
    }
}
