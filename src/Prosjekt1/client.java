package Prosjekt1;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
//class for client to connect to server
public class client {
    private String hostname;
    private int port;
    private String userName;
    private int isbot;


    //constructor
    public client(String hostname, int port, int isbot) {
        this.hostname = hostname;
        this.port = port;
        this.isbot = isbot;
    }

    //goes of whenever a new client starts to connect.
    public void exe() {
        try {
            Socket socket = new Socket(hostname, port);
            System.out.println("Connected to server");
            System.out.println("Welcome the fully automated kingdom of bots\n"+
                    "If you need assistance simply type --help or --h\nTo exit a bye would suffice\n" +
                    "This server has a number 'users' included so feel free to chat! they are totally real and human");

            //start new reader and writer for each connected client
            new ReadThread(socket, this, null).start();
            new WriteThread(socket, this, null).start();

            //catches not finding host and any I/O errors.
        } catch (UnknownHostException i) {
                System.out.println("Server not found: " + i.getMessage());
        }catch (IOException e) {
            System.out.println("Error." +e.getMessage());
        }
    }
    //setter and getter for username hashset
    void setusername(String userName) {
        this.userName = userName;
    }
    String getusername() {
        return this.userName;
    }

public static void main (String [] args) throws IOException {
        //hostname and port must be used in the terminal when executing the program.
        //checks if more than 2 entries are present, if so then dont connect
        // java client hostname portnumber
    if (args.length < 2) return;
    String hostname = args[0];
    int port = Integer.parseInt(args[1]);
    int isbot = 0;

    //if conditions are met, start client and connect to server with exe
    client client = new client(hostname,port, isbot);
    client.exe();

}
}
