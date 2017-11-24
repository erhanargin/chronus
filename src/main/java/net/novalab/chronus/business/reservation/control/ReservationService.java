package net.novalab.chronus.business.reservation.control;

import net.novalab.chronus.business.capacity.control.CapacityManager;
import net.novalab.chronus.business.capacity.entity.Capacity;
import net.novalab.chronus.business.reservation.entity.Reservation;
import net.novalab.chronus.business.reservation.entity.ReservationValidationInfo;
import net.novalab.chronus.business.reservationcontext.entity.ReservationContext;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class ReservationService {
    @Inject
    CapacityManager capacityManager;
    @Inject
    ReservationValidator reservationValidator;
    @Inject
    ReservationContext reservationContext;
    @Inject
    EntityManager entityManager;

    public ReservationResponse reserve() {
        ReservationResponse response = requestReservation();
        if (response.isSuccess()) {
            Reservation reservation = prepareReservation(response);
            entityManager.persist(reservation);
        }
        return response;
    }

    private ReservationResponse requestReservation() {
        ReservationResponse response = find();
        if (response.isSuccess()) {
            allocateCapacity(response);
        }
        return response;
    }

    public ReservationResponse find() {
        ReservationResponse response = new ReservationResponse();
        List<Capacity> availableCapacities = capacityManager
                .getAvailableCapacities(reservationContext.getProduct(), reservationContext.getRequestedQty());
        List<ReservationValidationInfo> validationInfos = availableCapacities.stream()
                .map(capacity -> reservationValidator.validateCapacity(capacity))
                .collect(toList());
        List<ReservationValidationInfo> validInfos = validationInfos.stream()
                .filter(ReservationValidationInfo::isValid).collect(toList());
        if (!validInfos.isEmpty()) {
            response.setCandidate(reservationValidator.findBestCandidate(validInfos));
            response.setSuccess(true);
        } else if (!validationInfos.isEmpty()) {
            response.setConstraintViolations(validationInfos.get(0).getConstraintViolations());
        }
        return response;
    }

    private void allocateCapacity(ReservationResponse response) {
        try {
            ReservationValidationInfo bestCandidate = response.getCandidate();
            capacityManager.updateCapacityWithValidatedCapacity(bestCandidate.getOriginalCapacity(),
                    bestCandidate.getValidatedRequirement());
        } catch (Exception e) {
            //TODO exception mekanizması düzenlenmeli
            e.printStackTrace();
            response.setSuccess(false);
        }
    }

    private Reservation prepareReservation(ReservationResponse response) {
        return null;
    }

}
