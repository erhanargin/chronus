package net.novalab.chronus.business.reservation.control;


import net.novalab.chronus.business.reservation.entity.ReservationConstraintViolation;
import net.novalab.chronus.business.reservation.entity.ReservationValidationInfo;

import java.util.List;

public class ReservationResponse {
    //TODO success mantığı detaylandırılabilir
    private boolean success;
    private ReservationValidationInfo candidate;
    private List<ReservationConstraintViolation> constraintViolations;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ReservationValidationInfo getCandidate() {
        return candidate;
    }

    public void setCandidate(ReservationValidationInfo candidate) {
        this.candidate = candidate;
    }

    public List<ReservationConstraintViolation> getConstraintViolations() {
        return constraintViolations;
    }

    public void setConstraintViolations(List<ReservationConstraintViolation> constraintViolations) {
        this.constraintViolations = constraintViolations;
    }
}
