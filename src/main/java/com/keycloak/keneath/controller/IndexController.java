package com.keycloak.keneath.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.saml.metadata.MetadataManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;

@Controller
public class IndexController {

    // Logger
    private static final Logger LOG = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private MetadataManager metadata;

    @GetMapping(path = "/")
    public String index() {
        return "index";
    }

    @GetMapping(path = "/welcome")
    public String welcome(Model model) {

        String username = null;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated()) {
            username = auth.getName();

            // Retrieves other user details
            Object principal = auth.getPrincipal();
            if (principal instanceof UserDetails) {
                UserDetails userDetails = (UserDetails) principal;

                // Accessing additional user details
                boolean accountNonExpired = userDetails.isAccountNonExpired();
                boolean accountNonLocked = userDetails.isAccountNonLocked();
                boolean credentialsNonExpired = userDetails.isCredentialsNonExpired();
                boolean enabled = userDetails.isEnabled();

                LOG.debug("Logged in username: " + username);
                LOG.debug("Account Non-Expired: " + accountNonExpired);
                LOG.debug("Account Non-Locked: " + accountNonLocked);
                LOG.debug("Credentials Non-Expired: " + credentialsNonExpired);
                LOG.debug("Enabled: " + enabled);

                Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
                if (!authorities.isEmpty()) {
                    LOG.debug("Authorities for user " + username + ":");
                    for (GrantedAuthority authority : authorities) {
                        LOG.debug("- " + authority.getAuthority());
                    }
                } else {
                    LOG.debug("No authorities found for user " + username);
                }
            } else {
                LOG.debug("Principal object is not an instance of UserDetails");
            }
        } else {
            LOG.debug("No user logged in");
        }
        model.addAttribute("username", username);
        return "welcome";
    }
}