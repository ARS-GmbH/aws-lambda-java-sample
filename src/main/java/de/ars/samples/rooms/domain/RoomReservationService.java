package de.ars.samples.rooms.domain;

import java.time.LocalDate;
import java.util.UUID;

public class RoomReservationService {

    /**
     * Does a reservation for the given room at the given date.
     *
     * @param roomNumber the room's number
     * @param date       the date
     * @return the UUID of the generated reservation
     * @throws RoomReservationUnavailableException if the room is still reserved
     * @throws RoomReservationInvalidException     if the given room's number is null, or the given date is null or in the past
     */
    public UUID doReservation(String roomNumber, LocalDate date)
            throws RoomReservationUnavailableException, RoomReservationInvalidException {

        // validation -> we could include Jakarta Bean Validation here
        if (roomNumber == null || date == null || date.isBefore(LocalDate.now())) {
            throw new RoomReservationInvalidException();
        }
        // just a simple random implementation
        if (Math.random() < 0.5) {
            return UUID.randomUUID();
        } else {
            throw new RoomReservationUnavailableException();
        }

    }

}
