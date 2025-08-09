package java8.hemang;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class MapSortingExamples {
	public static void main(String[] args) {
		Map<String, Integer> map = new HashMap();
		map.put("Banana", 3);
		map.put("Apple", 5);
		map.put("Orange", 2);
		map.put("Mango", 5); // duplicate value for tie-break example
		map.put("Grapes", null); // null value example

		sortByKey(map);
	}

	private static void sortByKey(Map<String, Integer> map) {
		// 1️⃣ Sort by Key (Ascending)
		Map<String, Integer> sortByKeyAsc = map.entrySet().stream().sorted(Map.Entry.comparingByKey())
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
		System.out.println("Sort by Key Asc: " + sortByKeyAsc);

		// 2️⃣ Sort by Key (Descending)
		Map<String, Integer> sortByKeyDesc = map.entrySet().stream()
				.sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
		System.out.println("Sort by Key Desc: " + sortByKeyDesc);

		// 3️⃣ Sort by Value (Ascending)
		Map<String, Integer> sortByValueAsc = map.entrySet().stream()
				.sorted(Map.Entry.comparingByValue(Comparator.nullsLast(Integer::compareTo)))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
		System.out.println("Sort by Value Asc: " + sortByValueAsc);

		// 4️⃣ Sort by Value (Descending)
		Map<String, Integer> sortByValueDesc = map.entrySet().stream()
				.sorted(Map.Entry.comparingByValue(Comparator.nullsLast(Comparator.reverseOrder())))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

		System.out.println("Sort by Value Desc: " + sortByValueDesc);

		// 5️⃣ Sort by Value, then Key (Tie-breaker)
		Map<String, Integer> sortByValueThenKey = map.entrySet().stream()
				.sorted(Map.Entry.<String, Integer>comparingByValue(Comparator.nullsLast(Integer::compareTo))
						.thenComparing(Map.Entry.comparingByKey()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
		System.out.println("Sort by Value then Key: " + sortByValueThenKey);

		// 6️⃣ Case-Insensitive Key Sort
		Map<String, Integer> caseInsensitiveSort = map.entrySet().stream()
				.sorted(Map.Entry.comparingByKey(String.CASE_INSENSITIVE_ORDER))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
		System.out.println("Case-Insensitive Key Sort: " + caseInsensitiveSort);

		// 7️⃣ Sort Custom Object Key
		Map<Person, Integer> personMap = new HashMap<>();
		personMap.put(new Person("John", 25), 90);
		personMap.put(new Person("Alice", 30), 85);
		personMap.put(new Person("Bob", 22), 95);

		Map<Person, Integer> sortPersonByName = personMap.entrySet().stream()
				.sorted(Map.Entry.comparingByKey(Comparator.comparing(Person::getName)))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
		System.out.println("Sort Person by Name: " + sortPersonByName);
	}
}

class Person {
	private String name;
	private int age;

	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name + "(" + age + ")";
	}
}
