package com.github.luiswolff.mocksshd;

import javax.swing.SwingUtilities;

import com.github.luiswolff.mocksshd.gui.MockSshFrame;

public class MockSshApplication {
    public static void main(String[] args) {
        MockSshFrame frame = new MockSshFrame();
        SwingUtilities.invokeLater(frame::show);
    }
}
