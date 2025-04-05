package chat.server.repository;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class ChatRepository implements ChatRepositoryInterface {
    private static final String LOG_FILE = "chat_log.txt";

    @Override
    public void writeLog(String message) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE, true))) {
            writer.write(message);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Ошибка записи в лог: " + e.getMessage());
        }
    }

    @Override
    public List<String> loadChatHistory() {
        try (BufferedReader reader = new BufferedReader(new FileReader(LOG_FILE))) {
            return reader.lines().collect(Collectors.toList());
        } catch (IOException e) {
            return List.of();
        }
    }
}
