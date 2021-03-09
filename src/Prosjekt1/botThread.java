package Prosjekt1;

import java.io.*;
import java.net.Socket;

public class botThread extends Thread{

    private Socket socket;
    private server server;
    private PrintWriter writer;
    public String botClientMessage = null;
    private bot bot;
    //constructor
    public botThread(Socket socket, server server) {
        this.socket = socket;
        this.server = server;
    }

    public String getBotclientmessage() {
        return botClientMessage;
    }
    public void run() {
        try {
            //inputstream to read from the stream
            InputStream input = socket.getInputStream();
            //buffreader for reading from client
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            OutputStream output = socket.getOutputStream();
            //writes output and autoflushes
            writer = new PrintWriter(output, true);

            //reads username from the buffreader
            // doesnt work, but im leaving it in.
            String botName = reader.readLine();
            //adds user to server
            server.addbotname(botName);

            //broadcasts that a new user has connected to all users.
            String serverMessage = "New bot connected: " + botName;
            server.botcast(serverMessage, this);

            //message client sends to server
            botClientMessage = "";
            //do this while message from client does not equal "bye"
            do {
                //reads line and puts it on clientmessage
                //servermessage message sent to server
                botClientMessage = reader.readLine();
                serverMessage = "[" + botName + "]: " + botClientMessage;
                server.botcast(serverMessage, this);

                //if message is bye then close connection and kick user
            }
            while (!botClientMessage.equals("remove "+botName));
            //calls removeuser and removes that client
            server.removeuser(null,null,botName, this);
            socket.close();
            //casts message to all users
            serverMessage = botName + " has been kicked :(";
            server.botcast(serverMessage, this);

        } catch (IOException e) {
            System.out.println("Error in UserThread: " + e.getMessage());
            e.printStackTrace();
        }
    }

    void sendMessage(String message) {
        writer.println(message);
    }

}
