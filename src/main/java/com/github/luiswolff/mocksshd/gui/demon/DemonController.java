package com.github.luiswolff.mocksshd.gui.demon;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.io.IOException;

import com.github.luiswolff.mocksshd.sshd.MockSshDemon;
import com.github.luiswolff.mocksshd.sshd.StartCommand;

public class DemonController {

    private StartCommand model = new StartCommand();
    private DemonPanel view = new DemonPanel(this, model);

    void startSshd(ActionEvent l) {
        try {
            MockSshDemon.getInstance().start(model); // TODO: it must be ensured that demon can't be started twice
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    void stopSshd(ActionEvent l) {
        try {
            MockSshDemon.getInstance().stop();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public Container getView() {
        return view;
    }
    
}
