package com.company;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server1 {
    private final static int port = 1111;
    private final static int sizeOfPool = 5;
    private static CopyOnWriteArrayList<Pj> collection;
    private static ExecutorService executor = Executors.newFixedThreadPool(sizeOfPool);

    public static void main(String... args) {
        String path = "C:\\Users\\chist\\Documents\\itmo\\proga\\Labbbb\\src\\form.xml";
//        String path = "D:\\0лабы\\Программирование(вуз)\\6\\Labbbb\\src\\form.xml";
//        In.getPjeys(path, PjCollection.pjeys);
//        collection = PjCollection.pjeys;
        try {
            ServerSocket server = new ServerSocket(port);
            while (!server.isClosed()) {
                Socket client = server.accept();
                executor.execute(new ThreadServer(client, collection));
            }
        } catch (IOException e) {

        }

    }
}
