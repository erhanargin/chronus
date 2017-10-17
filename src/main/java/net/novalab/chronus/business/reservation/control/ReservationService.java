package net.novalab.chronus.business.reservation.control;

import net.novalab.chronus.business.capacity.control.CapacityManager;
import net.novalab.chronus.business.capacity.entity.Capacity;
import net.novalab.chronus.business.capacity.entity.CapacityDetail;
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
        ReservationResponse response = new ReservationResponse();
        List<Capacity> availableCapacities = capacityManager
                .getAvailableCapacities(reservationContext.getProduct(), reservationContext.getRequestedQty());
        List<ReservationValidationInfo> validationInfos = availableCapacities.stream()
                .map(capacity -> reservationValidator.validateCapacity(capacity))
                .collect(toList());
        List<ReservationValidationInfo> validInfos = validationInfos.stream()
                .filter(ReservationValidationInfo::isValid).collect(toList());
        if (!validInfos.isEmpty()) {
            ReservationValidationInfo bestCandidate = reservationValidator.findBestCandidate(validInfos);
            response.setCandidate(bestCandidate);
            updateCapacityWithValidatedCapacity(bestCandidate.getOriginalCapacity(), bestCandidate.getValidatedRequirement());
            try {
                capacityManager.allocate(bestCandidate.getOriginalCapacity());
                response.setSuccess(true);
            } catch (Exception e) {
                //TODO exception mekanizması düzenlenmeli
                e.printStackTrace();
                response.setSuccess(false);
            }
        } else {
            //TODO: burası pek olmadı
            if (!validationInfos.isEmpty()) {
                ReservationValidationInfo validationInfo = validationInfos.get(0);
                response.setConstraintViolations(validationInfo.getConstraintViolations());
            }
            response.setSuccess(false);
        }
        return response;
    }

    private void updateCapacityWithValidatedCapacity(Capacity originalCapacity, Capacity validatedRequirement) {
        for (CapacityDetail requirementDetail : validatedRequirement.getDetails()) {
            CapacityDetail originalCapacityDetail = originalCapacity.getDetails().stream()
                    .filter(capacityDetail -> capacityDetail.getStart().isEqual(requirementDetail.getStart()))
                    .findFirst().get();
            originalCapacityDetail.setCapacity(originalCapacityDetail.getCapacity() - requirementDetail.getCapacity());
            if (originalCapacityDetail.getCapacity() < 0) {
                originalCapacity.getDetails().remove(originalCapacityDetail);
            }
        }
    }

    private Reservation prepareReservation(ReservationResponse response) {
        return null;
    }

}
