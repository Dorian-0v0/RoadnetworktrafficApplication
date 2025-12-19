package roadnetworktraffic.roadnetworktraffic.service;


import roadnetworktraffic.roadnetworktraffic.entity.dto.UserLoginDto;
import roadnetworktraffic.roadnetworktraffic.entity.dto.UserLogout;
import roadnetworktraffic.roadnetworktraffic.entity.dto.UserRegisterDto;
import roadnetworktraffic.roadnetworktraffic.entity.pojo.Result;
import roadnetworktraffic.roadnetworktraffic.entity.vo.UserLoginVo;

public interface UserLoginService {
    // 登录
    Result<UserLoginVo> login(UserLoginDto userLoginDto);

//    注册
    Result<UserLoginVo> register(UserRegisterDto userRegisterDto);

    Result logout(UserLogout userLogout);
}
