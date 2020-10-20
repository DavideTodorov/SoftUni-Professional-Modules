import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int presentsCount = Integer.parseInt(scanner.nextLine());
        int squareMatrixSize = Integer.parseInt(scanner.nextLine());

        String[][] matrix = new String[squareMatrixSize][];

        for (int i = 0; i < squareMatrixSize; i++) {
            matrix[i] = scanner.nextLine().split("\\s+");
        }

        int santaRow = 0;
        int santaCol = 0;
        int niceKids = 0;


        boolean isFound = false;
        for (int i = 0; i < squareMatrixSize; i++) {
            for (int j = 0; j < squareMatrixSize; j++) {
                if (!isFound) {
                    if (matrix[i][j].equals("S")) {
                        santaRow = i;
                        santaCol = j;
                        isFound = true;
                    }
                }
                if (matrix[i][j].equals("V")) {
                    niceKids++;
                }
            }
        }

        int niceKidsCount = niceKids;

        String input = scanner.nextLine();
        while (!"Christmas morning".equals(input)) {

            switch (input) {
                case "up":
                    int oldSantaRow = santaRow;
                    int oldSantaCol = santaCol;

                    if (isInBounds(--santaRow, santaCol, matrix)) {
                        matrix[oldSantaRow][oldSantaCol] = "-";

                        if (matrix[santaRow][santaCol].equals("V")) {
                            presentsCount--;
                            niceKids--;
                            if (presentsCount <= 0) {
                                System.out.println("Santa ran out of presents.");
                                printEnd(niceKids, niceKidsCount, matrix);

                                return;
                            }
                        } else if (matrix[santaRow][santaCol].equals("C")) {

                            oldSantaRow = santaRow;
                            oldSantaCol = santaCol;
                            //UP
                            if (isInBounds(--santaRow, santaCol, matrix)) {
                                if (matrix[santaRow][santaCol].equals("V")) {
                                    matrix[santaRow][santaCol] = "-";

                                    presentsCount--;
                                    niceKids--;
                                    if (ifEquals(presentsCount, matrix, santaRow, santaCol, niceKids, niceKidsCount)) {
                                        return;
                                    }
                                } else if (matrix[santaRow][santaCol].equals("X")) {
                                    matrix[santaRow][santaCol] = "-";

                                    presentsCount--;
                                    if (ifEquals(presentsCount, matrix, santaRow, santaCol, niceKids, niceKidsCount)) {
                                        return;
                                    }
                                }
                            }

                            santaRow = oldSantaRow;
                            santaCol = oldSantaCol;
                            //DOWN
                            if (isInBounds(++santaRow, santaCol, matrix)) {
                                if (matrix[santaRow][santaCol].equals("V")) {
                                    matrix[santaRow][santaCol] = "-";

                                    presentsCount--;
                                    niceKids--;
                                    if (ifEquals(presentsCount, matrix, santaRow, santaCol, niceKids, niceKidsCount)) {
                                        return;
                                    }
                                } else if (matrix[santaRow][santaCol].equals("X")) {
                                    matrix[santaRow][santaCol] = "-";

                                    presentsCount--;
                                    if (ifEquals(presentsCount, matrix, santaRow, santaCol, niceKids, niceKidsCount)) {
                                        return;
                                    }
                                }
                            }

                            santaRow = oldSantaRow;
                            santaCol = oldSantaCol;
                            //LEFT
                            if (isInBounds(santaRow, --santaCol, matrix)) {
                                if (matrix[santaRow][santaCol].equals("V")) {
                                    matrix[santaRow][santaCol] = "-";

                                    presentsCount--;
                                    niceKids--;
                                    if (ifEquals(presentsCount, matrix, santaRow, santaCol, niceKids, niceKidsCount)) {
                                        return;
                                    }
                                } else if (matrix[santaRow][santaCol].equals("X")) {
                                    matrix[santaRow][santaCol] = "-";

                                    presentsCount--;
                                    if (ifEquals(presentsCount, matrix, santaRow, santaCol, niceKids, niceKidsCount)) {
                                        return;
                                    }
                                }
                            }

                            santaRow = oldSantaRow;
                            santaCol = oldSantaCol;
                            //RIGHT
                            if (isInBounds(santaRow, ++santaCol, matrix)) {
                                if (matrix[santaRow][santaCol].equals("V")) {
                                    matrix[santaRow][santaCol] = "-";

                                    presentsCount--;
                                    niceKids--;
                                    if (ifEquals(presentsCount, matrix, santaRow, santaCol, niceKids, niceKidsCount)) {
                                        return;
                                    }
                                } else if (matrix[santaRow][santaCol].equals("X")) {
                                    presentsCount--;
                                    if (ifEquals(presentsCount, matrix, santaRow, santaCol, niceKids, niceKidsCount)) {
                                        return;
                                    }
                                }
                            }

                            santaRow = oldSantaRow;
                            santaCol = oldSantaCol;
                        }

                        matrix[santaRow][santaCol] = "S";

                    } else {
                        System.out.println("Santa ran out of presents.");
                        printEnd(niceKids, niceKidsCount, matrix);

                        return;
                    }
                    break;

                case "down":
                    oldSantaRow = santaRow;
                    oldSantaCol = santaCol;

                    if (isInBounds(++santaRow, santaCol, matrix)) {
                        matrix[oldSantaRow][oldSantaCol] = "-";

                        if (matrix[santaRow][santaCol].equals("V")) {
                            presentsCount--;
                            niceKids--;
                            if (ifEquals(presentsCount, matrix, santaRow, santaCol, niceKids, niceKidsCount)) {
                                return;
                            }
                        } else if (matrix[santaRow][santaCol].equals("C")) {

                            oldSantaRow = santaRow;
                            oldSantaCol = santaCol;
                            //UP
                            if (isInBounds(--santaRow, santaCol, matrix)) {
                                if (matrix[santaRow][santaCol].equals("V")) {
                                    matrix[santaRow][santaCol] = "-";

                                    presentsCount--;
                                    niceKids--;
                                    if (ifEquals(presentsCount, matrix, santaRow, santaCol, niceKids, niceKidsCount)) {
                                        return;
                                    }
                                } else if (matrix[santaRow][santaCol].equals("X")) {
                                    matrix[santaRow][santaCol] = "-";

                                    presentsCount--;
                                    if (ifEquals(presentsCount, matrix, santaRow, santaCol, niceKids, niceKidsCount)) {
                                        return;
                                    }
                                }
                            }

                            santaRow = oldSantaRow;
                            santaCol = oldSantaCol;
                            //DOWN
                            if (isInBounds(++santaRow, santaCol, matrix)) {
                                if (matrix[santaRow][santaCol].equals("V")) {
                                    matrix[santaRow][santaCol] = "-";

                                    presentsCount--;
                                    niceKids--;
                                    if (ifEquals(presentsCount, matrix, santaRow, santaCol, niceKids, niceKidsCount)) {
                                        return;
                                    }
                                } else if (matrix[santaRow][santaCol].equals("X")) {
                                    matrix[santaRow][santaCol] = "-";

                                    presentsCount--;
                                    if (ifEquals(presentsCount, matrix, santaRow, santaCol, niceKids, niceKidsCount)) {
                                        return;
                                    }
                                }
                            }

                            santaRow = oldSantaRow;
                            santaCol = oldSantaCol;
                            //LEFT
                            if (isInBounds(santaRow, --santaCol, matrix)) {
                                if (matrix[santaRow][santaCol].equals("V")) {
                                    matrix[santaRow][santaCol] = "-";

                                    presentsCount--;
                                    niceKids--;
                                    if (ifEquals(presentsCount, matrix, santaRow, santaCol, niceKids, niceKidsCount)) {
                                        return;
                                    }
                                } else if (matrix[santaRow][santaCol].equals("X")) {
                                    matrix[santaRow][santaCol] = "-";

                                    presentsCount--;
                                    if (ifEquals(presentsCount, matrix, santaRow, santaCol, niceKids, niceKidsCount)) {
                                        return;
                                    }
                                }
                            }

                            santaRow = oldSantaRow;
                            santaCol = oldSantaCol;
                            //RIGHT
                            if (isInBounds(santaRow, ++santaCol, matrix)) {
                                if (matrix[santaRow][santaCol].equals("V")) {
                                    matrix[santaRow][santaCol] = "-";

                                    presentsCount--;
                                    niceKids--;
                                    if (ifEquals(presentsCount, matrix, santaRow, santaCol, niceKids, niceKidsCount)) {
                                        return;
                                    }
                                } else if (matrix[santaRow][santaCol].equals("X")) {
                                    matrix[santaRow][santaCol] = "-";
                                    presentsCount--;
                                    if (ifEquals(presentsCount, matrix, santaRow, santaCol, niceKids, niceKidsCount)) {
                                        return;
                                    }
                                }
                            }

                            santaRow = oldSantaRow;
                            santaCol = oldSantaCol;
                        }

                        matrix[santaRow][santaCol] = "S";

                    } else {
                        System.out.println("Santa ran out of presents.");
                        printEnd(niceKids, niceKidsCount, matrix);

                        return;
                    }
                    break;

                case "right":
                    oldSantaRow = santaRow;
                    oldSantaCol = santaCol;

                    if (isInBounds(santaRow, ++santaCol, matrix)) {
                        matrix[oldSantaRow][oldSantaCol] = "-";

                        if (matrix[santaRow][santaCol].equals("V")) {
                            presentsCount--;
                            niceKids--;
                            if (ifEquals(presentsCount, matrix, santaRow, santaCol, niceKids, niceKidsCount)) {
                                return;
                            }
                        } else if (matrix[santaRow][santaCol].equals("C")) {

                            oldSantaRow = santaRow;
                            oldSantaCol = santaCol;
                            //UP
                            if (isInBounds(--santaRow, santaCol, matrix)) {
                                if (matrix[santaRow][santaCol].equals("V")) {
                                    matrix[santaRow][santaCol] = "-";

                                    presentsCount--;
                                    niceKids--;
                                    if (ifEquals(presentsCount, matrix, santaRow, santaCol, niceKids, niceKidsCount)) {
                                        return;
                                    }
                                } else if (matrix[santaRow][santaCol].equals("X")) {
                                    matrix[santaRow][santaCol] = "-";

                                    presentsCount--;
                                    if (ifEquals(presentsCount, matrix, santaRow, santaCol, niceKids, niceKidsCount)) {
                                        return;
                                    }
                                }
                            }

                            santaRow = oldSantaRow;
                            santaCol = oldSantaCol;
                            //DOWN
                            if (isInBounds(++santaRow, santaCol, matrix)) {
                                if (matrix[santaRow][santaCol].equals("V")) {
                                    matrix[santaRow][santaCol] = "-";

                                    presentsCount--;
                                    niceKids--;
                                    if (ifEquals(presentsCount, matrix, santaRow, santaCol, niceKids, niceKidsCount)) {
                                        return;
                                    }
                                } else if (matrix[santaRow][santaCol].equals("X")) {
                                    matrix[santaRow][santaCol] = "-";

                                    presentsCount--;
                                    if (ifEquals(presentsCount, matrix, santaRow, santaCol, niceKids, niceKidsCount)) {
                                        return;
                                    }
                                }
                            }

                            santaRow = oldSantaRow;
                            santaCol = oldSantaCol;
                            //LEFT
                            if (isInBounds(santaRow, --santaCol, matrix)) {
                                if (matrix[santaRow][santaCol].equals("V")) {
                                    matrix[santaRow][santaCol] = "-";

                                    presentsCount--;
                                    niceKids--;
                                    if (ifEquals(presentsCount, matrix, santaRow, santaCol, niceKids, niceKidsCount)) {
                                        return;
                                    }
                                } else if (matrix[santaRow][santaCol].equals("X")) {
                                    matrix[santaRow][santaCol] = "-";

                                    presentsCount--;
                                    if (ifEquals(presentsCount, matrix, santaRow, santaCol, niceKids, niceKidsCount)) {
                                        return;
                                    }
                                }
                            }

                            santaRow = oldSantaRow;
                            santaCol = oldSantaCol;
                            //RIGHT
                            if (isInBounds(santaRow, ++santaCol, matrix)) {
                                if (matrix[santaRow][santaCol].equals("V")) {
                                    matrix[santaRow][santaCol] = "-";

                                    presentsCount--;
                                    niceKids--;
                                    if (ifEquals(presentsCount, matrix, santaRow, santaCol, niceKids, niceKidsCount)) {
                                        return;
                                    }
                                } else if (matrix[santaRow][santaCol].equals("X")) {
                                    matrix[santaRow][santaCol] = "-";
                                    presentsCount--;
                                    if (ifEquals(presentsCount, matrix, santaRow, santaCol, niceKids, niceKidsCount)) {
                                        return;
                                    }
                                }
                            }

                            santaRow = oldSantaRow;
                            santaCol = oldSantaCol;
                        }

                        matrix[santaRow][santaCol] = "S";

                    } else {
                        System.out.println("Santa ran out of presents.");
                        printEnd(niceKids, niceKidsCount, matrix);

                        return;
                    }
                    break;

                case "left":
                    oldSantaRow = santaRow;
                    oldSantaCol = santaCol;

                    if (isInBounds(santaRow, --santaCol, matrix)) {
                        matrix[oldSantaRow][oldSantaCol] = "-";

                        if (matrix[santaRow][santaCol].equals("V")) {
                            presentsCount--;
                            niceKids--;
                            if (ifEquals(presentsCount, matrix, santaRow, santaCol, niceKids, niceKidsCount)) {
                                return;
                            }
                        } else if (matrix[santaRow][santaCol].equals("C")) {

                            oldSantaRow = santaRow;
                            oldSantaCol = santaCol;
                            //UP
                            if (isInBounds(--santaRow, santaCol, matrix)) {
                                if (matrix[santaRow][santaCol].equals("V")) {
                                    matrix[santaRow][santaCol] = "-";

                                    presentsCount--;
                                    niceKids--;
                                    if (ifEquals(presentsCount, matrix, santaRow, santaCol, niceKids, niceKidsCount)) {
                                        return;
                                    }
                                } else if (matrix[santaRow][santaCol].equals("X")) {
                                    matrix[santaRow][santaCol] = "-";

                                    presentsCount--;
                                    if (ifEquals(presentsCount, matrix, santaRow, santaCol, niceKids, niceKidsCount)) {
                                        return;
                                    }
                                }
                            }

                            santaRow = oldSantaRow;
                            santaCol = oldSantaCol;
                            //DOWN
                            if (isInBounds(++santaRow, santaCol, matrix)) {
                                if (matrix[santaRow][santaCol].equals("V")) {
                                    matrix[santaRow][santaCol] = "-";

                                    presentsCount--;
                                    niceKids--;
                                    if (ifEquals(presentsCount, matrix, santaRow, santaCol, niceKids, niceKidsCount)) {
                                        return;
                                    }
                                } else if (matrix[santaRow][santaCol].equals("X")) {
                                    matrix[santaRow][santaCol] = "-";

                                    presentsCount--;
                                    if (ifEquals(presentsCount, matrix, santaRow, santaCol, niceKids, niceKidsCount)) {
                                        return;
                                    }
                                }
                            }

                            santaRow = oldSantaRow;
                            santaCol = oldSantaCol;
                            //LEFT
                            if (isInBounds(santaRow, --santaCol, matrix)) {
                                if (matrix[santaRow][santaCol].equals("V")) {
                                    matrix[santaRow][santaCol] = "-";

                                    presentsCount--;
                                    niceKids--;
                                    if (ifEquals(presentsCount, matrix, santaRow, santaCol, niceKids, niceKidsCount)) {
                                        return;
                                    }
                                } else if (matrix[santaRow][santaCol].equals("X")) {
                                    matrix[santaRow][santaCol] = "-";

                                    presentsCount--;
                                    if (ifEquals(presentsCount, matrix, santaRow, santaCol, niceKids, niceKidsCount)) {
                                        return;
                                    }
                                }
                            }

                            santaRow = oldSantaRow;
                            santaCol = oldSantaCol;
                            //RIGHT
                            if (isInBounds(santaRow, ++santaCol, matrix)) {
                                if (matrix[santaRow][santaCol].equals("V")) {
                                    matrix[santaRow][santaCol] = "-";
                                    presentsCount--;
                                    niceKids--;
                                    if (ifEquals(presentsCount, matrix, santaRow, santaCol, niceKids, niceKidsCount)) {
                                        return;
                                    }
                                } else if (matrix[santaRow][santaCol].equals("X")) {
                                    matrix[santaRow][santaCol] = "-";
                                    presentsCount--;
                                    if (ifEquals(presentsCount, matrix, santaRow, santaCol, niceKids, niceKidsCount)) {
                                        return;
                                    }
                                }
                            }

                            santaRow = oldSantaRow;
                            santaCol = oldSantaCol;
                        }

                        matrix[santaRow][santaCol] = "S";

                    } else {
                        System.out.println("Santa ran out of presents.");
                        printEnd(niceKids, niceKidsCount, matrix);

                        return;
                    }
                    break;
            }


            input = scanner.nextLine();
        }

        printEnd(niceKids, niceKidsCount, matrix);
    }

    private static boolean ifEquals(int presentsCount, String[][] matrix, int santaRow, int santaCol, int niceKids, int niceKidsCount) {
        if (presentsCount <= 0) {
            System.out.println("Santa ran out of presents.");
            printEnd(niceKids, niceKidsCount, matrix);

            return true;
        }
        return false;
    }

    private static boolean isInBounds(int santaRow, int santaCol, String[][] matrix) {
        if (santaRow >= 0 && santaRow < matrix.length &&
                santaCol >= 0 && santaCol < matrix.length) {
            return true;
        }
        return false;
    }

    public static void printEnd(int niceKids, int niceKidsCount, String[][] matrix) {
        for (String[] strings : matrix) {
            for (String string : strings) {
                System.out.print(string + " ");
            }
            System.out.println();
        }

        if (niceKids <= 0) {
            System.out.printf("Good job, Santa! %d happy nice kid/s.\n", niceKidsCount);
        } else {
            System.out.printf("No presents for %d nice kid/s.\n", niceKids);
        }
    }
}