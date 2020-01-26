package kalkulaator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.Random;

public class Controller {
    
    @FXML
    private Label result;
    private long num1 = 0;
    private String operator = "";
    private boolean start = true;
    private Model model = new Model();
    @FXML
    private TextField sisend = new TextField();
    
    @FXML
    public void processUserInput(ActionEvent event) {
        String valem = sisend.getText();
    }
    
    @FXML
    public void processNumbers(ActionEvent event) {
        if (start) {
            result.setText(""); // Kui numbri sisestamine algab või kui eelnevalt on vajutatud = nuppu, siis sea stringi väärtuseks tühi string.
            start = false;
        }
        String value = ((Button) event.getSource()).getText(); // Kui juba käib arvutamine, siis võta nupu peal kirjas olev arv.
        result.setText(result.getText() + value); // liida kokku tühi string ja arv nupu pealt.
    }

//    ScriptEngineManager manager = new ScriptEngineManager();
//    ScriptEngine engine = manager.getEngineByName("js");
//    Object result = engine.eval("2*(4+5)");
//        System.out.println(result.toString());
    
    @FXML
    public void processOperators(ActionEvent event) {
        String value = ((Button) event.getSource()).getText();
//        kui ei ole võrdusmärki, siis on esimene arv
        if (!value.equals("=")) {
            if (!operator.isEmpty()) {
                return;
            } else {
                operator = value;
                num1 = Long.parseLong(result.getText());
                result.setText("");
            }
        } else {
            if (operator.isEmpty()) {
                return;
            } else {
                long num2 = Long.parseLong(result.getText());
                double output = model.Calculate(num1, num2, operator);
                result.setText(String.valueOf(output));
                operator = "";
                start = true;
            }
        }
    }
    
    static boolean kasAlgarv(long arv) {
        for (int i = 2; i <= arv / 2; ++i) {
            if (arv % i == 0) return false;
        }
        return true;
    }
    
    @FXML
    public void processAC(ActionEvent event) {
        result.setText("");
    }
    
    @FXML
    public void processPrime(ActionEvent event) {
        num1 = Long.parseLong(result.getText());
        boolean algarv = kasAlgarv(num1);
        if (algarv) {
            result.setText(String.valueOf(num1) + " on algarv");
        } else {
            result.setText(String.valueOf(num1) + " ei ole algarv");
        }
    }
}
