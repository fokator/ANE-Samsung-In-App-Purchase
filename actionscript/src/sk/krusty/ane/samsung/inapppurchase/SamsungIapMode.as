/**
 * Created by rjuhasz on 2. 11. 2016.
 *
 * This is the mode for production. When releasing your application, the service must be set to production mode.
 * The actual payment process occurs only in production mode.
 *
 */
package sk.krusty.ane.samsung.inapppurchase {

public class SamsungIapMode {

	// IAP Mode
	// ========================================================================
	/** Test mode for development. Always return true. */
	public static const IAP_MODE_TEST_SUCCESS:int = 1;

	/** Test mode for development. Always return failed. */
	public static const IAP_MODE_TEST_FAIL:int = -1;

	/** Production mode. Set mode to this to charge for your item. */
	public static const IAP_MODE_COMMERCIAL:int = 0;

	public function SamsungIapMode()
	{
	}
}
}
