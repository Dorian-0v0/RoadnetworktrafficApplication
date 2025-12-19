package roadnetworktraffic.roadnetworktraffic.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import roadnetworktraffic.roadnetworktraffic.entity.dto.UserLoginDto;
import roadnetworktraffic.roadnetworktraffic.entity.dto.UserLogout;
import roadnetworktraffic.roadnetworktraffic.entity.dto.UserRegisterDto;
import roadnetworktraffic.roadnetworktraffic.entity.pojo.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import roadnetworktraffic.roadnetworktraffic.service.UserLoginService;

// 登录的Controller
@RequestMapping("/user")
@RestController
@Slf4j
public class UserLoginController {
    @Autowired
    private UserLoginService userLoginService;

    /**
     * 登录
     *
     * @param userLoginDto
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestBody UserLoginDto userLoginDto) {
        log.info("登录: {}", userLoginDto);
        return userLoginService.login(userLoginDto);
    }



    /**
     * 注册
     *
     * @param userRegisterDto
     * @return
     */
    @PostMapping("/register")
    public Result register(@RequestBody UserRegisterDto userRegisterDto) {
        log.info("注册: {}", userRegisterDto);
        return userLoginService.register(userRegisterDto);
    }


    /**
     * 登出
     *
     * @param userLogout
     * @return
     */
    @PostMapping("/logout")
    public Result logout(@RequestBody UserLogout userLogout) {
        log.info("登出: {}", userLogout);
        return userLoginService.logout(userLogout);
    }

}
