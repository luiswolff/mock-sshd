package com.github.luiswolff.mocksshd.sshd;

import java.io.IOException;

import org.apache.sshd.server.SshServer;

public class MockSshDemon {

    private static final MockSshDemon INSTANCE = new MockSshDemon();

    private SshServer sftpServer;

    private MockSshDemon() {
    }

    public void start(StartCommand command) throws IOException {
        sftpServer = SshServerFactory.createSshServer(command);
        this.sftpServer.start();
    }

    public void stop() throws IOException {
        this.sftpServer.stop();
    }

    public boolean isRunning() {
        return sftpServer != null && sftpServer.isStarted();
    }

    public static MockSshDemon getInstance() {
        return INSTANCE;
    }

}
