package kalkulaator;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Model {
    
    Valemid valemid = new Valemid();
    
    public String evalFormula(String sisendTekst) throws ScriptException {
//        Kui on tegemist astendamisega ehk valemis on **
        if (sisendTekst.contains("**")) {
            String[] astendatavJaAstendaja = sisendTekst.split("\\*\\*");
            double astendatav = Double.parseDouble(astendatavJaAstendaja[0]);
            double astendaja = Double.parseDouble(astendatavJaAstendaja[1]);
            try {
                double vastus = Math.pow(astendatav, astendaja);
                return Double.toString(vastus);
            } catch (Exception e) {
                return "Vigane valem";
            }

//            Ei ole astendamine, aga tegemist on valemiga (sisestus algab arvuga), siis kasutada JavaScripti, et valemit lugeda
        }
        try {
            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("js");
            Object result = engine.eval(sisendTekst);
            return result.toString();
        } catch (Exception e) {
            return "Vigane valem";
        }
    }
    
    
    public String evalString(String sisendTekst) {
        String sulgudeSisu = evalRegex(sisendTekst);
        if (sisendTekst.toLowerCase().startsWith("poisson")) {
            return valemid.poisson(sulgudeSisu);
        } else if (sisendTekst.toLowerCase().startsWith("kombin")) {
            return valemid.kombinatsioon(sulgudeSisu);
        } else if (sisendTekst.toLowerCase().startsWith("binoom")) {
            return valemid.binoom(sulgudeSisu);
        } else {
            return "Vigane sisend";
        }
    }
    
    public String evalRegex(String kasutajaSisend) {
//        Tagastab kasutaja sisestatud tekstist sulgude sisu. Kui sulge pole, siis on valem vigane.
        Pattern muster = Pattern.compile("(.*\\()(.*)(\\))");
        Matcher m = muster.matcher(kasutajaSisend);
        if (m.matches()) {
            return m.group(2);
        } else {
            return "Valem on vigane";
        }
        
    }
    
    
}
