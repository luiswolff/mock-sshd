package com.github.luiswolff.mocksshd.gui.demon;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.io.IOException;

import com.github.luiswolff.mocksshd.sshd.MockSshDemon;

public class DemonController {

    private MockSshDemon model = MockSshDemon.getInstance();
    private DemonPanel view = new DemonPanel(this, model);

    void startSshd(ActionEvent l) {
        try {
            model.start();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    void stopSshd(ActionEvent l) {
        try {
            model.stop(); // TODO: Apache Sshd can only be started once. So either after stopping a new Demon must be created or the demon must create a new server after stopping
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public Container getView() {
        return view;
    }
    
}
