package view;

import config.ConfigReadAndWrite;
import controller.RoomController;
import jdk.internal.dynalink.linker.LinkerServices;
import model.Room;
import model.RoomStatus;
import service.roomService.RoomServiceIMPL;

import java.util.List;
import java.util.Scanner;

public class AdminRoomManage {
    public static String ROOMPATH = "C:\\Users\\Sang\\IntelliJ IDEA\\MD2_CaseStudy\\src\\data\\roomData.txt";
    public static List<Room> roomList = new ConfigReadAndWrite<Room>().readFromFile(ROOMPATH);

    public AdminRoomManage() {
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Show roomlist");
        System.out.println("2. Add room");
        System.out.println("3. Edit room");
        System.out.println("4. Show available rooms");
        System.out.println("5. Find room by price range");
        System.out.println("6. Find room by Id");
        System.out.println("7. Delete room");
        System.out.println("8. Come back to menu");
        int choose = sc.nextInt();
        switch (choose) {
            case 1:
                new RoomController().showList();
                new AdminRoomManage();
            case 2:
                int id = 1;
                if (roomList.size() == 0) {
                    id = 1;
                } else {
                    id = roomList.get(roomList.size() - 1).getRoomId() + 1;
                }
                System.out.println("Enter room price: ");
                double price = sc.nextDouble();
                sc.nextLine();
                RoomStatus roomStatus = RoomStatus.AVAILABLE;
                System.out.println("Enter number of Bedrooms: ");
                int numBedroom = sc.nextInt();
                sc.nextLine();
                System.out.println("Enter number of Toilets: ");
                int numToilet = sc.nextInt();
                sc.nextLine();
                Room room = new Room(id, price, roomStatus, numBedroom, numToilet);
                new RoomController().addRoom(room);
                new AdminRoomManage();
            case 3:
                System.out.println("Enter room's id to edit");
                int editId = sc.nextInt();
                sc.nextLine();
                if (new RoomServiceIMPL().findById(editId) == null) {
                    System.err.println("This room is not exist!");
                    new AdminRoomManage();
                } else {
                    RoomStatus roomStatus1 = new RoomServiceIMPL().findById(editId).getRoomStatus();
                    System.out.println("Enter editing price: ");
                    double editPrice = sc.nextDouble();
                    sc.nextLine();
                    System.out.println("Enter number of bedrooms to edit: ");
                    int editNumBed = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter numbers of toilets to edit: ");
                    int editNumToil = sc.nextInt();
                    sc.nextLine();
                    Room room1 = new Room(editId, editPrice, roomStatus1, editNumBed, editNumToil);
                    new RoomController().editById(room1);
                    new AdminRoomManage();
                }
            case 4:
                new RoomController().findAvailableRoom();
                new AdminRoomManage();
            case 5:
                System.out.println("Enter min range: ");
                double min = sc.nextDouble();
                sc.nextLine();
                System.out.println("Enter max range: ");
                double max = sc.nextDouble();
                sc.nextLine();
                new RoomController().findByPrice(min, max);
                new AdminRoomManage();
            case 6:
                System.out.println("Enter room's id to find: ");
                int roomId = sc.nextInt();
                sc.nextLine();
                new RoomController().findRoomById(roomId);
                new AdminRoomManage();
            case 7:
                System.out.println("Enter room's id to delete: ");
                int deleteId = sc.nextInt();
                sc.nextLine();
                new RoomController().deleteRoom(deleteId);
                new AdminRoomManage();
            case 8:
                new AdminMenu();
        }
    }
}
