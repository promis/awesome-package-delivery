package my.awesome.delivery;

import my.awesome.delivery.service.InputService;

import java.util.Map;
import java.util.Scanner;
import java.util.TimerTask;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 * Worker to process the input
 *
 * @author Mykhailo Prolagaiev
 *
 */
public class InputWorker extends TimerTask {

    private Map<Integer, Double> postalCodeWeights;
    private InputService inputService = new InputService();

    public InputWorker(Map<Integer, Double> postalCodeWeights) {
        this.postalCodeWeights = postalCodeWeights;
    }

    public void run() {
        process(postalCodeWeights);
    }

    private void process(Map<Integer, Double> postalCodeWeights) {
        try(Scanner scanner = new Scanner(System.in)) {
            while (true) {
                inputService.processLine(scanner.nextLine(), postalCodeWeights);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
