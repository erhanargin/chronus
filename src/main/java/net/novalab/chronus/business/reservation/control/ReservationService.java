package net.novalab.chronus.business.reservation.control;

import net.novalab.chronus.business.capacity.control.CapacityManager;
import net.novalab.chronus.business.capacity.entity.Capacity;
import net.novalab.chronus.business.capacityrequirement.control.CapacityRequirementCalculator;
import net.novalab.chronus.business.capacityrequirement.entity.CapacityRequirement;
import net.novalab.chronus.business.reservation.entity.Reservation;
import net.novalab.chronus.business.reservationcontext.entity.ReservationContext;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.NavigableSet;

import static java.util.stream.Collectors.*;

public class ReservationService {
    @Inject
    CapacityManager capacityManager;
    @Inject
    CapacityRequirementCalculator capacityRequirementCalculator;
    @Inject
    ReservationValidator reservationValidator;
    @Inject
    ReservationContext reservationContext;
    @Inject
    EntityManager entityManager;

    public Reservation reserve() {
        CapacityRequirement capacityRequirement = capacityRequirementCalculator.calculate(reservationContext.getProduct(),
                reservationContext.getQty());
        NavigableSet<Capacity> availableCapacities = capacityManager.getAvailableCapacities(capacityRequirement);
        NavigableSet<Capacity> validatedCapacities = reservationValidator.validate(availableCapacities);
        Map<LocalDateTime, CapacityRequirement> requirements = validatedCapacities.stream().collect(toMap(Capacity::getStart,
                capacity -> capacityRequirementCalculator.calculate(reservationContext.getProduct(), capacity.getQty())));
        capacityManager.allocate(requirements);
        Reservation reservation = prepareReservation(validatedCapacities);
        entityManager.persist(reservation);
        return reservation;
    }

    private Reservation prepareReservation(NavigableSet<Capacity> validatedCapacities) {
        return null;
    }

}
