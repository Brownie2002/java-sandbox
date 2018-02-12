package brownie2002.fr.rds;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RDSRequestTest {

	RDSRequest request;

	@Before
	public void setUp() throws Exception {
		String responseType = "GLOBAL";
		List<String> layers = new ArrayList<String>();
		layers.add("GTOPO30");
		layers.add("SRTM");

		request = new RDSRequest(layers, responseType);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFormatRequest() {
		System.out.println(request.toString());
		assertEquals("Toto", request.toString());
	}

}
