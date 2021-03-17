package Prosjekt1;

import java.io.Console;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

//reading userinput and sending it to the server
//runs until user types 'bye'
public class WriteThread extends Thread{
    //printwriter writes to server
    private PrintWriter writer;
    private Socket socket;
    private client client;



    //constructor
    public WriteThread(Socket socket, client client) {
        this.socket = socket;
        this.client = client;
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
        try {
            WriteThread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
            // a loop until the user writes bye
            //then closes if line = bye
            String line;
            do {
                line = console.readLine("[" + userName + "]: ");
                if (line.equals("--help")) {
                    System.out.println("List of commands are:\n-printusers - prints all users\n" +
                            "-AB2.0 - unleashes the beast\n-exit - let the beast sleep\n-leaveall - disconnects all bots\nlets talk - initiates a random conversation with the bots" +
                            "\nbye - leave the channel\n-find - finds users if he/she is present\n-AB1.0 - the actual bot version of urbanbot\n-suggestion + what you suggest to do\n" +
                            "-rps - play rock, paper or scissors vs my RPS bot");
                }
                writer.println(line);

            } while (!line.equals("bye"));
            try {
                //does not need to close out and inputstream
                //socket.close does that for me in java
                socket.close();
            } catch (IOException e) {
                System.out.println("Error writing to server: " + e.getMessage());
            }
    }
}
