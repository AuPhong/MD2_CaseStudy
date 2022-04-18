package view;

import config.ConfigLogin;
import controller.UserController;
import model.Role;
import model.User;

import java.util.Scanner;
import java.util.regex.Pattern;

public class AdminAccount {
    public AdminAccount() {
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Show your info");
        System.out.println("2. Edit your info");
        System.out.println("3. Show user list");
        System.out.println("4. Edit user list");
        System.out.println("5. Delete user");
        System.out.println("6. Comeback menu");
        System.out.println("7. Logout");
        int choose = sc.nextInt();
        sc.nextLine();
        String emailRegex = "^[A-Za-z0-9]+[A-Za-z0-9]*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)$";
        String accountRegex = "^([a-z]|[0-9]){8,16}$";
        String phonenumberRegex = "^0[0-9]{8,9}$";
        String passwordRegex = "^[A-Za-z0-9]{8,16}$";
        switch (choose) {
            case 1:
                new UserController().showInfo();
                new AdminAccount();
            case 2:
                int id = new ConfigLogin().readFromFile("C:\\Users\\Sang\\IntelliJ IDEA\\MD2_CaseStudy\\src\\data\\userLoginData.txt").getId();
                System.out.println("Enter username to change:");
                String username;
                boolean checkUsernameRegex;
                while (true) {
                    username = sc.nextLine();
                    checkUsernameRegex = Pattern.matches(accountRegex, username);
                    if (checkUsernameRegex || username.equals("")) {
                        break;
                    } else {
                        System.err.println("Wrong account pattern, enter again: ");
                    }
                }

                System.out.println("Enter password to change");
                String password;
                boolean checkPasswordRegex;
                while (true) {
                    password = sc.nextLine();
                    checkPasswordRegex = Pattern.matches(passwordRegex, password);
                    if (checkPasswordRegex || password.equals("")) {
                        break;
                    } else {
                        System.err.println("Wrong password pattern, enter again: ");
                    }
                }
                System.out.println("Enter email to change");
                String email;
                boolean checkEmailRegex;
                while (true) {
                    email = sc.nextLine();
                    checkEmailRegex = Pattern.matches(emailRegex, email);
                    if (checkEmailRegex || email.equals("")) {
                        break;
                    } else {
                        System.err.println("Wrong email pattern, enter again: ");
                    }
                }

                System.out.println("Enter phonenumber to change: ");
                String phonenumber;
                boolean checkPhonenumberRegex;
                while (true) {
                    phonenumber = sc.nextLine();
                    checkPhonenumberRegex = Pattern.matches(phonenumberRegex, phonenumber);
                    if (checkPhonenumberRegex || phonenumber.equals("")) {
                        break;
                    } else {
                        System.err.println("Wrong phonenumber pattern, enter again: ");
                    }
                }

                System.out.println("Enter roll to change: admin or staff ");
                String roleSelect = sc.nextLine();
                Role role = null;
                switch (roleSelect.toLowerCase()) {
                    case "":
                        role = new ConfigLogin().readFromFile("C:\\Users\\Sang\\IntelliJ IDEA\\MD2_CaseStudy\\src\\data\\userLoginData.txt").getRole();
                        break;
                    case "admin":
                        role = Role.ADMIN;
                        break;
                    case "staff":
                        role = Role.STAFF;
                        break;
                }

                User user = new User(id, username, password, email, phonenumber, role);
                new UserController().editInfo(user);
                new UserController().showInfo();
                new AdminAccount();
            case 3:
                new UserController().showList();
                new AdminAccount();
            case 4:
                System.out.println("Enter user's id to edit: ");
                int editId = sc.nextInt();
                sc.nextLine();
                System.out.println("Enter username to change:");
                String name;
                boolean checkNameRegex;
                while (true) {
                    name = sc.nextLine();
                    checkNameRegex = Pattern.matches(accountRegex, name);
                    if (checkNameRegex || name.equals("")) {
                        break;
                    } else {
                        System.err.println("Wrong account pattern, enter again: ");
                    }
                }

                System.out.println("Enter password to change");
                String pass;
                boolean checkPassRegex;
                while (true) {
                    pass = sc.nextLine();
                    checkPassRegex = Pattern.matches(passwordRegex, pass);
                    if (checkPassRegex || pass.equals("")) {
                        break;
                    } else {
                        System.err.println("Wrong password pattern, enter again: ");
                    }
                }
                System.out.println("Enter email to change");
                String mail;
                boolean checkMailRegex;
                while (true) {
                    mail = sc.nextLine();
                    checkMailRegex = Pattern.matches(emailRegex, mail);
                    if (checkMailRegex || mail.equals("")) {
                        break;
                    } else {
                        System.err.println("Wrong email pattern, enter again: ");
                    }
                }

                System.out.println("Enter phonenumber to change: ");
                String phone;
                boolean checkPhoneRegex;
                while (true) {
                    phone= sc.nextLine();
                    checkPhoneRegex = Pattern.matches(phonenumberRegex, phone);
                    if (checkPhoneRegex || phone.equals("")) {
                        break;
                    } else {
                        System.err.println("Wrong phonenumber pattern, enter again: ");
                    }
                }

                System.out.println("Enter roll to change: admin or staff ");
                String roleChoose = sc.nextLine();
                Role role1 = null;
                switch (roleChoose.toLowerCase()) {
                    case "":
                        role1 = new ConfigLogin().readFromFile("C:\\Users\\Sang\\IntelliJ IDEA\\MD2_CaseStudy\\src\\data\\userLoginData.txt").getRole();
                        break;
                    case "admin":
                        role1 = Role.ADMIN;
                        break;
                    case "staff":
                        role1 = Role.STAFF;
                        break;
                }
                User user1 = new User(editId, name, pass, mail, phone, role1);
                new UserController().editById(user1);
                new AdminAccount();
            case 5:
                System.out.println("Enter id to delete: ");
                int deleteId = sc.nextInt();
                new UserController().deleteById(deleteId);
                new AdminAccount();
            case 6:
                new AdminMenu();
                break;
            case 7:
                new Account().Logout();
                break;
        }
    }
}
