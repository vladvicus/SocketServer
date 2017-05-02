package com.epam.handler.imphandler;

import com.epam.Store;
import com.epam.constants.CommonConstants;
import com.epam.constants.ResponseConstants;
import com.epam.handler.IHandle;
import com.epam.method.Request;
import com.epam.method.Response;
import com.epam.model.Book;
import com.epam.model.BooksPojo;
import com.epam.utils.jackson.JsonUtils;
import com.epam.utils.marshaller.MarshallerHelper;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

public class GetAllBooks implements IHandle {

    public void handle(Request rq, Response rp) throws IOException {
        String acceptType = rq.getAccept();

        try {
            response(rq, rp, acceptType);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    private void response(Request rq, Response rp, String acceptType) throws JAXBException {
        String body = "";
        if(Store.getBooks().isEmpty()){Store.getAllBook();}
        List<Book> books = Store.getBooks();

        rp.setVersion(rq.getVersion());
        rp.setStatusCode(ResponseConstants.STATUS_CODE_200_OK);
        rp.setContentType(rq.getAccept());

        BooksPojo book = new BooksPojo(books);

        if (acceptType.equals(CommonConstants.ACCEPT_TYPE_XML)) {

            StringWriter writer = new StringWriter();
            MarshallerHelper.marshall(book, writer);

            body = writer.toString();

            rp.setContentLength(String.valueOf(body.getBytes().length));
            rp.setBody(body);
        } else {
            body = JsonUtils.toJson(book);
            rp.setContentLength(String.valueOf(body.getBytes().length));
            rp.setBody(body);
        }

        try {
            rp.write();
        } catch (IOException e) {
            System.out.println("Exception in GetAllBooks!!!");
        }
    }
}
