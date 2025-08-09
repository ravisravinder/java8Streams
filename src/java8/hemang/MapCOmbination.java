package java8.hemang;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.function.*;
import java.util.*;

public class MapCOmbination {

	public static void main(String args[]) {
		String str = "hello    world";

		mapWithCharCount(str);
		mapWithCharCountEven(str);
		getOnlyNthFrequentCharacter_Print(str, 2);
		// printCountofEachChar(str,1);
		Map<String, Long> map = returnMapCountofEachChar(str);
		tieBreakerofValuesUsingKeys("helloo");

	}

	public static void tieBreakerofValuesUsingKeys(String s) {
		Arrays.stream(s.split("")).collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet()
				.stream()
				.peek(System.out::println) 
				.sorted(Map.Entry.<String, Long>comparingByValue(Comparator.reverseOrder())
						.thenComparing(Map.Entry.comparingByKey()))
				.skip(0).limit(1).forEach(a -> System.out.print("tie breaker: " + a));
	}

	public static Map<String, Long> returnMapCountofEachChar(String s) {
		return Arrays.stream(s.split("")).filter(a -> !a.isBlank())
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet().stream()
				.sorted(Map.Entry.<String, Long>comparingByValue(Comparator.reverseOrder())).skip(1).limit(1)
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

	}

	public static void getOnlyNthFrequentCharacter_Print(String s, int n) {
		Arrays.stream(s.split("")).filter(a -> !a.isBlank())
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet().stream()
				.sorted(Map.Entry.<String, Long>comparingByValue(Comparator.reverseOrder())).skip(n - 1)// 2-1 =1 so
																										// skipped top 1
																										// element
				.limit(1).forEach(a -> System.out.println("key: " + a.getKey() + ", value: " + a.getValue()));
	}

	public static void printCountofEachChar(String s, int n) {
		Arrays.stream(s.split("")).filter(a -> !a.isBlank())
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet().stream()
				.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).skip(n)//// n=2, top 1 value skipped
				.forEach(a -> System.out.println("print values " + a));
	}

	public static boolean mapWithCharCountEven(String s) {
		return Arrays.stream(s.split("")).filter(a -> !a.matches(" "))
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet().stream()
				.max(Map.Entry.comparingByValue()) // comparator comparing Value
				.get().getValue() % 2 == 0 ? true : false;
	}

	public static Map<String, Long> mapWithCharCount(String s) {

		Map<String, Long> map = Arrays.stream(s.split("")).filter(a -> !a.isBlank())
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		return map;
	}

	/*
	 * 
	 * groupingByConcurrent
	 * 
	 * trim() usage
	 * 
	 * parallel stream
	 * 
	 * peek()
	 */

}
