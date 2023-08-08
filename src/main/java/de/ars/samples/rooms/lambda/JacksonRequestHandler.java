package de.ars.samples.rooms.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * This request handler uses Jackson for JSON serialization
 */
public class JacksonRequestHandler implements RequestStreamHandler {

    // do avoid duplicate code, we can use the simple request handler for delegation here
    private final SimpleRequestHandler delegate = new SimpleRequestHandler();

    private final ObjectMapper mapper = createObjectMapper();

    private static ObjectMapper createObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return mapper;
    }

    @Override
    public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {
        final var request = mapper.readValue(input, RoomReservationRequest.class);
        final var response = delegate.handleRequest(request, context);
        mapper.writeValue(output, response);
    }

}
