package app;

import java.util.Scanner;

import app.exception.UnparsableAddressException;
import app.splitter.AddressSplitter;

public class Adressline {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		while (true) {
			try {
				System.out.println("Type in an address:");
				String address = in.nextLine();
				
				if ("TERMINATE".equals(address.toUpperCase())) {
					break;
				}
				
				String[] streetInfos = adressSplitter(address);
				String output = String.format("[%s, %s]", streetInfos[0], streetInfos[1]);

				System.out.println(output);

			} catch (UnparsableAddressException e) {
				System.out.println(e.getMessage());
			}
		}

		in.close();
	}

	public static String[] adressSplitter(String address) throws UnparsableAddressException {
		AddressSplitter instance = AddressSplitter.getCorrectInstance(address);
		return instance.splitToArray(address);
	}

}
