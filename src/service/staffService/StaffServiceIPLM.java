package service.staffService;

import config.ConfigReadAndWrite;
import model.Staff;

import java.util.List;

public class StaffServiceIPLM implements IStaffService {
    public static String PATH = "C:\\Users\\Sang\\IntelliJ IDEA\\MD2_CaseStudy\\src\\data\\staffData.txt";
    public static List<Staff> staffList = new ConfigReadAndWrite<Staff>().readFromFile(PATH);
    @Override
    public List<Staff> findAll() {
        return staffList;
    }

    @Override
    public void save(Staff staff) {

    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public Staff findById(int id) {
        return null;
    }

    @Override
    public void editById(int id) {

    }
}
