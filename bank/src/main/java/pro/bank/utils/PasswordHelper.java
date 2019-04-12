package pro.bank.utils;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class PasswordHelper {

	private static String algorithmName = "SHA-256";

	/*public static String encryptPassword(User user, UserInfo info) {
		String newPassword = new SimpleHash(algorithmName, info.getPassword(), ByteSource.Util.bytes(user.getJtCode())).toHex();
		return newPassword;
	}*/

	public static String encryptPassword(String oldPassowrd, String sign) {
		String password = new SimpleHash(algorithmName, oldPassowrd, ByteSource.Util.bytes(sign)).toHex();
		return password;
	}
	
	public static void main(String[] args) {
		System.out.println(encryptPassword("123456", "admin"));	
	}
}
