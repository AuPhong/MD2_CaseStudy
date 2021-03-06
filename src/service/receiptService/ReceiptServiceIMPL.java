package service.receiptService;

import config.ConfigReadAndWrite;
import model.Customer;
import model.Receipt;
import model.Room;
import model.RoomStatus;

import java.util.Date;
import java.util.List;

public class ReceiptServiceIMPL implements IReceiptService {
    public static String ROOMPATH = "C:\\Users\\Sang\\IntelliJ IDEA\\MD2_CaseStudy\\src\\data\\roomData.txt";
    public static String RECEIPTPATH = "C:\\Users\\Sang\\IntelliJ IDEA\\MD2_CaseStudy\\src\\data\\receiptData.txt";
    public static String CSVPATH = "C:\\Users\\Sang\\IntelliJ IDEA\\MD2_CaseStudy\\src\\data\\receiptData.csv";
    public static ConfigReadAndWrite<Room> configReadAndWriteRoom = new ConfigReadAndWrite<>();
    public static ConfigReadAndWrite<Receipt> configReadAndWriteReceipt = new ConfigReadAndWrite<>();
    public static List<Room> roomList = configReadAndWriteRoom.readFromFile(ROOMPATH);
    public static List<Receipt> receiptList = configReadAndWriteReceipt.readFromFile(RECEIPTPATH);

    @Override
    public List<Receipt> findAll() {
        configReadAndWriteReceipt.writeToFile(RECEIPTPATH, receiptList);
        configReadAndWriteReceipt.writeToFile(CSVPATH, receiptList);
        return receiptList;
    }

    @Override
    public void save(Receipt receipt) {
        receiptList.add(receipt);
        configReadAndWriteReceipt.writeToFile(RECEIPTPATH, receiptList);
    }

    @Override
    public void deleteById(int id) {
        if (findById(id) == null) {
            System.err.println("Cannot found receipt to delete!");
        } else {
            for (int i = 0; i < receiptList.size(); i++) {
                if (receiptList.get(i).getReceiptId() == id) {
                    receiptList.remove(receiptList.get(i));
                    configReadAndWriteReceipt.writeToFile(RECEIPTPATH, receiptList);
                }
            }
        }

    }

    @Override
    public Receipt findById(int id) {
        for (int i = 0; i < receiptList.size(); i++) {
            if (id == receiptList.get(i).getReceiptId()) {
                return receiptList.get(i);
            }
        }
        return null;
    }

    @Override
    public void editById(Receipt receipt) {
//        Customer customerChange = new Customer();
//        Room roomChange = new Room();
          int id = receipt.getReceiptId();
//        Customer customer = receipt.getCustomer();
//        Room room = receipt.getRoom();
//        int roomId = room.getRoomId();
        if (findById(id)==null){
            System.err.println("Cannot find receipt to edit!");
        }else {
            for (int i = 0; i < receiptList.size(); i++) {
                if (receiptList.get(i).getReceiptId()==id){
                    if (receipt.getCustomer().getName().equals("")){}
                    else {
                        receiptList.get(i).getCustomer().setName(receipt.getCustomer().getName());
                    }
                    if (receipt.getCustomer().getAge().equals("")){}
                    else {
                        receiptList.get(i).getCustomer().setAge(receipt.getCustomer().getAge());
                    }
                    if (receipt.getCustomer().getAddress().equals("")){}
                    else {
                        receiptList.get(i).getCustomer().setAddress(receipt.getCustomer().getAddress());
                    }
                    if (receipt.getCustomer().getPhonenumber().equals("")){}
                    else {
                        receiptList.get(i).getCustomer().setPhonenumber(receipt.getCustomer().getPhonenumber());
                    }
                    if (receipt.getCustomer().getEmail().equals("")){}
                    else {
                        receiptList.get(i).getCustomer().setEmail(receipt.getCustomer().getEmail());
                    }
                    if (receipt.getRoom().getRoomId()==-1){}
                    else {
                        receiptList.get(i).getRoom().setRoomId(receipt.getRoom().getRoomId());
                    }
                    if (receipt.getRoom().getPrice()==-1){}
                    else {
                        receiptList.get(i).getRoom().setPrice(receipt.getRoom().getPrice());
                    }
                    if (receipt.getRoom().getNumberOfBedroom()==-1){}
                    else {
                        receiptList.get(i).getRoom().setNumberOfBedroom(receipt.getRoom().getNumberOfBedroom());
                    }
                    if (receipt.getRoom().getNumberOfToilet()==-1){}
                    else {
                        receiptList.get(i).getRoom().setNumberOfToilet(receipt.getRoom().getNumberOfToilet());
                    }
                    if (receipt.getCheckIn().equals("")){}
                    else {
                        receiptList.get(i).setCheckIn(receipt.getCheckIn());
                    }
                    if (receipt.getCheckOut().equals("")){}
                    else {
                        receiptList.get(i).setCheckOut(receipt.getCheckOut());
                    }
                    receiptList.get(i).setTotalPrice(receipt.getTotalPrice());
                }
            }
        }
    }

    public double calculateIncome(Date dateMin, Date dateMax){
        double income = 0;
        for (int i = 0; i < receiptList.size(); i++) {
            if (receiptList.get(i).getCheckOut().compareTo(dateMax)<=0 && receiptList.get(i).getCheckOut().compareTo(dateMin)>=0 ){
                income += receiptList.get(i).getTotalPrice();
            }
        }
        return income;
    }

    public void setRoomStt() {
        int roomId = 0;
        Date date = new Date();
        for (int i = 0; i < receiptList.size(); i++) {
            if (receiptList.get(i).getCheckOut().compareTo(date) >= 0 && receiptList.get(i).getCheckIn().compareTo(date) <= 0) {
                roomId = receiptList.get(i).getRoom().getRoomId();
                for (int j = 0; j < roomList.size(); j++) {
                    if (roomId == roomList.get(j).getRoomId()) {
                        //System.out.println("Change status " +roomList.get(j) +" to UNAVAILABLE");
                        roomList.get(j).setRoomStatus(RoomStatus.UNAVAILABLE);
                        configReadAndWriteRoom.writeToFile(ROOMPATH, roomList);
                    }
                }
            } else {

                roomId = receiptList.get(i).getRoom().getRoomId();
                for (int j = 0; j < roomList.size(); j++) {
                    if (roomId == roomList.get(j).getRoomId()) {
                        //System.out.println("Change status " +roomList.get(j) +" to AVAILABLE");
                        roomList.get(j).setRoomStatus(RoomStatus.AVAILABLE);
                        configReadAndWriteRoom.writeToFile(ROOMPATH, roomList);
                    }
                }
            }
        }
    }
}
