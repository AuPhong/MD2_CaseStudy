package view;

import java.util.Scanner;

public class AdminRoomManage {
    public AdminRoomManage() {
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Show roomlist");
        System.out.println("2. Add room");
        System.out.println("3. Edit room");
        System.out.println("4. Show available room");
        System.out.println("5. Find room by price");
        System.out.println("6. Come back to menu");
        System.out.println("7. Logout");
        int choose = sc.nextInt();
        switch (choose) {
            case 6:
                new AdminMenu();
                break;
            case 7:
                new Account().Logout();
        }
    }
}
