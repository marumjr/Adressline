package app.splitter;

import java.util.Map;

public class NumberAtBeginningSplitter extends AddressSplitter {
	
	private static NumberAtBeginningSplitter instance;

	public static NumberAtBeginningSplitter getInstance() {
		if (instance == null) {
			instance = new NumberAtBeginningSplitter();
		}
		
		return instance;
	}

	@Override
	public boolean checkElegibility(String address) {
		boolean matches = address.trim().matches("^\\d+[- ].*$");
		return matches;
	}

	@Override
	public Map<String, String> splitToMap(String address) {
		String streetName = "";
		String streetNo = "";
		
		boolean switchToName = false;
		String[] pieces = address.split(" ");
		for (String piece : pieces) {
			char c = piece.charAt(0);
			
			if (switchToName) {
				streetName += piece + " ";
				
			} else {
				if (((c >= 'A') && (c <= 'Z')) || ((c >= 'a') && (c <= 'z'))) {
					switchToName = true;
					streetName += piece + " ";
				
				} else {
					streetNo += piece + " ";
				}
			}
			
		}
		
		this.setStreetName(streetName.trim());
		this.setStreetNumber(streetNo.trim());
		
		return getStreetElements();
	}

}
