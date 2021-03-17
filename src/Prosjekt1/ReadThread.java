package Prosjekt1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class ReadThread extends Thread {
    //buffreader for reading from the stream
    private BufferedReader reader;
    private Socket socket;
    private client client;



    //constructor
    public ReadThread(Socket socket, client client) {
        this.socket = socket;
        this.client = client;

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
            while (true) {
                try {
                    //respons gets stored from the bufferedreader
                    //bufferreader needs a new line before next one can be read
                    String response = reader.readLine();
                    //need a \n for the reader so stop reading current line
                    //also get a seperator for easier reading
                    System.out.println("\n" + response);


                    // displays unsername in own console
                    //make it look stupid, or not understandable what the user types
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
