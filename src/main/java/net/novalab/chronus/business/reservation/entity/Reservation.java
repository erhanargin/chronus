package net.novalab.chronus.business.reservation.entity;

import java.time.LocalDateTime;
import java.util.NavigableSet;

public class Reservation {
    private Customer customer;
    private Product product;
    private double requestedQty;
    private NavigableSet<ReservationDetail> reservationDetails;
    private LocalDateTime commitDate;

}
