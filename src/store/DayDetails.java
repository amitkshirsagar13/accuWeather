package store;

public class DayDetails implements Constants {

	private String time = null;
	private String icon = null;
	private String cond = null;
	private String hi = null;
	private String temp = null;
	private String realfeel = null;
	private String wind = null;
	private String windStats = null;
	private String uvIndex = null;
	private String stormProb = null;
	private String humidity = null;
	private String pressure = null;
	private String cloudCover = null;
	private String dewPoint = null;
	private String amountOfPrecipitation = null;
	private String visibility = null;
	private String city = null;
	private String ceiling = null;

	public String getHumidity() {
		return humidity;
	}

	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}

	public String getPressure() {
		return pressure;
	}

	public void setPressure(String pressure) {
		this.pressure = pressure;
	}

	public String getCloudCover() {
		return cloudCover;
	}

	public void setCloudCover(String cloudCover) {
		this.cloudCover = cloudCover;
	}

	public String getDewPoint() {
		return dewPoint;
	}

	public void setDewPoint(String dewPoint) {
		this.dewPoint = dewPoint;
	}

	public String getAmountOfPrecipitation() {
		return amountOfPrecipitation;
	}

	public void setAmountOfPrecipitation(String amountOfPrecipitation) {
		this.amountOfPrecipitation = amountOfPrecipitation;
	}

	public String getVisibility() {
		return visibility;
	}

	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getCond() {
		return cond;
	}

	public void setCond(String cond) {
		this.cond = cond;
	}

	public String getHi() {
		return hi;
	}

	public void setHi(String hi) {
		this.hi = hi;
	}

	public String getTemp() {
		return temp;
	}

	public void setTemp(String temp) {
		this.temp = temp;
	}

	public String getRealfeel() {
		return realfeel;
	}

	public void setRealfeel(String realfeel) {
		this.realfeel = realfeel;
	}

	public String getWind() {
		return wind;
	}

	public void setWind(String wind) {
		this.wind = wind;
	}

	public String getWindStats() {
		return windStats;
	}

	public void setWindStats(String windStats) {
		this.windStats = windStats;
	}

	public String getUvIndex() {
		return uvIndex;
	}

	public void setUvIndex(String uvIndex) {
		this.uvIndex = uvIndex;
	}

	public String getStormProb() {
		return stormProb;
	}

	public void setStormProb(String stormProb) {
		this.stormProb = stormProb;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCeiling() {
		return ceiling;
	}

	public void setCeiling(String ceiling) {
		this.ceiling = ceiling;
	}

	@Override
	public String toString() {
		return time + " Icon:" + icon + NL + time + " Temp:" + temp + NL + time
				+ " Feel:" + realfeel + NL + time + " Cond:" + cond + NL + time
				+ " Wind:" + wind + NL + time + " UV:" + uvIndex + NL + time
				+ " Humi:" + humidity + NL + time + " Press:" + pressure + NL
				+ time + " Visi:" + visibility + NL + time + " Cloud:"
				+ cloudCover + NL + time + " Dew:" + dewPoint + NL + time
				+ " Presp:" + amountOfPrecipitation + NL + time + " City:"
				+ city + NL + time + " Ceiling:" + ceiling;
	}

}
