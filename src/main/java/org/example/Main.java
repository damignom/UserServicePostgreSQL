package org.example;

import org.example.dao.UserDAO;
import org.example.models.User;

import java.io.Console;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner console = new Scanner(System.in);
        UserDAO userDAO = new UserDAO();

        while (true) {

            System.out.println("Please select action:");
            System.out.println("1.Get list users.");
            System.out.println("2.Add new user.");
            System.out.println("3.Update user data.");
            System.out.println("4.Delete user.");
            System.out.println("0.Exit");


            int option = console.nextInt('\n');
            console.nextLine();

            switch (option) {
                case 1:
                    System.out.println("U select list users");
                    for (User user : userDAO.getAll().stream().toList()) {
                        System.out.println(user.toString());
                    }
                    break;
                case 2:
                    System.out.println("Get user");
                    System.out.println("Input user id:");
                    int getId = console.nextInt();
                    console.nextLine();
                    User foundUser = userDAO.getT(getId);
                    System.out.println("Found user: " + foundUser.toString());
                    break;
                case 3:
                    System.out.println("Add new user");
                    System.out.println("Input name");
                    String name = console.nextLine();
                    System.out.println("Input email");
                    String email = console.nextLine();
                    System.out.println("Input age");
                    int age = console.nextInt();

                    userDAO.save(new User(email, name, age));
                    break;
                case 4:
                    System.out.println("Update user");
                    System.out.print("Enter ID: ");
                    int updateId = console.nextInt();
                    console.nextLine();
                    User u = userDAO.getT(updateId);
                    if (u != null) {
                        System.out.print("New name: ");
                        u.setName(console.nextLine());
                        System.out.print("New email: ");
                        u.setEmail(console.nextLine());
                        System.out.print("New age: ");
                        u.setAge(console.nextInt());
                        userDAO.update(u);
                    } else {
                        System.out.println("User not found");
                    }
                    break;
                case 5:
                    System.out.println("Delete user");
                    System.out.println("Input ID User for delete");
                    int deleteId = console.nextInt();
                    User getDeletedUser = userDAO.getT(deleteId);
                    System.out.println(getDeletedUser.toString());
                    userDAO.delete(getDeletedUser);
                    break;
                default:
                    System.out.println("Wrong input");
                    break;
                case 0:
                    System.exit(0);
            }

        }


    }
}