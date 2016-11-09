/**
 * Created by rjuhasz on 3. 11. 2016.
 */
package sk.krusty.ane.samsung.inapppurchase.item {
public class ItemInbox {
	private var _rawData:Object;

	public function ItemInbox(rawData:String)
	{
		_rawData = JSON.parse(rawData);
	}

	public function get paymentId():String
	{
		return _rawData["mPaymentId"];
	}

	public function get purchaseDate():String
	{
		return _rawData["mPurchaseDate"];
	}

	public function get purchaseId():String
	{
		return _rawData["mPurchaseId"];
	}

	/**
	 * The product ID for the product.
	 */
	public function get storeId():String
	{
		return _rawData["mItemId"];
	}

	/**
	 * Title of the product.
	 */
	public function get title():String
	{
		return _rawData["mItemName"];
	}

	/**
	 * Description of the product.
	 */
	public function get desc():String
	{
		return _rawData["mItemDesc"];
	}

	/**
	 * For example, if price is "€7.99", this returns "7.99".
	 */
	public function get priceAmount():String
	{
		return _rawData["mItemPrice"];
	}

	/**
	 * Formatted price of the item, including its currency sign.
	 * The price does not include tax.
	 */
	public function get formattedPrice():String
	{
		return _rawData["mItemPriceString"];
	}

	/**
	 * ISO 4217 currency code for price. For example, if price is specified
	 * in British pounds sterling, price_currency_code is "GBP".
	 */
	public function get priceCurrencyCode():String
	{
		return _rawData["mCurrencyCode"];
	}

	// Item Type
	// ------------------------------------------------------------------------
	// Consumable      : 00
	// Non Consumable  : 01
	// Subscription    : 02
	// All             : 10
	// ========================================================================
	public function get itemType():String
	{
		return _rawData["mType"];
	}

	public function get originalData():Object
	{
		return _rawData;
	}

	public function toString():String
	{
		return "[ItemInbox" +
				" storeId:'" + storeId + "'" +
				" title:'" + title + "'" +
				" formattedPrice:'" + formattedPrice + "'" +
				" paymentId:'" + paymentId + "'" +
				" purchaseId:'" + purchaseId + "'" +
				" itemType:'" + itemType + "'" +
				" ]";
	}

}
}
