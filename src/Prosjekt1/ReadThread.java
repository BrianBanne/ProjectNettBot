package Prosjekt1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class ReadThread extends Thread {
    //buffreader for reading from client
    private BufferedReader reader;
    private Socket socket;
    private client client;
    private bot bot;
    //constructor
    public ReadThread(Socket socket, client client, bot bot) {
        this.socket = socket;
        this.client = client;
        this.bot = bot;
        //same as in writethread
        //try to see if messages are getting though
        try {
            InputStream input = socket.getInputStream();
            reader = new BufferedReader(new InputStreamReader(input));
        } catch (IOException ex) {
            System.out.println("Error getting input stream: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    public void run() {
        //runs until false
        if (client != null && bot == null) {
            while (true) {
                try {
                    //respons gets stored from the bufferedreader
                    String response = reader.readLine();
                    //makes sure every line is on a new line
                    System.out.println("\n" + response);

                    // sets username displaying the server's message
                    if (client.getusername() != null) {
                        System.out.print("[" + client.getusername() + "]: ");
                    }
                } catch (IOException ex) {
                    System.out.println("Error reading from server: " + ex.getMessage());
                    break;
                }
            }
        } else {
            while (true) {
                try {
                    //respons gets stored from the bufferedreader
                    String response = reader.readLine();
                    //makes sure every line is on a new line
                    System.out.println("\n" + response);

                    // sets username displaying the server's message
                    if (bot.getBotNamee() != null) {
                        System.out.print("[" + bot.getBotNamee() + "]: ");
                    }
                } catch (IOException ex) {
                    System.out.println("Error reading from server: " + ex.getMessage());
                    break;
                }
            }
        }
    }
}
