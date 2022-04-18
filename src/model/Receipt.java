package model;

import java.io.Serializable;
import java.util.Date;

public class Receipt implements Serializable {
    private int receiptId;
    private Customer guest;
    private User staff;
    private Room room;
    private Date checkIn;
    private Date checkOut;
}
