package net.novalab.chronus.business.reservation.control;


import net.novalab.chronus.business.reservation.entity.ReservationCandidate;

public class ReservationResponse {
    private ReservationRequest request;
    //TODO success mantığı detaylandırılabilir
    private boolean success;
    private ReservationCandidate candidate;

    public ReservationResponse(ReservationRequest request) {
        this.request = request;
    }

    public ReservationRequest getRequest() {
        return request;
    }

    public void setRequest(ReservationRequest request) {
        this.request = request;
    }

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
