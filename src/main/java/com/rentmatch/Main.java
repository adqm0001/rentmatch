package com.rentmatch;

import com.rentmatch.model.Listing;
import com.rentmatch.source.ListingSource;
import com.rentmatch.source.SeedSource;
import com.rentmatch.embedding.EmbeddingClient;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        ListingSource source = new SeedSource();
        List<Listing> listings = source.getListings();

        EmbeddingClient embeddingClient = new EmbeddingClient();

        Listing first = listings.get(0);
        String text = first.getTitle() + " " + first.getDescription();
        float[] vector = embeddingClient.getEmbedding(text);

        System.out.println("Embedded: " + first.getTitle());
        System.out.println("Vector length: " + vector.length);
        System.out.println("First 5 numbers: " + vector[0] + ", " + vector[1] + ", "
                + vector[2] + ", " + vector[3] + ", " + vector[4]);
    }
}
