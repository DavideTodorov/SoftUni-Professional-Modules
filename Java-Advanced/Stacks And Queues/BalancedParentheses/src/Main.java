import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] parentheses = scanner.nextLine().split("");


        boolean isBalanced = true;
        for (int i = 0; i < parentheses.length / 2; i++) {
            char currParenthesis = parentheses[i].charAt(0);
            char toMatch = 'a';

            if (currParenthesis == '(') {
                toMatch = ')';
            } else if (currParenthesis == '{') {
                toMatch = '}';
            } else if (currParenthesis == '[') {
                toMatch = ']';
            }

            char parenthesisToCheck = parentheses[parentheses.length - 1 - i].charAt(0);

            if (parenthesisToCheck != toMatch){
                isBalanced = false;
                break;
            }
        }

        if (isBalanced) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}
