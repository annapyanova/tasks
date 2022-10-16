public class Task3 {
    public static void main(String[] args) {
        int [] s_1 = {9, 8, 7, 6}; //массив1 для задания 6
        int [] s_2 = {4, 4, 3, 1}; //массив2 для задания 6
        //Вывод результатов для каждого задания
        System.out.println(solutions(1, 0, 1)); //1
        System.out.println(findZip("all zip zd")); //2
        System.out.println(checkPerfect(496)); //3
        System.out.println(flipEndChars("Cat, dog and mouse.")); //4
        System.out.println(isValidHexCode("#CD5C5C")); //5
        System.out.println(same(s_1, s_2)); //6
        System.out.println(isKaprekar(297)); //7
        System.out.println(longestZero("110011001")); //8
        System.out.println(nextPrime(29)); //9
        System.out.println(rightTriangle(145, 105, 100)); //10
    }
    public static int solutions(int a, int b, int c) { //возвращение числа решений квадратного ур-ния
        double D = Math.pow(b, 2) - 4*a*c;
        if (D > 0) return (2);
        if (D == 0) return (1);
        else return (0);
    }
    public static int findZip(String a) { //возвращение позиции второго вхождения "zip" в строку
        int n = 0;
        int m = 0;
        int x = -1;
        for (int i=0; i<a.length(); i++) { //проверка на первое вхождение "zip"
            if (a.charAt(i) == 'z' && a.charAt(i+1) == 'i' && a.charAt(i+2) == 'p') {
                m = i;
                n++;  
                break;
            }
        }
        if (n == 1) { //проверка на второе вхождение "zip"
            for (int i=m+3; i<a.length(); i++) { 
                if (a.charAt(i) == 'z' && a.charAt(i+1) == 'i' && a.charAt(i+2) == 'p') {
                    x = i;
                    break;
                }
            }
        }
        return (x);
    }
    public static boolean checkPerfect(int a) { //проверка числа на совершенное (сумма цифр = числу)
        int sum = 0;
        for (int i=1; i<a; i++) {
            if (a % i == 0) sum += i;
        }
        if (a == sum) return true;
        else return false;
    }
    public static String flipEndChars(String a) { //замена в строке первого символа на последний и наоборот
        if (a.length() < 2) return("Incompatible");
        if (a.charAt(0) == a.charAt(a.length()-1)) return ("Two's a pair");
        else return (a.charAt(a.length()-1) + a.substring(1, a.length()-1) + a.charAt(0));
    }
    public static boolean isValidHexCode(String a) { //проверка строки на допустимый шестнадцатеричный код
        int n = 0;
        if (a.charAt(0) == '#' && a.length() == 7) {
            n++;
            for (int i=1; i<a.length(); i++) {
                if (Character.isDigit(a.charAt(i)) == true || a.charAt(i) == 'a' || a.charAt(i) == 'A' || a.charAt(i) == 'b' || a.charAt(i) == 'B' || a.charAt(i) == 'c' || a.charAt(i) == 'C' || a.charAt(i) == 'd' || a.charAt(i) == 'D' || a.charAt(i) == 'e' || a.charAt(i) == 'E' || a.charAt(i) == 'f' || a.charAt(i) == 'F') n++;
            }
            if (n == 7) return true;
            else return false;
        }
        else return false;
    }
    public static boolean same(int [] a, int [] b) { //проверка двух массивов на одинаковое кол-во уникальных элементов
        int n = 0;
        for (int i=0; i<a.length; i++) { //для 1-ого массива
            int m = 0;
            for (int j=0; j<i; j++) {
                if (a[i] != a[j]) {
                    m++;
                }
            }
            if (m == i) {
                n++;
                m = 0;
            } 
            else m = 0;
        }
        int k = 0;
        for (int i=0; i<b.length; i++) { //для 2-ого массива
            int p = 0;
            for (int j=0; j<i; j++) {
                if (b[i] != b[j]) {
                    p++;
                }
            }
            if (p == i) {
                k++;
                p = 0;
            } 
            else p = 0;
        }
        if (k == n) return true;
        else return false;
    }
    public static boolean isKaprekar(int a) { //проверка на число Капрекара
        int b = a*a;
        int length = String.valueOf(b).length();
        if (length % 2 != 0) { //кол-во цифр нечётное
            int left = b/(int)Math.pow(10, length/2+1);
            int right = b%(int)Math.pow(10, length/2+1);
            if (left + right == a) return true;
            else return false;
        }
        else { //кол-во цифр чётное
            int left = b/(int)Math.pow(10, length/2);
            int right = b%(int)Math.pow(10, length/2);
            if (left + right == a) return true;
            else return false;
        }
    }
    public static String longestZero(String a) { //возвращение самой длинной последовательности нулей в строке
        String l = "";
        String mx = "";
        for (int i=0; i<a.length(); i++) {
            if (a.charAt(i) == '0') l += a.charAt(i);
            else {
                if (l.length() > mx.length()) {
                    mx = l;
                    l = "";
                }
                else l = "";
            }
        }
        return mx;
    }
    public static int nextPrime(int a) { //возвращение следующего простого числа после заданного
        while (nPrime(a) == false) {
            a++;
        }
        return (a);
    }
    public static boolean nPrime(int a) { //проверка на простое число
        int n = 0;
        for (int i=2; i<a; i++) {
            if (a%i == 0) n++;
        }
        if (n == 0) return true;
        else return false;
    }
    public static boolean rightTriangle(int a, int b, int c) { //являются ли три числа рёбрами прямоугольного треугольника
        if (c*c == a*a + b*b || a*a == b*b + c*c || b*b == a*a + c*c) return true;
        else return false;
    }
}
