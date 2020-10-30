import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    private enum Light {
        RED, GREEN, YELLOW;

        private static Light[] lights = values();

        // implementation of next value
        public Light next(){
            return lights[(this.ordinal() + 1) % lights.length];
        }
    }

    private static class TrafficLight {

        private Light color;

        //constructor
        public TrafficLight(Light color){
            this.color = color;
        }
        // next color method calls next value method of light
        public void nextColor(){
            this.color = color.next();
        }

        @Override
        public String toString(){
            return color.name();
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // arraylist of trafficlights
        List<TrafficLight> trafficLights = Arrays.stream(sc.nextLine().split("\\s+"))
                .map(e -> new TrafficLight(Light.valueOf(e)))
                .collect(Collectors.toList());

        int n = Integer.parseInt(sc.nextLine());

        // cycle to switch lights and print to console
        for (int i = 0; i < n; i++) {

            trafficLights.forEach(TrafficLight::nextColor);
            System.out.println(trafficLights.toString().replaceAll("[\\[\\],]", ""));

        }
    }
}
