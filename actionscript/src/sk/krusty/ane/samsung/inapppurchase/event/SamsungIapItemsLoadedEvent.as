/**
 * Created by rjuhasz on 2. 11. 2016.
 */
package sk.krusty.ane.samsung.inapppurchase.event {
import flash.events.Event;

import sk.krusty.ane.samsung.inapppurchase.item.ItemVo;

public class SamsungIapItemsLoadedEvent extends Event {

	public static const ITEMS_LOADED:String = "SamsungIapItemsLoadedEvent.ITEMS_LOADED";
	private var _items:Vector.<ItemVo>;

	public function SamsungIapItemsLoadedEvent(type:String, items:Vector.<ItemVo>, bubbles:Boolean = false, cancelable:Boolean = false)
	{
		super(type, bubbles, cancelable);

		_items = items;
	}

	public function get items():Vector.<ItemVo>
	{
		return _items;
	}
}
}
