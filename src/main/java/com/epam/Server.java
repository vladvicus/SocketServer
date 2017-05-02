package com.epam;

import com.epam.constants.CommonConstants;
import com.epam.handler.Handler;
import com.epam.handler.IHandle;
import com.epam.handler.imphandler.DefHandler;
import com.epam.method.Request;
import com.epam.method.Response;
import com.epam.utils.MatcherUtils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private final List<Handler> handlers = new ArrayList<Handler>();
    private ExecutorService pool;
    private int port;
    private int sizeOfTreadPool;

    public Server() {
    }

    public Server(int port, int sizeOfThreadPool) {
        this.port = port;
        this.sizeOfTreadPool = sizeOfThreadPool;
    }

    @SuppressWarnings("resource")
    public void start() throws IOException {

        pool = Executors.newFixedThreadPool(sizeOfTreadPool);
        ServerSocket serSocket = new ServerSocket(port);
        while (true) {
            Socket sock = serSocket.accept();
            pool.submit(new SocketProcessor(sock));
        }
    }

    public void stop() {
        pool.shutdown();
    }

    public Handler findHendler(Request rq) {
        Handler defHandler = new Handler(CommonConstants.GET, null, new DefHandler());
        String metnodFromRequest = rq.getMethod();
        System.out.println(metnodFromRequest);
        String pathFromRequest = rq.getPath();
        System.out.println(pathFromRequest);


        for (Handler handler : handlers) {
            if (metnodFromRequest.equals(handler.getMethod()) && MatcherUtils.isMatches(handler.getUri(), pathFromRequest)) {
                return handler;
            }
        }
        return defHandler;
    }

    public void addHendler(String method, String path, IHandle handler) {
        handlers.add(new Handler(method, path, handler));
    }

    private class SocketProcessor implements Runnable {

        private Request rq;
        private Response rp;
        private Socket socket;

        public SocketProcessor(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {

                rq = new Request(new BufferedReader(new InputStreamReader(socket.getInputStream())));
             //   rq.setAccept("application/json");
                System.out.println(rq.toString());
                rp = new Response(new DataOutputStream(socket.getOutputStream()));

            } catch (IOException e1) {
                e1.printStackTrace();
            }


            Handler handlerForInvoke = findHendler(rq);
            try {
                handlerForInvoke.getiHandle().handle(rq, rp);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(rp.toString());

            try {
                this.socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
