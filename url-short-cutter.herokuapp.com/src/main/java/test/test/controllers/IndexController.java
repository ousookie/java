package test.test.controllers;

import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import test.test.core.UrlCutter;
import test.test.entities.Url;
import test.test.repos.UrlsRepo;

@Controller
@RequestMapping(value = {"/index", ""})
public class IndexController {

    private final UrlsRepo urlsRepo;
    private final static String HOST = "https://url-short-cutter.herokuapp.com/";

    @Autowired
    public IndexController(UrlsRepo urlsRepo) {
        this.urlsRepo = urlsRepo;
    }

    @GetMapping()
    public String index(@ModelAttribute(name = "url") Url url) {
        return "index";
    }

    @PostMapping()
    public String save(Url url, Model model) {
        if (UrlValidator.getInstance().isValid(url.getSrcUrl().trim())) {
            if (urlsRepo.getUrlBySrcUrl(url.getSrcUrl().trim()) != null) {

                model.addAttribute("host", HOST);
                model.addAttribute("savedUrl", urlsRepo.getUrlBySrcUrl(url.getSrcUrl().trim()));

                return "saved";
            }

            url.setSrcUrl(url.getSrcUrl().trim());
            urlsRepo.save(url);
            Url savedUrl = urlsRepo.getUrlBySrcUrl(url.getSrcUrl());
            urlsRepo.setCutUrl(UrlCutter.getBase62String(savedUrl.getId()), savedUrl.getId());

            savedUrl.setCutUrl(HOST + UrlCutter.getBase62String(savedUrl.getId()));
            model.addAttribute("savedUrl", savedUrl);

            return "saved";
        } else return "redirect:/";
    }

    @GetMapping("/{url}")
    public String redirect(@PathVariable(value = "url") String url) {
        Url url1 = urlsRepo.getUrlByCutUrl(url.trim());

        return "redirect:" + url1.getSrcUrl();
    }
}

