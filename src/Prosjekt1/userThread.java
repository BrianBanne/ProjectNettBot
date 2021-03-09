package Prosjekt1;

import java.io.*;
import java.net.Socket;

public class userThread extends Thread{
    private Socket socket;
    private server server;
    private PrintWriter writer;
    public String clientMessage = null;


    public userThread(Socket socket, server server) {
        this.socket = socket;
        this.server = server;
    }

    public String getClientMessage() {
        return clientMessage;
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

            //prints all connected users and bots
            printUsers();
            //reads username from the buffreader
            String userName = reader.readLine();
            //adds user to server
            server.addusername(userName);

            //broadcasts that a new user has connected to all users.
            String serverMessage = "New user connected: " + userName;
            server.cast(serverMessage, this);

            //message client sends to server
            clientMessage= "";
            //do this while message from client does not equal "bye"
            do {
                //reads line and puts it on clientmessage
                //servermessage is what the servers sends to all users except the one who sent it
                clientMessage = reader.readLine();
                if (clientMessage.equals("hi") || clientMessage.equals("hello") || clientMessage.equals("hey")){
                    serverMessage = "["+bot.botname()+"]"+clientMessage+"!";
                    //this way not work
                    server.cast(serverMessage,this);
                } else {
                    //send message
                    serverMessage = "[" + userName + "]: " + clientMessage;
                    server.cast(serverMessage, this);
                }
            //if message is bye then close connection and kick user
            } while (!clientMessage.equals("bye"));
            //calls removeuser and removes that client
            server.removeuser(userName, this, null, null);
            socket.close();
            //casts message to all users
            serverMessage = userName + " has left the channel.";
            server.cast(serverMessage, this);

        } catch (IOException e) {
            System.out.println("Error in UserThread: " + e.getMessage());
            e.printStackTrace();
        }
    }

    //cheks if the server has any connected users, then prints out all who are connected
    //this method gets called when a new user connects so that person can tell who is online

    void printUsers() {
        if (server.hasUsers()) {
            writer.println("Connected users: " + server.getUserNames());
        } else {
            writer.println("No other users connected");
        }
        if (server.hasBots()) {
            writer.println("Connected bots: "+server.getBotnames());
        } else {
            writer.println("No bots are connected");
        }
    }


    //sends message to the client from server
    void sendMessage(String message) {
        writer.println(message);
    }

}
