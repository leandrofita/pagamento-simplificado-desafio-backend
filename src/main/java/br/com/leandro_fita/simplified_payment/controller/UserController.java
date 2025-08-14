package br.com.leandro_fita.simplified_payment.controller;

import br.com.leandro_fita.simplified_payment.model.user.UserCreateDTO;
import br.com.leandro_fita.simplified_payment.model.user.UserResponseDTO;
import br.com.leandro_fita.simplified_payment.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> save(@RequestBody @Valid final UserCreateDTO dto) {
        return ResponseEntity.ok(userService.save(dto));
    }
}
