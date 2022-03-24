# ProjectNettwork

Locally hosted chattingprogram in the terminal -- with bots if you'd like --

hello and welcome to my chatroom

You need to compile both server and client in a terminal to run this chatbot experience
this is all wrong, forgot to update readme
simply copy the file into any directory in your terminal with:
and run with: (if using a linux terminal
javac server.java
javac client.java

then to make the client and server connect to another
java server <portnumber>
java client <host> <portnumber>
java bot <localhost> <portnumber> <botname>
//without the <>

List of available bots are: Bjarne, Urbanbot, Knut, Lisa, Jensine, Anna, Alice
Please use the referred names for the bots. upper or lowercase doesnt matter.
You can choose names yourself, but then they wont recognize the conversation. its based on names

you need to do it in this order, or else the client wont have a server to connect to.
portnumber and host are not set to a specific value, but i would recommend
<localhost> for host and <5000> for portnumber. Remember the portnumber needs to be the same in each execution.

Any number of clients can connect.

--help for list of things you can do

my bots are stupid so please forgive them.
When initiating a conversation, don't get weirded out by their skills.
The conversations are mostly generic filler, but it can lead to some hilarious things imo.

If u want to suggest something, start with -suggestion and end with the activity/thing you want to suggest.

Due to the nature of the server, it will remain up until closed by admin.
So you can disconnect all users and still reconnect with others.
Ctrl+c for servershutdown.

The goal of this program was not for showcasing my botmaking skills, but rather networking.

And also dont try the Urbanbot more than 500 times, then i have to pay some API website.

There are two different ways to send messages, but they are more or less the same.

Printwriter send the message to the server, the the reader threads reads from the server and prints it out
to the users.
I could go on forever about why either method has its own pos/neg but i liked to keep the
methods and actions seperate.
The cast method in userThread is mostly used for calling on serverside bots, and funcs
like printusers and find bot.

it may look messy codewise, but efficient and clever code is not needed for this project. And the time is limited
May continiue in the future with cleanup etc.



 
