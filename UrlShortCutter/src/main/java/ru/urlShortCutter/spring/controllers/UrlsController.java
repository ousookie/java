package ru.urlShortCutter.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.urlShortCutter.spring.DAO.UrlDAO;
import ru.urlShortCutter.spring.models.Url;

@Controller
public class UrlsController {

    private final UrlDAO urlDAO;
    private final static String HOST = "http://localhost:8080/";

    @Autowired
    public UrlsController(UrlDAO urlDAO) {
        this.urlDAO = urlDAO;
    }

    @GetMapping("/")
    public String index(@ModelAttribute("url") Url url, Model model) {
        model.addAttribute("urls", urlDAO.getAllUrls());
        return "index/indexForm";
    }

    @PostMapping()
    public String postShortUrlToDb(@ModelAttribute("url") Url url) {
        urlDAO.saveUrl(url);
        return "redirect:/";
    }

    @GetMapping("/{url}")
    public String redirect(@PathVariable(value = "url") String cutUrl) {
        Url url = urlDAO.getSourceLongUrl(HOST + cutUrl);
        return "redirect:" + url.getSourceUrl();
    }


}
