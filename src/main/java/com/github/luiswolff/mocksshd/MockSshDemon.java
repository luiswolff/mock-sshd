package com.github.luiswolff.mocksshd;

import java.awt.Desktop;
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

    public static void main(String[] args) throws IOException {


        SshServer sftpServer = SshServer.setUpDefaultServer();
        sftpServer.setPort(22);
        sftpServer.setHost("127.0.0.1");
        sftpServer.setKeyPairProvider(createKeyPairProvider());
        sftpServer.setSubsystemFactories(Arrays.asList(new SftpSubsystemFactory()));
        sftpServer.setFileSystemFactory(createFileSystemFactory());
        sftpServer.setPasswordAuthenticator(MockSshDemon::authenticate);
        sftpServer.start();

        Desktop.getDesktop().open(Paths.get(".data").toAbsolutePath().toFile());

        System.in.read();

        sftpServer.stop();
    }

    private static VirtualFileSystemFactory createFileSystemFactory() throws IOException {
        Path serverRoot = Paths.get(".data");
        Files.createDirectories(serverRoot);

        return new VirtualFileSystemFactory(serverRoot.toAbsolutePath());
    }

    private static KeyPairProvider createKeyPairProvider() {
        SimpleGeneratorHostKeyProvider simpleGeneratorHostKeyProvider = new SimpleGeneratorHostKeyProvider();
        simpleGeneratorHostKeyProvider.setAlgorithm(KeyUtils.RSA_ALGORITHM);
        return simpleGeneratorHostKeyProvider;
    }

    private static boolean authenticate(String username, String password, ServerSession session) {
        return "foo".equals(username) && "pass".equals(password);
    }

}
