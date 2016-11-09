package sk.krusty.ane.samsung.inapppurchase.functions;

import android.content.Intent;
import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import sk.krusty.ane.samsung.inapppurchase.SamsungInAppPurchaseExtension;
import sk.krusty.ane.samsung.inapppurchase.activities.InitializeActivity;
import sk.krusty.ane.samsung.inapppurchase.utils.FREConversionUtil;

public class InitializeFunction implements FREFunction {

    @Override
    public FREObject call(FREContext context, FREObject[] args) {
        SamsungInAppPurchaseExtension.logToAS(":: " + getClass().getCanonicalName());

        Integer iapMode = FREConversionUtil.toInt(args[0]);

        SamsungInAppPurchaseExtension.logToAS("     iapMode:" + iapMode);

        Intent intent = new Intent(context.getActivity(), InitializeActivity.class);
        intent.putExtra(InitializeActivity.IAP_MODE, iapMode);
        context.getActivity().startActivity(intent);

        return null;
    }
}
