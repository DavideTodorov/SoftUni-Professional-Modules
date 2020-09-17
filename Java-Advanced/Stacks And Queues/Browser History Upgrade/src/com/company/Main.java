package com.company;

import java.util.ArrayDeque;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayDeque<String> backStack = new ArrayDeque<>();
        ArrayDeque<String> forwardQueue = new ArrayDeque<>();

        String input = scanner.nextLine();

        while (!input.equals("Home")) {

            if (input.equals("back")) {

                if (backStack.size() > 1) {
                    forwardQueue.push(backStack.pop());
                    System.out.println(backStack.peek());
                } else {
                    System.out.println("no previous URLs");
                }

            } else if (input.equals("forward")) {

                if (!forwardQueue.isEmpty()) {
                    System.out.println(forwardQueue.peek());
                    backStack.push(forwardQueue.pop());
                } else {
                    System.out.println("no next URLs");
                }

            } else {

                if (!input.equals("")) {
                    backStack.push(input);

                    if (!forwardQueue.isEmpty()) {
                        forwardQueue.clear();
                    }

                }
                System.out.println(input);
            }

            input = scanner.nextLine();
        }
    }
}
