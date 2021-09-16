package com.eureka.eurekaservice;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TokenUtil {

	public static String genAccessToken(String userId, String keySid, String keySecret, int expireInSecond) {
		try {
			Algorithm algorithmHS = Algorithm.HMAC256(keySecret);

			Map<String, Object> headerClaims = new HashMap<String, Object>();
			headerClaims.put("typ", "JWT");
			headerClaims.put("alg", "HS256");
			headerClaims.put("cty", "stringee-api;v=1");

			long exp = System.currentTimeMillis() + expireInSecond * 1000;

			String token = JWT.create().withHeader(headerClaims)
					.withClaim("jti", keySid + "-" + System.currentTimeMillis())
					.withClaim("iss", keySid)
					.withClaim("rest_api", true)
					.withClaim("userId", userId)
					.withExpiresAt(new Date(exp))
					.sign(algorithmHS);

			return token;
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}

}
