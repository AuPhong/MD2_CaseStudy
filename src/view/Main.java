package view;

import config.ConfigLogin;

import java.util.Scanner;

public class Main {
    public Main() {
        String LOGINPATH = "C:\\Users\\Sang\\IntelliJ IDEA\\MD2_CaseStudy\\src\\data\\userLoginData.txt";
        new ConfigLogin().writeToFile(LOGINPATH,null);
        System.out.println("=================HOTEL MANAGEMENT=================");
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Logout");
        int choose = sc.nextInt();
        switch (choose){
            case 1: new Account().Register();
                break;
            case 2: new Account().Login();
                break;
            case 3: new Account().Logout();
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}
