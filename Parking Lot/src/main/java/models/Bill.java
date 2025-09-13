package models;

import enums.BillStatus;

import java.util.Date;
import java.util.List;

public class Bill extends BaseModel {
    private int billNumber;
    private int amount;
    private Date exitTime;
    private Ticket ticket;
    private List<Payment> paymentModes;
    private Gate gate;
    private BillStatus billStatus;

    public int getBillNumber() {
        return billNumber;
    }

    public void setBillNumber(int billNumber) {
        this.billNumber = billNumber;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getExitTime() {
        return exitTime;
    }

    public void setExitTime(Date exitTime) {
        this.exitTime = exitTime;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public List<Payment> getPaymentModes() {
        return paymentModes;
    }

    public void setPaymentModes(List<Payment> paymentModes) {
        this.paymentModes = paymentModes;
    }

    public Gate getGate() {
        return gate;
    }

    public void setGate(Gate gate) {
        this.gate = gate;
    }

    public BillStatus getBillStatus() {
        return billStatus;
    }

    public void setBillStatus(BillStatus billStatus) {
        this.billStatus = billStatus;
    }
}
