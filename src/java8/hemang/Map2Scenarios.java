package java8.hemang;

import java.util.HashMap;
import java.util.Map;

public class Map2Scenarios {
	public static void main(String[] args) {
		Map<String, Long> map1 = Map.of("a", 2l, "b", 3L);
		Map<String, Long> map2 = Map.of("a", 4L, "b", 1L, "c", 5L);
		Map<String, Long> merged = new HashMap<>();
		map2.forEach((k, v) -> merged.merge(k, v, Long::sum));

	}
}
