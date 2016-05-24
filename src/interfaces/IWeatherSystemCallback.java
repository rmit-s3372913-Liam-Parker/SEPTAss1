package interfaces;

/**
 * Classes that implement this interface are capable of registering themselves
 * with the weather system and receiving updates on changes to data.
 * 
 * @author Liam
 *
 */
public interface IWeatherSystemCallback {
	/**
	 * Called when they system modifies data, or whenever else the user may need
	 * to refresh their view of the model.
	 */
	public void Refresh();
}
