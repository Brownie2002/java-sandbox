package brownie2002.fr.rds;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

public class RDSRequest {

	private enum RequestProps {
		intersects, response_type, layers
	}

	private Map<RequestProps, Object> request;

	public RDSRequest(List<String> layers, String responseType) {

		request = new EnumMap<RequestProps, Object>(RequestProps.class);

		request.put(RequestProps.layers, layers);
		request.put(RequestProps.response_type, responseType);

		Gson json = new Gson();

		System.out.println(json.toJson(request));
	}

}
