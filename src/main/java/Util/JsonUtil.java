package Util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
	private static final ObjectMapper MAPPER = new ObjectMapper();

	private JsonUtil() {}

	// Serializes to JSON for embedding directly into a JSP <script> block (e.g. `var x = ${...};`).
	// Free-text DB fields (memo/note/comment/product code) can contain '<' — left unescaped, a value
	// containing "</script>" would let the browser's HTML parser close the script tag early, breaking
	// the page or enabling stored script injection. Neutralizing '<' as its unicode escape keeps the
	// JSON valid (it only ever appears inside quoted string values) while making that impossible.
	public static String toScriptSafeJson(Object value) throws JsonProcessingException {
		return MAPPER.writeValueAsString(value).replace("<", "\\u003c");
	}
}
