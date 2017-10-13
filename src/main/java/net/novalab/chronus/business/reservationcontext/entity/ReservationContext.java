package net.novalab.chronus.business.reservationcontext.entity;

import net.novalab.chronus.business.reservation.control.ReservationRequest;

import java.time.LocalDateTime;
import java.util.List;

public class ReservationContext {
    private double requestedQty;
    private LocalDateTime commitDate;
    private List<ReservationRequest> reservationRequests;

    public List<ReservationRequest> getReservationRequests() {
        return reservationRequests;
    }

    public void setReservationRequests(List<ReservationRequest> reservationRequests) {
        this.reservationRequests = reservationRequests;
    }
}
