package sk.krusty.ane.samsung.inapppurchase {
import flash.events.EventDispatcher;
import flash.events.StatusEvent;
import flash.external.ExtensionContext;
import flash.system.Capabilities;

import sk.krusty.ane.samsung.inapppurchase.event.SamsungIapErrorEvent;
import sk.krusty.ane.samsung.inapppurchase.event.SamsungIapEvent;
import sk.krusty.ane.samsung.inapppurchase.event.SamsungIapItemsInboxLoadedEvent;
import sk.krusty.ane.samsung.inapppurchase.event.SamsungIapItemsLoadedEvent;
import sk.krusty.ane.samsung.inapppurchase.event.SamsungIapPaymentEvent;
import sk.krusty.ane.samsung.inapppurchase.item.ItemInbox;
import sk.krusty.ane.samsung.inapppurchase.item.ItemVo;
import sk.krusty.ane.samsung.inapppurchase.item.Purchase;

[Event(name="SamsungIapEvent.INITIALIZED", type="sk.krusty.ane.samsung.inapppurchase.event.SamsungIapEvent")]
[Event(name="SamsungIapErrorEvent.ERROR", type="sk.krusty.ane.samsung.inapppurchase.event.SamsungIapErrorEvent")]
[Event(name="SamsungIapItemsInboxLoadedEvent.ITEMS_INBOX_LOADED", type="sk.krusty.ane.samsung.inapppurchase.event.SamsungIapItemsInboxLoadedEvent")]
[Event(name="SamsungIapItemsLoadedEvent.ITEMS_LOADED", type="sk.krusty.ane.samsung.inapppurchase.event.SamsungIapItemsLoadedEvent")]
[Event(name="SamsungIapPaymentEvent.PAYMENT", type="sk.krusty.ane.samsung.inapppurchase.event.SamsungIapPaymentEvent")]

/**
 * http://developer.samsung.com/iap
 *
 * Samsung In-App Purchase SDK Version : 4.0.1Sep 23, 2016
 */
public class SamsungInAppPurchaseANE extends EventDispatcher {

	/**
	 * Whether the ANE is supported on the current device or not.
	 * This ANE only works on Android.
	 */
	public static function isSupported():Boolean
	{
		return isAndroid();
	}

	public static function isIOS():Boolean
	{
		return Capabilities.manufacturer.indexOf('iOS') > -1;
	}

	public static function isAndroid():Boolean
	{
		return Capabilities.manufacturer.indexOf('Android') > -1;
	}

	// CONSTANTS
	public static const VERSION:String = "1.0.0";
	private static const EXTENSION_ID:String = "sk.krusty.ane.samsung.inapppurchase";

	private static const NATIVE_METHOD_INITIALIZE:String = "initialize_samsung_inapppurchase";
	private static const NATIVE_METHOD_GET_ITEMS:String = "get_items";
	private static const NATIVE_METHOD_GET_ITEMS_INBOX:String = "get_items_inbox";
	private static const NATIVE_METHOD_START_PAYMENT:String = "start_payment";

	// PROPERTIES
	private var _extContext:ExtensionContext;
	private var _debug:Boolean;

	private var _initialized:Boolean;

	public function get isInitialized():Boolean
	{
		return _initialized;
	}

	/**
	 * Creates the extension context if possible. Call <code>initialize()</code> before using the rest of the extension.
	 */
	public function SamsungInAppPurchaseANE()
	{
		trace("SamsungInAppPurchaseANE", VERSION);

		_extContext = ExtensionContext.createExtensionContext(EXTENSION_ID, null);
		if (_extContext != null) {

			_extContext.addEventListener(StatusEvent.STATUS, onStatusEvent);
		} else {

			trace("ERROR - Extension context is null. Please check if extension.xml is setup correctly.");
		}
	}

	/**
	 * Dispose extension context object.
	 *
	 * @return Return true if it success.
	 */
	public function dispose():Boolean
	{
		if (_extContext != null) {

			_extContext.removeEventListener(StatusEvent.STATUS, onStatusEvent);
			_extContext.dispose();

			return true;
		}
		return false;
	}

	//--------------------------------------------------------------------------
	//
	// public
	// Initializing
	//
	//--------------------------------------------------------------------------

	/**
	 * Initialize Extension.
	 *
	 * @see SamsungIapMode
	 *
	 * @param iapMode
	 */
	public function initialize(iapMode:int = 1):void
	{
		if (!isSupported())
			return;

		if (iapMode != SamsungIapMode.IAP_MODE_COMMERCIAL)
			trace("[SamsungInAppPurchaseANE] WARNING iapMode is in TESTING! iapMode:" + iapMode);

		_extContext.call(NATIVE_METHOD_INITIALIZE, iapMode);
	}

	/**
	 * Request the given products information. Dispatches ITEMS_LOADED event.
	 */
	public function getItemList(startNum:int, endNum:int, productType:String):void
	{
		if (!isSupported())
			return;

		if (_initialized)
			_extContext.call(NATIVE_METHOD_GET_ITEMS, startNum, endNum, productType);
	}

	/**
	 * Loading inbox list( or list of purchased item ) Dispatches ITEMS_INBOX_LOADED event.
	 *
	 * @param ids "item1,item2,item3,item58"
	 */
	public function getItemInboxList(ids:Array):void
	{
		if (!isSupported())
			return;

		if (_initialized)
			_extContext.call(NATIVE_METHOD_GET_ITEMS_INBOX, ids.join(","));
	}

	/**
	 * Start payment process by starting.
	 *
	 * @param itemId        The native product ID
	 */
	public function startPayment(itemId:String):void
	{
		if (!isSupported())
			return;

		if (_initialized)
			_extContext.call(NATIVE_METHOD_START_PAYMENT, itemId);
	}

	//--------------------------------------------------------------------------
	//
	//  private
	//
	//--------------------------------------------------------------------------

	private static const CODE_INITIALIZED:String = "EVENT_INITIALIZED";
	private static const CODE_ITEMS_LOADED:String = "EVENT_ITEMS_LOADED";
	private static const CODE_ITEMS_INBOX_LOADED:String = "EVENT_ITEMS_INBOX_LOADED";
	private static const CODE_ITEMS_ERROR:String = "EVENT_ITEMS_ERROR";
	private static const CODE_ITEMS_INBOX_ERROR:String = "EVENT_ITEMS_INBOX_ERROR";
	private static const CODE_ERROR:String = "EVENT_ERROR";
	private static const CODE_LOG:String = "EVENT_LOG";
	private static const CODE_PAYMENT:String = "EVENT_PAYMENT";
	private static const CODE_PAYMENT_ERROR:String = "EVENT_PAYMENT_ERROR";

	/**
	 * Called on each Status Event from the native code.
	 */
	private function onStatusEvent(event:StatusEvent):void
	{
		if (_debug) trace("onStatusEvent code:", event.code, "level:", event.level);

		switch (event.code) {
			case CODE_INITIALIZED:
				_initialized = true;
				dispatchEvent(new SamsungIapEvent(SamsungIapEvent.INITIALIZED));

				break;
			case CODE_PAYMENT:
				dispatchEvent(new SamsungIapPaymentEvent(
						SamsungIapPaymentEvent.PAYMENT, createPurchaseVo(event.level))
				);

				break;
			case CODE_ITEMS_LOADED:
				dispatchEvent(new SamsungIapItemsLoadedEvent(
						SamsungIapItemsLoadedEvent.ITEMS_LOADED, createItemsVo(event.level))
				);

				break;
			case CODE_ITEMS_INBOX_LOADED:
				dispatchEvent(new SamsungIapItemsInboxLoadedEvent(
						SamsungIapItemsInboxLoadedEvent.ITEMS_INBOX_LOADED, createItemsInboxVo(event.level))
				);

				break;
			case CODE_ITEMS_ERROR:
			case CODE_ITEMS_INBOX_ERROR:
			case CODE_ERROR:
			case CODE_PAYMENT_ERROR:

				var error:Error = null;
				try {
					var errorVo:Object = JSON.parse(event.level);
					error = new SamsungIapError(
							errorVo["message"], errorVo["code"], errorVo["extra"], errorVo["dump"]
					);

				} catch (e:Error) {
					error = e;
				}

				dispatchEvent(new SamsungIapErrorEvent(SamsungIapErrorEvent.ERROR, error, event.level));

				break;
			default :
		}
	}

	private static function createPurchaseVo(data:String):Purchase
	{
		return new Purchase(data);
	}

	private static function createItemsInboxVo(data:String):Vector.<ItemInbox>
	{
		var result:Vector.<ItemInbox> = new <ItemInbox>[];
		var itemsRaw:Array = JSON.parse(data) as Array;
		for (var i:int = 0; i < itemsRaw.length; i++) {
			result.push(new ItemInbox(itemsRaw[i]));
		}
		return result;
	}

	private static function createItemsVo(data:String):Vector.<ItemVo>
	{
		var result:Vector.<ItemVo> = new <ItemVo>[];
		var itemsRaw:Array = JSON.parse(data) as Array;
		for (var i:int = 0; i < itemsRaw.length; i++) {
			result.push(new ItemVo(itemsRaw[i]));
		}
		return result;
	}

	//--------------------------------------------------------------------------
	//
	//  debug
	//
	//--------------------------------------------------------------------------

	/**
	 * is in debug mode
	 */
	public function get debug():Boolean
	{
		return _debug;
	}

	/**
	 * set debug mode, traces
	 * @param value
	 */
	public function set debug(value:Boolean):void
	{
		_debug = value;
	}
}
}