package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.dtos.UserDTO;
import org.example.service.UserService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/users")
@Tag(name = "User API", description = "Операции с пользователями")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @Operation(summary = "Получить список всех пользователей")
    public CollectionModel<EntityModel<UserDTO>> getAllUsers() {
        List<EntityModel<UserDTO>> users = userService.getAllUsers().stream()
                .map(user -> EntityModel.of(user,
                        linkTo(methodOn(UserController.class).getUser(user.getId())).withSelfRel(),
                        linkTo(methodOn(UserController.class).getAllUsers()).withRel("all-users")
                ))
                .toList();

        return CollectionModel.of(users,
                linkTo(methodOn(UserController.class).getAllUsers()).withSelfRel(),
                linkTo(methodOn(UserController.class).createUser(null)).withRel("create")
        );
    }

    @Operation(summary = "Получить пользователя по ID")
    @GetMapping("/{id}")
    public EntityModel getUser(@Parameter(description = "ID пользователя") @PathVariable Long id) {
        UserDTO user = userService.getUser(id);

        return EntityModel.of(user, linkTo(methodOn(UserController.class).getUser(id)).withSelfRel(),
                            linkTo(methodOn(UserController.class).getAllUsers()).withRel("allUsers"),
                            linkTo(methodOn(UserController.class).deleteUser(id)).withRel("delete"),
                            linkTo(methodOn(UserController.class).updateUser(id, user)).withRel("update")
        );
    }

    @PostMapping
    @Operation(summary = "Создать нового пользователя")
    public EntityModel<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        UserDTO user = userService.createUser(userDTO);
        return EntityModel.of(user,
                linkTo(methodOn(UserController.class).getUser(user.getId())).withSelfRel(),
                linkTo(methodOn(UserController.class).getAllUsers()).withRel("allUsers")
        );
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновить пользователя")
    public EntityModel<UserDTO> updateUser(
            @PathVariable Long id,
            @RequestBody UserDTO dto) {
        UserDTO updatedUser = userService.updateUser(id, dto);
        return EntityModel.of(updatedUser,
                                linkTo(methodOn(UserController.class).getUser(updatedUser.getId())).withSelfRel(),
                                linkTo(methodOn(UserController.class).getAllUsers()).withRel("allUsers")
        );
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить пользователя по ID")
    public ResponseEntity<Void> deleteUser(@Parameter(description = "ID пользователя")@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}