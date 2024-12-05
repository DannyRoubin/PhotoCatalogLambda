package org.example.controller;

import org.example.model.ImageTagPayloadModel;
import org.example.service.ImageTagPayloadService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"}, allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class ImageTagPayloadController {

    @PostMapping("/process-image-tags")
    public ResponseEntity<String> processImageTags(@RequestBody ImageTagPayloadModel imageTagPayload) {
        try {
            // Process the tags using ImageTagPayloadService
            String result = ImageTagPayloadService.processImageTags(imageTagPayload);

            return ResponseEntity.ok(result);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error processing tags: " + e.getMessage());
        }
    }
}
