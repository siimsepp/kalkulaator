package kalkulaator;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReplModel {
    
    Valemid valemid = new Valemid();
    
    public String evalFormula(String sisendTekst) throws ScriptException {
        try {
            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("js");
            Object result = engine.eval(sisendTekst);
            return result.toString();
        } catch (Exception e) {
            return "Vigane valem";
        }
    }


//
//    String rida = sc.nextLine();
//    Pattern muster = Pattern.compile("(.*) ([ABCD]{1}) (.*)");
//    Matcher m = muster.matcher(rida);
//                if (m.matches()) {
//        String jook = m.group(1);
    
    
    public String evalString(String sisendTekst) {
        if (sisendTekst.toLowerCase().startsWith("poisson")) {
            String sulgudeSisu = evalRegex(sisendTekst);
            return valemid.poisson(sulgudeSisu);
            
            
        } else {
            return "pole poissoni";
        }
    }
    
    public String evalRegex(String kasutajaSisend) {
//        Tagastab kasutaja sisestatut tekstist sulgude sisu. Kui sulge pole, siis on valem vigane.
        Pattern muster = Pattern.compile("(.*\\()(.*)(\\))");
        Matcher m = muster.matcher(kasutajaSisend);
        if (m.matches()) {
            return m.group(2);
        } else {
            return "Valem on vigane";
        }
        
    }
    
    
}
