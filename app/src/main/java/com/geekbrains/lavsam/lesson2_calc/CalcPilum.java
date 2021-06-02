package com.geekbrains.lavsam.lesson2_calc;

public class CalcPilum {
    // https://habr.com/ru/post/263775/
    // https://ideone.com/BnUec4

    private static String Lex[];
    private static int ptrL;

    private static String opStack[];
    private static int ptrOp;

    private static String valStack[];
    private static int ptrVal;

    private static double resultExec;

    public CalcPilum() {
        opStack = new String[2000];
        ptrOp = 0;
        valStack = new String[2000];
        ptrVal = 0;
    }

    double Calculate(String F) {
        int i;
        String curr, top;

//        Init();
        parse(F);

        for (i = 0; i <= ptrL; i++) {
            curr = Lex[i];
            switch (curr) {
                case "(":
                    pushOp(curr);
                    break;
                case "+":
                case "-":
                case "*":
                case "/":
                case "^":
                    if (isEmptyOp()) {
                        pushOp(curr);
                        break;
                    }
                    top = peekOp();
                    if (Prty(curr) > Prty(top)) {
                        pushOp(curr);
                        break;
                    } else {
                        exec();
                        pushOp(curr);
                        break;
                    }
                case ")":
                    while (true) {
                        top = peekOp();
                        if (top.equals("(")) {
                            top = popOp();
                            break;
                        }
                        exec();
                    }
                    break;
                default:
                    pushVal(curr);
            }
        }

        while (!isEmptyOp()) {
            exec();
        }
        return resultExec;
    }

    private static void parse(String Formula) {
        char s;
        int i;
        String Tmp = "";
        Lex = new String[200];
        for (i = 0; i < 200; i++) Lex[i] = "";
        ptrL = 0;
        for (i = 0; i < Formula.length(); i++) {
            s = Formula.charAt(i);
            switch (s) {
                case '+':
                case '-':
                case '*':
                case '^':
                case '/':
                case '(':
                case ')':
                    if (Tmp.length() > 0) {
                        Lex[ptrL++] = Tmp;
                        Tmp = "";
                    }
                    Lex[ptrL++] = "" + s;
                    break;
                case ' ':
                    break;
                default:
                    Tmp = Tmp + s;
            }
        }
        if (Tmp.length() > 0) Lex[ptrL] = Tmp;
    }

    private static void exec() {
        double a1, a2;
        String v1, v2;
        String op;

        v2 = popVal();
        v1 = popVal();
        op = popOp();

        a1 = Double.parseDouble(v1);
        a2 = Double.parseDouble(v2);

        resultExec = 0.0;

        switch (op) {
            case "+":
                resultExec = a1 + a2;
                break;
            case "-":
                resultExec = a1 - a2;
                break;
            case "*":
                resultExec = a1 * a2;
                break;
            case "/":
                resultExec = a1 / a2;
                break;
            case "^":
                resultExec = Math.pow(a1, a2);
                break;
        }

        v1 = Double.toString(resultExec);
        pushVal(v1);

    }

    private static int Prty(String o) {
        int r = -1;
        switch (o) {
            case "(":
                r = 0;
                break;
            case "+":
            case "-":
                r = 1;
                break;
            case "*":
            case "/":
                r = 2;
                break;
            case "^":
                r = 3;
        }
        return r;
    }

    private static String peekOp() {
        return opStack[ptrOp - 1];
    }

    private static boolean isEmptyOp() {
        return (ptrOp == 0);
    }

    private static boolean isEmptyVal() {
        return (ptrVal == 0);
    }

    private static void pushOp(String op) {
        opStack[ptrOp++] = op;
    }

    private static void pushVal(String v) {
        valStack[ptrVal++] = v;
    }

    private static String popOp() {
        return opStack[--ptrOp];
    }

    private static String popVal() {
        return valStack[--ptrVal];
    }
}
