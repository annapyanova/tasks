import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Task6 {
    public static void main(String[] args) {
        System.out.println(bell(3)); //1
        System.out.println(translateWord("flag")); //2
        System.out.println(translateSentence("I like to eat honey waffles.")); //2
        System.out.println(validColor("rgba(255,254,255)")); //3
        System.out.println(stripUrlParams("https://edabit.com?a=1&b=2&a=2", new String[]{})); //4
        System.out.println(Arrays.toString(getHashTags("Why You Will Probably Pay More for Your Christmas Tree This Year"))); //5
        System.out.println(ulam(206)); //6
        System.out.println(longestNonrepeatingSubstring("abcbdabcbb")); //7
        System.out.println(convertToRoman(39)); //8
        System.out.println( formula("6 * 4 = 24 = 20 + 4")); //9
        System.out.println(palindromeDescendant(23336014)); //10
    }

    public static int bell(int n) { //число Белла (массив из n элементов может быть разбит на непустые подмножества
        int[] sets = new int[n];
        int previous;
        int cur;
        sets[0] = 1;
        for (int i=1; i<n; ++i) {
            previous = sets[0];
            sets[0] = sets[i - 1];
            for (int j=1; j<=i; ++j) {
                cur = sets[j];
                sets[j] = sets[j-1] + previous;
                previous = cur;
            }
        }
        return sets[n - 1];
    }
    
    public static String translateWord(String a) { //перевод слова в "Поросячую латынь"
        StringBuilder res = new StringBuilder();
        StringBuilder cons = new StringBuilder();
        int k=0;
        if (isVowel(a.charAt(0))) {
            res.append(a).append("yay");
        }
        else {
            for (int i=0; i<a.length(); i++) {
                if (!isVowel(a.charAt(i))) cons.append(a.charAt(i));
                else {
                    k = i;
                    break;
                }
            }
            for (int i=k; i<a.length(); i++) {
                res.append(a.charAt(i));
            }
            res.append(cons).append("ay");
        }
        return(res.toString());
    }

    public static String translateSentence(String a) { //перевод предложения
        String newA = a.replaceAll("[.?!]", "");
        String[] words = newA.split(" ");
        StringBuilder res = new StringBuilder();
        for (String word: words) {
            res.append(translateWord(word) + " ");
        }
        res.deleteCharAt(res.length()-1); //удаление последнего пробела
        res.append(a.charAt(a.length()-1));
        return(res.toString());
    }

    public static boolean isVowel(Character a) { //проверка буквы на гласную
        String strA = a.toString().toLowerCase();
        if ("a".equals(strA) || "i".equals(strA) || "o".equals(strA) || "e".equals(strA) || "u".equals(strA))
            return true; 
        else return false;
    }

    public static boolean validColor(String s){ //верный формат параметров RGB(A)
        String[] opt = (s.substring(s.indexOf("(") + 1, s.indexOf(")"))).split(",");
        int optInt = 0;
        double optDoub = 0;
        if (s.contains("rgba") && opt.length != 4)
            return false;
        if (s.contains("rgb") && opt.length != 3)
            return false;
        for (String cur : opt) {
            if ("".equals(cur))
                return false;
            if (cur.contains("."))
                optDoub = Double.valueOf(cur);
            else optInt = Integer.valueOf(cur);
            if (!s.contains("rgba") && cur.contains("."))
                return false;
            if (optInt<0 || optInt>255)
                return false;
            if (s.contains("rgba") && (cur.contains(".") && optDoub>1.0 || (cur.contains(".") && optDoub<0)))
            return false;
        }
        return true;
    }
    
    public static String stripUrlParams(String url, String[] paramsToStrip) { //удаление повторяющихся параметров URL
        if (!url.contains("?")) 
            return url;
        else {
            StringBuilder res = new StringBuilder();
            String[] request = url.split("\\?");
            res.append(request[0]).append("?");
            String[] params = request[1].split("&");
            HashMap<String, String> map = new HashMap<>();
            for (String i : params) {
                String[] val = i.split("=");
                map.put(val[0], val[1]);
            }
            HashSet<String> noRepeat = new HashSet<>();
            noRepeat.addAll(Arrays.asList(paramsToStrip));
            int k = 1;

            for (String key : map.keySet()) {
                if(!noRepeat.contains(key)){
                    if(k > 1)
                        res.append("&");
                    res.append(key).append("=").append(map.get(key));
                    k++;
                }
            }
            return res.toString();
        } 
    }

    public static String[] getHashTags(String a) { //преобразование трёх самых длинных слов в хэштеги
        a = a.replaceAll(",", "");
        String[] arr = a.split(" ");
        int k = 0;
        int maxLenght = 0;
        StringBuilder maxWord = new StringBuilder();
        StringBuilder res = new StringBuilder();
        if (arr.length < 3) {
            for (String word : arr)
                res.append("#").append(word.toLowerCase()).append(",");
        }
        else {
            for (int i=0; i<3; i++) {
                for (int j=0; j<arr.length; j++) {
                    if (arr[j].length() != maxLenght) {
                        if(arr[j].length() >= maxLenght){
                            maxLenght = arr[j].length();
                            maxWord = new StringBuilder(arr[j].toLowerCase());
                            k = j;
                        }
                    }
                }
                arr[k] = " ";
                res.append("#").append(maxWord).append(",");
                maxLenght = 0;
            }
        }
        String[] resArr = res.toString().split(",");
        return resArr;
    }

    public static int ulam(int n) { //последовательность Улама
        ArrayList<Integer> ulam = new ArrayList<>();
        ulam.add(1);
        ulam.add(2);
        int i;
        int j;
        for (i=3, j=2; j<n; i++) {
            int m = 0;
            for (int k=0; k<ulam.size()-1; k++) {
                for (int l = k + 1; l < ulam.size(); l++) {
                    if (ulam.get(k) + ulam.get(l) == i)
                        m++;
                    if (m > 1)
                        break;
                }
                if (m > 1)
                    break;
            }
            if (m == 1) {
                ulam.add(i);
                j++;
            }
        }
        return i - 1;
    }
    
    public static String longestNonrepeatingSubstring(String a) { //самая длинная неповторяющаяся подстрока
        StringBuilder res = new StringBuilder();
        StringBuilder cur = new StringBuilder();
        for(int i=0; i<a.length(); ++i) {
            for (int j=i; j<a.length(); ++j) {
                if (!cur.toString().contains(String.valueOf(a.charAt(j))))
                    cur.append(a.charAt(j));
                else {
                    if (cur.length() > res.length())
                        res = new StringBuilder(cur);
                    cur = new StringBuilder();
                    break;
                }
            }
        }
        return (res.toString());
    }

    public static String convertToRoman(int num) { //перевод в римское число
        StringBuilder res = new StringBuilder();
        while(num != 0){
            if(num >= 1000){
                num -= 1000;
                res.append("M");
            }
            else if(num >= 900){
                num -= 900;
                res.append("CM");
            }
            else if(num >= 500){
                num -= 500;
                res.append("D");
            }
            else if(num >= 400){
                num -= 400;
                res.append("CD");
            }
            else if(num >= 100){
                num -= 100;
                res.append("C");
            }
            else if(num >= 90){
                num -= 90;
                res.append("XC");
            }
            else if(num >= 50){
                num -= 50;
                res.append("L");
            }
            else if(num >= 40){
                num -= 40;
                res.append("XL");
            }
            else if(num >= 10){
                num -= 10;
                res.append("X");
            }
            else if(num >= 9){
                num -= 9;
                res.append("IX");
            }
            else if(num >= 5){
                num -= 5;
                res.append("V");
            }
            else if(num >= 4){
                num -= 4;
                res.append("IV");
            }
            else if(num > 0){
                num -= 1;
                res.append("I");
            }
        }
        return res.toString();
    }

    public static boolean formula(String a) { //проверка правильности формулы
        String[] values = a.split("=");
        for (int i=0; i<values.length-1; i++) {
            if (calc(values[i]) != calc(values[i+1]))
                return false;
        }
        return true;
    }

    public static int calc(String a) { //выполнение арифметических операций
        String newA = a.replaceAll(" ", "");
        String[] val;
        if (newA.contains("*")) {
            val = newA.split("\\*");
            return (Integer.parseInt(val[0]) * Integer.parseInt(val[1]));
        }
        if (newA.contains("+")) {
            val = newA.split("\\+");
            return (Integer.parseInt(val[0]) + Integer.parseInt(val[1]));
        }
        if (newA.contains("/")) {
            val = newA.split("/");
            return (Integer.parseInt(val[0]) / Integer.parseInt(val[1]));
        }
        if (newA.contains("-")) {
            val = newA.split("\\-");
            return (Integer.parseInt(val[0]) - Integer.parseInt(val[1]));
        }
        else return Integer.parseInt(newA);
    }

    public static boolean palindromeDescendant(int num) { //проверка числа и его потомков на палидром
        boolean symmetrical = false;
        while (num > 9) {
            if (symmetrical(num)) {
                symmetrical = true;
                break;
            }
            num = sumDigits(num);
        }
        return symmetrical;
    }

    public static boolean symmetrical(int num) { //симметрия
        int reversenum = 0; 
        int n = num;
        if (n < 0)
            n *= -1;
        while (n != 0) {
            reversenum *= 10;
            reversenum += n%10;
            n /= 10;
        }
        return (reversenum == num);
    }
    
    public static int sumDigits(int n) { //сумма соседних цифр
        String strN = Integer.toString(n);
        StringBuilder res = new StringBuilder();
        for(int i=0; i<strN.length()-1; i+=2) {
            int cur1 = Integer.parseInt(Character.toString(strN.charAt(i)));
            int cur2 = Integer.parseInt(Character.toString(strN.charAt(i+1)));
            int sum = cur1 + cur2;
            res.append(Integer.toString(sum));
        }
        return Integer.parseInt(res.toString());
    }
}
