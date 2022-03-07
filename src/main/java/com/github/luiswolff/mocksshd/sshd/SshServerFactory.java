package com.github.luiswolff.mocksshd.sshd;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.apache.sshd.common.config.keys.KeyUtils;
import org.apache.sshd.common.file.FileSystemFactory;
import org.apache.sshd.common.file.virtualfs.VirtualFileSystemFactory;
import org.apache.sshd.common.keyprovider.KeyPairProvider;
import org.apache.sshd.server.SshServer;
import org.apache.sshd.server.auth.password.PasswordAuthenticator;
import org.apache.sshd.server.keyprovider.SimpleGeneratorHostKeyProvider;
import org.apache.sshd.sftp.server.SftpSubsystemFactory;

class SshServerFactory {

    private SshServerFactory() {
    }

    static SshServer createSshServer(StartCommand command) throws IOException {
        SshServer sftpServer = SshServer.setUpDefaultServer();
        sftpServer.setPort(command.getPort());
        sftpServer.setHost(command.getHost());
        sftpServer.setKeyPairProvider(createKeyPairProvider(command));
        sftpServer.setSubsystemFactories(createSubsystemFactories());
        sftpServer.setFileSystemFactory(createFileSystemFactory(command));
        sftpServer.setPasswordAuthenticator(createPasswortAuthenticator(command));
        return sftpServer;
    }

    private static KeyPairProvider createKeyPairProvider(StartCommand command) { // NOSONAR maybe we use the unused parameter later
        SimpleGeneratorHostKeyProvider simpleGeneratorHostKeyProvider = new SimpleGeneratorHostKeyProvider();
        simpleGeneratorHostKeyProvider.setAlgorithm(KeyUtils.RSA_ALGORITHM);
        return simpleGeneratorHostKeyProvider;
    }

    private static List<SftpSubsystemFactory> createSubsystemFactories() {
        return Arrays.asList(new SftpSubsystemFactory());
    }

    private static FileSystemFactory createFileSystemFactory(StartCommand command) throws IOException {
        Path serverRoot = Paths.get(command.getRoot());
        Files.createDirectories(serverRoot);

        return new VirtualFileSystemFactory(serverRoot.toAbsolutePath());
    }

    private static PasswordAuthenticator createPasswortAuthenticator(StartCommand command) {
        return (username, password, session) -> command.getUser().equals(username) && command.getPass().equals(password);
    }

}
