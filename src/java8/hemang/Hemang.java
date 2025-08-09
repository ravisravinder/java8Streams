package java8.hemang;

import java.util.Arrays;
import java.util.Comparator;

public class Hemang {
	public static void main(String[] args) {
		String s = "my name is Ravinder";
		findLongLarged(s);
	}
	private static void  findLongLarged(String s) {
		String result =Arrays.stream(s.split(""))
		.max(Comparator.comparing(String::length))
		.get();
		System.out.println(result);
		
		int result2 =Arrays.stream(s.split(""))
				.max(Comparator.comparing(String::length))
				.get().length();
		System.out.println(result2);
	}
	

}
