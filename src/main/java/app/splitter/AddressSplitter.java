package app.splitter;

import java.util.HashMap;
import java.util.Map;

import app.exception.UnparsableAddressException;

/**
 * Class responsible for splitting an address written into a single piece of
 * information into two detailed blocks: street name and street number
 * 
 * @author marumjr
 */
public abstract class AddressSplitter {

	protected static final String STREET_NAME = "streetName";
	protected static final String STREET_NUMBER = "streetNumber";

	protected Map<String, String> streetElements = new HashMap<String, String>();

	protected Map<String, String> getStreetElements() {
		return this.streetElements;
	}

	/**
	 * Check if the address is parsable by this instance of
	 * {@link AddressSplitter}
	 * 
	 * @param address
	 *            Address we need to check
	 * @return <code>true</code> if this instance is able to parse the address,
	 *         <code>false</code> otherwise
	 */
	public abstract boolean checkElegibility(String address);

	/**
	 * Split the Address and store its informations in a map
	 * 
	 * @param address
	 *            The address to split
	 * @return the map already configured
	 */
	public abstract Map<String, String> splitToMap(String address);

	/**
	 * Split the Address in an array of strings, so that the first element is
	 * the street name and the second element is the number plus supplementary
	 * information
	 * 
	 * @param address
	 *            The address we'd like to split
	 * @return the following array: [street name, street number]
	 */
	public String[] splitToArray(String address) {
		Map<String, String> streetElements = this.splitToMap(address);
		String street = streetElements.get(STREET_NAME);
		String streetNumber = streetElements.get(STREET_NUMBER);

		return new String[] { street, streetNumber };
	}

	/**
	 * Retrieve the correct instance of an implementation for
	 * {@link AddressSplitter}, according the the address provided
	 * 
	 * @param address
	 *            The address we'll try to pair with an implementation of this
	 *            class
	 * @return An instance of an implementation of this class
	 * @throws UnparsableAddressException
	 *             Thrown when the provided address cannot be parsed by any
	 *             implementation of this class
	 */
	public static AddressSplitter getCorrectInstance(String address) throws UnparsableAddressException {
		AddressSplitter instance = TokenSplitter.getInstance();
		if (instance.checkElegibility(address)) {
			return instance;
		}

		instance = NumberAtBeginningSplitter.getInstance();
		if (instance.checkElegibility(address)) {
			return instance;
		}

		instance = NumberAtEndSplitter.getInstance();
		if (instance.checkElegibility(address)) {
			return instance;
		}

		throw new UnparsableAddressException("The provided address could not be parsed: '%s'", address);
	}

	/**
	 * @return the value stored in streetElements[STREET_NAME]
	 */
	protected String getStreetName() {
		return this.streetElements.get(STREET_NAME);
	}

	/**
	 * @return the value stored in streetElements[STREET_NUMBER]
	 */
	protected String getStreetNumber() {
		return this.streetElements.get(STREET_NUMBER);
	}

	/**
	 * @param streetName
	 *            The new name of the street
	 * @return The streetElements already updated
	 */
	protected Map<String, String> setStreetName(String streetName) {
		this.streetElements.put(STREET_NAME, streetName);
		return this.streetElements;
	}

	/**
	 * @param streetNo
	 *            The new number (and other informations) of the street
	 * @return The streetElements already updated
	 */
	protected Map<String, String> setStreetNumber(String streetNo) {
		this.streetElements.put(STREET_NUMBER, streetNo);
		return this.streetElements;
	}

}
