package Prosjekt1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class server {

    //sockets for server and client
    //this way someone can control serverside for a more personal touch if needed
    private Socket socket = null;
    private  int port;

    //hashset for storing usernames
    //hashset for storing userclients
    private Set<userThread> userThreads = new HashSet<>();
    private static Set<String> userNames = new HashSet<>();

    public server(int port) {
        this.port = port;
    }

    static boolean findname(String name) {
        return userNames.contains(name);
    }
    void shutdown() throws IOException {
        if (!socket.isClosed()) {
            socket.close();
        }
    }

    public void exe() throws IOException {
        try (ServerSocket serversocket = new ServerSocket(port)) {
            System.out.println("Searching for connections on port: " + port);
            Scanner keyboard = new Scanner(System.in);
            boolean serverclose = true;

            //always listening
            while (serverclose) {
                    socket = serversocket.accept();
                    System.out.println("New user connected");
                    userThread newuser = new userThread(socket, this);
                    userThreads.add(newuser);
                    newuser.start();
            }
        } catch (IOException e) {
            System.out.println("Error in connecting user: " + e.getMessage());
            e.printStackTrace();
            //if error, shutdown socket
            shutdown();
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

        }
    }

    void cast(String mess, userThread notforthisuser) {
        for (userThread user : userThreads) {
            if (user != notforthisuser) {
                user.sendMessage(mess);
            }
        }
    }
    void foryoureyesonly(String mess, userThread forthisuser) {
        for (userThread user : userThreads) {
            if (user == forthisuser) {
                user.sendMessage(mess);
            }
        }
    }

        //adds username to hashset
        static void addusername(String UserName) {
        userNames.add(UserName);
    }

    //remove from hashset
    void removeuser(String userName, userThread user) {
        boolean removed = userNames.remove(userName);
        //easier to read
        if (removed) { // or removed == true
            userThreads.remove(user);
            //message for server only
            System.out.println(userName + " has left the channel");
        }
    }


    static Set<String> getUserNames() {
        return userNames;
    }


    boolean hasUsers() {
        return !this.userNames.isEmpty();
    }


}
