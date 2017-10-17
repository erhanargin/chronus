package net.novalab.chronus.business.reservation.entity;

import net.novalab.chronus.business.capacity.entity.Capacity;

import java.util.List;


public class ReservationValidationInfo {
    private Capacity originalCapacity;
    private Capacity validatedRequirement;
    private List<ReservationConstraintViolation> constraintViolations;

    public Capacity getOriginalCapacity() {
        return originalCapacity;
    }

    public void setOriginalCapacity(Capacity originalCapacity) {
        this.originalCapacity = originalCapacity;
    }

    public Capacity getValidatedRequirement() {
        return validatedRequirement;
    }

    public void setValidatedRequirement(Capacity validatedRequirement) {
        this.validatedRequirement = validatedRequirement;
    }

    public List<ReservationConstraintViolation> getConstraintViolations() {
        return constraintViolations;
    }

    public void setConstraintViolations(List<ReservationConstraintViolation> constraintViolations) {
        this.constraintViolations = constraintViolations;
    }

    public boolean isValid(){
        return constraintViolations.isEmpty();
    }
}
