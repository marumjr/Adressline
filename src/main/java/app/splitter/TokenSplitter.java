package app.splitter;

import java.util.Map;

public class TokenSplitter extends AddressSplitter {

	/** Tokens commonly associated to a number in an address */
	private static final String[] NUMBER_TOKENS = new String[] { ", ", " no ", ", no ", ",no ", " #", ", #", ",#", " n.",
			", n.", ",n.", " n ", ", n ", ",n " };

	private static TokenSplitter instance;

	public static TokenSplitter getInstance() {
		if (instance == null) {
			instance = new TokenSplitter();
		}

		return instance;
	}

	@Override
	public boolean checkElegibility(String address) {
		for (String token : NUMBER_TOKENS) {
			if (address.toLowerCase().contains(token)) {
				return true;
			}
		}

		return false;
	}

	@Override
	public Map<String, String> splitToMap(String address) {
		for (String token : NUMBER_TOKENS) {
			if (address.toLowerCase().contains(token)) {
				// (?i) flag turns the RegEx case insensitive
				String[] pieces = address.split("(?i)" + token);

				int maxNoLetters = 0;
				int indexMaxNoLetters = 0;
				for (int x = 0; x < pieces.length; x++) {
					String piece = pieces[x];

					int noLetters = 0;
					for (int i = 0; i < piece.length(); i++) {
						char c = piece.charAt(i);
						if (((c >= 'A') && (c <= 'Z')) || ((c >= 'a') && (c <= 'z'))) {
							noLetters++;
						}
					}

					if (noLetters >= maxNoLetters) {
						maxNoLetters = noLetters;
						indexMaxNoLetters = x;
					}
				}

				String streetName = "";
				String streetNo = "";
				for (int x = 0; x < pieces.length; x++) {
					if (x == indexMaxNoLetters) {
						streetName = pieces[x].trim();

					} else {
						streetNo += pieces[x].trim();
						streetNo += " ";
					}
				}

				this.setStreetName(streetName);
				this.setStreetNumber(streetNo.trim());

				// Stop iterating
				break;
			}
		}

		return getStreetElements();
	}

}
