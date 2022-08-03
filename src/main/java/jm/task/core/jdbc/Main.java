package jm.task.core.jdbc;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;


public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

            userService.createUsersTable();
            userService.saveUser("Alex", "Vunder", (byte) 28);
            userService.saveUser("Petr", "Ivanov", (byte) 35);
            userService.saveUser("Michail", "Draw", (byte) 18);
            userService.saveUser("User", "QWEr", (byte) 45);
            System.out.println(userService.getAllUsers());
            userService.cleanUsersTable();
            userService.dropUsersTable();
        }
}

