package Util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

class JsonUtilTest {

	@Test
	void neutralizesClosingScriptTag() throws Exception {
		String json = JsonUtil.toScriptSafeJson("</script><script>alert(1)</script>");
		assertFalse(json.contains("</script>"),
				"a literal </script> inside a JSON string must not survive, or it would close the enclosing <script> block");
		// the '<' character is replaced by its backslash-u unicode escape sequence, so no
		// literal '<' remains for the HTML parser to recognize as the start of a closing
		// tag; '>' needs no escaping since the tag-close scan only triggers on '<' first.
		assertTrue(json.contains("\\u003c/script>"));
	}

	@Test
	void preservesRegularContent() throws Exception {
		String json = JsonUtil.toScriptSafeJson("plain memo, no special chars");
		assertEquals("\"plain memo, no special chars\"", json);
	}

	@Test
	void producesValidJsonForCollections() throws Exception {
		String json = JsonUtil.toScriptSafeJson(List.of("a", "b"));
		assertEquals("[\"a\",\"b\"]", json);
	}

	@Test
	void handlesNull() throws Exception {
		assertEquals("null", JsonUtil.toScriptSafeJson(null));
	}
}
