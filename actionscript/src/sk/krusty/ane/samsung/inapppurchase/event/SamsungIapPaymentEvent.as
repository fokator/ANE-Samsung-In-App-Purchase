/**
 * Created by rjuhasz on 4. 11. 2016.
 */
package sk.krusty.ane.samsung.inapppurchase.event {
import flash.events.Event;

import sk.krusty.ane.samsung.inapppurchase.item.Purchase;

public class SamsungIapPaymentEvent extends Event {

	public static const PAYMENT:String = "SamsungIapPaymentEvent.PAYMENT";
	
	private var _purchase:Purchase;

	public function SamsungIapPaymentEvent(type:String, purchase:Purchase, bubbles:Boolean = false, cancelable:Boolean = false)
	{
		super(type, bubbles, cancelable);
		_purchase = purchase;
	}

	public function get purchase():Purchase
	{
		return _purchase;
	}
}
}
