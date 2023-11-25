package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

//rainbow테이블공격에대한 추가적인 방어는 bcrypt알아볼것. 연속대입시 속도자체를 떨어뜨려준다. ->성능저하있음
public class EncryptSha256Util {
	public EncryptSha256Util() {
		
	}
	
	public static String getSalt() {
		SecureRandom sr = new SecureRandom();
		byte[] saltBytes = new byte[16];
		sr.nextBytes(saltBytes); // saltBytes 난수생성
		
		StringBuffer sb = new StringBuffer();
		for(byte b : saltBytes) {
			sb.append(String.format("%02x", b)); // 16진수형식으로 byte를 append
		}

		return sb.toString();
	}
	
	public static String getSha256WithSalt(String str, String salt) {
		String result = "";
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
//			System.out.println(str+salt);
			md.update((str+salt).getBytes());
			byte[] strWithSaltBytes = md.digest();
			
			StringBuffer sb = new StringBuffer();
			for(byte b : strWithSaltBytes) {
				sb.append(String.format("%02x", b));
			}
			
			result = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			System.out.println(e.toString());
		}
		return result;
	}

}
