package autorization;

public class User {
    private static boolean authorization = false;
    private static String login;
    private static String role;

    public static boolean isAuthorization() {
        return authorization;
    }
    public static String loginAuthorization() {
        return login;
    }
    public static String roleAuthorization() { return role; }

    public static void setAuthorization(boolean autorization, String login, String role) {
        User.authorization = autorization;
        User.login = login;
        User.role = role;
    }
}
