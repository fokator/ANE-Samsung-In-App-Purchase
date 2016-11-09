package sk.krusty.ane.samsung.inapppurchase.utils;

import com.adobe.fre.FREObject;

public class FREConversionUtil {

    public static String toString(FREObject object) {
        try {
            if (object == null) {

                return null;
            }
            return object.getAsString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Integer toInt(FREObject object) {
        try {
            if (object == null) {

                return null;
            }
            return object.getAsInt();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
