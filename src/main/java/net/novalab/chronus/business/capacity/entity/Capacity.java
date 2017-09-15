package net.novalab.chronus.business.capacity.entity;

import java.time.LocalDateTime;

public class Capacity {
    private LocalDateTime start;
    private LocalDateTime end;
    private double qty;

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public double getQty() {
        return qty;
    }

    public void setQty(double qty) {
        this.qty = qty;
    }
}
