package Prosjekt1;

import java.io.*;
import java.net.Socket;
import java.lang.Thread;


public class UserThread extends Thread {
    private Socket socket;
    private server server;
    private PrintWriter writer;
    public static String clientMessage;

    public UserThread(Socket socket, server server) {
        this.socket = socket;
        this.server = server;
    }

    public static String getClientMessage() {
        return clientMessage;
    }

    public void run() {
            try  {
                //inputstream to read from the stream
                InputStream input = socket.getInputStream();
                //buffreader for reading from client
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                OutputStream output = socket.getOutputStream();
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
                //do this while message from client does not equal "bye"
                do {
                    //reads line and puts it on clientmessage
                    //servermessage is what the servers sends to all users except the one who sent it
                    clientMessage = reader.readLine();
                    //activates a certain "bot", not over sockets, but its a better solution so i will leave it in
                    if (clientMessage.equalsIgnoreCase("-AB2.0")) {
                        // if u want to exit mid mode
                        if (clientMessage.equalsIgnoreCase("-exit")) {
                                return;
                        }
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
                            server.foryoureyesonly(serverMessage, this);
                    }
                    if (clientMessage.equalsIgnoreCase("-rps")) {
                        // if u want to exit mid mode
                        if (clientMessage.equalsIgnoreCase("-exit")) {
                            return;
                        }
                       int word =  bot.random(2);
                        String reply;
                        if (word == 0) {
                            reply = "paper";
                        } else if (word==1) {
                            reply = "rock";
                        } else {
                            reply = "scissors";
                        }
                        server.foryoureyesonly("[RSP]: Rock, paper or scissors?",this);
                        clientMessage = reader.readLine();
                        if (clientMessage.equalsIgnoreCase("rock") || clientMessage.equalsIgnoreCase("paper") ||clientMessage.equalsIgnoreCase("scissors")){
                            server.foryoureyesonly("[RSP]: i picked "+reply,this);
                            server.foryoureyesonly("[RSP]: "+ rsp(clientMessage, reply),this);
                        } else {
                            server.foryoureyesonly("[RSP]: Thats not a valid response to RPS",this);
                            clientMessage = reader.readLine();
                        }

                    }
                    //print connected users
                    if (clientMessage.equals("-printusers")) {
                        printUsers();
                    }
                    //find bot, but only if connected
                    if (clientMessage.equals("-find")) {
                        server.foryoureyesonly("Who do you want to find? (I am case sensitive)",this);
                        clientMessage = reader.readLine();
                        boolean name = server.findname(clientMessage);
                        if (name) {
                            server.foryoureyesonly("["+clientMessage+"]: I'm here :)",this);
                        } else {
                            server.foryoureyesonly("Sorry, "+clientMessage+" is not here",this);
                        }
                    }

                    //send if null either way, seem to remember i need this one
                    else if (clientMessage.isBlank()){
                        //send message but not if blank
                        System.out.println("message "+clientMessage+ " is blank");
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


    static String rsp(String clientMessage, String reply) {
        if (reply.equalsIgnoreCase(clientMessage)){
            return "It's a tie!";
        } else if(reply.equals("rock") && clientMessage.equalsIgnoreCase("paper")) {
            return "You win!";
        } else if (reply.equals("rock") && clientMessage.equalsIgnoreCase("scissors")) {
            return "I win!";
        } else if (reply.equals("paper") && clientMessage.equalsIgnoreCase("rock")) {
            return "I win!";
        } else if (reply.equals("paper") && clientMessage.equalsIgnoreCase("scissors")) {
            return "You win!";
        } else if (reply.equals("scissors") && clientMessage.equalsIgnoreCase("paper")) {
            return "I win!";
        } else if (reply.equals("scissors") && clientMessage.equalsIgnoreCase("rock")) {
            return "You win!";
        } else {
            return "Ehhh i don't know";
        }
    }
    //checks if the server has any connected users, then prints out all who are connected
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
