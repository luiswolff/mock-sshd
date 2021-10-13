package com.github.luiswolff.mocksshd.gui.demon;

import java.awt.Container;
import java.io.IOException;
import java.io.Serializable;

import com.github.luiswolff.mocksshd.sshd.MockSshDemon;
import com.github.luiswolff.mocksshd.sshd.StartCommand;

public class DemonController implements Serializable {

    private static final long  serialVersionUID = -1987924477363435321L;

    private StartCommand       model            = new StartCommand();
    private DemonPanel         view             = new DemonPanel(this, model);

    void startSshd() {
        MockSshDemon mockSshDemon = MockSshDemon.getInstance();
        try {
            mockSshDemon.start(model);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (mockSshDemon.isRunning()) {
            view.demonStarted();
        }
    }

    void stopSshd() {
        MockSshDemon mockSshDemon = MockSshDemon.getInstance();
        try {
            mockSshDemon.stop();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (!mockSshDemon.isRunning()) {
            view.demonStopped();
        }
    }

    public Container getView() {
        return view;
    }

}
