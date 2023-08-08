package de.ars.samples.rooms.lambda;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder(access = AccessLevel.PRIVATE)
@Getter
public class RoomReservationResult {

    public enum RoomReservationStatus {

        SUCCESSFUL, INVALID, UNAVAILABLE

    }

    private RoomReservationStatus status;
    private UUID uuid;

    public static RoomReservationResult ok(UUID uuid) {
        return RoomReservationResult
                .builder()
                .status(RoomReservationStatus.SUCCESSFUL)
                .uuid(uuid)
                .build();
    }

    public static RoomReservationResult unavailable() {
        return RoomReservationResult
                .builder()
                .status(RoomReservationStatus.UNAVAILABLE)
                .build();
    }

    public static RoomReservationResult invalid() {
        return RoomReservationResult
                .builder()
                .status(RoomReservationStatus.INVALID)
                .build();
    }

}
