public class Task2 {
    public static void main(String[] args) {
        int [] two = {44, 32, 86, 19}; //массив для задания 2
        double [] three = {9, 2, 2, 5}; //массив для задания 3
        //Вывод результатов для каждого задания
        System.out.println(repeat("hello", 3)); //1
        System.out.println(differenceMaxMin(two)); //2
        System.out.println(isAvgWhole(three)); //3
        cumulativeSum(new int [] {3, 3, -2, 408, 3, 3}); //4
        System.out.println();
        System.out.println(getDecimalPlaces("400.2564")); //5
        System.out.println(Fibonacci(17)); //6
        System.out.println(isValid("5423")); //7
        System.out.println(isStrangePair("sparkling", "groups")); //8
        System.out.println(isPrefix("retrospect", "ret-")); //9
        System.out.println(isSuffix("vocation", "-ation")); //9
        System.out.println(boxSeq(5)); //10
    }
    public static String repeat(String a, int b) { //метод для повтора каждого символа в строке b раз
        String s = "";
        for (int i=0; i<a.length(); i++) {
            for (int j=0; j<b; j++)
                s += a.charAt(i);
        }
        return (s);
    }
    public static int differenceMaxMin(int [] a) { //возвращение разницы между максимальным и минимальным числом
        int mx = -10000;
        int mn = 10000;
        for (int i=0; i<a.length; i++) {
            if (a[i]>mx) mx = a[i];
            if (a[i]<mn) mn = a[i];
        }
        return (mx-mn);
    }
    public static boolean isAvgWhole (double [] a) { //проверка: является ли среднее значение целым
        double sum = 0;
        for (int i=0; i<a.length; i++) {
            sum += a[i]; 
        }
        if (((sum/a.length)*10)%10 == 0) return true; 
        else return false;
    }
    public static void cumulativeSum(int [] a) { //возвращение массива, в котором каждое число - сумма самого себя + всех предыдущих
        for (int i=0; i<a.length; i++) {
            int sum = 0;
            int[] b = new int[a.length];
            for (int j=0; j<=i; j++)
                sum += a[j];
            b[i] = sum;
            System.out.printf(b[i] + " ");
        }
    }
    public static int getDecimalPlaces(String a) { //возвращение числа знаков после запятой
        int n = 0;
        for (int i=0; i<a.length(); i++) {
            if (a.charAt(i) == '.') n = i;
        }
        if (n == 0) return 0; 
        else return (a.length()-(n+1));
    }
    public static int Fibonacci(int n) { //возвращение соответсвующего числа Фибоначчи
        int[] a = new int[n+1];
        a[0] = a[1] = 1;
        for (int i=2; i<=n; i++) { 
            a[i] = a[i-1] + a[i-2];
        }
        return (a[n]);
    }
    public static boolean isValid(String a) { //определение действительного почтового индекса
        int n = 0;
        for (int i=0; i<a.length(); i++) {
            if (Character.isDigit(a.charAt(i)) == false || a.length() != 5 || a.charAt(i) == ' ') n++; 
        }
        if (n == 0) return true; 
        else return false;
    }
    public static boolean isStrangePair(String a, String b) { //проверка пары строк на странную пару
        if (a == "" && b == "") return true; 
        else {
            if ((a == "" || b == "") && a != b) return false; 
            else {
                if (a.charAt(a.length()-1) == b.charAt(0) && b.charAt(b.length()-1) == a.charAt(0))
                return true; 
                else return false;
            }
        }
    }
    public static boolean isPrefix (String a, String b) { //проверка: начинается ли строка с префиксного аргумента
        int n = 0;
        for (int i=0; i<b.length()-1; i++) {
            if (a.charAt(i) == b.charAt(i)) n++;
        }
        if (n == b.length()-1) return true; 
        else return false;
    }
    public static boolean isSuffix (String a, String b) { //проверка: заканчивается ли строка аргументом суффикса
        int n = 0;
        for (int i=1; i<b.length(); i++) {
            if (a.charAt(i + a.length() - b.length()) == b.charAt(i)) n++;
        }
        if (n == b.length()-1) return true; 
        else return false;
    }
    public static int boxSeq(int n) { //возвращение кол-ва полей на заданном шаге последовательности
        int[] a = new int[n+1];
        a[0] = 0;
        for (int i=1; i<=n; i++) {
            if (i % 2 == 0) a[i] = a[i-1] - 1;
            else a[i] = a[i-1] + 3;
        }
        return (a[n]);
    }
}
