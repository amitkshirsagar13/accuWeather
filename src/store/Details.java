package store;

public class Details implements Constants {
	private DayDetails _dayDetails = null;
	private DayDetails _nightDetails = null;
	private String _day = null;
	private String _iday = null;

	public Details(DayDetails dayDetails, DayDetails nightDetails) {
		_dayDetails = dayDetails;
		_nightDetails = nightDetails;
	}

	public String get_day() {
		return _day;
	}

	public void set_day(String _day) {
		this._day = _day;
	}

	public DayDetails get_dayDetails() {
		return _dayDetails;
	}

	public DayDetails get_nightDetails() {
		return _nightDetails;
	}

	@Override
	public String toString() {
		return "DayOfWeek:" + _day + NL + NL + _dayDetails.toString() + NL
				+ _nightDetails.toString();
	}

	public String toStringDayDetails() {
		return "DayOfWeek:" + _day + NL + NL + _dayDetails.toString();
	}

	public String get_iday() {
		return _iday;
	}

	public void set_iday(String _iday) {
		this._iday = _iday;
	}

}
