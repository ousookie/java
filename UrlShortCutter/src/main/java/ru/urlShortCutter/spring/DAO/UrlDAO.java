package ru.urlShortCutter.spring.DAO;

import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.urlShortCutter.spring.models.Url;
import ru.urlShortCutter.spring.utils.UrlCutter;

import java.util.List;

@Component
public class UrlDAO {

    private final JdbcTemplate jdbcTemplate;
    private final UrlValidator urlValidator;

    @Autowired
    public UrlDAO(JdbcTemplate jdbcTemplate, UrlValidator urlValidator) {
        this.jdbcTemplate = jdbcTemplate;
        this.urlValidator = urlValidator;
    }

    public void saveUrl(Url url) {
        if (urlValidator.isValid(url.getSourceUrl().trim())) {
            Url currentUrl = jdbcTemplate.query("SELECT * FROM url WHERE sourceurl=?",
                    new Object[]{url.getSourceUrl().trim()},
                    new BeanPropertyRowMapper<>(Url.class)).stream().findAny().orElse(null);
            if (currentUrl == null) {
                url.setCutUrl(UrlCutter.createShortUrl(url.getSourceUrl()));
                jdbcTemplate.update("" + "INSERT INTO url(sourceurl, cuturl) VALUES(?, ?)",
                        url.getSourceUrl(), "http://localhost:8080/" + url.getCutUrl());
            }
        }
    }

    public Url getSourceLongUrl(String cutUrl) {
        return jdbcTemplate.query("SELECT * FROM url WHERE cuturl=?", new Object[]{cutUrl},
                new BeanPropertyRowMapper<>(Url.class)).stream().findAny().orElse(null);
    }

    public List<Url> getAllUrls() {
        return jdbcTemplate.query("SELECT * FROM url", new BeanPropertyRowMapper<>(Url.class));
    }
}
