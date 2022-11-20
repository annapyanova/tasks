import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Set;

public class Task5 {
    public static void main(String[] args) {
        int[] one = new  int[]{72, 33, -73, 84, -12, -3, 13, -13, -68};
        String[] five = new String[]{"toe", "ocelot", "maniac"};
        System.out.println(Arrays.toString(encrypt("Sunshine")));
        System.out.println(decrypt(one));
        System.out.println(canMove("Queen", "C4", "D6"));
        System.out.println(canComplete("butl", "beautiful"));
        System.out.println(sumDigProd(1, 2, 3, 4, 5, 6));
        System.out.println(Arrays.toString(sameVowelGroup(five)));
        System.out.println(validateCard(1234567890123452L));
        System.out.println(numToEng(17));
        System.out.println(getSha256Hash("password123"));
        System.out.println(correctTitle("jOn SnoW, kINg IN thE noRth."));
        System.out.println(hexLattice(19));
    }

    public static int[] encrypt(String a) {
        int lSymb = 0;
        int[] ascii = new int[a.length()];
        for (int i=0; i<a.length(); i++) {
            ascii[i] = (int)a.charAt(i) - lSymb;
            lSymb = (int)a.charAt(i);
        }
        return ascii;
    }
    public static boolean canMove(String figure, String from, String to) {
        if (figure.equals("Pawn")) {
            if (from.charAt(0) == to.charAt(0)) {
                if (from.charAt(1) == '2' && to.charAt(1) == '4') return true;
                if (from.charAt(1) == '5' && to.charAt(1) == '7') return true;
                if (Math.abs((int)from.charAt(1) - (int)to.charAt(1)) == 1) return true;
            }
        }
        if (figure.equals("Horse")) {
            if (Math.abs((int)from.charAt(0) - (int)to.charAt(0)) == 2
                        && Math.abs((int)from.charAt(1) - (int)to.charAt(1)) == 1)
                    return true;

            if (Math.abs((int)from.charAt(1) - (int)to.charAt(1)) == 2
                    && Math.abs((int)from.charAt(0) - (int)to.charAt(0)) == 1)
                return true;
        }
        if (figure.equals("Bishop")) {
            if (Math.abs((int)from.charAt(0) - (int)to.charAt(0)) == Math.abs((int)from.charAt(1) - (int)to.charAt(1)))
                return true;
        }
        if (figure.equals("Rook")) {
            if (from.charAt(0) == to.charAt(0) || from.charAt(1) == to.charAt(1))
                return true;
        }
        if (figure.equals("Queen")) {
            if (Math.abs((int)from.charAt(0) - (int)to.charAt(0)) == Math.abs((int)from.charAt(1) - (int)to.charAt(1)))
                return true;
            if (from.charAt(0) == to.charAt(0) || from.charAt(1) == to.charAt(1))
                return true;
        }
        if (figure.equals("King")) {
            if (Math.abs((int)from.charAt(0) - (int)to.charAt(0)) <= 1
                    && Math.abs((int)from.charAt(1) - (int)to.charAt(1)) <= 1)
                return true;
        }
        return false;
    }
    public static String decrypt(int[] s) {
        char lAscii = 0;
        String symbols = "";
        for (int i = 0; i < s.length; i++) {
            symbols += (char) (lAscii + s[i]);
            lAscii = symbols.charAt(i);
        }
        return symbols;
    }
    public static boolean canComplete(String a, String b) {
        int n = 0;
        for (int i=0; i<b.length(); i++) {
            if (a.charAt(n) == b.charAt(i)) n++;
        }
        if (n == a.length()) return true;
        else return false;
    }
    public static int sumDigProd(int... a) {
        int sum = 0;
        for (int i : a) {
            sum += i;
        }
        while (sum >= 10) {
            sum = multiply(sum);
        }
        return (sum);
    }
    public static int multiply(int a) {
        int m = 1;
        while (a > 0) {
            m *= a%10;
            a /= 10;
        }
        return m;
    }
    public static String[] sameVowelGroup(String[] words) {
        Set<Character> vow1 = new HashSet<>();
        String word1 = words[0].replaceAll("[qwrtpsdfghjklzxcvbnm]", "");
        for (char charIn1 : word1.toCharArray()) {
            vow1.add(charIn1);
        }
        ArrayList<String> res = new ArrayList<>();
        res.add(words[0]);
        for (int i=1; i<words.length; i++) {
            Set<Character> vowCur = new HashSet<>();
            String wordCur = words[i].replaceAll("[qwrtpsdfghjklzxcvbnm]", "");
            for (char charInCur : wordCur.toCharArray()) {
                vowCur.add(charInCur);
            }
            if (vow1.equals(vowCur)) res.add(words[i]);
        }
        return res.toArray(new String[]{});
    }
    public static boolean validateCard(Long a) {
        if (a<Math.pow(10, 14) || a>=Math.pow(10, 20)) return false;
        int checkDigit = (int)(a % 10);
        String num = String.valueOf(a/10);
        int sum = 0;
        for (int i=num.length()-1; i>=0; i--) {
            int doubleInt = (int)num.charAt(i);
            if ((num.length() - i) % 2 != 0) {
                doubleInt *= 2;
                if (doubleInt > 9) doubleInt = doubleInt / 10 + doubleInt % 10;
            }
            sum += doubleInt;
        }
        return (10 - (sum%10)) == checkDigit;
    }
    public static String numToEng(int num) {
        String[] ones = {"zero","one","two","three","four","five","six","seven",
                "eight","nine"};
        String[] tens1 = {"ten","eleven","twelve","thirteen","fourteen","fifteen",
                "sixteen","seventeen","eighteen","nineteen"};
        String[] tens2 = {"","twenty","thirty","forty","fifty","sixty","seventy","eighty",
                "ninety"};
        String strNum = Integer.toString(num);

        switch (strNum.length()) {
            case 1:
                return ones[Integer.parseInt(strNum)];
            case 2:
                return tens1[Integer.parseInt(strNum) - 10];
            case 3:
                StringBuilder sb = new StringBuilder();
                int[] digits = new int[3];
                int k=0;
                while(num != 0) {
                    digits[k] = num % 10;
                    num /= 10;
                    k++;
                }
                sb.append(ones[digits[2]]).append(" hundred ");
                if (digits[1] == 1) {
                    int c = digits[1] + digits[2];
                    sb.append(tens1[c]);
                    return sb.toString();
                }
                if (digits[1] > 1) sb.append(tens2[digits[1]-1]).append(" ");
                if (digits[0] > 0) sb.append(ones[digits[0]]);
                return sb.toString();
            default:
                break;
        }
        return "";
    }
    public static String getSha256Hash(String str) {
        String res = "";
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(str.getBytes());
            for (int i = 0; i < hash.length; i++){
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) res += 0;
                res += hex;
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return res;
    }
    public static String correctTitle(String str) {
        String s = str.toLowerCase();
        String[] words = s.split(" ");
        String res = "";
        for (String word: words) {
            if (!word.equals("of") && !word.equals("in") && !word.equals("and") && !word.equals("the"))
                res += word.substring(0,1).toUpperCase() + word.substring(1) + " ";   
        }
        return res;
    }
    public static String hexLattice(int n) {
        int i = 0; //номер центрированного числа по порядку (0,1,2...)
        boolean isHexNum = false;
        while (3*i*(i+1)+1 <= n){
            if (3*i*(i+1)+1 == n) isHexNum = true;
            i++;
        }
        String res = "";
        if (isHexNum){
            int l = i; //для 'o'
            int m = i; //для пробелов
            String space;
            for (int j = 0; j < 2*i-1; j++){
                res += "\n";
                space = "";
                for (int k = 1; k < m; k++){
                    space +=  " ";
                }
                res += space;
                for (int k = 0; k < l; k++){
                    res +=  " o";
                }
                res += space + " ";
                l += (j < i-1) ? 1 : -1;
                m += (j < i-1) ? -1 : 1;
            }
            res = res.replaceFirst("\n", "");
            return res;
        } else return "Invalid";
    }
}
