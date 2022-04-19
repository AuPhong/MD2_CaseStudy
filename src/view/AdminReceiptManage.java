package view;

import config.ConfigLogin;
import config.ConfigReadAndWrite;
import controller.ReceiptController;
import controller.RoomController;
import model.Customer;
import model.Receipt;
import model.Room;
import model.User;
import service.roomService.RoomServiceIMPL;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

public class AdminReceiptManage {
    public AdminReceiptManage() throws ParseException {
        String emailRegex = "^[A-Za-z0-9]+[A-Za-z0-9]*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)$";
        String phonenumberRegex = "^0[0-9]{8,9}$";

        SimpleDateFormat myFormat = new SimpleDateFormat("dd MM yyyy");

        Scanner sc = new Scanner(System.in);
        String RECEIPTPATH = "C:\\Users\\Sang\\IntelliJ IDEA\\MD2_CaseStudy\\src\\data\\receiptData.txt";
        List<Receipt> receiptList = new ConfigReadAndWrite<Receipt>().readFromFile(RECEIPTPATH);
        System.out.println("1. Show receipt list");
        System.out.println("2. Add receipt");
        System.out.println("3. Edit receipt by ID");
        System.out.println("4. Find receipt by ID");
        System.out.println("5. Delete receipt");
        System.out.println("6. Calculate total income by time");
        System.out.println("7. Comeback menu");
        System.out.println("8. Logout");
        int choose = sc.nextInt();
        sc.nextLine();
        switch (choose) {
            case 1:
                ArrayList<Room> arrayList = new ArrayList<>();
                if (new ReceiptController().showList().equals(arrayList)) {
                    System.err.println("There is no receipt data!");
                } else {
                    System.out.println(new ReceiptController().showList());
                }
                new AdminReceiptManage();
            case 2:
                int id = 0;
                if (receiptList.size() == 0) {
                    id = 1;
                } else {
                    id = receiptList.get(receiptList.size() - 1).getReceiptId() + 1;
                }
                System.out.println("Enter customer information. ");
                System.out.println("Enter customer's name: ");
                String name = sc.nextLine();
                System.out.println("Enter customer's age: ");
                int age = sc.nextInt();
                sc.nextLine();
                System.out.println("Enter customer's address: ");
                String address = sc.nextLine();
                System.out.println("Enter customer's phone number: ");
                String phonenumber = "";
                while (true) {
                    phonenumber = sc.nextLine();
                    if (Pattern.matches(phonenumberRegex, phonenumber)) {
                        break;
                    } else {
                        System.err.println("Wrong phonenumber pattern, enter again: ");
                    }
                }
                System.out.println("Enter customer's email: ");
                String email = "";
                while (true) {
                    email = sc.nextLine();
                    if (Pattern.matches(emailRegex, email)) {
                        break;
                    } else {
                        System.err.println("Wrong email pattern, enter again: ");
                    }
                }
                Customer customer = new Customer(name, age, address, phonenumber, email);
                User staff = new ConfigLogin().readFromFile("C:\\Users\\Sang\\IntelliJ IDEA\\MD2_CaseStudy\\src\\data\\userLoginData.txt");
                System.out.println("Choose from available room: ");
                List<Room> availableRooms = new RoomServiceIMPL().findAvailableRoom();
                System.out.println(availableRooms);
                System.out.println("Enter room's id:");
                Room room = null;
                int roomId = sc.nextInt();
                sc.nextLine();
                for (int i = 0; i < availableRooms.size(); i++) {
                    if (roomId == availableRooms.get(i).getRoomId()) {
                        room = availableRooms.get(i);
                    }
                }
                System.out.println("Enter checkin date (dd MM yy): ");
                String dateIn = sc.nextLine();
                System.out.println("Enter checkout date (dd MM yy): ");
                String dateOut = sc.nextLine();

                Date checkIn = myFormat.parse(dateIn);
                Date checkOut = myFormat.parse(dateOut);
                long diff = checkOut.getTime() - checkIn.getTime();
                long days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
                double totalPrice = days * room.getPrice();
                Receipt receipt = new Receipt(id,customer,staff,room,checkIn,checkOut,totalPrice);
                new ReceiptController().addReceipt(receipt);
                new ReceiptController().setRoomStt();
                new AdminReceiptManage();
            case 7:
                new AdminMenu();
                break;
            case 8:
                new Account().Logout();
                break;
        }
    }
}
