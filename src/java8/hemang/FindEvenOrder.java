package java8.hemang;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FindEvenOrder {
public static void main(String[] args) {
	int [] a= {1,2,3,4,5,6,7,8,9};
	Map<Boolean,List<Integer>> al =Arrays.stream(a).boxed().collect(Collectors.partitioningBy(n->n%2==0));
	 for (Map.Entry<Boolean, List<Integer>> entry : al.entrySet()) {
         System.out.println("Key (isEven): " + entry.getKey() + " -> Values: " + entry.getValue());
     }
}
}
