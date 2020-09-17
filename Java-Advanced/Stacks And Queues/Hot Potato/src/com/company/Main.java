package com.company;

import java.util.ArrayDeque;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] input = scanner.nextLine().split("\\s+");

        ArrayDeque<String> names = new ArrayDeque<>();

        for (String s : input) {
            names.offer(s);
        }

        int n = Integer.parseInt(scanner.nextLine());

        while (names.size() > 1) {

            for (int i = 0; i < n - 1; i++) {
                names.offer(names.poll());
            }

            System.out.printf("Removed %s%n", names.poll());
        }

        System.out.printf("Last is %s%n", names.poll());
    }
}
