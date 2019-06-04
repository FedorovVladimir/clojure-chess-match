package autorization;

public class User {
    private static boolean authorization = false;
    private static String login;

    public static boolean isAuthorization() {
        return authorization;
    }
    public static String loginAuthorization() {
        return login;
    }

    public static void setAuthorization(boolean autorization, String login) {
        System.out.println("Переменная изменена");
        User.authorization = autorization;
        User.login = login;
    }
}
