package view;

import config.ConfigLogin;
import config.ConfigReadAndWrite;
import model.Role;
import model.User;
import service.staffService.UserServiceIPLM;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Account {
    List<User> userList = UserServiceIPLM.userList;
    String PATH = "C:\\Users\\Sang\\IntelliJ IDEA\\MD2_CaseStudy\\src\\data\\userData.txt";
    Scanner sc = new Scanner(System.in);
    String LOGINPATH = "C:\\Users\\Sang\\IntelliJ IDEA\\MD2_CaseStudy\\src\\data\\userLoginData.txt";

    public void Register() {
        int id;
        if (userList.size() == 0) {
            id = 1;
        } else {
            id = userList.get(userList.size() - 1).getId() + 1;
        }
        String emailRegex = "^[A-Za-z0-9]+[A-Za-z0-9]*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)$";
        String accountRegex = "^([a-z]|[0-9]){8,16}$";
        String phonenumberRegex = "^0[0-9]{8,9}$";
        String passwordRegex = "^[A-Za-z0-9]{8,16}$";

        System.out.println("Enter username:");
        String username;
        boolean checkUsernameRegex;
        while (true) {
            username = sc.nextLine();
            checkUsernameRegex = Pattern.matches(accountRegex, username);
            if (checkUsernameRegex) {
                break;
            } else {
                System.err.println("Wrong account pattern, enter again: ");
            }
        }

        System.out.println("Enter password");
        String password;
        boolean checkPasswordRegex;
        while (true) {
            password = sc.nextLine();
            checkPasswordRegex = Pattern.matches(passwordRegex, password);
            if (checkPasswordRegex) {
                break;
            } else {
                System.err.println("Wrong password pattern, enter again: ");
            }
        }
        System.out.println("Enter email");
        String email;
        boolean checkEmailRegex;
        while (true) {
            email = sc.nextLine();
            checkEmailRegex = Pattern.matches(emailRegex, email);
            if (checkEmailRegex) {
                break;
            } else {
                System.err.println("Wrong email pattern, enter again: ");
            }
        }

        System.out.println("Enter phonenumber");
        String phonenumber;
        boolean checkPhonenumberRegex;
        while (true) {
            phonenumber = sc.nextLine();
            checkPhonenumberRegex = Pattern.matches(phonenumberRegex, phonenumber);
            if (checkPhonenumberRegex) {
                break;
            } else {
                System.err.println("Wrong phonenumber pattern, enter again: ");
            }
        }

        System.out.println("Enter roll: admin or staff ");
        String roleSelect = sc.nextLine();
        Role role=null;
        switch (roleSelect.toLowerCase()){
            case "admin": role = Role.ADMIN;
            break;
            case "staff": role = Role.STAFF;
            break;
        }

        User staff = new User(id, username, password, email, phonenumber,role);
        userList.add(staff);
        new ConfigReadAndWrite<User>().writeFromFile(PATH, userList);
        new Main();
    }


    public void Login() {
        System.out.println("Enter login username: ");
        String loginname = sc.nextLine();
        for (int i = 0; i < userList.size(); i++) {
            if (loginname.equals(userList.get(i).getUserName())) {
                System.out.println("Enter password");
                String password;
                while (true) {
                    password = sc.nextLine();
                    if (password.equals(userList.get(i).getPassWord())) {
                        break;
                    } else {
                        System.out.println("Wrong password, enter again: ");
                    }
                }
                User user = new UserServiceIPLM().findByUsername(loginname);
                new ConfigLogin().writeFromFile(LOGINPATH, user);
                if (user.getRole()==Role.ADMIN){
                    new AdminMenu();
                }else if (user.getRole()==Role.STAFF){
                    new StaffMenu();
                }

            }
        }
    }

    public void Logout(){
        new ConfigLogin().writeFromFile(LOGINPATH,null);
        new Main();
    }
}


//System.out.println("Enter login username: ");
//        String loginname = sc.nextLine();
//        System.out.println("Enter password: ");
//        String loginpassword = sc.nextLine();
//        for (int i = 0; i < staffList.size(); i++) {
//        if (loginname.equals(staffList.get(i).getUserName()) && loginpassword.equals(staffList.get(i).getPassWord())) {
//        new Menu();
//        }
//        }


//       if (checkAccountRegex) {
//            if (checkPasswordRegex) {
//                if (checkEmailRegex){
//                    if (checkPhoneRegex){
//                        Staff staff = new Staff(id, username, password, email, phone);
//                        staffList.add(staff);
//                        new ConfigReadAndWrite<Staff>().writeFromFile(PATH, staffList);
//                        new Main();
//                    }else {
//                        System.out.println("Wrong phone pattern");
//                        new Account().Register();
//                    }
//                }else {
//                    System.out.println("Wrong email pattern");
//                    new Account().Register();
//                }
//            }
//            else {
//                System.out.println("Wrong password pattern");
//                new Account().Register();
//            }
//        }else {
//            System.out.println("Wrong account pattern");
//            new Account().Register();
//        }
