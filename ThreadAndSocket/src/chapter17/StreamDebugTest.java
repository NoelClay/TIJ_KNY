package chapter17;
import java.util.ArrayList;
import java.util.List;
import java.util.Spliterator;
import java.util.stream.Stream;

public class StreamDebugTest {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");

        Stream<String> stream = list.stream();
        
        stream.map(s -> {
                System.out.println("Changing: " + s);
                return s.toLowerCase();
            })
            .forEach(s -> {
                System.out.println("last: " + s);
            });
            
        Stream<String> newStream = list.stream();
        Spliterator<String> spliterator = newStream.spliterator();
    }
}