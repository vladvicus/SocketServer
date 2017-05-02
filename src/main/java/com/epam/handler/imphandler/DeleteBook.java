package com.epam.handler.imphandler;

import com.epam.Store;
import com.epam.constants.ResponseConstants;
import com.epam.handler.IHandle;
import com.epam.method.Request;
import com.epam.method.Response;
import com.epam.model.Book;

import java.io.IOException;
import java.util.List;


public class DeleteBook implements IHandle {

    public void handle(Request rq, Response rp) throws IOException {
        String acceptType = rq.getAccept();

            response(rq, rp, acceptType);


    }

    private void response(Request rq, Response rp, String acceptType) {


        if (Store.getBooks().isEmpty()) {
            Store.getAllBook();
        }
        try {
            List<Book> books = Store.getBooks();
            int counter=0;
            for (Book singleBook : books){

                if (singleBook.getId() == rq.getDelBookId()) {
                counter++;
                    books.remove(singleBook);
                    System.out.println(books);
                    rp.setVersion(rq.getVersion());
                    rp.setStatusCode(ResponseConstants.STATUS_CODE_200_OK);
                    rp.setContentType(rq.getAccept());
                    rp.setContentLength("");
                    rp.setBody("Delete book with id-->" + rq.getDelBookId());
                    rp.write();
                    break;
                }
            }
            if(counter==0){
                rp.setVersion(rq.getVersion());
                rp.setStatusCode(ResponseConstants.STATUS_CODE_400_BAD_REQUEST);
                rp.setContentType(rq.getAccept());
                rp.setContentLength("");
                rp.setBody("No such Id in Store!!!" + rq.getDelBookId());
                rp.write();


            }
            System.out.println(Store.getBooks());
        }

           catch (Exception e){
               System.out.println("Exception in DELETE!!");

        }

    }


}
