package org.example;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.DeserializationFeature;
import org.example.service.ImageTagPayloadService;
import org.example.model.ImageTagPayloadModel;

import java.util.Map;

public class Main implements RequestHandler<Map<String, Object>, String> {

    private static final ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    @Override
    public String handleRequest(Map<String, Object> input, Context context) {
        context.getLogger().log("Lambda function triggered with input: " + input.toString());

        if (input == null || input.isEmpty()) {
            context.getLogger().log("Error: Received empty request body");
            return "{\"status\":\"error\", \"message\":\"Received empty request body\"}";
        }

        try {
            // Convert input JSON map into ImageTagPayloadModel
            ImageTagPayloadModel payload = objectMapper.convertValue(input, ImageTagPayloadModel.class);
            context.getLogger().log("Parsed Payload - PhotoID: " + payload.getPhotoID());

            // Process image tags
            String result = ImageTagPayloadService.processImageTags(payload);
            return "{\"status\":\"success\", \"photoID\":\"" + payload.getPhotoID() + "\", \"result\":\"" + result + "\"}";

        } catch (Exception e) {
            context.getLogger().log("Error processing request: " + e.getMessage());
            return "{\"status\":\"error\", \"message\":\"" + e.getMessage() + "\"}";
        }
    }
}
