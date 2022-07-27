package jm.task.core.jdbc;
import jm.task.core.jdbc.service.UserServiceImpl;


public class Main {
    public static void main(String[] args) {
        UserServiceImpl us = new UserServiceImpl();
        us.createUsersTable();
        us.saveUser("Paul", "Anderson", (byte) 45);
        us.saveUser("Alexander", "Vunder", (byte) 28);
        us.saveUser("John", "Lennon", (byte) 75);
        us.saveUser("Name", "LastName", (byte) 1);
        System.out.println();
        System.out.println(us.getAllUsers());
        us.cleanUsersTable();
        us.dropUsersTable();
        // реализуйте алгоритм здесь
    }
}
