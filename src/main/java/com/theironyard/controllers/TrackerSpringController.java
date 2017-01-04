package com.theironyard.controllers;

import com.theironyard.entities.Thought;
import com.theironyard.entities.User;
import com.theironyard.services.ThoughtRepository;
import com.theironyard.services.UserRepository;
import com.theironyard.utilities.PasswordStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * Created by scofieldservices on 1/2/17.
 */
@Controller
public class TrackerSpringController {
    @Autowired
    ThoughtRepository thoughts;

    @Autowired
    UserRepository users;

    @PostConstruct
    public void init() throws PasswordStorage.CannotPerformOperationException {
        if(users.count()==0) {
            User user = new User();
            user.name = "Matthew";
            user.password = PasswordStorage.createHash("nonStop");
            users.save(user);
        }
    }// end if init postconstruct method

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model, HttpSession session, String category, String search){
        String userName = (String) session.getAttribute("userName");
        List<Thought> thoughtEntities;
        if (userName != null) {
            User user = users.findFirstByName(userName);
            model.addAttribute("user", user);
            model.addAttribute("now", LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        }
        if (category != null) {
            thoughtEntities = thoughts.findByCategory(category);
        }else if (search != null) {
            thoughtEntities = thoughts.findByDescriptionStartsWith(search);
        }else{
            thoughtEntities = (List<Thought>) thoughts.findAllByOrderByCategory();
        }
        model.addAttribute("thoughts", thoughtEntities);
        return "home";
    }//end of home slash method

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(HttpSession session, String userName, String password) throws Exception {
        User user = users.findFirstByName(userName);
        if (user == null) {
            user = new User(userName, PasswordStorage.createHash(password));
            users.save(user);
        }else if (!PasswordStorage.verifyPassword(password, user.password)){
            throw new   Exception("Incorrect Password");
        }
        session.setAttribute("userName", userName);
        return "redirect:/";
    }// end of login method

    @RequestMapping(path = "/logout", method = RequestMethod.POST)
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }//end of logout method

    @RequestMapping(path = "/add-thought", method = RequestMethod.POST)
    public String addthought(HttpSession session, String descriptionText, String dateTime, String thoughtCategory){
        String userName = (String) session.getAttribute("userName");
        User user = users.findFirstByName(userName);
        System.out.println(descriptionText);
        System.out.println(dateTime);
        System.out.println(thoughtCategory);
        Thought thought = new Thought(descriptionText, LocalDateTime.parse(dateTime), thoughtCategory, user);
        thoughts.save(thought);
        return "redirect:/";
    }//end of add thought method
}// end of controller class
