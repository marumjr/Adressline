package app;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import app.exception.UnparsableAddressException;
import app.testcase.AddresslineTestCase;

public class AddresslineTest {

	public List<AddresslineTestCase> loadBaseTestCase() {
		List<AddresslineTestCase> listTestcases = new ArrayList<AddresslineTestCase>();
		listTestcases.add(new AddresslineTestCase("Winterallee 3", new String[]{"Winterallee", "3"}));
		listTestcases.add(new AddresslineTestCase("Musterstrasse 45", new String[]{"Musterstrasse", "45"}));
		listTestcases.add(new AddresslineTestCase("Blaufeldweg 123B", new String[]{"Blaufeldweg", "123B"}));

		listTestcases.add(new AddresslineTestCase("Am Bächle 23", new String[]{"Am Bächle", "23"}));
		listTestcases.add(new AddresslineTestCase("Auf der Vogelwiese 23 b", new String[]{"Auf der Vogelwiese", "23 b"}));

		listTestcases.add(new AddresslineTestCase("4, rue de la revolution", new String[]{"rue de la revolution", "4"}));
		listTestcases.add(new AddresslineTestCase("200 Broadway Av", new String[]{"Broadway Av", "200"}));
		listTestcases.add(new AddresslineTestCase("Calle Aduana, 29", new String[]{"Calle Aduana", "29"}));
		listTestcases.add(new AddresslineTestCase("Calle 39 No 1540", new String[]{"Calle 39", "1540"}));
		
		return listTestcases;
	}
	
	@Test
	public void adressSplitterBaseTest() {
		try {
			List<AddresslineTestCase> testcases = loadBaseTestCase();

			for (AddresslineTestCase testcase : testcases) {
				String[] processedResult = Adressline.adressSplitter(testcase.getInput());
				testcase.assertExpectedResult(processedResult);
			}
			
		} catch (UnparsableAddressException e) {
			Assert.fail("Unpredicted Exception: " + e.getMessage());
		}
	}

	public List<AddresslineTestCase> loadAdditionalTestCase() {
		List<AddresslineTestCase> listTestcases = new ArrayList<AddresslineTestCase>();
		
		listTestcases.add(new AddresslineTestCase("3415 Lobortis. Avenue", new String[]{"Lobortis. Avenue", "3415"}));
		listTestcases.add(new AddresslineTestCase("430-985 Eleifend St.", new String[]{"Eleifend St.", "430-985"}));
		listTestcases.add(new AddresslineTestCase("Ap #481-7473, Cum Rd.", new String[]{"Cum Rd.", "Ap #481-7473"}));
		
		return listTestcases;
	}
	
	@Test
	public void adressSplitterAdditionalTest() {
		try {
			List<AddresslineTestCase> testcases = loadAdditionalTestCase();

			for (AddresslineTestCase testcase : testcases) {
				String[] processedResult = Adressline.adressSplitter(testcase.getInput());
				testcase.assertExpectedResult(processedResult);
			}
			
		} catch (UnparsableAddressException e) {
			Assert.fail("Unpredicted Exception: " + e.getMessage());
		}
	}

	public List<AddresslineTestCase> loadAdditionalExceptionCase() {
		List<AddresslineTestCase> listTestcases = new ArrayList<AddresslineTestCase>();
		
		listTestcases.add(new AddresslineTestCase("Lobortis. Avenue", null));
		listTestcases.add(new AddresslineTestCase("430-985", null));
		listTestcases.add(new AddresslineTestCase("N 3787 O 3748934", null));
		
		return listTestcases;
	}
	
	@Test
	public void adressSplitterExceptionTest() {
		try {
			List<AddresslineTestCase> testcases = loadAdditionalExceptionCase();

			for (AddresslineTestCase testcase : testcases) {
				String[] processedResult = Adressline.adressSplitter(testcase.getInput());
				Assert.fail("Exception should've been thrown: [" + processedResult[0] + ", " + processedResult[1] + "]");
			}
			
		} catch (UnparsableAddressException e) {
			// It's excepted to throw this exception
		}
	}
	
}
