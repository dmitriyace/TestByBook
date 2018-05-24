package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class CBook {
    public void go() {
        try {
            Socket s = new Socket("localhost", 3000);

            InputStreamReader streamReader = new InputStreamReader(s.getInputStream());
            BufferedReader reader = new BufferedReader(streamReader);

            String advice = reader.readLine();
            System.out.println(advice + " said the cat.");



            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String... args) {
        CBook client = new CBook();
        client.go();
    }
}
