package app.splitter;

import java.util.Map;

public class NumberAtEndSplitter extends AddressSplitter {
	
	private static NumberAtEndSplitter instance;

	public static NumberAtEndSplitter getInstance() {
		if (instance == null) {
			instance = new NumberAtEndSplitter();
		}
		
		return instance;
	}
	
	@Override
	public boolean checkElegibility(String address) {
		boolean matches = address.trim().matches("^.* \\d+.*$");
		return matches;
	}

	@Override
	public Map<String, String> splitToMap(String address) {
		String streetName = "";
		String streetNo = "";
		
		boolean switchToNo = false;
		String[] pieces = address.split(" ");
		for (String piece : pieces) {
			char c = piece.charAt(0);
			
			if (switchToNo) {
				streetNo += piece + " ";
				
			} else {
				if (((c >= '0') && (c <= '9'))) {
					switchToNo = true;
					streetNo += piece + " ";
				
				} else {
					streetName += piece + " ";
				}
			}
			
		}
		
		this.setStreetName(streetName.trim());
		this.setStreetNumber(streetNo.trim());
		
		return getStreetElements();
	}
	
}
