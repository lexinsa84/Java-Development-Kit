package chat.server.repository;

import java.util.List;

public interface ChatRepositoryInterface {
    void writeLog(String message);
    List<String> loadChatHistory();
}