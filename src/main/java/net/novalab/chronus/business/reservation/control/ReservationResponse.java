package net.novalab.chronus.business.reservation.control;


import net.novalab.chronus.business.reservation.entity.ReservationCandidate;

public class ReservationResponse {
    //TODO success mantığı detaylandırılabilir
    private boolean success;
    private ReservationCandidate candidate;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ReservationCandidate getCandidate() {
        return candidate;
    }

    public void setCandidate(ReservationCandidate candidate) {
        this.candidate = candidate;
    }
}
