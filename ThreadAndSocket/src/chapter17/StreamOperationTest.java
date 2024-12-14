package chapter17;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collector.Characteristics;

public class StreamOperationTest {
    public static void main(String[] args) {
        List<String> data = Arrays.asList(
            "A1", "A2", "B1", "B2", "A1", "C1", "C2", "B1"
        );

        System.out.println("=== 스트림 파이프라인 시작 ===");
        
        List<String> result = data.stream()
            // 1. Stateless 중간 연산
            .filter(s -> {
                System.out.println("[Stateless] filter 검사 C면 그냥 걸러!!: " + s);
                return s.startsWith("A") || s.startsWith("B");
            })
            .map(s -> {
                System.out.println("[Stateless] map 변환 Mapped 붙여!: " + s);
                return s + "_mapped";
            })
            .peek(s -> 
                System.out.println("[Stateless] 1번 peek! distinct 전: " + s)
            )
            // 2. Stateful 중간 연산
            .distinct()  // 중복 제거
            .peek(s -> 
                System.out.println("[Stateful] 2번peek! distinct로 상태 저장!!: " + s)
            )
            .sorted((s1, s2) -> {
                System.out.println("[Stateful] 정렬 비교: " + s1 + " <-> " + s2 + "==" + s1.compareTo(s2));
                return s1.compareTo(s2);
            })
            .peek(s -> 
                System.out.println("[Stateful] 3번peek! sorted 후: " + s)
            )
            //실제로는 이런식의 구현을 할필요가 없는데 Sysout 찍을려고 Collector를 만든것
            //Collectors에 Collector 생성 정적 매서드 존재하고 그걸 쓰는게 맞다.
            .collect(new Collector<String, List<String>, List<String>>() {
                @Override
                public Supplier<List<String>> supplier() {
                    return () -> {
                        System.out.println("[Final] 새로운 리스트 생성");
                        return new ArrayList<>();
                    };
                }

                @Override
                public BiConsumer<List<String>, String> accumulator() {
                    return (list, item) -> {
                        System.out.println("[Final] 누적 처리: " + item);
                        list.add(item);
                    };
                }

                @Override
                public BinaryOperator<List<String>> combiner() {
                    return (list1, list2) -> {
                        System.out.println("[Final] 리스트 병합");
                        list1.addAll(list2);
                        return list1;
                    };
                }

                @Override
                public Function<List<String>, List<String>> finisher() {
                    return list -> {
                        System.out.println("[Final] 최종 처리 완료");
                        return list;
                    };
                }

                @Override
                public Set<Characteristics> characteristics() {
                    return Collections.emptySet();
                }
            });

        System.out.println("\n=== 최종 결과 ===");
        result.forEach(System.out::println);
    }
}
