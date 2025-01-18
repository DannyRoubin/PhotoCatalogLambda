package org.example.service;

import org.example.Secrets;
import org.example.model.ImageTagPayloadModel;
import com.azure.cosmos.*;
import com.azure.cosmos.models.CosmosItemResponse;

public class ImageTagPayloadService {

    public static String processImageTags(ImageTagPayloadModel payload) {
        CosmosClient cosmosClient = new CosmosClientBuilder()
                .endpoint(Secrets.COSMOS_ENDPOINT)
                .key(Secrets.COSMOS_KEY)
                .consistencyLevel(ConsistencyLevel.EVENTUAL)
                .buildClient();

        CosmosContainer cosmosContainer = cosmosClient
                .getDatabase(Secrets.DATABASE_ID)
                .getContainer(Secrets.CONTAINER_ID);

        try {
            // Log the payload
            System.out.println("Payload being inserted: " + payload);
            System.out.println("Payload ID being inserted: " + payload.getPhotoID());

            // Ensure PartitionKey matches the PhotoID field in the document
            CosmosItemResponse<ImageTagPayloadModel> itemResponse = cosmosContainer.createItem(payload);
            return "Success: Tags added successfully for photoID=" + payload.getPhotoID() +
                    ", Status Code: " + itemResponse.getStatusCode();
        } catch (CosmosException e) {
            e.printStackTrace();
            return "Error: Failed to add tags - " + e.getMessage();
        } finally {
            cosmosClient.close();
        }
    }

    // New method to test insertion with static values
    public static void testInsertWithStaticData() {
        String PhotoID = "42";
        String photoGUID = "1f2fa872-fbe3-4823-814c-4b9263e7d3b4";
        String tags = "small to medium-sized cats,cat,felidae,animal,domestic cat,wall,whiskers,mammal";

        ImageTagPayloadModel payload = new ImageTagPayloadModel(PhotoID, photoGUID, tags);

        String result = processImageTags(payload);

        System.out.println(result);
    }
}