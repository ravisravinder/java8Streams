package java8.hemang;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FindFirstNonRepeatative {
public static void main(String[] args) {
	String s= "JaJava";
	Character s1 =s.chars().mapToObj(a->(char)a)
	.collect(Collectors.groupingBy(Function.identity(),LinkedHashMap::new,Collectors.counting()))
	.entrySet()
	.stream()
	.filter(e->e.getValue()==1)
	.findFirst().get().getKey();
	System.out.println(s1);
	
	
}
}

/*
String s1 = Arrays.stream(s.split(""))
.collect(Collectors.groupingBy(Function.identity(),LinkedHashMap::new,Collectors.counting()))
.entrySet()
.stream()
.filter(e->e.getValue()==1)
.findFirst().get().getKey();
System.out.println(s1);
*/