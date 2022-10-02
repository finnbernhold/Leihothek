package com.finnbernhold.leihothek.start;

import com.finnbernhold.leihothek.db.OfferRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StartController {
    private final OfferRepository repo;

    public StartController(OfferRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/")
    public String showAllOffers(Model model){
        model.addAttribute("offers", repo.findAllByOrderByIdAsc());
        return "start";
    }
}
