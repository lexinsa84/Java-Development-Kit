package chat;

import chat.client.ClientWindow;
import chat.server.repository.ServerWindow;

public class Main {
    public static void main(String[] args) {
        ServerWindow serverWindow = new ServerWindow();
        new ClientWindow(serverWindow.getServerController());
    }
}

