package com.github.luiswolff.mocksshd.sshd;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.sshd.common.config.keys.KeyUtils;
import org.apache.sshd.common.file.FileSystemFactory;
import org.apache.sshd.common.file.virtualfs.VirtualFileSystemFactory;
import org.apache.sshd.common.keyprovider.KeyPairProvider;
import org.apache.sshd.server.auth.password.PasswordAuthenticator;
import org.apache.sshd.server.keyprovider.SimpleGeneratorHostKeyProvider;

public class StartCommand {

    private int port = 22;
    private String host = "127.0.0.1";
    private String root = ".data";
    private String user = "foo";
    private String pass = "pass";

    public int getPort() {
        return port;
    }
    public void setPort(int port) {
        this.port = port;
    }
    public String getHost() {
        return host;
    }
    public void setHost(String host) {
        this.host = host;
    }
    public String getRoot() {
        return root;
    }
    public void setRoot(String root) {
        this.root = root;
    }
    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }
    public String getPass() {
        return pass;
    }
    public void setPass(String pass) {
        this.pass = pass;
    }

    FileSystemFactory createFileSystemFactory() throws IOException {
        Path serverRoot = Paths.get(root);
        Files.createDirectories(serverRoot);

        return new VirtualFileSystemFactory(serverRoot.toAbsolutePath());
    }

    KeyPairProvider createKeyPairProvider() {
        SimpleGeneratorHostKeyProvider simpleGeneratorHostKeyProvider = new SimpleGeneratorHostKeyProvider();
        simpleGeneratorHostKeyProvider.setAlgorithm(KeyUtils.RSA_ALGORITHM);
        return simpleGeneratorHostKeyProvider;
    }

    PasswordAuthenticator createPasswordAuthenticator() {
        return (username, password, session) -> this.user.equals(username) && this.pass.equals(password);
    }
}
