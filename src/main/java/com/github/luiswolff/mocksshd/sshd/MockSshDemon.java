package com.github.luiswolff.mocksshd.sshd;

import java.io.IOException;
import java.util.Arrays;

import org.apache.sshd.server.SshServer;
import org.apache.sshd.sftp.server.SftpSubsystemFactory;

public class MockSshDemon {

    private static final MockSshDemon INSTANCE = new MockSshDemon();

    private SshServer sftpServer;

    private MockSshDemon() {
    }

    public void start(StartCommand command) throws IOException {
        sftpServer = SshServer.setUpDefaultServer();
        sftpServer.setPort(command.getPort());
        sftpServer.setHost(command.getHost());
        sftpServer.setKeyPairProvider(command.createKeyPairProvider());
        sftpServer.setSubsystemFactories(Arrays.asList(new SftpSubsystemFactory()));
        sftpServer.setFileSystemFactory(command.createFileSystemFactory());
        sftpServer.setPasswordAuthenticator(command.createPasswordAuthenticator());
        this.sftpServer.start();
    }

    public void stop() throws IOException {
        this.sftpServer.stop();
    }

    public static MockSshDemon getInstance() {
        return INSTANCE;
    }

}
