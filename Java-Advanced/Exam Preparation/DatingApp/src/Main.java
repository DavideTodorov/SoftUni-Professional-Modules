import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayDeque<Integer> maleStack = new ArrayDeque<>();

        Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .forEach(maleStack::push);


        ArrayDeque<Integer> femaleQueue = Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayDeque::new));

        int countOfMatches = 0;

        while (!femaleQueue.isEmpty() && !maleStack.isEmpty()) {
            int currFemaleNumber = femaleQueue.poll();
            int currMaleNumber = maleStack.pop();

            if (currFemaleNumber <= 0 && currMaleNumber <= 0) {
                continue;
            }

            if (currFemaleNumber <= 0) {
                maleStack.push(currMaleNumber);
                continue;
            }

            if (currMaleNumber <= 0) {
                femaleQueue.offer(currFemaleNumber);
                continue;
            }

            if (currFemaleNumber % 25 == 0 && currMaleNumber % 25 == 0){
                femaleQueue.poll();
                maleStack.pop();
                continue;
            }

            if (currFemaleNumber % 25 == 0) {
                femaleQueue.poll();
                maleStack.push(currMaleNumber);
                continue;
            }

            if (currMaleNumber % 25 == 0) {
                maleStack.pop();
                femaleQueue.offer(currFemaleNumber);
                continue;
            }

            if (currFemaleNumber == currMaleNumber) {
                countOfMatches++;
            } else {
                maleStack.push(currMaleNumber - 2);
            }


        }

        System.out.println(String.format("Matches: %d", countOfMatches));

        StringBuilder maleSB = new StringBuilder().append("Males left: ");
        if (maleStack.isEmpty()) {
            maleSB.append("none");
            System.out.println(maleSB.toString());
        } else {
            while (!maleStack.isEmpty()) {
                maleSB.append(maleStack.pop()).append(", ");
            }
            System.out.println(maleSB.toString().substring(0, maleSB.lastIndexOf(", ")));
        }



        StringBuilder femaleSB = new StringBuilder().append("Females left: ");
        if (femaleQueue.isEmpty()) {
            femaleSB.append("none");
            System.out.println(femaleSB.toString());

        } else {
            while (!femaleQueue.isEmpty()) {
                femaleSB.append(femaleQueue.poll()).append(", ");
            }
            System.out.println(femaleSB.toString().substring(0, femaleSB.lastIndexOf(", ")));
        }

    }
}
