package pl.sebaszczen.controller.restController.userController;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pl.sebaszczen.domain.user.User;
import pl.sebaszczen.domain.user.UserDto;
import pl.sebaszczen.facade.UserFacade;

import javax.validation.Valid;
import java.security.Principal;


@RestController
@Api(value = "User Controller")
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserFacade userFacade;

    @ApiOperation(value = "Get the logged user", response = User.class)
    @ApiResponses(value = {
            @ApiResponse(code=404, message = "User not found")
    })
    @GetMapping("/logged")
    public User getLoggedUser(Principal principal) {
        User user = (User) ((Authentication) principal).getPrincipal();
        return user;
    }

    @DeleteMapping("delUserById")
    @ApiOperation(value = "Delete user from data base by ID", response = UserDto.class)
    @ApiImplicitParam(required = true, name = "userId", value = "userId", dataType = "long", paramType = "query")
    public void deleteUser(Long userId) {
        userFacade.deleteUser(userId);
    }

    @PostMapping("/add")
    @ApiOperation(value = "Create new User")
//    @ApiImplicitParam(required = true, name = "email", value = "email", dataType = "string", paramType = "query",example = "jan@example.com")
    public void createNewUser(@RequestBody @Valid UserDto userDto) {
        userFacade.createUserAccount(userDto);
    }
}
