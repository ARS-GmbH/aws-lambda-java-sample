package de.ars.samples.rooms.lambda;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RoomReservationRequest {

    @JsonProperty("room")
    private String roomNumber;
    private LocalDate date;

}
