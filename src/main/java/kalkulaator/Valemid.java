package kalkulaator;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class Valemid {
    
    // tagastab true, kui long-tüüpi parameeter on algarv.
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
    
    
    // Leiab järjestamata kombinatsioonide arvu n arvust k kaupa. Näiteks mitu 13 kaardist koosnevat kaardikätt on
    // võimalik saada 52-kaardilisest kaardipakist: kombinatsioon(52, 13)
    public String kombinatsioon(String sulgudeSisu) {
        try {
            String[] arvud = sulgudeSisu.split(",\\s*");
            int n = Integer.parseInt(arvud[0]);
            int k = Integer.parseInt(arvud[1]);
            long nFaktoriaal = IntStream.range(1, n + 1).reduce(1, (x, y) -> x * y);
            long kFaktoriaal = IntStream.range(1, k + 1).reduce(1, (x, y) -> x * y);
            long nMinKFaktoriaal = IntStream.range(1, n - k + 1).reduce(1, (x, y) -> x * y);
            return Long.toString(nFaktoriaal / (kFaktoriaal * nMinKFaktoriaal));
        } catch (Exception e) {
            return "Vigane valem";
        }
    }
    
    // Seda meetodit kasutab programm abimeetodina. Näiteks Bernoulli valemi üheks osaks on kommbinatsioonide arvu
    // leidmine. Seega kui n ja k on täisarvud (mitte tekst kasutaja sisendina), siis saab kasutada seda meetodit.
    public long kombinatsioon(int n, int k) {
        long nFaktoriaal = IntStream.range(1, n + 1).reduce(1, (x, y) -> x * y);
        long kFaktoriaal = IntStream.range(1, k + 1).reduce(1, (x, y) -> x * y);
        long nMinKFaktoriaal = IntStream.range(1, n - k + 1).reduce(1, (x, y) -> x * y);
        return nFaktoriaal / (kFaktoriaal * nMinKFaktoriaal);
    }
    
    // Korvpallur viskab n vabaviset. Tõenäosus tabada on p. Mis on tõenäosus, et ta saab k viset sisse.
    // Meetod realiseerib Bernoulli valemi.
    public String binoom(String sulgudeSisu) {
        try {
            String[] arvud = sulgudeSisu.split(",\\s*");
            int n = Integer.parseInt(arvud[0]);
            double p = Double.parseDouble(arvud[1]);
            int k = Integer.parseInt(arvud[2]);
            double komb = kombinatsioon(n, k);
            double pk = Math.pow(p, k);
            double ylej = Math.pow((1 - p), (n - k));
            return Double.toString(komb * pk * ylej);
        } catch (Exception e) {
            return "Vigane valem";
        }
    }
    public String geom(String sulgudeSisu) {
//          Tagastab geomeetrilise jaotuse tõneäosuse sisestatud katsete arvul, keskväärtuse, dispersiooni ja standardhälbe.
//          Kasutamine: geom(otsitav katsete arv k: P{X = k}, toimumise tõenäosus ühel katsel)
        try {
            String[] arvud = sulgudeSisu.split(",\\s*"); // koma ja arvu vahel võib olla 0 või rohkem tühikut
            int katseteArv = Integer.parseInt(arvud[0]);
            double tõenäosus = Double.parseDouble(arvud[1]);
            double keskväärtus = 1 / tõenäosus;
            double otsitav = Math.pow(1 - tõenäosus, katseteArv - 1) * tõenäosus;
            double dispersioon = (1-tõenäosus) / Math.pow(tõenäosus, 2);
            double standardhälve = Math.sqrt(dispersioon);
            return "P (X=" + katseteArv + ") = " + Math.round(otsitav * 1000.0) / 1000.0 + ", EX = " +
                    Math.round(keskväärtus * 1000.0) / 1000.0 + ", DX = " + Math.round(dispersioon * 1000.0) / 1000.0 +
                    ", SD = " + Math.round(standardhälve * 1000.0) / 1000.0;
        }
        catch (Exception e) {
            return "Vigane valem";
        }
    }

    public String random(String sulgudeSisu) {
//        Leiab juhusliku täisarvu. Arv jääb parameetreite (kaasaarvatud) vahele. Kasutamine: random(vähim arv, suurim arv)
        try {
            String[] arvud = sulgudeSisu.split(",\\s*"); // koma ja arvu vahel võib olla 0 või rohkem tühikut
            int vähim = Integer.parseInt(arvud[0]);
            int suurim = Integer.parseInt(arvud[1]);
            int arv = vähim + (int) (Math.random() * ((suurim - vähim) + 1));
            return Integer.toString(arv);
        } catch (Exception e) {
            return "Vigane valem";
        }
    }

    public String bernoull(String sulgudeSisu) {
//        Leiab bernoulli jaotuse keskväärtuse, dispersiooni, standardhälbe. Kasutamine bernoull(tõenäosus)
        try {
            double tõenäosus = Double.parseDouble(sulgudeSisu);
            double dispersioon = tõenäosus * (1 - tõenäosus);
            double standardhälve = Math.sqrt(dispersioon);
            return "EX = " + Math.round(tõenäosus * 1000.0) / 1000.0 +
                    ", DX = " + Math.round(dispersioon * 1000.0) / 1000.0 +
                    ", SD = " + Math.round(standardhälve * 1000.0) / 1000.0;
        } catch (Exception e) {
            return "Vigane valem";
        }
    }



    // Loeb sisse kuupäeva kujul dd.mm.yyyy ja tagastab eestikeelse nädalapäeva.
    public String nädalapäev(String kuupäev) {
        Map<String, String> nädalapäevad = new HashMap<>();
        nädalapäevad.put("MONDAY", "esmaspäev");
        nädalapäevad.put("TUESDAY", "teisipäev");
        nädalapäevad.put("WEDNESDAY", "kolmapäev");
        nädalapäevad.put("THURSDAY", "neljapäev");
        nädalapäevad.put("FRIDAY", "reede");
        nädalapäevad.put("SATURDAY", "laupäev");
        nädalapäevad.put("SUNDAY", "pühapäev");
        
        String[] kuupäevaJupid = kuupäev.split("\\.");
        
        int aasta = Integer.parseInt(kuupäevaJupid[2]);
        int kuu = Integer.parseInt(kuupäevaJupid[1]);
        int päev = Integer.parseInt(kuupäevaJupid[0]);
        LocalDate kuupaev = LocalDate.of(aasta, kuu, päev);
        String dayOfWeek = kuupaev.getDayOfWeek().toString();
        return nädalapäevad.get(dayOfWeek);
    }
    
    
}
