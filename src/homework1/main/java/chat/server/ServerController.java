package chat.server;

import chat.server.repository.ChatRepositoryInterface;

import java.util.HashSet;
import java.util.Set;

public class ServerController {
    private final ServerListener listener;
    private final ChatRepositoryInterface chatRepository;
    private boolean isRunning;
    private final Set<String> connectedUsers;

    public ServerController(ServerListener listener, ChatRepositoryInterface chatRepository) {
        this.listener = listener;
        this.chatRepository = chatRepository;
        this.isRunning = false;
        this.connectedUsers = new HashSet<>();
    }

    public void startServer() {
        isRunning = true;
        listener.onServerMessage("Сервер запущен!");
    }

    public void stopServer() {
        isRunning = false;
        listener.onServerMessage("Сервер остановлен.");
    }

    public boolean isServerRunning() {
        return !isRunning;
    }

    public void userConnected(String username) {
        if (!connectedUsers.contains(username)) {
            connectedUsers.add(username);
            listener.onServerMessage("Пользователь '" + username + "' вошел в чат.");
        }
    }

    public void receiveMessage(String message) {
        chatRepository.writeLog(message);
        listener.onServerMessage(message);
    }
}


