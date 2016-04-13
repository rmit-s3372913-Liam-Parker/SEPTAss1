package model;

/**
 * A state enumeration storing the states of Australian territory.
 * Used in conjunction with the weather model to access
 * weather stations among other things.
 * @author Liam
 */
public enum State 
{
	Antarctica("Antarctica"),
	Canberra("Canberra"),
	NewSouthWales("New South Wales"),
	NorthernTerritory("Northern Territory"),
	Queensland("Queensland"),
	SouthAustralia("South Australia"),
	Tasmania("Tasmania"),
	Victoria("Victoria"),
	WesternAustralia("Western Australia");
	
	private String str;
	
	State(String str)
	{
		this.str = str;
	}
	
	/**
	 * Returns the string representation of this state as
	 * it appears in the BOM json API.
	 * @return A string representation of the state.
	 */
	public String getString()
	{
		return str;
	}
}
