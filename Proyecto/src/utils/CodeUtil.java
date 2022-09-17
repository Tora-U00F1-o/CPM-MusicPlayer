package utils;

public class CodeUtil {

	public static int getRandomNumber(int minLimit, int maxLimit) {
			return (int) (Math.random()*(maxLimit-minLimit+1)+minLimit);
	}
	
}