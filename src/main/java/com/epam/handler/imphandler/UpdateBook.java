package com.epam.handler.imphandler;

import com.epam.Store;
import com.epam.constants.CommonConstants;
import com.epam.constants.ResponseConstants;
import com.epam.handler.IHandle;
import com.epam.method.Request;
import com.epam.method.Response;
import com.epam.model.Book;
import com.epam.utils.jackson.JsonUtils;
import com.epam.utils.marshaller.MarshallerHelper;

import java.io.IOException;

/**
 * Created by Администратор on 25.04.2017.
 */
public class UpdateBook implements IHandle {


    public void handle(Request rq, Response rp) throws IOException {
        boolean isMap = true;
        rp.setVersion(rq.getVersion());

        Book bookCreate = null;
        String body = "";
        try {
            if (rq.getAccept().equals(CommonConstants.ACCEPT_TYPE_XML)) {
                System.out.println("--------------->" + rq.getBody());

                bookCreate = (Book) (MarshallerHelper.unmarshall(rq.getBody(), Book.class)); // bookCreate.getClass()));
                System.out.println("!!!!!!!!!!!" + bookCreate.toString());

                rp.setContentLength(String.valueOf(rq.getBody().getBytes().length));
                rp.setStatusCode(ResponseConstants.STATUS_CODE_201_CREATED);
                rp.setBody(rq.getBody());
                rp.write();

            } else if (rq.getAccept().equals(CommonConstants.ACCEPT_TYPE_JSON)) {

                bookCreate = JsonUtils.fromJson(rq.getBody(), Book.class);

                rp.setContentLength(String.valueOf(body.getBytes().length));
                rp.setStatusCode(ResponseConstants.STATUS_CODE_201_CREATED);
                rp.write();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            rp.setStatusCode(ResponseConstants.STATUS_CODE_400_BAD_REQUEST);
            isMap = false;
        }

        if (isMap) {
            if (Store.getBooks().isEmpty()) {
                Store.getAllBook();
            }
            if (Store.updateBook(bookCreate)) {
                System.out.println(Store.getBooks());
            } else {
                rp.setVersion(rq.getVersion());
                rp.setStatusCode(ResponseConstants.STATUS_CODE_400_BAD_REQUEST);
                rp.setContentType(rq.getAccept());
                rp.setBody("No such Id in Store!!!Can't update!!!" + bookCreate.getId());
                rp.write();
               }
        }

    }
}

