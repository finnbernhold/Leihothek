package com.finnbernhold.leihothek.offer;

import com.finnbernhold.leihothek.db.OfferRepository;
import com.finnbernhold.leihothek.mail.EmailServiceImpl;
import com.finnbernhold.leihothek.offer.image.ImageService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Service
public class OfferService {
    private final OfferRepository repo;
    private final EmailServiceImpl emailService;
    private final ImageService imageService;

    public OfferService(OfferRepository repo, EmailServiceImpl emailService, ImageService imageService) {
        this.repo = repo;
        this.emailService = emailService;
        this.imageService = imageService;
    }

    public void addOffer(String title, String description, String categoryString, String email, MultipartFile image) throws IOException {
        Categories category = Categories.valueOf(categoryString);
        Integer imageId = imageService.addImage(image);
        repo.save(new Offer(null, title, description, category, imageId, null, email));
    }

    public Offer findOfferById(Integer id) {
        return repo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Offer not found"));
    }

    public Iterable<Offer> findAllOffers() {
        return repo.findAllByOrderByIdAsc();
    }

    public void deleteById(Integer id) {
        repo.deleteById(id);
    }

    public Iterable<Offer> findAllOwnOffers(Principal principal) {
        return repo.findAllOffersOfUser(principal.getName());
    }

    public Iterable<Offer> findAllOffersOfUser(String user) {
        return repo.findAllOffersOfUser(user);
    }

    public List<Offer> findOffersByTitle(String query) {
        return repo.findByTitleContainingIgnoreCase(query);
    }

    public List<Offer> findOffersByTitleAndCategory(Categories category, String query) {
        return repo.findByTitleAndCategoryContainingIgnoreCase(category, query);
    }

    public List<Offer> findOffersByCategory(Categories category) {
        return repo.findAllByCategory(category);
    }

    public void saveEditedOffer(Offer editedOffer) {
        repo.save(editedOffer);
    }

    public void sendEmail(Offer offer, String message, String contactData) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        emailService.sendMessage(offer, username, message, contactData);

    }
}
