import java.util.Scanner;

public class Crypto {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter text: ");
        String text = input.nextLine();

        System.out.print("Enter shift value: ");
        int num = input.nextInt();

        System.out.print("Enter group number: ");
        int groupNum = input.nextInt();

        // encrypt string
        String encrypted = encryptString(text, num, groupNum);
        System.out.println(encrypted);

        // decrypt string
        String decrypted = decryptString(encrypted, num);
        System.out.println(decrypted);
    }

    // Part 1 - Normalize Text
    public static String normalizeText(String text) {
        text = text.replaceAll(" ", ""); // remove whitespaces
        text = text.replaceAll("[^a-zA-Z ]", ""); // remove punctuations
        text = text.toUpperCase(); // lower case to upper case
        return text;
    }


    // Part 2 - Caesar Cipher
    public static String caesarify(String normT, int num) {
        String result = "";
        String shiftedABC = shiftAlphabet(num); // shifted alphabet based on num

        for (int i = 0; i < normT.length(); i++) {
            result += shiftedABC.charAt(normT.charAt(i) - 65);
        }

        return result;
    }

    // given function "shiftAlphabet"
    public static String shiftAlphabet(int shift) {
        int start = 0;
        if (shift < 0) {
            start = (int) 'Z' + shift + 1;
        } else {
            start = 'A' + shift;
        }
        String result = "";
        char currChar = (char) start;
        for(; currChar <= 'Z'; ++currChar) {
            result = result + currChar;
        }
        if(result.length() < 26) {
            for(currChar = 'A'; result.length() < 26; ++currChar) {
                result = result + currChar;
            }
        }
        return result;
    }

    // Part 3 - Codegroups
    public static String groupify(String shiftedT, int groupNum) {
        if (groupNum == 0)
            return shiftedT;

        String result = "";
        String temp = ""; // to keep a temporary string in case it needs to be padded with x's
        int j = 0;
        for (int i = 0; i < shiftedT.length(); i++) {
            // if j is equal to group number, add space, reset temp to null and reset j to 0
            if (j == groupNum) {
                result += " ";
                temp = "";
                j = 0;
            }
            result += shiftedT.charAt(i);
            temp += shiftedT.charAt(i);
            j++;
        }
        // pad with x's if necessary
        if (temp.length() != groupNum) {
            for (int i = temp.length(); i < groupNum; i++) {
                result += "x";
            }
        }
        return result;
    }

    // Part 4 - Putting it all together
    public static String encryptString(String text, int num, int groupNum) {
        String normT = normalizeText(text);
        String shiftedT = caesarify(normT, num);
        String groupedT = groupify(shiftedT, groupNum);
        return groupedT;
    }

    // Part 5 - Decrypt
    public static String ungroupify(String groupedT) {
        String result = "";
        for (int i = 0; i < groupedT.length(); i++) {
            if (groupedT.charAt(i) == ' ' || groupedT.charAt(i) == 'x') {
                result = result;
            }
            else {
                result += groupedT.charAt(i);
            }
        }
        return result;
    }

    public static String decryptString(String encrypted, int num) {
        String ungroupedT =  ungroupify(encrypted);
        String shiftedT = caesarify(ungroupedT, -num);
        return shiftedT;
    }
}
