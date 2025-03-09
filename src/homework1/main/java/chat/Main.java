package chat;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        ServerWindow server =new ServerWindow();
        new ClientWindow(server);
    }
}

