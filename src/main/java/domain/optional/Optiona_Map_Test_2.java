package domain.optional;

import java.util.Optional;

public class Optiona_Map_Test_2 {

    public static void main(String[] args) {
        Optional<OptionalA> optA = Optional.of(new OptionalA(1));

        optA.map(a -> {
            a.add(1);
            return a;
        });

        optA.ifPresent(a -> System.out.println(a.getMax()));

    }

}

class OptionalA {

    OptionalA(Integer i) {
        this.max = i;
    }

    private Integer max;

    public Integer getMax() {
        return max;
    }

    public void add(Integer i) {
        this.max = Integer.sum(this.max, i);
        return;
    }

}
