package interfaces;

import org.json.JSONObject;

public interface IJsonSerializable 
{
	/**
	 * An object can serialize state it wants to maintain
	 * into a JSONObject and return to called for storage
	 * @return A JSON object that can then be stored in memory or on file.
	 */
	JSONObject SaveToJsonObject();
	
	/**
	 * This Object can load state from a json object. This is expected
	 * to be used in conjuction with SaveToJsonObject.
	 * @see SaveToJsonObject
	 * @param obj
	 */
	void LoadFromJsonObject(JSONObject obj);
}
