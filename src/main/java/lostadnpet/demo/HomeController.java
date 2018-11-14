package lostadnpet.demo;

import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

@Controller
public class HomeController {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    @Autowired
    PetRepository petRepository;
    @Autowired
    CloudinaryConfig cloudinaryConfig;


    @RequestMapping("/")
    public String homePage(Model model) {
        model.addAttribute("pets", petRepository.findAll());
        return "home";
    }

    @RequestMapping("/register")
    public String registerUser(Model model) {
        model.addAttribute("user", new User());
        return "registerform";
    }

    @PostMapping("/register")
    public String processRegister(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "registerform";
        } else {
            userService.saveUser(user);
//            userRepository.save(user);
            model.addAttribute("user", "User account created");
        }
        return "redirect:/";
    }

    @RequestMapping("/login")
    public String login() {
        return "loginform";
    }

    @RequestMapping("/addlostpet")
    public String addLostPet(Model model) {
        model.addAttribute("pet", new Pet());
        return "lostpetform";
    }

    @PostMapping("/addlostpet")
    public String processPet(@ModelAttribute("pet") Pet pet, @RequestParam("file") MultipartFile file, BindingResult result) {
        if (result.hasErrors()) {
            return "lostpetform";
        }

        try {
            Map uploadResult = cloudinaryConfig.upload(file.getBytes(), ObjectUtils.asMap("resourcetype", "auto"));
            pet.setImage(uploadResult.get("url").toString());
            petRepository.save(pet);

        } catch (IOException e) {
            e.printStackTrace();
            return "redirect:/addlostpet";
        }
        pet.setUser(userService.getUser());
        return "redirect:/";
    }

    @RequestMapping("/allpets")
    public String allDogs(Model model) {
        model.addAttribute("pets", petRepository.findAll());
        return "/";
    }

    @RequestMapping("/lostpet")
    public String lostPet(Model model) {
        String status = "lost";
        ArrayList<Pet> pets = (new ArrayList<Pet>());
        petRepository.findAllByStatusContainingIgnoreCase(status);
        model.addAttribute("lostpets", pets);
        return "lostpets";
    }

    @RequestMapping("/detail/{id}")
    public String showPet(@PathVariable("id") long id, Model model) {
        model.addAttribute("pet", petRepository.findById(id).get());

        if (userService.getUser()!=null){
            model.addAttribute("currentUser", userService.getUser().getId());
        }
        return "show";
    }

    @RequestMapping("/update/{id}")
    public String updatePet(@PathVariable("id") long id, Model model) {
        model.addAttribute("pet", petRepository.findById(id).get());

        return "lostpetform";
    }

    @RequestMapping("/delete/{id}")
    public String deletePet(@PathVariable("id") long id) {
        petRepository.deleteById(id);
        return "redirect:/";

    }
}
