package com.github.luiswolff.mocksshd.gui.demon;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.github.luiswolff.mocksshd.sshd.MockSshDemon;

public class DemonPanel extends JPanel {

    public DemonPanel(DemonController controller, MockSshDemon model) {
        super(new FlowLayout());


        addPortInput(model.getPort());
        addButton("start", controller::startSshd);
        addButton("stop", controller::stopSshd);
    }

    private void addPortInput(int port) {
        JTextField portInput = new JTextField();
        portInput.setText(Integer.toString(port));
        add(portInput);
    }

    private void addButton(String title, ActionListener action) {
        JButton button = new JButton(title);
        button.addActionListener(action);
        add(button);
    }

}
