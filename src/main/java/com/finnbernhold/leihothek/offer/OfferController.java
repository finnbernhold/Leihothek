package com.finnbernhold.leihothek.offer;

import com.finnbernhold.leihothek.offer.image.ImageService;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Controller
public class OfferController {
    private final OfferService offerService;
    private final ImageService imageService;

    public OfferController(OfferService offerService, ImageService imageService) {
        this.offerService = offerService;
        this.imageService = imageService;
    }

    @GetMapping("/new-offer")
    public String newOfferForm() {
        return "addOffer";
    }

    @PostMapping("/new-offer")
    public String newOffer(@RequestParam String title, @RequestParam String description, @RequestParam String category, @RequestParam String email, @RequestParam(required = false) MultipartFile image) throws IOException {
        offerService.addOffer(title, description, category, email, image);
        return "redirect:/ownOffers";
    }

    @GetMapping("/offer/{id}")
    public String showOfferForm(@PathVariable Integer id, Model model) {
        model.addAttribute("offer", offerService.findOfferById(id));
        return "offerView";
    }

    @GetMapping("/")
    public String showAllOffers(Model model, @RequestParam(required = false) String query, @RequestParam(required = false) String category) {
        if (category == null || category.equals("Alle")) {
            if (query == null) {
                Iterable<Offer> allOffers = offerService.findOffers();
                model.addAttribute("count", IterableUtils.size(offerService.findOffers()));
                model.addAttribute("offers", allOffers);
            } else {
                model.addAttribute("query", query);
                List<Offer> filteredOffers = offerService.findOffersByTitle(query);
                model.addAttribute("count", filteredOffers.size());
                model.addAttribute("offers", filteredOffers);
            }
        } else {
            try {
                Categories categoryEnum = Categories.valueOf(category);
                if (query == null) {
                    Iterable<Offer> allOffers = offerService.findOffersByCategory(categoryEnum);
                    model.addAttribute("count", IterableUtils.size(allOffers));
                    model.addAttribute("offers", allOffers);
                } else {
                    model.addAttribute("query", query);
                    List<Offer> filteredOffers = offerService.findOffersByTitleAndCategory(categoryEnum, query);
                    model.addAttribute("count", filteredOffers.size());
                    model.addAttribute("offers", filteredOffers);
                    model.addAttribute("selectedCategory", categoryEnum);
                }

            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Keine Kategorie");

            }
        }

        return "start";
    }

    @GetMapping("/ownOffers")
    public String showAllOwnOffers(Model model, Principal principal){
        model.addAttribute("offersOfUser", offerService.findAllOwnOffers(principal));
        return "offersOfUser";
    }
    @GetMapping("/user/{userName}")
    public String showAllOffersOfUser(Model model, @PathVariable String userName){
        model.addAttribute("offersOfUser", offerService.findAllOffersOfUser(userName));
        return "offersOfUser";
    }

    @PostMapping("/offer/{id}/delete")
    public String deleteOffer(@PathVariable Integer id) {
        Offer offer = offerService.findOfferById(id);
        if (offer.imageId() != null) {
            imageService.deleteById(offerService.findOfferById(id).imageId());

        }
        offerService.deleteById(id);
        return "redirect:/ownOffers";
    }

    @GetMapping("offer/{id}/edit")
    public String editOfferForm(Model model, @PathVariable Integer id) {
        model.addAttribute("editOffer", offerService.findOfferById(id));
        return "editOffer";
    }

    @PostMapping("offer/{id}/edit")
    public String editOffer(@RequestParam String title, @RequestParam String description, @RequestParam String category, @RequestParam String contactEmail, @RequestParam(required = false) MultipartFile image, @RequestParam String createdBy, @RequestParam int id, @RequestParam int imageId) throws IOException {
        System.out.println(contactEmail);
        offerService.saveEditedOffer(title, description, category, contactEmail, image, createdBy, id, imageId);
        return "redirect:/ownOffers";
    }

    @PostMapping("/offer/{id}/message")
    public String sendMessage(@PathVariable Integer id, @RequestParam String message, @RequestParam String contactData) {
        offerService.sendEmail(offerService.findOfferById(id), message, contactData);
        return "redirect:/offer/" + id;
    }
}