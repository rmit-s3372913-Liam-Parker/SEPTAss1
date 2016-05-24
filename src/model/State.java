package model;

/**
 * A state enumeration storing the states of Australian territory. Used in
 * conjunction with the weather model to access weather stations among other
 * things.
 * 
 * @author Liam
 */
public enum State {
	Antarctica("Antarctica"), Canberra("Canberra"), NewSouthWales("New South Wales"), NorthernTerritory(
			"Northern Territory"), Queensland("Queensland"), SouthAustralia("South Australia"), Tasmania(
					"Tasmania"), Victoria("Victoria"), WesternAustralia("Western Australia");

	private String str;

	State(String str) {
		this.str = str;
	}

	/**
	 * Returns the string representation of this state as it appears in the BOM
	 * json API.
	 * 
	 * @return A string representation of the state.
	 */
	public String getString() {
		return str;
	}

	/**
	 * Returns The state enum of a given string
	 * 
	 * @param str
	 *            The string to convert
	 * @return The state or null if it doesn't exist
	 */
	public static State fromString(String str) {
		switch (str) {
		case "Antarctica":
			return State.Antarctica;
		case "Canberra":
			return State.Canberra;
		case "New South Wales":
			return State.NewSouthWales;
		case "Northern Territory":
			return State.NorthernTerritory;
		case "Queensland":
			return State.Queensland;
		case "South Australia":
			return State.SouthAustralia;
		case "Tasmania":
			return State.Tasmania;
		case "Victoria":
			return State.Victoria;
		case "Western Australia":
			return State.WesternAustralia;
		default:
			return null;
		}
	}
}
