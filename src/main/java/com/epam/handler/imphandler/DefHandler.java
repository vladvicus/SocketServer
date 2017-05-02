package com.epam.handler.imphandler;

import com.epam.constants.ResponseConstants;
import com.epam.handler.IHandle;
import com.epam.method.Request;
import com.epam.method.Response;

import java.io.IOException;

public class DefHandler implements IHandle {

	public void handle(Request rq, Response rp) {

		System.out.println("DEfHandler!!!!!");

		rp.setVersion(rq.getVersion());
		System.out.println(rp.getVersion());
		rp.setStatusCode(ResponseConstants.STATUS_CODE_400_BAD_REQUEST);
		rp.setContentType(rq.getAccept());
		System.out.println(rp.getStatusCode());
		rp.setBody("No such resource!!!!");

		try {
			rp.write();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
