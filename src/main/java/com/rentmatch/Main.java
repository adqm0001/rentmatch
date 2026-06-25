package com.rentmatch;

import com.rentmatch.model.Listing;
import com.rentmatch.embedding.EmbeddingClient;
import com.rentmatch.repository.ListingRepository;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        EmbeddingClient embeddingClient = new EmbeddingClient();
        ListingRepository repository = new ListingRepository();

        String query = "sunny quiet one bedroom near cafes";
        float[] queryVector = embeddingClient.getEmbedding(query, "query");

        List<Listing> matches = repository.findSimilar(queryVector, 5);

        System.out.println("Query: \"" + query + "\"\n");
        System.out.println("Top matches (closest first):");
        for (Listing l : matches) {
            System.out.println("  - " + l.getTitle() + " - $" + l.getPrice()
                    + " (" + l.getNeighbourhood() + ", " + l.getBedrooms() + "bd)");
        }
    }
}
