package view;

import config.ConfigReadAndWrite;
import model.Staff;
import service.staffService.StaffServiceIPLM;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Account {
    List<Staff> staffList = StaffServiceIPLM.staffList;
    String PATH = "C:\\Users\\Sang\\IntelliJ IDEA\\MD2_CaseStudy\\src\\data\\staffData.txt";
    Scanner sc = new Scanner(System.in);



    public void Register() {
        int id;
        if (staffList.size() == 0) {
            id = 1;
        } else {
            id = staffList.get(staffList.size() - 1).getId() + 1;
        }
        String emailRegex = "^[A-Za-z0-9]+[A-Za-z0-9]*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)$";
        String accountRegex = "^[a-z]{3}$";
        String phoneRegex = "^0[0-9]{8,9}$";
        String passwordRegex = "^[A-Za-z0-9]{8,16}$";
//        System.out.println("Enter username: ");
//        String username = sc.nextLine();
//        System.out.println("Enter password: ");
//        String password = sc.nextLine();
//        System.out.println("Enter email: ");
//        String email = sc.nextLine();
//        System.out.println("Enter phone: ");
//        String phone = sc.nextLine();
        //boolean check = Pattern.matches()

        String username = "";
        String password = "";
        String email = "";
        String phone = "";

       // boolean b = matcher.matches();
Check check = new Check();

        boolean checkAccountRegex = Pattern.matches(accountRegex, username);
        boolean checkPasswordRegex = Pattern.matches(passwordRegex, password);
        boolean checkEmailRegex = Pattern.matches(emailRegex, email);
        boolean checkPhoneRegex = Pattern.matches(phoneRegex, phone);


//        if (checkAccountRegex) {
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

        System.out.println("Enter account:");
        username = sc.nextLine();
        System.out.println(checkAccountRegex);
        System.out.println(username);
        while (!check.validate(username)) {
            System.out.println("Wrong account pattern");
            username = sc.nextLine();
        }
        while (!checkPasswordRegex) {
            System.out.println("Wrong password pattern");
            password = sc.nextLine();
        }
        while (!checkEmailRegex) {
            System.out.println("Wrong email pattern");
            email = sc.nextLine();
        }
        while (!checkPhoneRegex) {
            System.out.println("Wrong phone pattern");
            phone = sc.nextLine();
        }
        Staff staff = new Staff(id, username, password, email, phone);
        staffList.add(staff);
        new ConfigReadAndWrite<Staff>().writeFromFile(PATH, staffList);
        new Main();

    }

    public void Login() {
        System.out.println("Enter login username: ");
        String loginname = sc.nextLine();
        System.out.println("Enter password: ");
        String loginpassword = sc.nextLine();
        for (int i = 0; i < staffList.size(); i++) {
            if (loginname.equals(staffList.get(i).getUserName()) && loginpassword.equals(staffList.get(i).getPassWord())) {
                new Menu();
            }
        }
    }
}
