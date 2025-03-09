package chat;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class ServerWindow extends JFrame {
    private static final int POS_X = 500;
    private static final int POS_Y = 550;
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;
    private static final String LOG_FILE = "chat_log.txt"; // Файл лога

    private final JButton btnStart = new JButton("Start");
    private final JButton btnStop = new JButton("Stop");
    private final JTextArea log = new JTextArea();
    private boolean isServerWorking;

    public ServerWindow() {
        isServerWorking = false;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(POS_X, POS_Y, WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Server");
        setAlwaysOnTop(true);

        createPanel();
        setVisible(true);
    }

    private void createPanel() {
        add(new JScrollPane(log), BorderLayout.CENTER);
        add(createButton(), BorderLayout.SOUTH);
    }

    private Component createButton() {
        JPanel panel = new JPanel(new GridLayout(1, 2));
        panel.add(btnStart);
        panel.add(btnStop);

        btnStart.addActionListener(e -> {
            if (isServerWorking) {
                appendToLog("Сервер уже включен");
            } else {
                isServerWorking = true;
                appendToLog("Сервер включен");
            }
        });

        btnStop.addActionListener(e -> {
            if (!isServerWorking) {
                appendToLog("Сервер уже отключен");
            } else {
                isServerWorking = false;
                appendToLog("Сервер отключен");
            }
        });

        return panel;
    }

    public boolean isServerWorking() {
        return isServerWorking;
    }

    public void receiveMessage(String message) {
        if (isServerWorking) {
            appendToLog(message);
        } else {
            appendToLog("Ошибка: сервер не работает, сообщение не принято");
        }
    }

    public List<String> loadChatHistory() {
        if (!new File(LOG_FILE).exists()) return List.of();
        try (BufferedReader reader = new BufferedReader(new FileReader(LOG_FILE))) {
            return reader.lines().collect(Collectors.toList());
        } catch (IOException e) {
            appendToLog("Ошибка чтения лога: " + e.getMessage());
            return List.of();
        }
    }

    private void appendToLog(String message) {
        log.append(message + "\n");
        writeLogToFile(message);
    }

    private void writeLogToFile(String message) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE, true))) {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            writer.write("[" + timestamp + "] " + message);
            writer.newLine();
        } catch (IOException e) {
            log.append("Ошибка записи в файл лога: " + e.getMessage() + "\n");
        }
    }
}




