public class Quadruple {
    String operator;
    String argument1;
    String argument2;
    String result;
    
    public Quadruple(String op, String arg1, String arg2, String r){
        operator = op;
        argument1 = arg1;
        argument2 = arg2;
        result = r;
    }

    public String toString() {
        return result + " := " + argument1 + " " + operator + 
                  " " + argument2;
    }
}
