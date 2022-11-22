import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Task4 {
    public static void main(String[] args) {
        double [] m1 = {13.25, 15, 30, 1.5}; //массив для 4 задания
        essay(10, 7, "hello my name is Bessie and this is my essay"); //1
        System.out.println(Arrays.toString(split("((())())(()(()()))"))); //2
        System.out.println(toCamelCase("_hello_eda_bit")); //3
        System.out.println(toSnakeCase("heLloEdabit")); //3
        System.out.println(overTime(m1)); //4
        System.out.println(BMI("154 pounds", "2 meters")); //5
        System.out.println(bugger(999)); //6
        System.out.println(toStarShorthand("abbccc")); //7
        System.out.println(doesRhyme("Sam I am!", "Green eggs and ham.")); //8
        System.out.println(trouble(122345, 122345)); //9
        System.out.println(countUniqueBooks("AZYWABBCATTTA", 'A')); //10
    }
    public static void essay(int n, int k, String a) { //текстовый процессор
        String[] strings = a.split(" "); //массив подстрок
        StringBuilder str_cur = new StringBuilder(); //текущая подстрока на строке
        for (String string : strings) {
            if (string.length() + str_cur.length() > k) {
                System.out.println(str_cur);
                str_cur = new StringBuilder();
            }
            str_cur.append(string + " ");
        }
        if (str_cur.length() != 0) {
            System.out.println(str_cur);
        }
    }
    public static String[] split(String a) { //группировка скобок в кластер
        Queue<Character> hooks = new LinkedList<>();
        int k = 0;
        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < a.length(); i++) {
            char currentChar = a.charAt(i);
            if (hooks.isEmpty() && i != 0) {
                strings.add(a.substring(k, i));
                k = i;
            }
            if (hooks.peek() != null && hooks.peek() == '(' && currentChar == ')') {
                hooks.poll();
            } else {
                hooks.add(currentChar);
            }
        }
        strings.add(a.substring(k));
        return strings.toArray(new String[]{});
    }
    public static String toCamelCase(String a) { //преобразование в CamelCase
        StringBuilder new_str = new StringBuilder();
        if (a.charAt(0) != '_') new_str.append(a.charAt(0));
        for (int i=1; i<a.length(); i++) {
            if (a.charAt(i) != '_' && a.charAt(i-1) != '_') new_str.append(a.charAt(i));
            if (a.charAt(i-1) == '_') new_str.append((char)((int)a.charAt(i) - 32));
        } 
        return (new_str.toString());
    }
    public static String toSnakeCase(String a) { //преобразование в snake_case
        StringBuilder new_str = new StringBuilder();
        for (int i=0; i<a.length(); i++) {
            if ((int)a.charAt(i) >= 65 && (int)a.charAt(i) <= 90) new_str.append("_" + (char)((int)a.charAt(i) + 32)) ;
            else new_str.append(a.charAt(i));
        }
        return (new_str.toString());
    }
    public static String overTime(double [] a) { //вычисление оплаты за обычную и сверхурочную работу
        double sum = 0;
        if (a[0]>a[1]) return ("Error");
        if (a[0]<=17 && a[1]<=17) sum = (a[1]-a[0])*a[2];
        if (a[0]>17 && a[1]>17) sum = (a[1]-a[0])*a[2]*a[3];
        if (a[0]<=17 && a[1]>17) sum = (17-a[0])*a[2] + (a[1]-17)*a[2]*a[3];
        return ("$" + String.format("%.2f", sum));
    }
    public static String BMI(String w, String h){ //вычисление индекса массы тела
        StringBuilder weight = new StringBuilder();
        StringBuilder hight = new StringBuilder();
        double k = 1;
        double m = 1;
        for (int i=0; i<w.indexOf(" ")+2; i++) {
            if (Character.isDigit(w.charAt(i)) || w.charAt(i) == '.') weight.append(w.charAt(i));
            if (w.charAt(i) == ' ' && w.charAt(i+1) == 'p') k = 0.454;
        }
        for (int i=0; i<h.indexOf(" ")+2; i++) {
            if (Character.isDigit(h.charAt(i)) || h.charAt(i) == '.') hight.append(h.charAt(i));
            if (h.charAt(i) == ' ' && h.charAt(i+1) == 'i') m = 1/39.37;
        }
        double bmi = k * Double.valueOf(weight.toString())/(m * Double.valueOf(hight.toString()) * m * Double.valueOf(hight.toString()));
        if (bmi < 18.5) return (String.format("%.1f", bmi) + " Underweight");
        if (bmi >= 18.5 && bmi < 25) return (String.format("%.1f", bmi) + " Normal weight");
        else return (String.format("%.1f", bmi) + " Overweight");
    }
    public static int bugger(int a) { //кол-во умножений цифр числа, пока в результате не 1 цифра
        int n = 0;
        int m = a;
        while (String.valueOf(m).length() != 1) {
            int p = 1;
            while (m > 1) {
                p *= m%10;
                m /= 10;
            }
            m = p;
            n++;
        }
        return (n);
    }
    public static String toStarShorthand(String a) { //символ повторяется n раз => *n
        StringBuilder new_a = new StringBuilder();
        for (int i=0; i<a.length(); i++) {
            int n = 1;
            for (int j=i+1; j<a.length(); j++) {
                if (a.charAt(i) == a.charAt(j)) {
                    n++;
                    i++;
                }  
            }
            if (n>1) new_a.append(a.charAt(i) + "*" + String.valueOf(n));
            else new_a.append(a.charAt(i));
        }
        return (new_a.toString());
    }
    public static boolean doesRhyme(String a, String b) { //проверка на рифму
        String a1 = a.toLowerCase();
        String b1 = b.toLowerCase();
        StringBuilder new_a = new StringBuilder();
        StringBuilder new_b = new StringBuilder();
        for (int i=a1.length()-1; i>0; i--) {
            if (a1.charAt(i) != ' ') {
                if (a1.charAt(i) == 'a' || a1.charAt(i) == 'u' || a1.charAt(i) == 'e' || a1.charAt(i) == 'o' || a1.charAt(i) == 'i')
                    new_a.append(a1.charAt(i));
            }
            else break;
        }
        for (int i=b1.length()-1; i>0; i--) {
            if (b1.charAt(i) != ' ') {
                if (b1.charAt(i) == 'a' || b1.charAt(i) == 'u' || b1.charAt(i) == 'e' || b1.charAt(i) == 'o' || b1.charAt(i) == 'i')
                    new_b.append(b1.charAt(i));
            }
            else break;
        }
        return new_a.toString().equals(new_b.toString());
    }
    public static boolean trouble(long num1, long num2) { //если число из num1 повторяется в любом месте num2 3 раза
        String n1 = String.valueOf(num1);
        String n2 = String.valueOf(num2);
        char h = 'n';
        for (int i=0; i<n1.length()-2; i++) {
            if (n1.charAt(i) == n1.charAt(i+1) && n1.charAt(i+1) == n1.charAt(i+2)) {
                h = n1.charAt(i);
                break;
            }
        }
        if (h != 'n') {
            for (int i=0; i<n2.length()-1; i++) {
                if (n2.charAt(i) == n2.charAt(i+1) && n2.charAt(i) == h) 
                    return true;
            }
        }
        return false;
    }
    public static int countUniqueBooks(String books, char bookend) { //общее кол-во уникальных символов между всеми парами bookend
        Set<Character> res = new HashSet<>();
        boolean between = false;
        for (char i:books.toCharArray()) {
            if (i == bookend) between = !between;
            else {
                if (between) res.add(i);
            }
        }
        return res.size();
    }
}