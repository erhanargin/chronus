package net.novalab.chronus.business.capacityrequirement.control;

import net.novalab.chronus.business.capacityrequirement.entity.CapacityRequirement;
import net.novalab.chronus.business.reservation.entity.Product;

public interface CapacityRequirementCalculator {
    CapacityRequirement calculate(Product product, double qty);
}
