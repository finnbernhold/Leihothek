package com.finnbernhold.leihothek.offer.image;

import com.finnbernhold.leihothek.db.OfferRepository;
import com.finnbernhold.leihothek.offer.Offer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
public class ImageController {
    private final ImageService imageService;
    private final OfferRepository offerRepository;

    public ImageController(ImageService imageService, OfferRepository offerRepository) {
        this.imageService = imageService;
        this.offerRepository = offerRepository;
    }

    @GetMapping("/offer/{id}/image/upload")
    public String addImageToBikeGet() {
        return "ImageUpload";
    }

    @PostMapping("/offer/{id}/image/upload")
    public String addImageToBike(@RequestParam("title") String title, @RequestParam("image") MultipartFile image, @PathVariable Integer id, RedirectAttributes redirectAttributes) throws IOException {
        Integer imageId = imageService.addImage(title, image);
        Offer offer = offerRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Offer not found"));
        Offer updatedOffer = new Offer(offer.id(), offer.title(), offer.description(), imageId);
        offerRepository.save(updatedOffer);
        redirectAttributes.addFlashAttribute("alert", "Image uploaded");
        return "redirect:/";
    }
    @GetMapping("/image/{id}")
    public ResponseEntity<byte[]> showImage(@PathVariable Integer id) {
        Image img = imageService.getImage(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Image not found"));
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(img.mimeType())).body(img.image());

    }
}
