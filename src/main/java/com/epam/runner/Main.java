package com.epam.runner;

import com.epam.Server;
import com.epam.constants.CommonConstants;
import com.epam.handler.imphandler.*;

import java.io.IOException;

public class Main {

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        Server webServer = new Server(8085, 20);
        webServer.addHendler(CommonConstants.GET, "/book", new GetAllBooks());
        webServer.addHendler(CommonConstants.GET, "/book/5", new GetCertainBook());
        webServer.addHendler(CommonConstants.GET, "/book/7", new GetCertainBook());
        webServer.addHendler(CommonConstants.GET, "/book/1", new GetCertainBook());
        webServer.addHendler(CommonConstants.GET, "/book/2", new GetCertainBook());
        webServer.addHendler(CommonConstants.POST, "/book", new AddBook());
        webServer.addHendler(CommonConstants.DELETE, "/book/2", new DeleteBook());
        webServer.addHendler(CommonConstants.DELETE, "/book/3", new DeleteBook());
        webServer.addHendler(CommonConstants.DELETE, "/book/5", new DeleteBook());
        webServer.addHendler(CommonConstants.DELETE, "/book/7", new DeleteBook());
        webServer.addHendler(CommonConstants.PUT, "/book", new UpdateBook());

        webServer.start();
    }
}
