package ticTacToe;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingWindow extends JFrame {
    private static final int WIDTH = 230;
    private static final int HEIGHT = 350;

    private static final String MODE_LABEL = "Выберите режим игры";
    private static final String FIELD_SIZE_LABEL = "Выберите размеры поля";
    private static final String WIN_LENGTH_LABEL = "Выберите длину для победы";

    private static final String FIELD_SIZE_PREFIX = "Установленный размер поля:";
    private static final String WIN_LENGTH_PREFIX = "Установленная длина:";

    private JRadioButton rbHumanVsAI, rbHumanVsHuman;
    private ButtonGroup modeGroup;
    private JSlider sliderFieldSize, sliderWinLength;
    private JLabel labelFieldSize, labelWinLength;
    private JButton btnStart;

    private GameWindow gameWindow;

    SettingWindow(GameWindow gameWindow) {
        this.gameWindow = gameWindow;

        setTitle("Настройки");
        setLocationRelativeTo(gameWindow);
        setSize(WIDTH, HEIGHT);

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        add(createModePanel());
        add(createFieldSizePanel());
        add(createWinLengthPanel());
        add(createStartButton());

        setVisible(true);
    }

    private JPanel createModePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel label = new JLabel(MODE_LABEL);
        panel.add(label);

        rbHumanVsAI = new JRadioButton("Человек против компьютера");
        rbHumanVsHuman = new JRadioButton("Человек против человека");

        modeGroup = new ButtonGroup();
        modeGroup.add(rbHumanVsAI);
        modeGroup.add(rbHumanVsHuman);

        panel.add(rbHumanVsAI);
        panel.add(rbHumanVsHuman);

        return panel;
    }

    private JPanel createFieldSizePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel label = new JLabel(FIELD_SIZE_LABEL);
        panel.add(label);

        sliderFieldSize = new JSlider(3, 10, 3);
        sliderFieldSize.setMajorTickSpacing(1);
        sliderFieldSize.setPaintTicks(true);
        sliderFieldSize.setPaintLabels(true);
        labelFieldSize = new JLabel(FIELD_SIZE_PREFIX + " " + sliderFieldSize.getValue());
        panel.add(sliderFieldSize);
        panel.add(labelFieldSize);

        sliderFieldSize.addChangeListener(e -> {
            labelFieldSize.setText(FIELD_SIZE_PREFIX + " " + sliderFieldSize.getValue());
            sliderWinLength.setMaximum(sliderFieldSize.getValue());
        });

        return panel;
    }

    private JPanel createWinLengthPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel label = new JLabel(WIN_LENGTH_LABEL);
        panel.add(label);

        sliderWinLength = new JSlider(3, 10, 3);
        sliderWinLength.setMajorTickSpacing(1);
        sliderWinLength.setPaintTicks(true);
        sliderWinLength.setPaintLabels(true);
        labelWinLength = new JLabel(WIN_LENGTH_PREFIX + " " + sliderWinLength.getValue());
        panel.add(sliderWinLength);
        panel.add(labelWinLength);

        sliderWinLength.addChangeListener(e -> labelWinLength.setText(WIN_LENGTH_PREFIX + " " + sliderWinLength.getValue()));

        return panel;
    }

    private JButton createStartButton() {
        btnStart = new JButton("Старт");
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int mode = rbHumanVsAI.isSelected() ? 0 : 1;
                int fieldSize = sliderFieldSize.getValue();
                int winLength = sliderWinLength.getValue();

                gameWindow.startNewGame(mode, fieldSize, fieldSize, winLength);
                setVisible(false);
            }
        });
        return btnStart;
    }
}
