package app.testcase;

import org.junit.Assert;

public class AddresslineTestCase {

	private String input;

	private String[] expectedResult;

	public AddresslineTestCase(String input, String[] expectedResult) {
		this.input = input;
		this.expectedResult = expectedResult;
	}

	public void assertExpectedResult(String[] actualResult) {
		Assert.assertArrayEquals(this.expectedResult, actualResult);
	}

	public String getInput() {
		return input;
	}

	public String[] getExpectedResult() {
		return expectedResult;
	}

}
