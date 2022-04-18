package service.receiptService;

import config.ConfigReadAndWrite;
import model.Receipt;
import model.Room;
import model.RoomStatus;

import java.util.Date;
import java.util.List;

public class ReceiptServiceIMPL implements IReceiptService {
    public static String ROOMPATH = "C:\\Users\\Sang\\IntelliJ IDEA\\MD2_CaseStudy\\src\\data\\roomData.txt";
    public static String RECEIPTPATH = "C:\\Users\\Sang\\IntelliJ IDEA\\MD2_CaseStudy\\src\\data\\receiptData.txt";
    public static ConfigReadAndWrite<Room> configReadAndWriteRoom = new ConfigReadAndWrite<>();
    public static ConfigReadAndWrite<Receipt> configReadAndWriteReceipt = new ConfigReadAndWrite<>();
    public static List<Room> roomList = configReadAndWriteRoom.readFromFile(ROOMPATH);
    public static List<Receipt> receiptList = configReadAndWriteReceipt.readFromFile(RECEIPTPATH);

    @Override
    public List<Receipt> findAll() {
        configReadAndWriteReceipt.writeToFile(RECEIPTPATH, receiptList);
        return receiptList;
    }

    @Override
    public void save(Receipt receipt) {
        receiptList.add(receipt);
        configReadAndWriteReceipt.writeToFile(RECEIPTPATH, receiptList);
    }

    @Override
    public void deleteById(int id) {

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

    }

    public void setRoomStt() {
        int roomId = 0;
        Date date = new Date();
        for (int i = 0; i < receiptList.size(); i++) {
            if (receiptList.get(i).getCheckOut().compareTo(date) >= 0 && receiptList.get(i).getCheckIn().compareTo(date)<=0) {
                roomId = receiptList.get(i).getRoom().getRoomId();
                for (int j = 0; j < roomList.size(); j++) {
                    if (roomId == roomList.get(j).getRoomId()) {
                        roomList.get(j).setRoomStatus(RoomStatus.UNAVAILABLE);
                        configReadAndWriteRoom.writeToFile(ROOMPATH, roomList);
                    }
                }
            } else {
                roomId = receiptList.get(i).getRoom().getRoomId();
                for (int j = 0; j < roomList.size(); j++) {
                    if (roomId == roomList.get(j).getRoomId()) {
                        roomList.get(j).setRoomStatus(RoomStatus.AVAILABLE);
                        configReadAndWriteRoom.writeToFile(ROOMPATH, roomList);
                    }
                }
            }
        }
    }
}
