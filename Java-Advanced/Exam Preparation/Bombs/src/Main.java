import java.util.ArrayDeque;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayDeque<Integer> bombEffectQueue = new ArrayDeque<>();
        String[] input = scanner.nextLine().split(",\\s+");
        for (String s : input) {
            bombEffectQueue.offer(Integer.parseInt(s));
        }

        ArrayDeque<Integer> bombCasingStack = new ArrayDeque<>();
        input = scanner.nextLine().split(",\\s+");
        for (String s : input) {
            bombCasingStack.push(Integer.parseInt(s));
        }


        int cherryBombs = 0;
        int daturaBombs = 0;
        int smokeDecoyBombs = 0;

        boolean done = false;
        while (!done) {

            if (!bombEffectQueue.isEmpty() && !bombCasingStack.isEmpty()) {
                int bombEffect = bombEffectQueue.peek();
                int bombCase = bombCasingStack.peek();


                int sum = bombEffect + bombCase;

                switch (sum) {
                    case 40:
                        daturaBombs++;
                        bombEffectQueue.poll();
                        bombCasingStack.pop();
                        break;

                    case 60:
                        cherryBombs++;
                        bombEffectQueue.poll();
                        bombCasingStack.pop();
                        break;

                    case 120:
                        smokeDecoyBombs++;
                        bombEffectQueue.poll();
                        bombCasingStack.pop();
                        break;

                    default:
                        bombCase -= 5;
                        bombCasingStack.pop();
                        bombCasingStack.push(bombCase);
                        break;
                }
                if (daturaBombs >= 3 && cherryBombs >= 3 && smokeDecoyBombs >= 3) {
                    System.out.println("Bene! You have successfully filled the bomb pouch!");
                    printInFinal(bombEffectQueue, bombCasingStack
                            , cherryBombs, daturaBombs, smokeDecoyBombs);
                    done = true;
                }
            } else {
                System.out.println("You don't have enough materials to fill the bomb pouch.");
                printInFinal(bombEffectQueue, bombCasingStack,
                        cherryBombs, daturaBombs,
                        smokeDecoyBombs);
                done = true;
            }
            if (done) {
                break;
            }
        }
    }

    private static void printInFinal(ArrayDeque<Integer> bombEffectQueue,
                                     ArrayDeque<Integer> bombCasingStack,
                                     int cherryBomb,
                                     int daturaBomb,
                                     int smokeDecoyBomb) {

        if (bombEffectQueue.isEmpty()) {
            System.out.println("Bomb Effects: empty");
        } else {
            System.out.print("Bomb Effects: ");
            for (int i = 0; i < bombEffectQueue.size(); i++) {
                if (i == bombEffectQueue.size() - 1) {
                    System.out.print(bombEffectQueue.poll());
                } else {
                    System.out.print(bombEffectQueue.poll() + ", ");
                    i--;
                }
            }
            System.out.println();
        }

        if (bombCasingStack.isEmpty()) {
            System.out.println("Bomb Casings: empty");
        } else {
            System.out.print("Bomb Casings: ");
            for (int i = 0; i < bombCasingStack.size(); i++) {
                if (i == bombCasingStack.size() - 1) {
                    System.out.print(bombCasingStack.pop());
                } else {
                    System.out.print(bombCasingStack.pop() + ", ");
                    i--;
                }
            }
            System.out.println();
        }

        System.out.printf("Cherry Bombs: %d\n" +
                "Datura Bombs: %d\n" +
                "Smoke Decoy Bombs: %d\n", cherryBomb, daturaBomb, smokeDecoyBomb);
    }
}
