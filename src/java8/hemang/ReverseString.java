package java8.hemang;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ReverseString {
	public static void main(String[] args) {
		String s = "Hello world java";
		getReverseOfEachWord(s);
	}

	public static void getReverseOfEachWord(String s) {
		Arrays.stream(s.split(" ")).map(a -> new StringBuilder(a).reverse().toString()).collect(Collectors.joining(""));

	}
}
