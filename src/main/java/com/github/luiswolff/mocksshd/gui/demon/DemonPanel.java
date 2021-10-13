package com.github.luiswolff.mocksshd.gui.demon;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.github.luiswolff.mocksshd.sshd.StartCommand;

public class DemonPanel extends JPanel {

    private static final long     serialVersionUID = -6403969028249994552L;

    private final DemonController controller;
    private final StartCommand    model;

    private final JTextField      portInput;
    private final JButton         startStopButto;

    private boolean               demonRunning;

    public DemonPanel(DemonController controller, StartCommand model) {
        super(new FlowLayout());

        this.controller = controller;
        this.model = model;

        portInput = addInput("Port", this.model.getPort());
        startStopButto = addButton("start", l -> startStop());
    }

    private JTextField addInput(String labelText, int port) {
        JLabel label = new JLabel(labelText);
        JTextField input = new JTextField();
        input.setText(Integer.toString(port));
        label.setLabelFor(input);
        add(label);
        add(input);
        return input;
    }

    private JButton addButton(String title, ActionListener action) {
        JButton button = new JButton(title);
        button.addActionListener(action);
        add(button);
        return button;
    }

    private void startStop() {
        if (demonRunning) {
            controller.stopSshd();
        } else {
            controller.startSshd();
        }
    }

    void demonStarted() {
        portInput.setEnabled(false);
        startStopButto.setText("stop");
        demonRunning = true;
        updateUI();
    }

    void demonStopped() {
        portInput.setEnabled(true);
        startStopButto.setText("start");
        demonRunning = false;
        updateUI();
    }

}
