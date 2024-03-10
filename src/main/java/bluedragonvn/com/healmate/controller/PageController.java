package bluedragonvn.com.healmate.controller;

import bluedragonvn.com.healmate.dto.CreateUserRequest;
import bluedragonvn.com.healmate.dto.GetUserResponse;
import bluedragonvn.com.healmate.dto.JwtRequest;
import bluedragonvn.com.healmate.dto.SignUpRequest;
import bluedragonvn.com.healmate.service.UserService;
import bluedragonvn.com.healmate.service.impl.UserDetailsServiceImpl;
import bluedragonvn.com.healmate.ulti.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/**
 * @author: blued, Date: 3/6/2024
 */
@Controller
public class PageController {
    @Autowired
    private UserService userService;
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @CrossOrigin(origins = "*", maxAge = 3600)
    @GetMapping("/")
    public String home(Model m)
    {
        m.addAttribute("msg", "Welcome");
        return "home";
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @GetMapping("/home")
    public String homePage() {
        return "home";
    }

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

    @PostMapping("/token")
    public String generateToken(Model m, HttpSession session,
                                @ModelAttribute JwtRequest jwtRequest, HttpServletResponse res) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
        } catch (UsernameNotFoundException e) {
            e.printStackTrace();
            session.setAttribute("msg", "Bad Credentials");
            return "redirect:/login";

        } catch (BadCredentialsException e) {
            e.printStackTrace();
            session.setAttribute("msg", "Bad Credentials");
            return "redirect:/login";
        }

        try {

            final UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequest.getUsername());
            final String token = jwtUtils.generateToken(userDetails);
            Cookie cookie = new Cookie("_jwt_healmate", token);
            // expiry 60 second * 60 minutes * 12 hours
            cookie.setMaxAge(60 * 60 * 12);
            res.addCookie(cookie);
            return "redirect:/home/";
        } catch (Exception e) {
            session.setAttribute("msg", "Credentials were right But something went wrong!!");
            return "redirect:/login";
        }
    }


    @GetMapping("/log_out")
    public String logout(HttpServletRequest request, HttpServletResponse res, Model m, HttpSession session) {
        String msg = null;
        Cookie[] cookies2 = request.getCookies();
        for (int i = 0; i < cookies2.length; i++) {
            if (cookies2[i].getName().equals("token")) {
                cookies2[i].setMaxAge(0);
                res.addCookie(cookies2[i]);
                msg = "Logout successfully";
            }
        }
        session.setAttribute("msg", msg);
        return "redirect:/login";

    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = {"/errorPage"}, method = RequestMethod.GET)
    public String errorPage() {
        return "errorPage";
    }
    // handler method to handle user registration request
    @GetMapping("register")
    public String showRegistrationForm(Model model) {
        SignUpRequest user = new SignUpRequest();
        model.addAttribute("user", user);
        return "register";
    }

    // handler method to handle register user form submit request
    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") SignUpRequest user,
                               BindingResult result,
                               Model model) {
        GetUserResponse existing = userService.getUserByPhone(user.getPhone());
        if (existing != null) {
            result.rejectValue("phone", null, "There is already!");
        }
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "register";
        }
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setPhone(user.getPhone());
        userService.createUser(createUserRequest);
        return "redirect:/register?success";
    }

    @GetMapping("/users")
    public String listRegisteredUsers(Model model) {
        List<GetUserResponse> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }
}
