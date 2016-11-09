package sk.krusty.ane.samsung.inapppurchase.functions;

import android.content.Intent;
import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import sk.krusty.ane.samsung.inapppurchase.SamsungInAppPurchaseExtension;
import sk.krusty.ane.samsung.inapppurchase.activities.GetItemsActivity;
import sk.krusty.ane.samsung.inapppurchase.utils.FREConversionUtil;

public class GetItemsFunction implements FREFunction {

    @Override
    public FREObject call(FREContext context, FREObject[] args) {
        SamsungInAppPurchaseExtension.logToAS(":: " + getClass().getCanonicalName());

        Integer startNum = FREConversionUtil.toInt(args[0]);
        Integer endNum = FREConversionUtil.toInt(args[1]);
        Integer itemType = FREConversionUtil.toInt(args[2]);

        SamsungInAppPurchaseExtension.logToAS("     startNum:" + startNum + " endNum:" + endNum + " itemType:" + itemType);

        Intent intent = new Intent(context.getActivity(), GetItemsActivity.class);
        intent.putExtra(GetItemsActivity.START_NUM, startNum);
        intent.putExtra(GetItemsActivity.END_NUM, endNum);
        intent.putExtra(GetItemsActivity.ITEM_TYPE, itemType);
        context.getActivity().startActivity(intent);

        return null;
    }
}
