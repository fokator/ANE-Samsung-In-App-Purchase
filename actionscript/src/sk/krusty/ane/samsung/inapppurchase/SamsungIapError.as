/**
 * Created by rjuhasz on 4. 11. 2016.
 */
package sk.krusty.ane.samsung.inapppurchase {
public class SamsungIapError extends Error {
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
