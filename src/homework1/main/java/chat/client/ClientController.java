package chat.client;

import chat.server.ServerController;
import chat.server.repository.ChatRepositoryInterface;

public class ClientController {
    private final ClientListener clientListener;
    private final ChatRepositoryInterface chatRepository;
    private final ServerController serverController;
    private boolean isConnected = false;
    private String username;

    public ClientController(ClientListener clientListener, ChatRepositoryInterface chatRepository, ServerController serverController) {
        this.clientListener = clientListener;
        this.chatRepository = chatRepository;
        this.serverController = serverController;
    }

    public void login(String ip, String port, String username) {
        if (serverController.isServerRunning()) {
            clientListener.onLoginError("Ошибка: сервер не запущен!");
            return;
        }

        if (username.isEmpty()) {
            clientListener.onLoginError("Ошибка: введите имя пользователя!");
            return;
        }

        this.username = username;
        isConnected = true;
        clientListener.onLoginSuccess(username);

        for (String message : chatRepository.loadChatHistory()) {
            clientListener.onMessageReceived(message);
        }
    }

    public void sendMessage(String message) {
        if (!isConnected) {
            clientListener.onLoginError("Ошибка: сначала подключитесь к серверу!");
            return;
        }

        if (serverController.isServerRunning()) {
            clientListener.onMessageReceived("Ошибка: сервер отключен, сообщение не отправлено!");
            return;
        }

        if (!message.trim().isEmpty()) {
            String formattedMessage = username + ": " + message;
            clientListener.onMessageReceived(formattedMessage);
            serverController.receiveMessage(formattedMessage);
        }
    }
}


