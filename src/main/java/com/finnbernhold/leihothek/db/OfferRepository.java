package com.finnbernhold.leihothek.db;

import com.finnbernhold.leihothek.offer.Categories;
import com.finnbernhold.leihothek.offer.Offer;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OfferRepository extends CrudRepository<Offer, Integer> {
    List<Offer> findAllByOrderByIdAsc();

    @Query("SELECT * from offer o where o.created_by =:userName ")
    List<Offer> findAllOffersOfUser(@Param("userName") String userName);

    @Query("SELECT * from offer o where o.category like :category AND strpos(LOWER(title), LOWER(:query)) > 0")
    List<Offer> findByTitleAndCategoryContainingIgnoreCase(Categories category, String query);

    List<Offer> findAllByCategory(Categories category);

    List<Offer> findByTitleContainingIgnoreCase(String title);
}
