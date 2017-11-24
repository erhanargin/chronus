package net.novalab.chronus.business.capacity.control;

import net.novalab.chronus.business.capacity.entity.Capacity;
import net.novalab.chronus.business.reservation.entity.Product;

import java.util.List;

public interface CapacityManager {
    List<Capacity> getAvailableCapacities(Product product, double qty);

    void updateCapacityWithValidatedCapacity(Capacity originalCapacity, Capacity validatedRequirement);
}
