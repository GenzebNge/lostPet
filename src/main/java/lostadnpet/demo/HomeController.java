package lostadnpet.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    @Autowired
    PetRepository petRepository;

    @RequestMapping("/")
    public String homePage(Model model){
        model.addAttribute("pets", petRepository.findAll());
        return "home";
    }

//    @RequestMapping("/register")
//    public String registerUser(Model model){
//        model.addAttribute("user", new User());
//        return "registerform";
//    }
//
//    @PostMapping("/register")
//    public String processRegister(@Valid @ModelAttribute("user") User user, BindingResult result, Model model){
//        if (result.hasErrors()){
//            return "registerform";
//        }
//        else {
//            userRepository.save(user);
//            model.addAttribute("user", "User account created");
//        }
//        return "redirect:/";
//    }
//
//    @RequestMapping("/login")
//    public String login(){
//        return "home";
//    }
//
//    @RequestMapping("/petform")
//    public String addLostPet(Model model){
//        model.addAttribute("pet", new Pet());
//        return "petform";
//    }
//
//    @PostMapping("/addlostpet")
//    public String processMessage(@Valid @ModelAttribute("message") Pet pet, BindingResult result){
//        if (result.hasErrors()){
//            return "messageform";
//        }
//        pet.setUser(userService.getUser());
//        petRepository.save(pet);
//        return "redirect:/";
//    }
}
