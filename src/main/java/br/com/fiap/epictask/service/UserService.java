package br.com.fiap.epictask.service;

import br.com.fiap.epictask.model.Role;
import br.com.fiap.epictask.model.User;
import br.com.fiap.epictask.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;

@Service
public class UserService {
    @Autowired
    private MessageSource message;

    @Autowired
    private UserRepository repository;

    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("users");
        List<User> users = findAll();
        modelAndView.addObject("users", users);
        return modelAndView;
    }

    public String create() {
        return "user-form";
    }

    public String save(User user, BindingResult result, RedirectAttributes redirect) {
        if (result.hasErrors()) return "user-form";
        user.setPassword(AuthenticationService.getPasswordEncoder().encode(user.getPassword()));


        Role regra = new Role();
        regra.setId(2L);
        Collection<Role> collection = new ArrayList<>();
        collection.add(regra);
        user.setRoles(collection);
        repository.save(user);

        redirect.addFlashAttribute("message", message.getMessage("deleteuser.success", null, LocaleContextHolder.getLocale()));
        return "redirect:/user";
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public ModelAndView editView(Long id) {
        ModelAndView modelAndView = new ModelAndView("user-form-update");
        Optional<User> user = repository.findById(id);
        user.ifPresent(obj -> modelAndView.addObject("user", obj));
        return modelAndView;
    }

    public String delete(Long id, RedirectAttributes redirect) {
        repository.deleteById(id);
        redirect.addFlashAttribute("message", message.getMessage("deleteuser.success", null, LocaleContextHolder.getLocale()));
        return "redirect:/user";
    }

    public String edit(User newUser, BindingResult result, RedirectAttributes redirect) {
        if (result.hasErrors()) return "user-form-update";

        Optional<User> optional = repository.findById(newUser.getId());
        newUser.setPassword(AuthenticationService.getPasswordEncoder().encode(newUser.getPassword()));
        User user = optional.get();
        user.setName(newUser.getName());
        user.setGithubuser(newUser.getGithubuser());
        user.setEmail(newUser.getEmail());
        user.setPassword(newUser.getPassword());

        redirect.addFlashAttribute("message", message.getMessage("edituser.success", null, LocaleContextHolder.getLocale()));
        repository.save(user);
        return "redirect:/user";
    }
}
