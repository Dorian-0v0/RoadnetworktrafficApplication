package roadnetworktraffic.roadnetworktraffic.service.impl;


import cn.hutool.core.date.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import roadnetworktraffic.roadnetworktraffic.entity.dto.UserLoginDto;
import roadnetworktraffic.roadnetworktraffic.entity.dto.UserLogout;
import roadnetworktraffic.roadnetworktraffic.entity.dto.UserRegisterDto;
import roadnetworktraffic.roadnetworktraffic.entity.pojo.Result;
import roadnetworktraffic.roadnetworktraffic.entity.pojo.User;
import roadnetworktraffic.roadnetworktraffic.entity.vo.UserLoginVo;
import roadnetworktraffic.roadnetworktraffic.mapper.LoginMapper;
import roadnetworktraffic.roadnetworktraffic.service.UserLoginService;
import roadnetworktraffic.roadnetworktraffic.utils.JwtUtils;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserLoginServiceImpl implements UserLoginService {
    @Autowired
    private LoginMapper loginMapper;

    @Override
    public Result<UserLoginVo> login(UserLoginDto userLoginDto) {
        // 判断账号密码是否为空
        if (userLoginDto.getAccount() == null || userLoginDto.getPassword() == null) {
            return Result.error("账号或密码不能为空");
        }
        // 根据账号密码查询用户
        UserLoginVo userLoginVo = loginMapper.selectUserByAccentAndPassword(userLoginDto.getAccount(), userLoginDto.getPassword());
        if (userLoginVo == null) {
            return Result.error("账号或密码错误");
        }
        //生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", userLoginVo.getId());
        String jwt = JwtUtils.generateToken(claims);
        userLoginVo.setToken(jwt);

        return Result.success(userLoginVo, "登陆成功");
    }

    @Override
    public Result register(UserRegisterDto userRegisterDto) {
        // 4个字段
        if (userRegisterDto.getAccount() == null || userRegisterDto.getPassword() == null || userRegisterDto.getNickName() == null || userRegisterDto.getSex() == null) {
            return Result.error("请填写完整信息");
        }
//        查询账号和昵称是否存在（就是查询数量count）
        Integer count = loginMapper.selectUserByAccentAndNickName(userRegisterDto.getAccount(), userRegisterDto.getNickName());
        if(count > 0){
            return Result.error("账号和昵称已存在");
        }
        User user = User.builder()
                .account(userRegisterDto.getAccount())
                .password(userRegisterDto.getPassword())
                .nickName(userRegisterDto.getNickName())
                .sex(userRegisterDto.getSex())
                .createTime(new DateTime())
                .updateTime(new DateTime())
                .avatar("https://i1.hdslb.com/bfs/face/c07d60e7b96fe31b7ec4ab1cec0dfbeb83bcd67d.jpg@128w_128h_1c_1s.webp")
                .build();

        Integer insert = loginMapper.insert(user);
        if (insert == 1) {
            return Result.success("注册成功");
        }

        return Result.error("注册失败");
    }

    @Override
    public Result<UserLoginVo> logout(UserLogout userLogout) {
        // 更新
        Integer update = loginMapper.updateUserMapState(userLogout);

        if (update == 1){
            return Result.success("登出成功");
        }
        return Result.error("登出失败");
    }
}
