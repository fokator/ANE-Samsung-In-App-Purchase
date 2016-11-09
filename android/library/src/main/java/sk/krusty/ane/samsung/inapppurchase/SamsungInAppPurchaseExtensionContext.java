package sk.krusty.ane.samsung.inapppurchase;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import sk.krusty.ane.samsung.inapppurchase.functions.GetItemsFunction;
import sk.krusty.ane.samsung.inapppurchase.functions.GetItemsInboxFunction;
import sk.krusty.ane.samsung.inapppurchase.functions.InitializeFunction;
import sk.krusty.ane.samsung.inapppurchase.functions.StartPaymentFunction;

import java.util.HashMap;
import java.util.Map;

public class SamsungInAppPurchaseExtensionContext extends FREContext {

    public SamsungInAppPurchaseExtensionContext() {
        super();
    }

    private static final String NATIVE_METHOD_INITIALIZE = "initialize_samsung_inapppurchase";
    private static final String NATIVE_METHOD_GET_ITEMS = "get_items";
    private static final String NATIVE_METHOD_GET_ITEMS_INBOX = "get_items_inbox";
    private static final String NATIVE_METHOD_START_PAYMENT = "start_payment";

    /**
     * Declares the functions mappings.
     */
    @Override
    public Map<String, FREFunction> getFunctions() {
        Map<String, FREFunction> functions = new HashMap<String, FREFunction>();

        functions.put(NATIVE_METHOD_INITIALIZE, new InitializeFunction());
        functions.put(NATIVE_METHOD_START_PAYMENT, new StartPaymentFunction());
        functions.put(NATIVE_METHOD_GET_ITEMS, new GetItemsFunction());
        functions.put(NATIVE_METHOD_GET_ITEMS_INBOX, new GetItemsInboxFunction());

        SamsungInAppPurchaseExtension.log(functions.size() + " extension functions declared.");

        return functions;
    }

    /**
     * Disposes the extension context instance.
     */
    @Override
    public void dispose() {

    }
}
