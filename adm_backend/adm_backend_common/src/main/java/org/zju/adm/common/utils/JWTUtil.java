package org.zju.adm.common.utils;

import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.*;
import org.apache.commons.lang3.StringUtils;
import org.zju.adm.common.constrant.SecretConstant;
import org.zju.adm.common.exception.BusinessException;
import org.zju.adm.common.exception.CommonError;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: JWTUtil
 * Description: TODO
 * Created by tiamo on 15/3/2020 2:52 下午
 */
public class JWTUtil {

    public static String generateJWT(String userId, String userName, String ...identities) {
        //签名算法，选择SHA-256
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        //获取当前系统时间
        long nowTimeMillis = System.currentTimeMillis();
        Date now = new Date(nowTimeMillis);
        //将BASE64SECRET常量字符串使用base64解码成字节数组
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SecretConstant.BASE64SECRET);
        //使用HmacSHA256签名算法生成一个HS256的签名秘钥Key
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        //添加构成JWT的参数
        Map<String, Object> headMap = new HashMap<>();
        headMap.put("alg", SignatureAlgorithm.HS256.getValue());
        headMap.put("typ", "JWT");
        JwtBuilder builder = Jwts.builder().setHeader(headMap)
                //加密后的客户编号
                .claim("userId", AESSecretUtil.encryptToStr(userId, SecretConstant.DATAKEY))
                //客户名称
                .claim("userName", userName)
                //客户端浏览器信息
                .claim("userAgent", identities[0])
                //Signature
                .signWith(signatureAlgorithm, signingKey);
        //添加Token过期时间
        if (SecretConstant.EXPIRESSECOND >= 0) {
            long expMillis = nowTimeMillis + SecretConstant.EXPIRESSECOND;
            Date expDate = new Date(expMillis);
            builder.setExpiration(expDate).setNotBefore(now);
        }
        return builder.compact();
    }

    /**
     * 解析JWT
     * @param jsonWebToken
     * @return
     * @throws BusinessException
     */
    public static Claims parseJWT(String jsonWebToken) throws BusinessException {
        Claims claims = null;
        Map<String, Object> responseData = new HashMap<>();
        try {
            if (StringUtils.isNotBlank(jsonWebToken)) {
                //解析jwt
                claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(SecretConstant.BASE64SECRET))
                        .parseClaimsJws(jsonWebToken).getBody();
            }else {
                throw new BusinessException(CommonError.JWT_NULL);
            }
        } catch (ExpiredJwtException e) {
            throw new BusinessException(CommonError.JWT_EXPIRE);
        } catch (SignatureException e) {
            throw new BusinessException(CommonError.JWT_ERROR);
        } catch (Exception e) {
            throw new BusinessException(CommonError.JWT_ABNORMAL);
        }
        return claims;
    }

    public static String validateLogin(String jsonWebToken) throws BusinessException {
        Map<String, Object> retMap = null;
        Claims claims = parseJWT(jsonWebToken);
        if (claims != null) {
            //解密客户编号
            String decryptUserId = AESSecretUtil.decryptToStr((String)claims.get("userId"), SecretConstant.DATAKEY);
            retMap = new HashMap<>();
            //加密后的客户编号
            retMap.put("userId", decryptUserId);
            //客户名称
            retMap.put("userName", claims.get("userName"));
            //客户端浏览器信息
            retMap.put("userAgent", claims.get("userAgent"));
            //刷新JWT
            retMap.put("freshToken", generateJWT(decryptUserId, (String)claims.get("userName"), (String)claims.get("userAgent"), (String)claims.get("domainName")));
        }else {
            throw new BusinessException(CommonError.JWT_NULL);
        }
        return JSONObject.toJSONString(retMap);
    }
}
