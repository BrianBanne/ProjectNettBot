package Prosjekt1;


public class Main {

    public static void main(String[] args) {

       String[] string = {"Bjarne","petter","bjørn"};
       if (bot.findbotname("Bjarne") == true) {
           System.out.println("yes");
       } else {
           System.out.println("nei");
       }
        System.out.println();


        System.out.println(server.getUserNames().contains("Alice"));

        System.out.println(server.findname("Brian"));
        //these all work

    }
}
