package Chat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatClient {
    JTextField tosend;
    BufferedReader reader;
    InputStreamReader streamReader;
    PrintWriter writer;
    Socket socket;

    public void go() {
        JFrame frame = new JFrame("Chat Client");
        JPanel panel = new JPanel();
        tosend = new JTextField(20);
        JButton btn = new JButton("Send");
        btn.addActionListener(new SendBtnListener());
        panel.add(tosend);
        panel.add(btn);
        frame.setContentPane(panel);
        setUpNetwork();
        frame.setSize(410, 500);
        frame.setVisible(true);

    }

    public void setUpNetwork() {
        try {
            socket = new Socket("localhost", 3000);
            writer = new PrintWriter(socket.getOutputStream());
            streamReader = new InputStreamReader(socket.getInputStream());
            reader = new BufferedReader(streamReader);
            System.out.println("setted up network");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public class SendBtnListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                writer.println(tosend.getText());
                writer.flush();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
            tosend.setText("");
            tosend.requestFocus();

        }
    }
    public static void main(String...args){
        new ChatClient().go();
    }
}

