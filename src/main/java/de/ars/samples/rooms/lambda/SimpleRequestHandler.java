package de.ars.samples.rooms.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import de.ars.samples.rooms.domain.RoomReservationInvalidException;
import de.ars.samples.rooms.domain.RoomReservationUnavailableException;
import de.ars.samples.rooms.domain.RoomReservationService;
import lombok.extern.log4j.Log4j2;

/**
 * This request handler uses AWS Lambda's default JSON serialization.
 */
@Log4j2
public class SimpleRequestHandler
        implements RequestHandler<RoomReservationRequest, RoomReservationResult> {

    // service lifecycle is bound to the request handler
    private final RoomReservationService service = new RoomReservationService();

    @Override
    public RoomReservationResult handleRequest(RoomReservationRequest input, Context context) {
        try {
            final var uuid = service.doReservation(input.getRoomNumber(), input.getDate());
            log.debug(
                    "Successfully reserved room '{}' on {}.",
                    input.getRoomNumber(),
                    input.getDate()
            );
            return RoomReservationResult.ok(uuid);
        } catch (RoomReservationUnavailableException e) {
            log.debug(
                    "Could not reserve room '{}' on {} because it is not available.",
                    input.getRoomNumber(),
                    input.getDate()
            );
            return RoomReservationResult.unavailable();
        } catch (RoomReservationInvalidException e) {
            log.debug(
                    "The reservation request is invalid (room '{}', date {}).",
                    input.getRoomNumber(),
                    input.getDate()
            );
            return RoomReservationResult.invalid();
        }
    }

}
