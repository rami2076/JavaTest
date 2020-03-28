package domain.function;

import java.util.function.Predicate;

public class PredicateTest {

    
    public static void main( String... aa) {
        
        
        String aaaa= "aaa";
       Predicate<String> filter = s->!s.isEmpty();
       filter= filter.and(s->s.equals("bbb"));
       
       
       System.out.println(filter.test(aaaa));
    }
    
}
