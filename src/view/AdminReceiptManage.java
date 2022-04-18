package view;

import controller.ReceiptController;
import model.Room;

import java.util.ArrayList;
import java.util.Scanner;

public class AdminReceiptManage {
    public AdminReceiptManage() {
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Show receipt list");
        System.out.println("2. Add receipt");
        System.out.println("3. Edit receipt by ID");
        System.out.println("4. Find receipt by ID");
        System.out.println("5. Delete receipt");
        System.out.println("6. Calculate total income by time");
        System.out.println("7. Comeback menu");
        System.out.println("8. Logout");
        int choose = sc.nextInt();
        switch (choose) {
            case 1:
                ArrayList<Room> arrayList = new ArrayList<>();
                if (new ReceiptController().showList().equals(arrayList)){
                    System.err.println("There is no receipt data!");
                } else {
                    System.out.println(new ReceiptController().showList());
                }
                new AdminRoomManage();
            case 7:
                new AdminMenu();
                break;
            case 8:
                new Account().Logout();
                break;
        }
    }
}
