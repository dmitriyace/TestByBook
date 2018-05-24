package com.company;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;

public class Client1 {
    private final static int port = 1111;

    public static void main(String... args) {
        Scanner in = new Scanner(System.in);
        CopyOnWriteArrayList<Pj> collection = new CopyOnWriteArrayList<>();
        while (true) {
            try {
                String command = in.nextLine();
                Socket s = new Socket("localhost", port);
                if (s.isConnected()) {
                    ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
                    ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());

                    if (command.equalsIgnoreCase("qq")) System.exit(0);
                    oos.writeObject(command);
                    ///////////////////////////////////////
                    Object o = ois.readObject();
                    if (o instanceof String)
                        System.out.println((String) o);
                    else {
                        collection = (CopyOnWriteArrayList<Pj>) o;
                        System.out.println(collection.size() > 0);
                    }
//                    if (command.startsWith("show")) {
//                        PjCol.show(collection);
//                    } else {
//                        if (!command.startsWith("start"))
//                            PjCol.commands(command);
//                    }


                } else throw new ConnectException();
            } catch (UnknownHostException e) {
                System.err.println("Host is incorrect");
            } catch (IOException e) {
                System.err.println("IO exception has come to us");
            } catch (ClassNotFoundException e) {
                System.err.println("R u sure 'bout da class?");
            }
        }
    }
}
