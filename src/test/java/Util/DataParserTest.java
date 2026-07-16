package Util;

import static org.junit.jupiter.api.Assertions.*;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

class DataParserTest {

	@Test
	void checkProductTypeRecognizesBase() {
		assertEquals("Base", DataParser.checkProductType("some 平板布 label"));
	}

	@Test
	void checkProductTypeRecognizesJaquard() {
		assertEquals("Jaquard", DataParser.checkProductType("some 花布 label"));
	}

	@Test
	void checkProductTypeDefaultsToEmpty() {
		assertEquals("", DataParser.checkProductType("unrelated label"));
	}

	@Test
	void checkFinishingTypeCombinesAllMatchedCodes() {
		String result = DataParser.checkFinishingType("涂层 轧毛 定型");
		assertEquals("KC,NP,HS", result);
	}

	@Test
	void checkFinishingTypeSingleMatchHasNoTrailingComma() {
		assertEquals("SW", DataParser.checkFinishingType("砂洗"));
	}

	@Test
	void checkFinishingTypeNoMatchIsEmpty() {
		assertEquals("", DataParser.checkFinishingType("nothing recognizable"));
	}

	@Test
	void parseSafeDoubleParsesValidNumber() {
		JSONObject o = new JSONObject().put("weight", "12.5");
		assertEquals(12.5, DataParser.parseSafeDouble(o, "weight"));
	}

	@Test
	void parseSafeDoubleReturnsZeroForMissingKey() {
		JSONObject o = new JSONObject();
		assertEquals(0, DataParser.parseSafeDouble(o, "weight"));
	}

	@Test
	void parseSafeDoubleReturnsZeroForBlankValue() {
		JSONObject o = new JSONObject().put("weight", "   ");
		assertEquals(0, DataParser.parseSafeDouble(o, "weight"));
	}

	@Test
	void parseSafeDoubleReturnsZeroForNonNumeric() {
		JSONObject o = new JSONObject().put("weight", "N/A");
		assertEquals(0, DataParser.parseSafeDouble(o, "weight"));
	}
}
