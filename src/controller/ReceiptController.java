package controller;

import model.Receipt;
import service.receiptService.ReceiptServiceIMPL;

import java.util.List;

public class ReceiptController {
    public List<Receipt> showList(){
        return new ReceiptServiceIMPL().findAll();
    }

    public Receipt findById(int id){
        return new ReceiptServiceIMPL().findById(id);
    }
}
