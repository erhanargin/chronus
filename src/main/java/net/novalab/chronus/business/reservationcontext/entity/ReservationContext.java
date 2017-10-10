package net.novalab.chronus.business.reservationcontext.entity;

import net.novalab.chronus.business.reservation.entity.Customer;
import net.novalab.chronus.business.reservation.entity.Product;

import java.util.List;

public class ReservationContext {
    private List<ReservationRequest> reservationRequests;

    public List<ReservationRequest> getReservationRequests() {
        return reservationRequests;
    }

    public void setReservationRequests(List<ReservationRequest> reservationRequests) {
        this.reservationRequests = reservationRequests;
    }
}
