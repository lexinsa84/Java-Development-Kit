package chat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientWindow extends JFrame {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;

    private final JTextArea log = new JTextArea();
    private final JTextField tfIPAddress = new JTextField("127.0.0.1");
    private final JTextField tfPort = new JTextField("8189");
    private final JTextField tfLogin = new JTextField("Alex");
    private final JPasswordField tfPassword = new JPasswordField("12345");
    private final JButton btnLogin = new JButton("Login");
    private final JPanel panelBottom = new JPanel(new BorderLayout());
    private final JTextField tfMessage = new JTextField();
    private final JButton btnSend = new JButton("Send");
    private DefaultListModel<String> userListModel;
    private JList<String> userList;
    private ServerWindow server;
    private boolean isConnected;

    public ClientWindow(ServerWindow server) {
        this.server = server;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(WIDTH, HEIGHT);
        setTitle("Chat Client");

        JPanel panelTop = new JPanel(new GridLayout(2, 3));
        panelTop.add(tfIPAddress);
        panelTop.add(tfPort);
        panelTop.add(tfLogin);
        panelTop.add(tfPassword);
        panelTop.add(btnLogin);
        add(panelTop, BorderLayout.NORTH);

        panelBottom.add(tfMessage, BorderLayout.CENTER);
        panelBottom.add(btnSend, BorderLayout.EAST);
        add(panelBottom, BorderLayout.SOUTH);

        userListModel = new DefaultListModel<>();
        userList = new JList<>(userListModel);
        userList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane userListScroll = new JScrollPane(userList);
        add(userListScroll, BorderLayout.WEST);

        log.setEditable(false);
        JScrollPane scrollLog = new JScrollPane(log);
        add(scrollLog, BorderLayout.CENTER);

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });

        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        tfMessage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        setVisible(true);
    }

    private void login() {
        if (server.isServerWorking()) {
            String userName = tfLogin.getText().trim();
            if (userName.isEmpty()) {
                appendToLog("Ошибка: введите имя пользователя!");
                return;
            }

            if (!userListModel.contains(userName)) {
                userListModel.addElement(userName);
                appendToLog("Пользователь '" + userName + "' вошёл в чат.");
            } else {
                appendToLog("Пользователь '" + userName + "' уже в чате.");
            }

            isConnected = true;

            log.setText("");
            for (String line : server.loadChatHistory()) {
                appendToLog(line);
            }
        } else {
            appendToLog("Ошибка: сервер не работает!");
        }
    }

    private void sendMessage() {
        if (!isConnected) {
            appendToLog("Ошибка: сначала подключитесь к серверу!");
            return;
        }

        String message = tfMessage.getText().trim();
        if (!message.isEmpty()) {
            String userName = tfLogin.getText().trim();
            appendToLog(userName + ": " + message);
            tfMessage.setText("");
            server.receiveMessage(userName + ": " + message);
        }
    }


    private void appendToLog(String message) {
        log.append(message + "\n");
    }
}



