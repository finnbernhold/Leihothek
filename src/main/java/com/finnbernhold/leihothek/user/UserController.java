package com.finnbernhold.leihothek.user;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Objects;


@Controller
public class UserController {
    private final UserDetailsManager userDetailsManager;
    private final PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    private final JdbcTemplate template;

    public UserController(UserDetailsManager userDetailsManager, JdbcTemplate template) {
        this.userDetailsManager = userDetailsManager;
        this.template = template;
    }

    @GetMapping("/admin/users")
    public String listAllUsers(Model model) {
        List<SimpleUser> simpleUsers = template.query("SELECT users.username, authority FROM users JOIN authorities on users.username = authorities.username ORDER BY users.username ASC",
                (rs, rowNum) -> {
                    var username = rs.getString("username");
                    var authority = rs.getString("authority");
                    return new SimpleUser(username, authority);
                });
        model.addAttribute("simpleUsers", simpleUsers);
        return "users";

    }

    @PostMapping("/admin/users/add")
    public String addUser(@RequestParam String userName, @RequestParam String password, @RequestParam String authority){
        final User user = new User(userName, passwordEncoder.encode(password), AuthorityUtils.createAuthorityList(authority));
        userDetailsManager.createUser(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/admin/users/add")
    public String addUserForm(){
        return "addUser";
    }

    @PostMapping("/admin/users/{userName}/delete")
    public String deleteUser(@PathVariable String userName){
        userDetailsManager.deleteUser(userName);
        return "redirect:/users";
    }
    @GetMapping("/changePassword")
    public String changePasswordForm(){
        return "changePassword";
    }

    @PostMapping("/changePassword")
    public String changePassword(@RequestParam String newPassword1, @RequestParam String newPassword2, @RequestParam String oldPassword){
        if (Objects.equals(newPassword1, newPassword2)){
            userDetailsManager.changePassword(oldPassword, passwordEncoder.encode(newPassword1));
            return "redirect:/";
        }else {
            return "redirect:/changePassword";
        }
    }
}
