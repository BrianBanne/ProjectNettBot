package Prosjekt1;

import java.io.*;
import java.net.Socket;

//reading userinput and sending it to the server
//runs until user types 'bye'
public class WriteThread extends Thread{
    //printwriter writes to server
    private PrintWriter writer;
    private Socket socket;
    private client client;
    private bot bot;
    private DataOutputStream out;
    private InputStream input;


    //constructor
    public WriteThread(Socket socket, client client, bot bot) {
        this.socket = socket;
        this.client = client;
        this.bot = bot;
    }

    public void run() {
        //check to find if this new user is bot or a client aka human

            //try catch to find if the outputstream got through
            try {
                OutputStream output = socket.getOutputStream();
                writer = new PrintWriter(output, true);
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
            Console console = System.console();
            //enter name for a more personal touch, sets username and writes it out
            String userName = console.readLine("\nEnter your name: ");
            client.setusername(userName);
            writer.println(userName);


            // a loop until the user writes bye
            //then closes if line = bye
            String line;
            do {
                line = console.readLine("[" + userName + "]: ");
                if (line.equals("--help")) {
                    System.out.println("List of commands are:\nprinthash - prints all users\n" +
                            "activate urbanbot - unleashes the beast\n-exit - let the bot sleep\nbye - leave the channel");
                }
                writer.println(line);
            } while (!line.equals("bye"));
            try {
                //closes connection
                writer.close();
                socket.close();
            } catch (IOException e) {
                System.out.println("Error writing to server: " + e.getMessage());
            }
    }
}
