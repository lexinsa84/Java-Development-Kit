package chat.server.repository;

import chat.server.ServerController;
import chat.server.ServerListener;

import javax.swing.*;
import java.awt.*;

public class ServerWindow extends JFrame implements ServerListener {
    private final JTextArea log = new JTextArea();
    private final JButton btnStart = new JButton("Запустить сервер");
    private final JButton btnStop = new JButton("Остановить сервер");
    private JPanel panelBottom;

    private final ServerController serverController;

    public ServerWindow() {
        ChatRepositoryInterface chatRepository = new ChatRepository();
        serverController = new ServerController(this, chatRepository);

        createPanelBottom();

        createPanelBottom();

        setting();

        log.setEditable(false);


        setVisible(true);
    }

    private void setting(){
        setTitle("Сервер");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
    }

    private void createPanelBottom(){
        panelBottom = new JPanel();
        panelBottom.add(btnStart);
        panelBottom.add(btnStop);
    }

    private void createPanel(){
        add(new JScrollPane(log), BorderLayout.CENTER);
        add(panelBottom, BorderLayout.SOUTH);
    }

    private void actionListenerButton(){
        btnStart.addActionListener(e -> serverController.startServer());
        btnStop.addActionListener(e -> serverController.stopServer());
    }

    @Override
    public void onServerMessage(String message) {
        log.append(message + "\n");
    }

    public ServerController getServerController() {
        return serverController;
    }
}


