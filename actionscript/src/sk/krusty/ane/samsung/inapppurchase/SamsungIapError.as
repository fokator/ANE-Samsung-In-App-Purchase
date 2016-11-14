/**
 * Created by rjuhasz on 4. 11. 2016.
 */
package sk.krusty.ane.samsung.inapppurchase {
public class SamsungIapError extends Error {

	/**
	 * Success
	 */
	public static const IAP_ERROR_NONE:int = 0;

	/**
	 * Payment canceled
	 */
	public static const IAP_PAYMENT_IS_CANCELED:int = 1;

	/**
	 * Failure during IAP initialization
	 */
	public static const IAP_ERROR_INITIALIZATION:int = -1000;

	/**
	 * IAP upgrade is required
	 */
	public static const IAP_ERROR_NEED_APP_UPGRADE:int = -1001;

	/**
	 * Error while running IAP
	 */
	public static const IAP_ERROR_COMMON:int = -1002;

	/**
	 * Error when a non-consumable product is repurchased or a subscription product
	 * is repurchased before the product expiration date.
	 */
	public static const IAP_ERROR_ALREADY_PURCHASED:int = -1003;

	/**
	 * Error when payment is requested without bundle information.
	 */
	public static const IAP_ERROR_WHILE_RUNNING:int = -1004;

	/**
	 * Error when the requested item list is not available.
	 */
	public static const IAP_ERROR_PRODUCT_DOES_NOT_EXIST:int = -1005;

	/**
	 * If the payment result is not received after requesting payment to the server, the purchased item list should be
	 * confirmed because the payment may have occurred successfully. This error message appears in this situation.
	 */
	public static const IAP_ERROR_CONFIRM_INBOX:int = -1006;

	/**
	 * Error when item group ID does not exist.
	 */
	public static const IAP_ERROR_ITEM_GROUP_DOES_NOT_EXIST:int = -1007;

	/**
	 * Error when network is not available.
	 */
	public static const IAP_ERROR_NETWORK_NOT_AVAILABLE:int = -1008;

	/**
	 * IOException
	 */
	public static const IAP_ERROR_IOEXCEPTION_ERROR:int = -1009;

	/**
	 * SocketTimeoutException
	 */
	public static const IAP_ERROR_SOCKET_TIMEOUT:int = -1010;

	/**
	 * ConnectTimeoutException
	 */
	public static const IAP_ERROR_CONNECT_TIMEOUT:int = -1011;

	/**
	 * The Item is not for sale in the country.
	 */
	public static const IAP_ERROR_NOT_EXIST_LOCAL_PRICE:int = -1012;

	/**
	 * IAP is not serviced in the country
	 */
	public static const IAP_ERROR_NOT_AVAILABLE_SHOP:int = -1013;

	private var _extra:String;
	private var _dump:String;

	public function SamsungIapError(message:String, id:int, extra:String, dump:String)
	{
		super(message, id);

		_extra = extra;
		_dump = dump;
	}

	public function get extra():String
	{
		return _extra;
	}

	public function get dump():String
	{
		return _dump;
	}
}
}
