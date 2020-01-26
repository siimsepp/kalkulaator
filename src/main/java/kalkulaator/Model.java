package kalkulaator;

public class Model {
    
    public double Calculate(long num1, long num2, String operator) {
        switch (operator) {
            case "+":
                return (double) num1 + num2;
            case "-":
                return (double) num1 - num2;
            case "*":
                return (double) num1 * num2;
            case "/":
                if (num2 == 0) {
                    return 0;
                } else {
                    return (double) num1 / num2;
                }
            default:
                return 0;
        }
        
    }
    
}
