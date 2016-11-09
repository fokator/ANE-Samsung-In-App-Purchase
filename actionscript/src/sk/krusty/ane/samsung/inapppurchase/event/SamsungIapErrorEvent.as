/**
 * Created by rjuhasz on 3. 11. 2016.
 */
package sk.krusty.ane.samsung.inapppurchase.event {
import flash.events.Event;

public class SamsungIapErrorEvent extends Event {

	public static const ERROR:String = "SamsungIapErrorEvent.ERROR";
	private var _rawData:String;
	private var _error:Error;
	
	public function SamsungIapErrorEvent(type:String, error:Error, rawData:String)
	{
		super(type, false, false);
		_error = error;

		_rawData = rawData;
	}

	public function get rawData():String
	{
		return _rawData;
	}

	public function get error():Error
	{
		return _error;
	}
}
}
