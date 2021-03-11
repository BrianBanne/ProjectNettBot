package Prosjekt1;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class server {

    //sockets for server and client
    //this way someone can control serverside for a more personal touch if needed
    private Socket socket = null;
    private Socket socketbot = null;
    private int port;

    //hashset for storing usernames
    //hashset for storing userclients
    private Set<userThread> userThreads = new HashSet<>();
    private Set<String> userNames = new HashSet<>();
    private Set<botThread> botThreads = new HashSet<>();
    private Set<String> botnames = new HashSet<>();

    public server(int port) {
        this.port = port;
    }

    public void exe() {
        try (ServerSocket serversocket = new ServerSocket(port)) {
            System.out.println("Searching for connections on port: " + port);

            //always listening
            //something needs to change here
            while (true) {
                    socket = serversocket.accept();
                    System.out.println("New user connected");
                    userThread newuser = new userThread(socket, this,1);
                    userThreads.add(newuser);
                    newuser.start();

                    System.out.println("New bot connected");
                    botThread newbot = new botThread(socket, this);
                    botThreads.add(newbot);
                    newbot.start();
            }
        } catch (IOException e) {
            System.out.println("Error in connecting user: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        //port must be used in the terminal when executing the program.
        //java server portnumber
        // if more than 1 entry fail and exit
        if (args.length < 1) {
            System.out.println("Syntax: java ChatServer <port-number>");
            System.exit(0);
        }
        //sets port to userinput and starts server
        int port = Integer.parseInt(args[0]);
        if (port < 0) {
            System.out.println("Port must be greater than 0");
        } else {
            server server = new server(port);
            server.exe();
            // server.botexe();
        }
    }

    void cast(String mess, userThread notforthisuser) {
        for (userThread user : userThreads) {
            if (user != notforthisuser) {
                user.sendMessage(mess);
            }
        }
    }
    void botcast(String mess, botThread notforthisuser) {
        for (botThread bot : botThreads) {
            if (bot != notforthisuser) {
                bot.sendMessage(mess);
            }
        }
    }
        //adds username to hashset
    void addusername(String UserName) {
        userNames.add(UserName);
    }
    void addbotname(String BotName) {
        botnames.add(BotName);
    }
    //remove from hashset
    void removeuser(String userName, userThread user, String botname, botThread bot) {
        boolean removed = userNames.remove(userName);
        //easier to read
        if (userNames.remove(userName)) { // or removed == true
            userThreads.remove(user);
            //message for server only
            System.out.println(userName + " has left the channel");
        }
        if (botnames.remove(botname)) {
            botThreads.remove(bot);
            System.out.println(botname + " has been kicked");
        }
    }

    Set<String> getUserNames() {
        return this.userNames;
    }
    Set<String> getBotnames() {
        return this.botnames;
    }

    boolean hasUsers() {
        return !this.userNames.isEmpty();
    }
    boolean hasBots() {
        return !this.botnames.isEmpty();
    }
}
