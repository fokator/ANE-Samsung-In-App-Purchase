package sk.krusty.ane.samsung.inapppurchase.functions;

import android.content.Intent;
import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import sk.krusty.ane.samsung.inapppurchase.SamsungInAppPurchaseExtension;
import sk.krusty.ane.samsung.inapppurchase.activities.GetItemsInboxActivity;
import sk.krusty.ane.samsung.inapppurchase.utils.FREConversionUtil;

public class GetItemsInboxFunction implements FREFunction {

    @Override
    public FREObject call(FREContext context, FREObject[] args) {
        SamsungInAppPurchaseExtension.logToAS(":: " + getClass().getCanonicalName());

        String ids = FREConversionUtil.toString(args[0]);

        SamsungInAppPurchaseExtension.logToAS("     ids:" + ids);

        Intent intent = new Intent(context.getActivity(), GetItemsInboxActivity.class);
        intent.putExtra(GetItemsInboxActivity.ITEM_IDS, ids);
        context.getActivity().startActivity(intent);

        return null;
    }
}
