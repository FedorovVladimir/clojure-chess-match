package autorization;

public class User {
    private static User ourInstance = new User();

    public static User getInstance() {
        return ourInstance;
    }
    private static boolean authorization = false;

    private User() {
    }

    public static boolean isAuthorization() {
        return authorization;
    }

    public static void setAuthorization(boolean autorization) {
        User.authorization = autorization;
    }
}
