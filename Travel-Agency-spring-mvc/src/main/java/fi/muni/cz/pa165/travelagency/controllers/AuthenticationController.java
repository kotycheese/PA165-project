package fi.muni.cz.pa165.travelagency.controllers;



import fi.muni.cz.pa165.travelagency.facade.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * ss
 */
@Controller
@RequestMapping("/auth")
public class AuthenticationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    private UserFacade userFacade;

    /**
     * x
     * @param model x
     * @param req x
     * @param res x
     * @return x
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String authForm(
            Model model,
            HttpServletRequest req,
            HttpServletResponse res) {
        LOGGER.error("GET request: /auth/login");

        return "auth/login";
    }

/*
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String authenticate(
            @RequestParam String email,
            @RequestParam String password,
            Model model,
            RedirectAttributes redirectAttributes,
            HttpServletRequest req,
            HttpServletResponse res) {
        LOGGER.error("POST request: /auth/auth");
        UserAuthenticateDTO userDto = new UserAuthenticateDTO();
        userDto.setPasswordHash(password);


        if (!userFacade.authenticate(userDto)) {
            redirectAttributes.addFlashAttribute("alert_danger", "You entered wrong username or password");
            return "redirect:/auth/auth";
        }

        HttpSession session = req.getSession(true);
        //TODO: not safe, probably alright for demo purposes -> use some auth token instead
        session.setAttribute("authenticatedAs", userDto.getId());

        redirectAttributes.addFlashAttribute("alert_info", "You have been logged in.");
        /*if() {
            return "redirect:/admin/machine";
        }*/

        //return "redirect:/machine";
    //}
}
