package view;

import java.util.Scanner;

public class Main {
    public Main() {
        System.out.println("Hello main");
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Register");
        System.out.println("2. Login");
        int choose = sc.nextInt();
        switch (choose){
            case 1: new Account().Register();
                break;
            case 2: new Account().Login();
                break;
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}
