package sk.krusty.ane.samsung.inapppurchase;

import android.util.Log;
import com.adobe.fre.FREContext;
import com.adobe.fre.FREExtension;
import com.samsung.android.sdk.iap.lib.helper.SamsungIapHelper;
import com.samsung.android.sdk.iap.lib.vo.ErrorVo;
import com.samsung.android.sdk.iap.lib.vo.InboxVo;
import com.samsung.android.sdk.iap.lib.vo.ItemVo;
import com.samsung.android.sdk.iap.lib.vo.PurchaseVo;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * The main extension file. Mainly instantiates the ExtensionContext.
 */
public class SamsungInAppPurchaseExtension implements FREExtension {

    /**
     * A reference to the extension context.
     */
    private static SamsungInAppPurchaseExtensionContext context;

    private static SamsungIapHelper helper;
    private static int iapMode;

    public static void setIapMode(int iapMode) {
        SamsungInAppPurchaseExtension.logToAS("[SamsungInAppPurchaseExtension] setIapMode:" + iapMode);
        SamsungInAppPurchaseExtension.iapMode = iapMode;
    }

    public static int getIapMode() {
        return iapMode;
    }

    /**
     * Creates a new extension context.
     */
    @Override
    public FREContext createContext(String type) {
        context = new SamsungInAppPurchaseExtensionContext();
        return context;
    }

    @Override
    public void dispose() {
        log("Extension disposed.");
    }

    @Override
    public void initialize() {
        log("Extension initialized.");
    }

    /**
     * Initialized handler.
     */
    public static void onInitialized() {
        context.dispatchStatusEventAsync(SamsungInAppPurchaseMessages.INITIALIZED, "done");
    }

    public static void onPayment(PurchaseVo purchaseVo) {
        context.dispatchStatusEventAsync(SamsungInAppPurchaseMessages.PAYMENT, purchaseVo.getJsonString());
    }

    public static void onPaymentError(ErrorVo errorVO) {
        context.dispatchStatusEventAsync(SamsungInAppPurchaseMessages.PAYMENT_ERROR, createJSONError(errorVO));
    }

    public static void onGetItems(ArrayList<ItemVo> itemList) {
        JSONArray jsonArray = new JSONArray();
        for (ItemVo itemVo : itemList) {
            SamsungInAppPurchaseExtension.logToAS(" - " + itemVo.getItemId() + " " + itemVo.getType());
            jsonArray.put(itemVo.getJsonString());
        }

        context.dispatchStatusEventAsync(SamsungInAppPurchaseMessages.ITEMS_LOADED, jsonArray.toString());
    }

    public static void onGetItemsInbox(ArrayList<InboxVo> inboxList) {
        JSONArray jsonArray = new JSONArray();
        for (InboxVo inboxVo : inboxList) {
            SamsungInAppPurchaseExtension.logToAS(" - " + inboxVo.getItemId() + " " + inboxVo.getType());
            jsonArray.put(inboxVo.getJsonString());
        }

        context.dispatchStatusEventAsync(SamsungInAppPurchaseMessages.ITEMS_INBOX_LOADED, jsonArray.toString());
    }

    public static void onGetItemsError(ErrorVo errorVO) {
        context.dispatchStatusEventAsync(SamsungInAppPurchaseMessages.ITEMS_ERROR, createJSONError(errorVO));
    }

    public static void onGetItemsInboxError(ErrorVo errorVO) {
        context.dispatchStatusEventAsync(SamsungInAppPurchaseMessages.ITEMS_INBOX_ERROR, createJSONError(errorVO));
    }

    public static void onError(String message) {
        context.dispatchStatusEventAsync(SamsungInAppPurchaseMessages.ERROR, message);
    }

    private static String createJSONError(ErrorVo errorVO) {

        JSONObject error = new JSONObject();

        try {
            error.put("code", errorVO.getErrorCode());
            error.put("message", errorVO.getErrorString());
            error.put("extra", errorVO.getExtraString());
            error.put("dump", errorVO.dump());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return error.toString();
    }

    /**
     * The logging TAG.
     */
    private static final String TAG = "SamsungInAppPurchaseExtension";

    /**
     * Logs the given message at info level.
     */
    public static void log(String message) {
        Log.i(TAG, message);
    }

    /**
     * Logs the given message at warning level.
     */
    public static void logW(String message) {
        Log.w(TAG, message);
    }

    /**
     * Logs the given message at error level.
     */
    public static void logE(String message) {
        Log.e(TAG, message);
    }

    /**
     * Logs the given message to the ActionScript part using a StatusEvent, if the context
     * exists. If it does not exist, does nothing.
     */
    public static void logToAS(String message) {
        log(message);

        if (context != null) {
            try {

                context.dispatchStatusEventAsync(SamsungInAppPurchaseMessages.LOG, "[" + TAG + "] : " + message);
            } catch (Exception e) {
                logE("Can't log to flash no context!");
            }
        }
    }

    /**
     * Returns a complete formated String stack trace for the given Exception.
     */
    public static String getStackString(Exception e) {
        String stackTrace = e.toString() + "\n";
        StackTraceElement[] stackArray = e.getStackTrace();
        int i, n = stackArray.length;
        for (i = 0; i < n; i++) {
            stackTrace += ("	" + stackArray[i].toString() + "\n");
        }

        return stackTrace;
    }
}
