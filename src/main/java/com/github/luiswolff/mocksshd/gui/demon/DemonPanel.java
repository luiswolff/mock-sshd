package com.github.luiswolff.mocksshd.gui.demon;

import com.github.luiswolff.mocksshd.sshd.StartCommand;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DemonPanel extends JPanel {

  private static final long serialVersionUID = -6403969028249994552L;

  private final DemonController controller;
  private final StartCommand model;

  private final JTextField portInput;
  private final JTextField hostInput;
  private final JTextField rootInput;
  private final JTextField userInput;
  private final JTextField passInput;
  private final JButton startStopButton;

  private boolean demonRunning;

  public DemonPanel(DemonController controller, StartCommand model) {
    super(new FlowLayout());

    this.controller = controller;
    this.model = model;

    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BorderLayout());

    JPanel inputPanel = new JPanel(new GridLayout(5, 2, 10, 10));
    portInput = addInput(inputPanel, "Port", model.getPort());
    hostInput = addInput(inputPanel, "Host", model.getHost());
    rootInput = addInput(inputPanel, "Root", model.getRoot());
    userInput = addInput(inputPanel, "User", model.getUser());
    passInput = addInput(inputPanel, "Password", model.getPass());
    mainPanel.add(inputPanel, BorderLayout.CENTER);

    JPanel buttonPanel = new JPanel();
    addJButton(buttonPanel, "Open Root", l -> controller.openRoot());
    startStopButton = addJButton(buttonPanel, "start", l -> startStop());
    mainPanel.add(buttonPanel, BorderLayout.SOUTH);

    add(mainPanel);
  }

  private JButton addJButton(JPanel buttonPanel, String title, ActionListener actionListener) {
    JButton jButton = new JButton(title);
    jButton.addActionListener(actionListener);
    buttonPanel.add(jButton);
    return jButton;
  }

  private static JTextField addInput(JComponent parent, String labelText, Object textValue) {
    JLabel label = new JLabel(labelText);
    JTextField input = new JTextField();
    input.setText(textValue.toString());
    label.setLabelFor(input);
    parent.add(label);
    parent.add(input);
    return input;
  }

  private void startStop() {
    if (demonRunning) {
      controller.stopSshd();
    } else {
      writeModel();
      controller.startSshd();
    }
  }

  private void writeModel() {
    model.setPort(Integer.parseInt(portInput.getText()));
    model.setHost(hostInput.getText());
    model.setRoot(rootInput.getText());
    model.setUser(userInput.getText());
    model.setRoot(passInput.getText());
  }

  void demonStarted() {
    inputsSetEnabled(false);
    startStopButton.setText("stop");
    demonRunning = true;
    updateUI();
  }

  void demonStopped() {
    inputsSetEnabled(true);
    startStopButton.setText("start");
    demonRunning = false;
    updateUI();
  }

  private void inputsSetEnabled(boolean enabled) {
    allInputs().forEach(input -> input.setEnabled(enabled));
  }

  private Iterable<JTextField> allInputs() {
    return Arrays.asList(portInput, hostInput, rootInput, userInput, passInput);
  }

}
