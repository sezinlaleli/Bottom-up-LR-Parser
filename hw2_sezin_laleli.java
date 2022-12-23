import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

public class hw2_sezin_laleli {

    public static String[] stackA = {"0"};
    public static String input;
    public static String[] inputA;
    public static String[][] actions = {
            {"S5", "", "", "S4", "", "", "1", "2", "3"},
            {"", "S6", "", "", "", "ACCEPT", "", "", ""},
            {"", "R2", "S7", "", "R2", "R2", "", "", ""},
            {"", "R4", "R4", "", "R4", "R4", "", "", ""},
            {"S5", "", "", "S4", "", "", "8", "2", "3"},
            {"", "R6", "R6", "", "R6", "R6", "", "", ""},
            {"S5", "", "", "S4", "", "", "", "9", "3"},
            {"S5", "", "", "S4", "", "", "", "", "10"},
            {"", "S6", "", "", "S11", "", "", "", ""},
            {"", "R1", "S7", "", "R1", "R1", "", "", ""},
            {"", "R3", "R3", "", "R3", "R3", "", "", ""},
            {"", "R5", "R5", "", "R5", "R5", "", "", ""}
    };

    public static void main(String[] args) {
        input = args[0];
        String outputFile = args[1];

        //test case
        //input = "id*((id))*(id)+(id)$";

        //overwrite file
        // create a new writer
        PrintWriter p = null;
        try {
            //if file not exist then create
            p = new PrintWriter(new FileOutputStream(outputFile));
            p.print("");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        p.close();


        //find lex count
        int lex = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == 'i' || input.charAt(i) == '+' || input.charAt(i) == '*' || input.charAt(i) == '(' || input.charAt(i) == ')' || input.charAt(i) == '$') {
                lex++;
            }
        }

        int k = 0;
        inputA = new String[lex];
        for (int i = 0; i < input.length(); i++) {
            switch (input.charAt(i)) {
                case 'i':
                    inputA[k] = "id";
                    k++;
                    break;
                case '+':
                    inputA[k] = "+";
                    k++;
                    break;
                case '*':
                    inputA[k] = "*";
                    k++;
                    break;
                case '(':
                    inputA[k] = "(";
                    k++;
                    break;
                case ')':
                    inputA[k] = ")";
                    k++;
                    break;
                case '$':
                    inputA[k] = "$";
                    k++;
                    break;
                default:
                    break;
            }
        }

        String[] tmp2 = inputA;

        String s = toString1(stackA);
        String inp = toString2(inputA);
        String make = "";

        int state = Integer.parseInt(stackA[stackA.length - 1]);
        int act_goto = actNmb(inputA[0]);

        make = actions[state][act_goto];
        String m = printAction(make);

        ArrayList<String> makes = new ArrayList<String>();
        makes.add(m);

        while (true) {
            if (make.equals("")) {
                m = printAction(make);
                makes.add(m);
                break;
            } else if (make.charAt(0) == 'S') {
                S(inputA[0], make.substring(1));
                m = printAction(make);
                makes.add(m);
            } else if (make.charAt(0) == 'R') {
                R(make.substring(1));
                m = printAction(make);
                makes.add(m);
            } else if (make.charAt(0) == 'A') {
                m = printAction(make);
                makes.add(m);
                break;
            }
            state = Integer.parseInt(stackA[stackA.length - 1]);
            act_goto = actNmb(inputA[0]);
            make = actions[state][act_goto];
        }

        int mC = 1;

        String[] tmp = {"0"};
        stackA = tmp;
        inputA = tmp2;

        state = Integer.parseInt(stackA[stackA.length - 1]);
        act_goto = actNmb(inputA[0]);

        make = actions[state][act_goto];
        m = printAction(make);

