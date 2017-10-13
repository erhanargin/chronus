package net.novalab.chronus.business.reservation.control;

import net.novalab.chronus.business.capacity.control.CapacityManager;
import net.novalab.chronus.business.capacity.entity.Capacity;
import net.novalab.chronus.business.capacity.entity.CapacityDetail;
import net.novalab.chronus.business.reservation.entity.Reservation;
import net.novalab.chronus.business.reservation.entity.ReservationCandidate;
import net.novalab.chronus.business.reservationcontext.entity.ReservationContext;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.LinkedList;
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
        //TODO: response'ların her biri farklı bir fabrikadan olabilir!!!!!!!!!!
        List<ReservationResponse> reservationResponses = reservationContext.getReservationRequests().stream()
                .map(this::requestReservation)
                .collect(toList());
        Reservation reservation = prepareReservation(bestCandidate.getValidatedRequirement());
        entityManager.persist(reservation);
        return reservation;
    }

    private ReservationResponse requestReservation(ReservationRequest reservationRequest) {
        ReservationResponse response = new ReservationResponse(reservationRequest);
        List<Capacity> availableCapacities = capacityManager
                .getAvailableCapacities(reservationRequest.getProduct(), reservationRequest.getQty());
        List<ReservationCandidate> reservationCandidates = availableCapacities.stream()
                .map(capacity -> new ReservationCandidate(capacity, reservationValidator.validateCapacityForRequest(capacity, reservationRequest)))
                .collect(toList());
        ReservationCandidate bestCandidate = reservationValidator.findBestCandidate(reservationCandidates);
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
        return response;
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
