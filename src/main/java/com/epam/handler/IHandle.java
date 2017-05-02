package com.epam.handler;

import java.io.IOException;

import com.epam.method.Request;
import com.epam.method.Response;

public interface IHandle {

	public void handle(Request rq, Response rp) throws IOException;

}
