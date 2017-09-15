package net.novalab.chronus.business.capacity.control;

import net.novalab.chronus.business.capacity.entity.Capacity;
import net.novalab.chronus.business.capacityrequirement.entity.CapacityRequirement;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.NavigableSet;

public interface CapacityManager {
    NavigableSet<Capacity> getAvailableCapacities(CapacityRequirement capacityRequirement);

    void allocate(Map<LocalDateTime, CapacityRequirement> allocations);
}
