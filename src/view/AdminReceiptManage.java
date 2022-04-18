package view;

import java.util.Scanner;

public class AdminReceiptManage {
    public AdminReceiptManage() {
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Show receipt list");
        System.out.println("2. Add receipt");
        System.out.println("3. Edit receipt by ID");
        System.out.println("4. Delete receipt");
        System.out.println("5. Calculate total income by time");
        System.out.println("6. Comeback menu");
        System.out.println("7. Logout");
        int choose = sc.nextInt();
        switch (choose) {
            case 6:
                new AdminMenu();
                break;
            case 7:
                new Account().Logout();
                break;
        }
    }
}
