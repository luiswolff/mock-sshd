package com.github.luiswolff.mocksshd.gui.demon;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.github.luiswolff.mocksshd.sshd.MockSshDemon;

public class DemonPanel extends JPanel {

    private DemonController controller;
    private MockSshDemon model;

    public DemonPanel(DemonController controller, MockSshDemon model) {
        super(new FlowLayout());

        this.controller = controller;
        this.model = model;

        addPortInput();
        addButton("start");
        addButton("stop");
    }

    private void addPortInput() {
        JTextField portInput = new JTextField();
        portInput.setText(Integer.toString(model.getPort()));
        add(portInput);
    }

    private void addButton(String title) {
        JButton button = new JButton(title);
        button.addActionListener(l -> System.out.println("Button " + title + " clicked!"));
        add(button);
    }

}
