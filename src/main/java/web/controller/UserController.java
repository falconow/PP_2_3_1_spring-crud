package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    //Список всех пользователей
    @GetMapping()
    public String printIndex(ModelMap modelMap) {
        List<User> listUsers = userService.getAll();
        modelMap.addAttribute("listUsers", listUsers);
        return "users";
    }

    //Форма для ввода нового пользователя
    @GetMapping(value = "/new")
    public String printFormCreate(Model model) {
        model.addAttribute("user", new User());
        return "createUserForm";
    }

    //Сохранение нового пользователя
    @PostMapping(value = "/new")
    public String createUser(@ModelAttribute User user) {
        userService.add(user);
        return "redirect:/users";
    }

    //Форма для редактирования
    @GetMapping(value = "/{id}/edit")
    public String editUser(Model model, @PathVariable("id") Long id) {
        User editUser = userService.findById(id);
        model.addAttribute("user", editUser);
        return "editUserForm";
    }

    //Изменение пользователя
    @PutMapping(value = "/{id}/edit")
    public String saveEditUser(Model model, @ModelAttribute User user) {
        userService.update(user);
        return "redirect:/users";
    }

    //Удаление пользователя
    @DeleteMapping(value = "/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return "redirect:/users";
    }

}
