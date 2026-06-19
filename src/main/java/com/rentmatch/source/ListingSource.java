package com.rentmatch.source;

import com.rentmatch.model.Listing;
import java.util.List;

public interface ListingSource {
    List<Listing> getListings();
}
