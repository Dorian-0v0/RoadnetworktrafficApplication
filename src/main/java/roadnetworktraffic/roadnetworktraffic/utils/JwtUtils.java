package roadnetworktraffic.roadnetworktraffic.utils;



import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.Map;
@Component
public class JwtUtils {

    // 定义固定的签名密钥
    private static final String SECRET_KEY = "6JSh5b6Q5Z2k";
    private static final Integer TIME = 12 * 3600 * 1000;  //12小时
    // 生成JWT令牌的方法，使用固定的签名密钥
    public static String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY) // 使用固定的签名密钥
                .addClaims(claims) // 添加自定义信息
                .setExpiration(new Date(System.currentTimeMillis() + TIME)) // 设置过期时间为当前时间后1小时
                .compact(); // 生成令牌
    }

    // 解析JWT令牌的方法，需要传入签名密钥
    public static Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY) // 指定固定的签名密钥
                .parseClaimsJws(token) // 解析令牌
                .getBody(); // 获取自定义信息
    }
}
