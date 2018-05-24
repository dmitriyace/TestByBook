package com.company;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.CopyOnWriteArrayList;

public class ThreadServer implements Runnable {
    private Socket client;
    private CopyOnWriteArrayList<Pj> collection;
//    private String way;
    String command;
    String answer;

    public ThreadServer(Socket client, CopyOnWriteArrayList<Pj> collection) {
        this.client = client;
        this.collection = collection;
    }

    @Override
    public void run() {
        try (ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(client.getInputStream());) {
            if (!client.isClosed()) {
                try {
                    command = (String) in.readObject();

                    while (!answer.equals("end") || !command.equals("q")) {
                        if (command.startsWith("pw")&&!command.startsWith("cont")) {
                            answer=Pj.send(command);
                            out.writeObject(answer);
                        }
                        if (command.startsWith("cont")){
//                            answer=Pj.send()
                        }
//                            PjCol.commands(command);

//                        CommandHandling.treat(answer);
                    }
//                    collection = PjCol.pjeys;

//                    out.writeObject(PjCol.pjeysSrt(collection));

                } catch (ClassNotFoundException e) {
                    out.writeObject("File handle mistake!!!");
                } catch (IllegalArgumentException e) {
                    out.writeObject("Command format trouble");
                } catch (SocketException se) {
                    System.err.println("client disconnected");
                } finally {
                    out.flush();
                    in.close();
                    out.close();
                    client.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
