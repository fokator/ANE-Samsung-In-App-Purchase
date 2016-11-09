/**
 * Created by rjuhasz on 2. 11. 2016.
 */
package sk.krusty.ane.samsung.inapppurchase.event {
import flash.events.Event;

public class SamsungIapEvent extends Event {

	public static const INITIALIZED:String = "SamsungIapEvent.INITIALIZED";

	public function SamsungIapEvent(type:String, bubbles:Boolean = false, cancelable:Boolean = false)
	{
		super(type, bubbles, cancelable);
	}
}
}
