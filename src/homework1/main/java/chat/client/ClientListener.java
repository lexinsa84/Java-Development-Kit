package chat.client;

public interface ClientListener {
    void onLoginSuccess(String username);
    void onLoginError(String message);
    void onMessageReceived(String message);
}