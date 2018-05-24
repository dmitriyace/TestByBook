package com.company;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SBook {
    public void go(){
        try{
            ServerSocket serverSocket = new ServerSocket(3000);
            while (true){
                Socket sock = serverSocket.accept();

                PrintWriter writer=new PrintWriter(sock.getOutputStream());
                String advice = "get up early in the mornong";
//                Thread.sleep(10000);
                writer.println(advice);
                writer.close();
                System.out.println("отправил и закрылся");

            }


        }catch (IOException e) {
            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        }
    }

    public static void main(String...args){
        SBook sbook = new SBook();
        sbook.go();
    }
}
