package kalkulaator;

import java.util.stream.IntStream;

public class Valemid {
    
    public boolean kasAlgarv(long arv) {
        if (arv < 2) {
            return false;
        } else {
            for (int i = 2; i <= arv / 2; ++i) {
                if (arv % i == 0) return false;
            }
            return true;
        }
    }
    
    public String poisson(String sulgudeSisu) {
//        Keskmine on keskmine rosinate arv kukli suuruses tainatükis. Tagastab tõenäosuse, et kuklis on täpselt otsitav arv rosinaid.
        try {
            String[] arvud = sulgudeSisu.split(",\\s*"); // koma ja arvu vahel võib olla 0 või rohkem tühikut
            int keskmine = Integer.parseInt(arvud[0]);
            int otsitav = Integer.parseInt(arvud[1]);
            int faktoriaal = IntStream.range(1, otsitav + 1).reduce(1, (x, y) -> x * y);
            double vastus = Math.pow(keskmine, otsitav) / faktoriaal * Math.pow(Math.E, -keskmine);
            return Double.toString(vastus);
        } catch (Exception e) {
            return "Vigane valem";
        }
    }
    
    
}
