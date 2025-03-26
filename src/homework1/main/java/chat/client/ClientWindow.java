package chat.client;

import chat.server.ServerController;
import chat.server.repository.ChatRepository;
import chat.server.repository.ChatRepositoryInterface;

import javax.swing.*;
import java.awt.*;

public class ClientWindow extends JFrame implements ClientListener {
    private final JTextArea log = new JTextArea();
    private final JTextField tfIPAddress = new JTextField("127.0.0.1");
    private final JTextField tfPort = new JTextField("8189");
    private final JTextField tfLogin = new JTextField("Alex");
    private final JButton btnLogin = new JButton("Login");
    private final JTextField tfMessage = new JTextField();
    private final JButton btnSend = new JButton("Send");
    private JPanel panelTop;
    private JPanel panelBottom;

    private final ClientController clientController;

    public ClientWindow(ServerController serverController) {
        ChatRepositoryInterface chatRepository = new ChatRepository();
        clientController = new ClientController(this, chatRepository, serverController);

        setting();

        createPanelTop();

        createPanelBottom();

        actionListenerButton();

        addPanel();

        log.setEditable(false);

        setVisible(true);
    }

    private void setting() {
        setTitle("Чат Клиент");
        setSize(400, 350);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
    }

    private void createPanelTop() {
        panelTop = new JPanel(new GridLayout(2, 3));
        panelTop.add(new JLabel("IP:"));
        panelTop.add(tfIPAddress);
        panelTop.add(new JLabel("Порт:"));
        panelTop.add(tfPort);
        panelTop.add(new JLabel("Логин:"));
        panelTop.add(tfLogin);
        panelTop.add(btnLogin);
    }

    private void createPanelBottom(){
        panelBottom = new JPanel(new BorderLayout());
        panelBottom.add(tfMessage, BorderLayout.CENTER);
        panelBottom.add(btnSend, BorderLayout.EAST);
    }

    private void actionListenerButton(){
        btnLogin.addActionListener(e -> clientController.login(tfIPAddress.getText(), tfPort.getText(), tfLogin.getText().trim()));
        btnSend.addActionListener(e -> {
            clientController.sendMessage(tfMessage.getText());
            tfMessage.setText("");
        });
    }

    private void addPanel(){
        add(panelTop, BorderLayout.NORTH);
        add(new JScrollPane(log), BorderLayout.CENTER);
        add(panelBottom, BorderLayout.SOUTH);
    }

    @Override
    public void onLoginSuccess(String username) {
        log.append("Пользователь '" + username + "' вошел в чат.\n");
        panelTop.setVisible(false);
    }

    @Override
    public void onLoginError(String message) {
        log.append(message + "\n");
    }

    @Override
    public void onMessageReceived(String message) {
        log.append(message + "\n");
    }
}