package com.rentmatch.source;

import com.rentmatch.model.Listing;
import java.util.List;
import java.util.ArrayList;

public class SeedSource implements ListingSource {
    @Override
    public List<Listing> getListings(){
        List<Listing> listings = new ArrayList<>();

        Listing list1 = new Listing(
            "L1", 1750, 1, 1.0, true, "Centretown",
            "210 Gloucester St, Ottawa",
            "Bright 1-bed steps from Elgin",
            "Sunny corner unit on a quiet tree-lined street, big south-facing windows, cafes and groceries a 2-minute walk away. Cat-friendly building."
        );

        Listing list2 = new Listing(
            "L2", 2200, 2, 1.5, false, "The Glebe",
            "85 Fourth Ave, Ottawa",
            "Spacious 2-bed near Lansdowne",
            "Renovated two-bedroom in a heritage low-rise, hardwood floors, lots of natural light, calm residential block close to the canal and weekend market. No pets."
        );

        Listing list3 = new Listing(
            "L3", 1400, 0, 1.0, true, "Sandy Hill",
            "120 Osgoode St, Ottawa",
            "Cozy studio by uOttawa",
            "Compact studio perfect for a student or WFH, quiet back unit away from street noise, fast transit downtown, dog-friendly with a small park next door."
        );

        listings.add(list1);
        listings.add(list2);
        listings.add(list3);

        return listings;
    }
}
