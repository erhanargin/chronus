package net.novalab.chronus.business.reservation.entity;

import net.novalab.chronus.business.capacity.entity.Capacity;


public class ReservationCandidate {
    private Capacity originalCapacity;
    private Capacity validatedRequirement;

    public ReservationCandidate(Capacity originalCapacity, Capacity validatedRequirement) {
        this.originalCapacity = originalCapacity;
        this.validatedRequirement = validatedRequirement;
    }

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
}
