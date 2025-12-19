package roadnetworktraffic.roadnetworktraffic.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import roadnetworktraffic.roadnetworktraffic.entity.dto.UserLogout;
import roadnetworktraffic.roadnetworktraffic.entity.pojo.User;
import roadnetworktraffic.roadnetworktraffic.entity.vo.UserLoginVo;

@Mapper
public interface LoginMapper {

    // 没token
    @Select("select id, account, nick_name, avatar, sex, base_map, center, zoom from traffic_system.\"user\" where account = #{account} and password = #{password}")
    UserLoginVo selectUserByAccentAndPassword(String account, String password);

    @Insert("insert into traffic_system.\"user\" (account, nick_name, avatar, sex, create_time, update_time, password) values (#{account}, #{nickName}, #{avatar}, #{sex}, #{createTime}, #{updateTime}, #{password})")
    Integer insert(User user);

    @Update("update traffic_system.\"user\" set base_map = #{base_map}, center = #{center}, zoom = #{zoom} where id = #{id}")
    Integer updateUserMapState(UserLogout userLogout);
}
