package com.github.luiswolff.mocksshd.sshd;

import java.io.Serializable;

public class StartCommand implements Serializable {

    private static final long serialVersionUID = -2969659995438983693L;
    private int               port             = 22;
    private String            host             = "127.0.0.1";
    private String            root             = ".data";
    private String            user             = "foo";
    private String            pass             = "pass";

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
}
