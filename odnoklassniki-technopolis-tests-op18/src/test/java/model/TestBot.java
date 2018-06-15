package model;

public class TestBot {
    private final String login;
    private final String password;

    public static TestBot create() {
        return new TestBot("QA18testbot100", "QA18testbot");
    }

    public TestBot(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
