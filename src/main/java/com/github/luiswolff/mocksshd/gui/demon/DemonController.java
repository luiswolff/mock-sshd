package com.github.luiswolff.mocksshd.gui.demon;

import java.awt.Container;

import com.github.luiswolff.mocksshd.sshd.MockSshDemon;

public class DemonController {

    private MockSshDemon model = MockSshDemon.getInstance();
    private DemonPanel view = new DemonPanel(this, model);

    public Container getView() {
        return view;
    }
    
}
