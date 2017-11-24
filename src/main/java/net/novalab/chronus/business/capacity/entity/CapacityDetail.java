package net.novalab.chronus.business.capacity.entity;


import net.novalab.chronus.business.reservation.entity.Facility;

import javax.persistence.Entity;
import javax.persistence.Version;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class CapacityDetail {
    private LocalDateTime start;
    private LocalDateTime end;
    private double capacity;
    @Version
    private Date updatedTime;
    private Facility facility;

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

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    private void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Facility getFacility() {
        return facility;
    }

    public void setFacility(Facility facility) {
        this.facility = facility;
    }
}
