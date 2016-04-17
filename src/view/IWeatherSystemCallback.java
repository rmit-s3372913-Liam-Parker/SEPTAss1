package view;

/**
 * Classes that implement this interface are capable
 * of registering themselves with the weather system
 * and receiving updates on changes to data.
 * @author Liam
 *
 */
public interface IWeatherSystemCallback 
{
	public void Refresh();
}
