package com.rentmatch.repository;

import com.rentmatch.model.Listing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ListingRepository {

    private static final String URL = "jdbc:postgresql://localhost:5433/postgres";
    private static final String USER = "postgres";

    public void saveListing(Listing listing, float[] embedding){
        final String PASSWORD = System.getenv("RENTMATCH_DB_PASSWORD");
        if (PASSWORD == null) throw new IllegalStateException("DB Password missing.");

        String sql = "INSERT INTO listings (id, price, bedrooms, bathrooms, pets_allowed, neighbourhood, address, title, description, embedding) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?::vector)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, listing.getId());
            pstmt.setInt(2, listing.getPrice());
            pstmt.setInt(3, listing.getBedrooms());
            pstmt.setDouble(4, listing.getBathrooms());
            pstmt.setBoolean(5, listing.isPetsAllowed());
            pstmt.setString(6, listing.getNeighbourhood());
            pstmt.setString(7, listing.getAddress());
            pstmt.setString(8, listing.getTitle());
            pstmt.setString(9, listing.getDescription());
            pstmt.setObject(10, java.util.Arrays.toString(embedding));

            if (pstmt.executeUpdate() != 1) {
                throw new SQLException("Expected to insert 1 row for listing " + listing.getId());
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public List<Listing> findSimilar(float[] queryEmbedding, int limit){
        final String PASSWORD = System.getenv("RENTMATCH_DB_PASSWORD");
        if (PASSWORD == null) throw new IllegalStateException("DB Password missing.");

        String sql = "SELECT id, price, bedrooms, bathrooms, pets_allowed, neighbourhood, address, title, description " +
                "FROM listings ORDER BY embedding <=> ?::vector LIMIT ?";

        List<Listing> results = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, java.util.Arrays.toString(queryEmbedding));
            pstmt.setInt(2, limit);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Listing listing = new Listing(
                        rs.getString("id"),
                        rs.getInt("price"),
                        rs.getInt("bedrooms"),
                        rs.getDouble("bathrooms"),
                        rs.getBoolean("pets_allowed"),
                        rs.getString("neighbourhood"),
                        rs.getString("address"),
                        rs.getString("title"),
                        rs.getString("description")
                );
                results.add(listing);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        return results;
    }
}
