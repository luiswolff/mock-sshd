package com.github.luiswolff.mocksshd.sshd;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.apache.sshd.common.config.keys.KeyUtils;
import org.apache.sshd.common.file.virtualfs.VirtualFileSystemFactory;
import org.apache.sshd.common.keyprovider.KeyPairProvider;
import org.apache.sshd.server.SshServer;
import org.apache.sshd.server.keyprovider.SimpleGeneratorHostKeyProvider;
import org.apache.sshd.server.session.ServerSession;
import org.apache.sshd.sftp.server.SftpSubsystemFactory;

public class MockSshDemon {

    private static final MockSshDemon INSTANCE = new MockSshDemon();

    private final SshServer sftpServer;

    private MockSshDemon() {
        try {
            sftpServer = SshServer.setUpDefaultServer();
            sftpServer.setPort(22);
            sftpServer.setHost("127.0.0.1");
            sftpServer.setKeyPairProvider(createKeyPairProvider());
            sftpServer.setSubsystemFactories(Arrays.asList(new SftpSubsystemFactory()));
            sftpServer.setFileSystemFactory(createFileSystemFactory());
            sftpServer.setPasswordAuthenticator(this::authenticate);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        // Desktop.getDesktop().open(Paths.get(".data").toAbsolutePath().toFile());

        //System.in.read();
    }

    private VirtualFileSystemFactory createFileSystemFactory() throws IOException {
        Path serverRoot = Paths.get(".data");
        Files.createDirectories(serverRoot);

        return new VirtualFileSystemFactory(serverRoot.toAbsolutePath());
    }

    private KeyPairProvider createKeyPairProvider() {
        SimpleGeneratorHostKeyProvider simpleGeneratorHostKeyProvider = new SimpleGeneratorHostKeyProvider();
        simpleGeneratorHostKeyProvider.setAlgorithm(KeyUtils.RSA_ALGORITHM);
        return simpleGeneratorHostKeyProvider;
    }

    private boolean authenticate(String username, String password, ServerSession session) {
        return "foo".equals(username) && "pass".equals(password);
    }

    public int getPort() {
        return sftpServer.getPort();
    }

    public void setPort(int port) {
        this.sftpServer.setPort(port);
    }

    public void start() throws IOException {
        this.sftpServer.start();
    }

    public void stop() throws IOException {
        this.sftpServer.stop();
    }

    public static MockSshDemon getInstance() {
        return INSTANCE;
    }

}
