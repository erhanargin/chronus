package net.novalab.chronus.business.reservationcontext.entity;

import net.novalab.chronus.business.reservation.entity.Customer;
import net.novalab.chronus.business.reservation.entity.Product;

import java.time.LocalDateTime;


public class ReservationRequest {
    private Customer customer;
    private Product product;
    private double qty;
    private LocalDateTime commitDate;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getQty() {
        return qty;
    }

    public void setQty(double qty) {
        this.qty = qty;
    }

    public LocalDateTime getCommitDate() {
        return commitDate;
    }

    public void setCommitDate(LocalDateTime commitDate) {
        this.commitDate = commitDate;
    }
}