        //overwrite file
        // create a new writer
        PrintWriter pw = null;
        try {
            //if file not exist then create
            pw = new PrintWriter(new FileOutputStream(outputFile, true));
            pw.printf("%-40s %-40s %s\n", "Stack", "Input ", "Action");
            while (true) {
                pw.printf("%-40s %-40s %s\n", s, inp, makes.get(mC));
                if (make.equals("")) {
                    mC++;
                    s = toString1(stackA);
                    inp = toString2(inputA);
                    System.out.println("Error occurred.");
                    pw.close();
                    System.exit(0);
                } else if (make.charAt(0) == 'S') {
                    S(inputA[0], make.substring(1));
                    mC++;
                    s = toString1(stackA);
                    inp = toString2(inputA);
                } else if (make.charAt(0) == 'R') {
                    R(make.substring(1));
                    mC++;;
                    s = toString1(stackA);
                    inp = toString2(inputA);
                } else if (make.charAt(0) == 'A') {
                    mC++;
                    s = toString1(stackA);
                    inp = toString2(inputA);
                    System.out.println("The input has been parsed successfully.");
                    pw.close();
                    System.exit(0);
                }
                state = Integer.parseInt(stackA[stackA.length - 1]);
                act_goto = actNmb(inputA[0]);
                make = actions[state][act_goto];
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }

    public static void S(String lex, String nmb) {
        int stI = stackA.length;  //stack index
        String[] tSt = new String[stI + 2];
        for (int i = 0; i < stI; i++) {
            tSt[i] = stackA[i];
        }
        tSt[stI] = lex;
        tSt[stI + 1] = nmb;
        stackA = tSt;
        stI = stackA.length;
        int c = inputA.length - 1;
        String[] t = new String[c];
        for (int i = 0; i < t.length; i++) {
            t[i] = inputA[i + 1];
        }
        inputA = t;
    }

    public static void R(String nmb) {
        switch (nmb) {
            //E->E+T
            case "1":
                for (int i = stackA.length - 1; i > 4; i--) {
                    int stI = stackA.length;  //stack index
                    if (stackA[i].equals("T") || stackA[i - 2].equals("+") || stackA[i - 4].equals("E")) {
                        String[] tSt = new String[stI - 4];
                        i = i - 4;
                        for (int j = 0; j < i; j++) {
                            tSt[j] = stackA[j];
                        }
                        tSt[i] = "E";
                        //GOTO
                        tSt[i + 1] = actions[Integer.parseInt(stackA[i - 1])][6];
                        stackA = tSt;
                        break;
                    }
                }
                break;
            //E->T
            case "2":
                for (int i = stackA.length - 1; i > 0; i--) {
                    int stI = stackA.length;  //stack index
                    if (stackA[i].equals("T")) {
                        String[] tSt = new String[stI];
                        for (int j = 0; j < i; j++) {
                            tSt[j] = stackA[j];
                        }
                        tSt[i] = "E";
                        //GOTO
                        tSt[i + 1] = actions[Integer.parseInt(stackA[i - 1])][6];
                        stackA = tSt;
                        break;
                    }
                }
                break;
            //T->T*F
            case "3":
                for (int i = stackA.length - 1; i > 4; i--) {
                    int stI = stackA.length;  //stack index
                    if (stackA[i].equals("F") || stackA[i - 2].equals("*") || stackA[i - 4].equals("T")) {
                        String[] tSt = new String[stI - 4];
                        i = i - 4;
                        for (int j = 0; j < i; j++) {
                            tSt[j] = stackA[j];
                        }
                        tSt[i] = "T";
                        //GOTO
                        tSt[i + 1] = actions[Integer.parseInt(stackA[i - 1])][7];
                        stackA = tSt;
                        break;
                    }
                }
                break;
            //T->F
            case "4":
                for (int i = stackA.length - 1; i > 0; i--) {
                    int stI = stackA.length;  //stack index
                    if (stackA[i].equals("F")) {
                        String[] tSt = new String[stI];
                        for (int j = 0; j < i; j++) {
                            tSt[j] = stackA[j];
                        }
                        tSt[i] = "T";
                        //GOTO
                        tSt[i + 1] = actions[Integer.parseInt(stackA[i - 1])][7];
                        stackA = tSt;
                        break;
                    }
                }
                break;
            //F->(E)
            case "5":
                for (int i = stackA.length - 1; i > 4; i--) {
                    int stI = stackA.length;  //stack index
                    if (stackA[i].equals(")") || stackA[i - 2].equals("E") || stackA[i - 4].equals("(")) {
                        String[] tSt = new String[stI - 4];
                        i = i - 4;
                        for (int j = 0; j < i; j++) {
                            tSt[j] = stackA[j];
                        }
                        tSt[i] = "F";
                        //GOTO
                        tSt[i + 1] = actions[Integer.parseInt(stackA[i - 1])][8];
                        stackA = tSt;
                        break;
                    }
                }
                break;
            //F->id
            case "6":
                for (int i = stackA.length - 1; i > 0; i--) {
                    int stI = stackA.length;  //stack index
                    if (stackA[i].equals("id")) {
                        String[] tSt = new String[stI];
                        for (int j = 0; j < i; j++) {
                            tSt[j] = stackA[j];
                        }
                        tSt[i] = "F";
                        //GOTO
                        tSt[i + 1] = actions[Integer.parseInt(stackA[i - 1])][8];
                        stackA = tSt;
                        break;
                    }
                }
                break;
            default:
                break;
        }
    }

    public static int actNmb(String s) {
        String[] actNmb = {"id", "+", "*", "(", ")", "$"};
        for (int i = 0; i < actNmb.length; i++) {
            if (actNmb[i].equals(s)) {
                return i;
            }
        }
        return 0;
    }

    public static String printAction(String s) {
        if (s.equals("")) {
            return "ERROR";
        }
        if (s.equals("ACCEPT")) {
            return "Accept";
        }
        if (s.charAt(0) == 'S') {
            return "Shift " + String.valueOf(s.charAt(1));
        } else if (s.charAt(0) == 'R') {
            return "Reduce " + String.valueOf(s.charAt(1));
        }
        return "";
    }

    //stack
    public static String toString1(String[] c) {
        String a = "";
        for (int i = 0; i < c.length; i++) {
            a += String.valueOf(c[i]);
        }
        return a;
    }

    //input
    public static String toString2(String[] c) {
        String a = "";
        for (int i = 0; i < c.length - 1; i++) {
            a += String.valueOf(c[i]) + " ";
        }
        a += String.valueOf(c[c.length - 1]);
        return a;
    }
}