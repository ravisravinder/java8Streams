package java8.hemang;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.*;

public class Basics {
	public static void main(String[] args) {
		//findLengthOfStrings();
		//flatMapExample();
		//listToCharUsingFlatmap();
		flatMapWithOptional();
		completableFeature();
		
	}
	private static void completableFeature() {
		CompletableFuture<String> future = CompletableFuture.completedFuture("Hi");
		CompletableFuture<Integer> lengthFuture =  future.thenCompose(msg->CompletableFuture.completedFuture(msg.length()));
//thenCompose() is the flatMap() of futures.
	}
	
	
	
	
	private static void flatMapWithOptional() {
		Optional<String> maybeName = Optional.of("Alice");
		Optional<Integer> mayBeLength = maybeName.flatMap(
				name->Optional.of(name.length()));
		System.out.println(mayBeLength.isEmpty()?false:mayBeLength.get());
	}//FlatMap unwraps the nested Optional cleanly.
	
	private static void listToCharUsingFlatmap() {
		List<String> words = List.of("Hi", "Bye");

		Stream<Stream<Character>> mapResult  =
		words.stream().map(word->word.chars().mapToObj(c->(char)c));
		System.out.println("Using map(): ");
		mapResult.forEach(ch->System.out.print(ch+" "));
		System.out.println();
		
		Stream<Character> flatMapResult  =
				words.stream().flatMap(word->word.chars().mapToObj(c->(char)c));
		flatMapResult.forEach(ch->System.out.println(ch+" "));
	}
	

	private static void findLengthOfStrings() {
		List<String> names = List.of("Alice", "Bob", "Charlie");
		List<Integer> l= names.stream().map(s->s.length())
		.collect(Collectors.toList());
		System.out.println(l);
	}
	
	private static void flatMapExample() {
		List<String> lines = List.of("apple orange", "banana", "grape melon");
		List<String> words = lines.stream()
		.flatMap(line->Arrays.stream(line.split(" ")))
		.collect(Collectors.toList());
	}

}

/*
 * 
 * map() is perfect when there’s a 1-to-1 transformation.
flatMap() rules when there’s 1-to-many or nested data.
boxed()

Format Code	Ctrl + Shift + F
Extract Method	Alt + Shift + M
Extract Local Variable	Alt + Shift + L	
Duplicate Line / Selection	Ctrl + Alt + Down	

Delete Line	Ctrl + D
*/