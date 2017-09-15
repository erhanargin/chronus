package net.novalab.chronus.business.reservation.entity;

import java.util.NavigableSet;

public class Reservation {
    private Customer customer;
    private Product product;
    private NavigableSet<ReservationDetail> reservationDetails;

}
