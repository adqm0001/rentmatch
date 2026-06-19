package com.rentmatch.embedding;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class EmbeddingClient {

    // Turns a piece of text into its 1024 number embedding vector (Voyage voyage-4).
    public float[] getEmbedding(String text) throws Exception {
        String VOYAGE_API_KEY = System.getenv("VOYAGE_API_KEY");
        if (VOYAGE_API_KEY == null) throw new IllegalStateException("API KEY missing.");

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        node.put("input", text);
        node.put("model", "voyage-4");
        node.put("input_type", "document");
        String jsonBody = mapper.writeValueAsString(node);

        HttpRequest postRequest = HttpRequest.newBuilder()
                .uri(new URI("https://api.voyageai.com/v1/embeddings"))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer" + " " + VOYAGE_API_KEY)
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(postRequest,
                HttpResponse.BodyHandlers.ofString());
        String responseBody = response.body();

        JsonNode root = mapper.readTree(responseBody);
        JsonNode embeddingNode = root.get("data").get(0).get("embedding");
        float[] embedding = new float[embeddingNode.size()];
        for (int i = 0; i < embeddingNode.size(); i++) {
            embedding[i] = embeddingNode.get(i).floatValue();
        }
        return embedding;
    }
}
