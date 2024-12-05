package org.example.service;

import com.azure.cosmos.*;
import com.azure.cosmos.models.CosmosItemResponse;
import com.azure.cosmos.models.PartitionKey;
import org.example.model.ImageTagPayloadModel;

public class ImageTagPayloadService {

    // Setup Cosmos DB client and container references
    private static final String COSMOS_ENDPOINT = "your-cosmos-db-endpoint"; // Replace with your Cosmos DB endpoint
    private static final String COSMOS_KEY = "your-cosmos-db-primary-key"; // Replace with your primary key
    private static final String DATABASE_ID = "PhotoCatalogDatabase"; // Replace with your database name
    private static final String CONTAINER_ID = "PhotoCatalog"; // Replace with your container name

    private static CosmosClient cosmosClient = new CosmosClientBuilder()
            .endpoint(COSMOS_ENDPOINT)
            .key(COSMOS_KEY)
            .consistencyLevel(ConsistencyLevel.EVENTUAL)
            .buildClient();

    private static CosmosContainer cosmosContainer = cosmosClient
            .getDatabase(DATABASE_ID)
            .getContainer(CONTAINER_ID);

    public static String processImageTags(ImageTagPayloadModel payload) {
        try {
            // Create the item to be inserted
            CosmosItemResponse<ImageTagPayloadModel> itemResponse = cosmosContainer.createItem(payload, new PartitionKey(payload.getPhotoID()), null);

            return "Success: Tags added successfully for photoID=" + payload.getPhotoID() +
                    ", Status Code: " + itemResponse.getStatusCode();
        } catch (CosmosException e) {
            e.printStackTrace();
            return "Error: Failed to add tags - " + e.getMessage();
        }
    }
}
