package Prosjekt1;

import java.io.*;
import java.net.Socket;

public class ReadThread extends Thread {
    //buffreader for reading from client
    private BufferedReader reader;
    private Socket socket;
    private client client;
    private bot bot;
    private InputStream input;
    private PrintWriter writer;

    //constructor
    public ReadThread(Socket socket, client client, bot bot) {
        this.socket = socket;
        this.client = client;
        this.bot = bot;
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

            //same as in writethread
            //try to see if messages are getting though
            while (true) {
                try {
                    //respons gets stored from the bufferedreader
                    //bufferreader needs a new line before next one can be read
                    String response = reader.readLine();

                    System.out.println("\n" + response);

                    // displays unsername in own console
                    if (client.getusername() != null) {
                        System.out.print("[" + client.getusername() + "]: ");
                    }
                } catch (IOException ex) {
                    System.out.println("Error reading from server: " + ex.getMessage());
                    break;
                }
            }
    }

}
