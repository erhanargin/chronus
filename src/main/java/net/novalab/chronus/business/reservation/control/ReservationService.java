package net.novalab.chronus.business.reservation.control;

import net.novalab.chronus.business.capacity.control.CapacityManager;
import net.novalab.chronus.business.capacity.entity.Capacity;
import net.novalab.chronus.business.capacity.entity.CapacityDetail;
import net.novalab.chronus.business.reservation.entity.Reservation;
import net.novalab.chronus.business.reservation.entity.ReservationCandidate;
import net.novalab.chronus.business.reservationcontext.entity.ReservationContext;
import net.novalab.chronus.business.reservationcontext.entity.ReservationRequest;

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

    public Reservation reserve() {
        for(ReservationRequest reservationRequest : reservationContext.getReservationRequests()){
            processReservationRequest(reservationRequest);
        }
        Reservation reservation = prepareReservation(bestCandidate.getValidatedRequirement());
        entityManager.persist(reservation);
        return reservation;
    }

    private void processReservationRequest(ReservationRequest reservationRequest) {
        List<Capacity> availableCapacities = capacityManager
                .getAvailableCapacities(reservationRequest.getProduct(), reservationRequest.getQty());
        List<ReservationCandidate> reservationCandidates = availableCapacities.stream()
                .map(capacity -> new ReservationCandidate(capacity, reservationValidator.validate(capacity)))
                .collect(toList());
        ReservationCandidate bestCandidate = reservationValidator.findBestCandidate(reservationCandidates);
        updateCapacityWithValidatedCapacity(bestCandidate.getOriginalCapacity(), bestCandidate.getValidatedRequirement());
        capacityManager.allocate(bestCandidate.getOriginalCapacity());
    }

    private void updateCapacityWithValidatedCapacity(Capacity originalCapacity, Capacity validatedRequirement) {
        for(CapacityDetail requirementDetail : validatedRequirement.getDetails()){
            CapacityDetail originalCapacityDetail = originalCapacity.getDetails().stream()
                    .filter(capacityDetail -> capacityDetail.getStart().isEqual(requirementDetail.getStart()))
                    .findFirst().get();
            originalCapacityDetail.setCapacity(originalCapacityDetail.getCapacity() - requirementDetail.getCapacity());
            if(originalCapacityDetail.getCapacity() < 0){
                originalCapacity.getDetails().remove(originalCapacityDetail);
            }
        }
    }

    private Reservation prepareReservation(Capacity validatedCapacity) {
        return null;
    }

}
