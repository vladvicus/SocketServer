package com.epam.handler.imphandler;

import com.epam.constants.ResponseConstants;
import com.epam.handler.IHandle;
import com.epam.method.Request;
import com.epam.method.Response;

import java.io.IOException;

public class DefHandler implements IHandle {

	public void handle(Request rq, Response rp) {

		System.out.println("DEfHandler!!!!!");
		try {

			rp.setVersion(rq.getVersion());
			rp.setStatusCode(ResponseConstants.STATUS_CODE_400_BAD_REQUEST);
			rp.setContentType(rq.getAccept());
			rp.setContentLength("");
			rp.setBody("No such resource!!!!");
			rp.write();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
