package com.example.advquerying;

import com.example.advquerying.entities.Size;
import com.example.advquerying.services.IngredientService;
import com.example.advquerying.services.ShampooService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

@Component
public class ConsoleRunner implements CommandLineRunner {
    private final Scanner scanner = new Scanner(System.in);
    private final ShampooService shampooService;
    private final IngredientService ingredientService;

    public ConsoleRunner(ShampooService shampooService, IngredientService ingredientService) {
        this.shampooService = shampooService;
        this.ingredientService = ingredientService;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Choose exercise number from 1 to 11: ");
        int num = Integer.parseInt(scanner.nextLine());

        switch (num) {
            case 1:
                exOne();
                break;
            case 2:
                exTwo();
                break;
            case 3:
                exThree();
                break;
            case 4:
                exFour();
                break;
            case 5:
                exFive();
                break;
            case 6:
                exSix();
                break;
            case 7:
                exSeven();
                break;
            case 8:
                exEight();
                break;
            case 9:
                exNine();
                break;
            case 10:
                exTen();
                break;
            case 11:
                exEleven();
                break;
        }
    }

    private void exEleven() {
        System.out.println("Enter name:");
        String name = scanner.nextLine();
        System.out.println
                (ingredientService.updateIngredientsPriceBy10PercentWithName(name));
    }

    private void exTen() {
        System.out.println
                (ingredientService.updateIngredientsPriceBy10Percent());
    }

    private void exNine() {
        System.out.println("Enter name:");
        String name = scanner.nextLine();

        int i = ingredientService.deleteIngredientByName(name);
        System.out.println(i);
    }

    private void exEight() {
        System.out.println("Enter count:");
        int count = Integer.parseInt(scanner.nextLine());

        shampooService.findAllByIngredientsCountLessThan(count)
                .forEach(s -> System.out.println(s.getBrand()));
    }

    private void exSeven() {
        System.out.println("Enter ingredients: ");
        Set<String> ing =
                Set.of(scanner.nextLine().split("\\s+"));

        shampooService.findAllByIngredients(ing)
                .forEach(s -> System.out.println(s.getBrand()));
    }

    private void exSix() {
        System.out.println("Enter price:");
        BigDecimal price = new BigDecimal(scanner.nextLine());

        System.out.println(shampooService.countAllByPriceLessThan(price));
    }

    private void exFive() {
        System.out.println("Enter ingredients: ");
        List<String> names =
                Arrays.asList(scanner.nextLine().split("\\s+"));

        ingredientService.findAllByNameInOrderByPrice(names)
                .forEach(i -> System.out.println(i.getName()));
    }

    private void exFour() {
        System.out.println("Enter name:");
        String name = scanner.nextLine();

        ingredientService.findAllByNameStartingWith(name)
                .forEach(i -> System.out.println(i.getName()));
    }

    private void exThree() {
        System.out.println("Enter price:");
        BigDecimal price = new BigDecimal(scanner.nextLine());

        shampooService.findAllByPriceGreaterThanOrderByPriceDesc(price)
                .forEach(System.out::println);
    }

    private void exTwo() {
        System.out.println("Enter size:");
        String sizeInput = scanner.nextLine();
        Size size = Size.valueOf(sizeInput.toUpperCase());

        System.out.println("Enter label id:");
        Long labelId = Long.parseLong(scanner.nextLine());

        shampooService.findAllBySizeOrLabelIdOrderByPriceAsc(size, labelId)
                .forEach(System.out::println);
    }

    private void exOne() {
        System.out.println("Enter size:");
        String sizeInput = scanner.nextLine();
        Size size = Size.valueOf(sizeInput.toUpperCase());

        shampooService.findAllBySizeOrderById(size)
                .forEach(System.out::println);

    }
}
