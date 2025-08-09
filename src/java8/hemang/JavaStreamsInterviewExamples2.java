package java8.hemang;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringJoiner;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class JavaStreamsInterviewExamples2 {
	public static void main(String[] args) {
		firstNonRepeatingChar();
		groupAnagrams();

	}

	private static void firstNonRepeatingChar() {
		String input = "swiss";

		String result = Arrays.stream(input.split(""))
				.collect(Collectors.groupingBy(c -> c, LinkedHashMap::new, Collectors.counting())).entrySet().stream()
				.filter(e -> e.getValue() == 1).map(Map.Entry::getKey).findFirst().orElse(null);

		System.out.println(result); // w

	}

	private static void sortMapByValue() {

		Map<String, Integer> map = Map.of("A", 10, "B", 30, "C", 20);

		Map<String, Integer> sorted = map.entrySet().stream()
				.sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

		System.out.println(sorted); // {B=30, C=20, A=10}

	}

	private static void twoStringsAnagramCheck() {
		String s1 = "listen";
        String s2 = "silent";
        boolean isAnagram = Arrays.stream(s1.split(""))
        .sorted()
        .collect(Collectors.joining())
        .equals(Arrays.stream(s2.split(""))
        .sorted()
        .collect(Collectors.joining()));
        System.out.println(isAnagram); // true

	}
	public static void groupAnagrams() {
		List<String> words = Arrays.asList("bat", "tab", "eat", "tea", "tan", "nat");

		Map<String, List<String>> anagramGroups = words.stream().collect(Collectors.groupingBy(
				word -> word.chars().sorted().mapToObj(c -> String.valueOf((char) c)).collect(Collectors.joining())));

		System.out.println(anagramGroups);
		// {abt=[bat, tab], aet=[eat, tea], ant=[tan, nat]}
	}

	private static void topNFrequentWords() {
		String text = "apple banana apple orange banana apple grape";
		int N = 2;
		Map<String, Long> topN = Arrays.stream(text.split("\\s+"))
				.collect(Collectors.groupingBy(n -> n, Collectors.counting())).entrySet().stream()
				.sorted(Map.Entry.<String, Long>comparingByValue().reversed()).limit(N)
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> a, LinkedHashMap::new));
		System.out.println(topN); // {apple=3, banana=2}

	}

	private static void secondHighest() {
		List<Integer> nums = Arrays.asList(5, 9, 1, 2, 8, 3, 9);
		Integer secondHighest = nums.stream().distinct().sorted(Comparator.reverseOrder()).skip(1).findFirst()
				.orElse(null);
		System.out.println(secondHighest); // 8

	}
	
	private static void wordFrequency() {
        String text = "Java is great and java is fun";

        Map<String, Long> freq = Arrays.stream(text.toLowerCase().split("\\s+"))
        		.collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
        		System.out.println(freq);
	}

	private static void wordFrequency1() {
		String input = "apple orange apple grape";

		Map<String, Long> wordCount = Arrays.stream(input.split("\\s+"))
				.collect(Collectors.groupingBy(word -> word, Collectors.counting()));

		System.out.println(wordCount);
	}

	private static void longestWord() {
        String text = "Java is a powerful language";

        String longest =  Arrays.stream(text.split("\\s+"))
        .max(Comparator.comparingInt(String::length))
        .orElse("");
        System.out.println(longest); // powerful

	}
	
	private static void removeDuplicates() {
        List<Integer> nums = Arrays.asList(10, 20, 10, 30, 20, 40);
        List<Integer> unique = nums.stream()
        .distinct()
        .collect(Collectors.toList());
        System.out.println(unique); // [10, 20, 30, 40]
	}
	
	private static void groupByLength() {
        List<String> words = Arrays.asList("one", "two", "three", "four");

        Map<Integer, List<String>> grouped = words.stream()
        	.collect(Collectors.groupingBy(String::length));
        System.out.println(grouped); // {3=[one, two], 5=[three], 4=[four]}

	}
	
	private static void commonElements() {
		List<Integer> list1 = Arrays.asList(1, 2, 3, 4);
        List<Integer> list2 = Arrays.asList(3, 4, 5, 6);

        List<Integer> common = list1.stream()
        		.filter(list2::contains)
        		.collect(Collectors.toList());
	}
	
	private static void findDuplicates() {
		
        List<String> items = Arrays.asList("apple", "banana", "apple", "orange", "banana");
        items.stream()
        .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
        .entrySet()
        .stream()
        .filter(e->e.getValue()>1)
        .map(Map.Entry::getKey)
        .collect(Collectors.toSet());
	}

	private static void customCollectorsExample() {
		List<String> names = Arrays.asList("Alice", "Bob", "Charlie");

		String result = names.stream().collect(Collector.of(() -> new StringJoiner(",", "[", "]"), StringJoiner::add,
				StringJoiner::merge, StringJoiner::toString));
		System.out.println(result); // [Alice, Bob, Charlie]

	}

	private static void flattenListRemoveDuplicates() {
		List<List<Integer>> nestedList = Arrays.asList(Arrays.asList(1, 2, 3), Arrays.asList(3, 4, 5),
				Arrays.asList(5, 6));

		List<Integer> flatList = nestedList.stream().flatMap(List::stream).distinct().collect(Collectors.toList());
		System.out.println(flatList); // [1, 2, 3, 4, 5, 6]

	}

	private static void parallelStreamPitFall_modifyingState() {
		List<Integer> numbers = IntStream.rangeClosed(1, 1000).boxed().collect(Collectors.toList());
		List<Integer> unsafeList = new ArrayList();
		numbers.parallelStream().forEach(unsafeList::add);

		System.out.println("List size: " + unsafeList.size()); // Often < 1000

	}

	private static void longestPalindrome() {
		List<String> words = Arrays.asList("madam", "racecar", "hello", "level", "world");

		String longest = words.stream().filter(w -> w.equals(new StringBuilder(w).reverse().toString()))
				.max(Comparator.comparingInt(String::length)).orElse(null);
		System.out.println(longest); // racecar

	}

	private static void characterFrequency() {
		String input = "apple orange";

		Map<String, Long> map = Arrays.stream(input.split(""))
				.collect(Collectors.groupingBy(c -> c, Collectors.counting()));
		System.out.println(map);

	}

	
}
/*
 * 
 * Less common Stream API methods (like teeing, dropWhile, takeWhile, mapMulti)
 * 
 * Edge-case collectors (like groupingBy with downstream collectors,
 * partitioningBy with complex predicates)
 * 
 * Uncommon Optional tricks
 * 
 * Parallel stream pitfalls
 * 
 * Custom collectors
 * 
 * Mixing functional style with imperative constraints
 * 
 * Weird behavior in streams with mutable state
 * 
 * String and number transformations that look simple but are trap-laden
 * 
 * 
 * 
 * Group anagrams
 * 
 * Custom collector example (unique sorted join)
 * 
 * Partition + max per partition (even/odd)
 * 
 * Fibonacci via Stream.iterate
 * 
 * Stream.generate example (unique UUIDs)
 * 
 * Median calculation with streams
 * 
 * List → indexed Map (index as key)
 * 
 * Most-frequent-per-group using collectingAndThen
 * 
 * reduce-based concatenation example
 * 
 * Partitioning counts (even/odd counts)
 * 
 * Parallel stream thread-safety pitfall example
 * 
 * Frequency-sorted LinkedHashMap example
 * 
 * Optional.map vs flatMap example
 */
