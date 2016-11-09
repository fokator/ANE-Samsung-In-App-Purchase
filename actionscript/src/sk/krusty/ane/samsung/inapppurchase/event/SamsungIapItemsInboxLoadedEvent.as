/**
 * Created by rjuhasz on 2. 11. 2016.
 */
package sk.krusty.ane.samsung.inapppurchase.event {
import flash.events.Event;

import sk.krusty.ane.samsung.inapppurchase.item.ItemInbox;

public class SamsungIapItemsInboxLoadedEvent extends Event {

	public static const ITEMS_INBOX_LOADED:String = "SamsungIapItemsInboxLoadedEvent.ITEMS_INBOX_LOADED";
	private var _items:Vector.<ItemInbox>;

	public function SamsungIapItemsInboxLoadedEvent(type:String, items:Vector.<ItemInbox>, bubbles:Boolean = false, cancelable:Boolean = false)
	{
		super(type, bubbles, cancelable);

		_items = items;
	}

	public function get items():Vector.<ItemInbox>
	{
		return _items;
	}
}
}
