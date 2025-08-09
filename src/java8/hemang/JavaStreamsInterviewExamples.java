package java8.hemang;

import java.util.List;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class JavaStreamsInterviewExamples {
	public static void main(String[] args) {
		countCharacterFrequency("banana");
		System.out.println("---------------");

		findSecondHighestSalary();
		System.out.println("---------------");

		flattenListOfLists();
		System.out.println("---------------");

		groupEmployeesByDepartment();
		System.out.println("---------------");

		convertStringsToUppercase();
		System.out.println("---------------");

		findSumAndAverage();
		System.out.println("---------------");

		removeDuplicates();
		System.out.println("---------------");

		sortMapByValues();
		System.out.println("---------------");

		partitionEvenAndOdd();
		System.out.println("-partitionEvenAndOdd^^--------------");

		findLongestString();
		System.out.println("---------------");
		
		wordCount();

	}

	// 1. Count frequency of each character
	private static void countCharacterFrequency(String s) {
		// Map<String,Long> map = Arrays.stream(s.split(""))
		// .collect(Collectors.groupingBy(c->c,Collectors.counting()));
		Arrays.stream(s.split("")).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
				.forEach((ch, count) -> System.out.println(ch + " = " + count));
	}

	// 2. Find second highest salary

	private static void findSecondHighestSalary() {
		List<Integer> salaries = Arrays.asList(3000, 5000, 7000, 9000, 5000);

		Integer secondHighest = salaries.stream().distinct().sorted(Comparator.reverseOrder()).skip(1).findFirst()
				.orElseGet(null);
		System.out.println("Second highest salary: " + secondHighest);

	}

	// 3. Flatten a list of lists
	private static void flattenListOfLists() {
		List<List<String>> listOfLists = Arrays.asList(Arrays.asList("A", "B"), Arrays.asList("C", "D"),
				Arrays.asList("E", "F"));

		List<String> flatList = listOfLists.stream().flatMap(Collection::stream).collect(Collectors.toList());
		System.out.println("Flattened List: " + flatList);
	}

	// 4. Group employees by department
	private static void groupEmployeesByDepartment() {

		record Employee(String name, String dept) {
		}
		List<Employee> employees = Arrays.asList(new Employee("John", "IT"), new Employee("Jane", "HR"),
				new Employee("Mark", "IT"), new Employee("Lucy", "Finance"));
		Map<String, List<Employee>> grouped = employees.stream().collect(Collectors.groupingBy(Employee::dept));
		System.out.println("Employees grouped by department: " + grouped);

	}

	// 5. Convert strings to uppercase
	private static void convertStringsToUppercase() {
		List<String> names = Arrays.asList("alice", "bob", "charlie");
		List<String> upper = names.stream().map(a -> a.toUpperCase()).collect(Collectors.toList());
		System.out.println("Uppercase names: " + upper);
	}

	// 6. Find sum and average of numbers
	private static void findSumAndAverage() {
        List<Integer> numbers = Arrays.asList(5, 10, 15, 20);
        int sum = numbers.stream().mapToInt(Integer::intValue).sum();
        double avg= numbers.stream().mapToInt(Integer::intValue).average().orElse(0);
        System.out.println("Sum: " + sum + ", Average: " + avg);
	}
	
	  // 7. Remove duplicates from a list
    private static void removeDuplicates() {
        List<Integer> numbers = Arrays.asList(1, 2, 2, 3, 4, 4, 5);
        
        List<Integer> unique = numbers.stream().distinct().collect(Collectors.toList());
        System.out.println("Unique numbers: " + unique);
    }
	
    // 8. Sort a map by values
    private static void sortMapByValues() {
        Map<String, Integer> map = new HashMap<>();
        map.put("Banana", 2);
        map.put("Apple", 5);
        map.put("Orange", 3);
        
        Map<String, Integer> sortedMapWithoutLinked =  map.entrySet()
        	      .stream()
        	      .sorted(Map.Entry.comparingByValue())
        	      .collect(Collectors.toMap(
        	    		  Map.Entry::getKey, 
        	    		  Map.Entry::getValue 
        	    		  ));
        
        System.out.println("Map sorted by values: " + sortedMapWithoutLinked);

        
        LinkedHashMap<String, Integer> sortedMap =  map.entrySet()
      .stream()
      .sorted(Map.Entry.comparingByValue())
      .collect(Collectors.toMap(
    		  Map.Entry::getKey, 
    		  Map.Entry::getValue,
    		  (e1,e2)->e1,
    		  LinkedHashMap::new  
    		  ));
        System.out.println("Map sorted by values: " + sortedMap);

    }
    
 // 9. Partition numbers into even and odd
    private static void partitionEvenAndOdd() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        
        Map<Boolean, List<Integer>> partitioned =
        		numbers.stream().collect(Collectors.partitioningBy(n->n%2==0));
        System.out.println("Partitioned numbers (even/odd): " + partitioned);
    }
    
 // 10. Find the longest string
    private static void findLongestString() {
        List<String> words = Arrays.asList("apple", "banana", "watermelon", "grape");
        
        String longest = words.stream().max(Comparator.comparingInt(String::length))
        .orElse("");
        
        System.out.println("Longest word: " + longest);
    }
    
    private static void wordCount() {
        String input = "apple orange apple grape";

        Map<String, Long> wordCount = Arrays.stream(input.split("\\s+"))
                .collect(Collectors.groupingBy(word -> word, Collectors.counting()));

        System.out.println(wordCount);
    }
    
    
}



/*



Less common Stream API methods (like teeing, dropWhile, takeWhile, mapMulti)

Edge-case collectors (like groupingBy with downstream collectors, partitioningBy with complex predicates)

Uncommon Optional tricks

Parallel stream pitfalls

Custom collectors

Mixing functional style with imperative constraints

Weird behavior in streams with mutable state

String and number transformations that look simple but are trap-laden

*/