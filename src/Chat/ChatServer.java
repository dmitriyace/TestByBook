package Chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class ChatServer {

    CopyOnWriteArrayList clientsMsgLists;

    public class ClientHandler implements Runnable {
        BufferedReader reader;
        Socket skt;

        public ClientHandler(Socket clientSkt) {
            skt = clientSkt;
            InputStreamReader isReader = null;
            try {
                isReader = new InputStreamReader(skt.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            reader = new BufferedReader(isReader);
        }

        @Override
        public void run() {
            String message;
            try {
                while ((message = reader.readLine()) != null) {
                    System.out.println("got msg " + message);

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    void tellEveryone(String message) {
        Iterator it = clientsMsgLists.iterator();
        while (it.hasNext()) {
            PrintWriter writer = (PrintWriter) it.next();
            writer.println(message);
            writer.flush();

        }
    }

    public static void main(String... args) throws IOException {
        new ChatServer().go();
    }

    public void go() throws IOException {
        clientsMsgLists = new CopyOnWriteArrayList();
        ServerSocket serverSocket = new ServerSocket(3000);
        while (true) {
            Socket clientSkt = serverSocket.accept();
            PrintWriter writer = new PrintWriter(clientSkt.getOutputStream());
            clientsMsgLists.add(writer);

            Thread t = new Thread(new ClientHandler(clientSkt));
            t.start();
            System.out.println("connected");
        }
    }
}