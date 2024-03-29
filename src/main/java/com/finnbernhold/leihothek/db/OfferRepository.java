package com.finnbernhold.leihothek.db;

import com.finnbernhold.leihothek.offer.Categories;
import com.finnbernhold.leihothek.offer.Offer;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OfferRepository extends CrudRepository<Offer, Integer> {
//    @Query("SELECT o FROM offer o ORDER BY o.id DESC ")
//    List<Offer> findOffers(Pageable pageable);

    List<Offer> findTop21ByOrderByIdDesc();

    @Query("SELECT * from offer o where o.created_by =:userName ORDER BY id DESC")
    List<Offer> findAllOffersOfUser(@Param("userName") String userName);

    @Query("SELECT * from offer o where o.category like :category AND strpos(LOWER(title), LOWER(:query)) > 0 ORDER BY id DESC ")
    List<Offer> findByTitleAndCategoryContainingIgnoreCase(Categories category, String query);

    List<Offer> findAllByCategoryOrderByIdDesc(Categories category);

    List<Offer> findByTitleContainingIgnoreCaseOrderByIdDesc(String title);
}