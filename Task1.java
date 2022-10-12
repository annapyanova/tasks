public class Task1 {
    public static void main(String[] args) {
        int [] nine = {1, 5, 9}; //массив для задания 9
        //Вывод результатов для каждого задания
        System.out.println(remainder(-9, 45)); //1
        System.out.println(triArea(7, 4)); //2
        System.out.println(animals(5, 2, 8)); //3
        System.out.println(profitableGamble(0.9, 3, 2)); //4
        System.out.println(operation(1, 12, 6)); //5
        System.out.println(ctoa('m')); //6
        System.out.println(addUpTo(10)); //7
        System.out.println(nextEdge(8, 10)); //8
        System.out.println(sumOfCubes(nine)); //9
        System.out.println(abcmath(42, 5, 10)); //10
    } 
    //Задание 1   
    public static int remainder(int a, int b) { //метод определения остатка от деления
        return (a%b);
    }
    //Задание 2
    public static int triArea(int a, int b) { //метод вычисления площади треугольника
        return (a*b/2);
    }
    //Задание 3
    public static int animals(int a, int b, int c) { //метод вычисления общего кол-ва ног среди всех животных
        return (2*a+4*b+4*c);
    }
    //Задание 4
    public static boolean profitableGamble(double prob, double prize, double pay) { //метод для проверки условия
        if (prob*prize > pay) return true; 
        else return false;
    }
    //Задание 5
    public static String operation(int N, int a, int b) { //метод определения математической операции
        if (a+b == N) return ("added");
        if (a-b == N) return ("subtracted");
        if (a*b == N) return ("multiplied");
        if (a/b == N) return ("divided"); else return ("none");
    }
    //Задание 6
    public static int ctoa(char a) { //метод возвращения ASCII переданного символа
        return ((int)a);
    }
    //Задание 7
    public static int addUpTo(int a) { //метод вычисления суммы всех чисел до переданного числа включительно
        int sum=0;
        for (int i=1; i<=a; i++) {
            sum += i;
        } 
        return (sum);
    }
    //Задание 8
    public static int nextEdge(int a, int b) { //метод нахождения максимального значения 3 ребра треугольника
        return (a+b-1);
    }
    //Задание 9
    public static int sumOfCubes(int[] x) { //метод вычисления суммы кубов массива чисел
        int sum = 0;
        for (int i=0; i<x.length; i++) {
            sum += Math.pow(x[i], 3);
        }
        return (sum);
    }
    //Задание 10
    public static boolean abcmath(int a, int b, int c) { //метод проверки делимости числа a, прибавленного к себе b раз, на c
        if ((Math.pow(2, b) * a) % c == 0) return true; else return false;
    }
}
