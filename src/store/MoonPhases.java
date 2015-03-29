package store;

public class MoonPhases {

	private String _icon = null;
	private String _heading = null;

	public String get_icon() {
		return _icon;
	}

	public void set_icon(String _icon) {
		this._icon = _icon;
	}

	public String get_heading() {
		return _heading;
	}

	public void set_heading(String _heading) {
		this._heading = _heading;
	}

	String NL = "\n";

	@Override
	public String toString() {
		return "Moon Details: " + NL + NL + "Heading: " + _heading + NL
				+ "Icon:" + _icon;
	}

}
