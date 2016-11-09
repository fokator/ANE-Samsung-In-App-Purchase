/**
 * Created by rjuhasz on 2. 11. 2016.
 *
 *  ItemVo – Product Information
 */
package sk.krusty.ane.samsung.inapppurchase {
public class SamsungIapProductType {

	// Item Type
	// ------------------------------------------------------------------------
	// Consumable      : 00
	// Non Consumable  : 01
	// Subscription    : 02
	// All             : 10
	// ========================================================================
	public static const ITEM_TYPE_CONSUMABLE:String = "00";
	public static const ITEM_TYPE_NON_CONSUMABLE:String = "01";
	public static const ITEM_TYPE_SUBSCRIPTION:String = "02";
	public static const ITEM_TYPE_AUTO_RECURRING_SUBSCRIPTIONS:String = "03";
	public static const ITEM_TYPE_ALL:String = "10";
	// ========================================================================

	public function SamsungIapProductType()
	{
	}
}
}
