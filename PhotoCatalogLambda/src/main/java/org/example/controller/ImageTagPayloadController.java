package org.example.controller;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.ImageTagPayloadModel;
import org.example.service.ImageTagPayloadService;

import java.util.Map;

public class ImageTagPayloadController implements RequestHandler<Map<String, Object>, String> {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String handleRequest(Map<String, Object> input, Context context) {
        try {
            // Extract JSON payload from input
            String body = (String) input.get("body");
            if (body == null) {
                return createErrorResponse("Missing request body");
            }

            // Convert JSON to ImageTagPayloadModel
            ImageTagPayloadModel imageTagPayload = objectMapper.readValue(body, ImageTagPayloadModel.class);

            // Process the image tags
            String result = ImageTagPayloadService.processImageTags(imageTagPayload);

            // Return success response as JSON
            return createSuccessResponse(result);

        } catch (Exception e) {
            context.getLogger().log("Error processing image tags: " + e.getMessage());
            return createErrorResponse("Error processing tags: " + e.getMessage());
        }
    }

    private String createSuccessResponse(String message) {
        return "{\"status\":\"success\", \"message\":\"" + message + "\"}";
    }

    private String createErrorResponse(String message) {
        return "{\"status\":\"error\", \"message\":\"" + message + "\"}";
    }
}
