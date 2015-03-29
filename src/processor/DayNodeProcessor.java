package processor;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import store.Constants;
import store.DayDetails;
import store.Details;

public class DayNodeProcessor implements Constants {

	private Node _inputNode = null;

	public DayNodeProcessor(Node inputNode) {
		this._inputNode = inputNode;
	}

	private final DayDetails _dayDetails = new DayDetails();
	private final DayDetails _nightDetails = new DayDetails();

	public Details processNode() {

		processIcon(getRequiredNode(DAY), DAY);
		processIcon(getRequiredNode(NIGHT), NIGHT);
		processTemp(getRequiredNode(DAY), DAY);
		processTemp(getRequiredNode(NIGHT), NIGHT);
		processFeel(getRequiredNode(DAY), DAY);
		processFeel(getRequiredNode(NIGHT), NIGHT);
		processCond(getRequiredNode(DAY), DAY);
		processCond(getRequiredNode(NIGHT), NIGHT);
		processWind(getRequiredNode(DAY), DAY);
		processWind(getRequiredNode(NIGHT), NIGHT);

		_dayDetails.setTime("Day");
		_nightDetails.setTime("Night");
		return new Details(_dayDetails, _nightDetails);
	}

	/**
	 * This will be day or night. <div class=NIGHT> or <div class=DAY>
	 * 
	 * @param time
	 */

	private Node getRequiredNode(String time) {
		NodeList childNodes = ((Document) _inputNode)
				.getElementsByTagName("div");
		Node requiredNode = null;

		for (int index = 0; index < childNodes.getLength(); index++) {
			if (childNodes.item(index).getAttributes().getNamedItem(classAttr)
					.getNodeValue().startsWith(time)) {
				requiredNode = childNodes.item(index);
				break;
			}
		}
		return requiredNode;
	}

	private void processCond(Node requiredNode, String time) {
		Node tempNode = ProcessorHelper.getRequiredNodeForAttribute(
				requiredNode, "span", "class", "cond");
		if (time.equalsIgnoreCase(DAY)) {
			_dayDetails.setCond(tempNode.getTextContent().trim());
		} else {
			_nightDetails.setCond(tempNode.getTextContent().trim());
		}
	}

	private void processTemp(Node requiredNode, String time) {
		Node tempNode = ProcessorHelper.getRequiredNodeForAttribute(
				requiredNode, "span", "class", "temp");
		if (time.equalsIgnoreCase(DAY)) {
			_dayDetails.setTemp(ProcessorHelper.getTempNodeText(tempNode));
		} else {
			_nightDetails.setTemp(ProcessorHelper.getTempNodeText(tempNode));
		}
	}

	private void processFeel(Node requiredNode, String time) {
		Node tempNode = ProcessorHelper.getRequiredNodeForAttribute(
				requiredNode, "span", "class", "real");
		if (time.equalsIgnoreCase(DAY)) {
			_dayDetails.setRealfeel(ProcessorHelper.getTempNodeText(tempNode));
		} else {
			_nightDetails
					.setRealfeel(ProcessorHelper.getTempNodeText(tempNode));
		}
	}

	private void processIcon(Node requiredNode, String time) {
		Node iconNode = ProcessorHelper.getRequiredNodeForAttribute(
				requiredNode, "div", "class", "icon");

		String icon = iconNode.getAttributes().getNamedItem("class")
				.getNodeValue().replaceAll("icon i-", "")
				.replaceAll("i-alarm", "");
		// + "-l";

		if (iconNode.getAttributes().getNamedItem("class").getNodeValue()
				.replaceAll("icon i-", "").contains("alarm")) {
			icon = icon + "-alarm";
		}
		icon = icon.replaceAll(" ", "");
		// System.out.println(iconNode.getAttributes().getNamedItem("class")
		// .getNodeValue()
		// + " --> " + icon + ".png");
		if (time.equalsIgnoreCase(DAY)) {
			_dayDetails.setIcon(icon + ".png");
		} else {
			_nightDetails.setIcon(icon + ".png");
		}
	}

	private void processWind(Node requiredNode, String time) {
		Node ulStats = ProcessorHelper.getRequiredNodeForAttribute(
				ProcessorHelper.getRequiredNodeForAttribute(ProcessorHelper
						.getRequiredNodeForAttribute(requiredNode, "div",
								"class", "rt"), "ul", "class", "stats"),
				"strong", "style", "");

		if (time.equalsIgnoreCase(DAY)) {
			_dayDetails.setWind(ulStats.getTextContent().trim());
		} else {
			_nightDetails.setWind(ulStats.getTextContent().trim());
		}

	}
}
