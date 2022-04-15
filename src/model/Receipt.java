package model;

import java.io.Serializable;
import java.util.Date;

public class Receipt implements Serializable {
    private int receiptId;
    private Guest guest;
    private Staff staff;
    private Date checkIn;
    private Date checkOut;

}
