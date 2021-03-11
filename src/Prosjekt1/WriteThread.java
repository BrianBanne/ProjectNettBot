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
    private bot bot;


    //constructor
    public WriteThread(Socket socket, client client, bot bot) {
        this.socket = socket;
        this.client = client;
        this.bot = bot;
        //try catch to find if the outputstream got through
        try {
            OutputStream output = socket.getOutputStream();
            writer = new PrintWriter(output, true);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void run() {
        if (this.client != null && this.bot == null) {
            Console console = System.console();
            //enter name for a more personal touch, sets username and writes it out
            String userName = console.readLine("\nEnter your name: ");
            client.setusername(userName);
            writer.println(userName);
            //string to store messages
            String line;
            // a loop until the user writes bye
            //then closes if line = bye
            do {
                line = console.readLine("[" + userName + "]: ");
                writer.println(line);

            } while (!line.equals("bye"));

            try {
                //closes connection
                socket.close();

            } catch (IOException e) {

                System.out.println("Error writing to server: " + e.getMessage());
            }
            //for the bots
        } else {
            Console console = System.console();
            //String botName = console.readLine("\nEnter the name of your bot: ");
            String botName =bot.getBotNamee();
            //bot.setbotname(botName);
            //writer.println(botName);

            String line;
            // a loop until the user writes bye
            //then closes if line = bye
            do {
                line = console.readLine("[" + botName + "]: ");
                writer.println(line);

            } while (!line.equals("bye"));

            try {
                //closes connection
                socket.close();

            } catch (IOException e) {

                System.out.println("Error writing to server: " + e.getMessage());
            }
        }

    }

}
