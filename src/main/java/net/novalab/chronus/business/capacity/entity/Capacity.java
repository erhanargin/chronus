package net.novalab.chronus.business.capacity.entity;

import net.novalab.chronus.business.reservation.entity.Facility;

import java.time.LocalDateTime;
import java.util.NavigableSet;

public class Capacity {
    private Facility facility;
    private NavigableSet<CapacityDetail> details;


    public Facility getFacility() {
        return facility;
    }

    public void setFacility(Facility facility) {
        this.facility = facility;
    }

    public NavigableSet<CapacityDetail> getDetails() {
        return details;
    }

    public void setDetails(NavigableSet<CapacityDetail> details) {
        this.details = details;
    }
}
