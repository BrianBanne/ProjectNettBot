package Prosjekt1;

import java.io.*;
import java.net.Socket;

public class userThread extends Thread {
    private Socket socket;
    private server server;
    private PrintWriter writer;
    public static String clientMessage;
    public static String botMessage;
    private bot bot;

    public userThread(Socket socket, server server) {
        this.socket = socket;
        this.server = server;

    }

    public static String getClientMessage() {
        return clientMessage;
    }

    public void run() {


            try {
                //inputstream to read from the stream
                InputStream input = socket.getInputStream();
                //buffreader for reading from client
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));


                OutputStream output = socket.getOutputStream();
               // BufferedReader readcoming = new BufferedReader(new OutputStream(output));
                //writes output and autoflushes
                writer = new PrintWriter(output, true);

                //prints all connected users and bots
                //adds user to server
                //reads username from the buffreader
                printUsers();
                String userName = reader.readLine();
                server.addusername(userName);

                //broadcasts that a new user has connected to all users.
                String serverMessage = "New user connected: " + userName;
                server.cast(serverMessage, this);

                //message client sends to server
                clientMessage = "";
                botMessage = "";
                //do this while message from client does not equal "bye"
                do {
                    //reads line and puts it on clientmessage
                    //servermessage is what the servers sends to all users except the one who sent it
                    clientMessage = reader.readLine();
                    botMessage = "";
                    //System.out.println(clientMessage);

                    if (clientMessage.equalsIgnoreCase("Activate UrbanBot111")) {
                        if (clientMessage.equalsIgnoreCase("-exit")) {
                                return;
                        }
                            // gets casted to all but who cares
                            server.foryoureyesonly("Enter any word of your choosing, the funkier the better: ", this);
                            clientMessage = reader.readLine();
                            String[] splitarr = clientMessage.split(":");
                            String word = splitarr[0].toString();
                            if (word.contains(" ") || bot.urbandef(word) == null) {
                                server.foryoureyesonly("cant contain spaces, try again", this);
                                clientMessage = reader.readLine();
                            }
                            String clientMessagecopy = "\n[Urbanbot]: " + clientMessage + " ehhh? " + bot.urbandef(word) +
                                    ". Does that remind you of something?";
                            serverMessage = "[" + userName + "]: " + clientMessagecopy;
                            //bot.sendsignal(true);
                            server.foryoureyesonly(serverMessage, this);
                    }
                    if (clientMessage.equals("printusers")) {
                        printUsers();
                    }
                    //say hello from alice but only if Alice is connected
                    if (clientMessage.equals("findAlice")) {
                        boolean name = server.findname("Alice");
                        if (name == true) {
                            server.cast("[Alice]: hei",null);
                        }
                    }
                    else if (clientMessage.equals(null)){
                        //send message but not if blank
                        serverMessage = "[" + userName + "]: " + clientMessage;
                        server.cast(serverMessage, this);
                    } else {
                        serverMessage = "[" + userName + "]: " + clientMessage;
                        server.cast(serverMessage, this);
                    }
                    //if message is bye then close connection and kick user
                } while (!clientMessage.equals("bye"));
                //calls removeuser and removes that client
                server.removeuser(userName, this);
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
            writer.println("Connected users: " + Prosjekt1.server.getUserNames());
        } else {
            writer.println("No other users connected");
        }
    }
    //sends message to all other users.
    void sendMessage(String message) {
        writer.println(message);
    }

}
