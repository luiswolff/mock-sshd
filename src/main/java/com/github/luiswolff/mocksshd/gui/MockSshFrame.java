package com.github.luiswolff.mocksshd.gui;

import javax.swing.JFrame;

import com.github.luiswolff.mocksshd.gui.demon.DemonController;

public class MockSshFrame {

    private final DemonController controller = new DemonController();

    public void show() {
        JFrame frame = new JFrame();
        frame.setContentPane(controller.getView());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setSize(800, 400);
		frame.setLocationRelativeTo(null);
        
		frame.setVisible(true);
    }
    
}
