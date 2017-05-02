package com.epam.method;

import com.epam.constants.CommonConstants;
import com.epam.constants.ResponseConstants;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class Response {

	private DataOutputStream os;
	private String version;
	private String statusCode;
	private String server;
	private String date;
	private String contentType;
	private String contentLength;
	private String body;

	public Response(DataOutputStream outputStream) {

		this.os = (outputStream);
	}

	public void write() throws IOException {
		String respose = "";
		Map<String, String> responseMap = new LinkedHashMap<String, String>();

		responseMap.put(version , statusCode);

		responseMap.put(ResponseConstants.SERVER, ResponseConstants.SERVER_VALUE);

		/*if (!contentType.isEmpty()) {
			responseMap.put(CommonConstants.CONTENT_TYPE, contentType + "\r\n");
		}*/

		if (!contentLength.isEmpty()) {
			responseMap.put(CommonConstants.CONTENT_LENGTH, contentLength + "\r\n");
		}

		responseMap.put(CommonConstants.CONNECTION, ResponseConstants.CONNECTION_VALUE);

		if (!body.isEmpty()) {
			responseMap.put(ResponseConstants.BODY, body);
		}

		for (Map.Entry<String, String> pair : responseMap.entrySet()) {
			String key = pair.getKey();
			String value = pair.getValue();
			if (key.equals(ResponseConstants.BODY)) {
				respose += value;
			} else
				respose += key + value;
		}
		os.writeBytes(respose);
		os.close();
	}

	@Override
	public String toString() {
		return "Response{" +
				"os=" + os +
				", version='" + version + '\'' +
				", statusCode='" + statusCode + '\'' +
				", server='" + server + '\'' +
				", date='" + date + '\'' +
				", contentType='" + contentType + '\'' +
				", contentLength='" + contentLength + '\'' +
				", body='" + body + '\'' +
				", connection='" + connection + '\'' +
				'}';
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getContentLength() {
		return contentLength;
	}

	public void setContentLength(String contentLength) {
		this.contentLength = contentLength;
	}

	private String connection;

	public String getConnection() {
		return connection;
	}

	public void setConnection(String connection) {
		this.connection = connection;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

}
