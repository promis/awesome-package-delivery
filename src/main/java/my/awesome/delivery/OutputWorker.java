package my.awesome.delivery;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TimerTask;

/**
 * Worker to process the output
 *
 * @author Mykhailo Prolagaiev
 *
 */
import static java.util.stream.Collectors.toMap;

public class OutputWorker extends TimerTask {

    private Map<Integer, Double> postalCodeWeights;

    public OutputWorker(Map<Integer, Double> postalCodeWeights) {
        this.postalCodeWeights = postalCodeWeights;
    }

    /**
     * Method to print the sorted postalCodes {@link Map} to the Console
     */
    public void run() {
        Map<Integer, Double> result = postalCodeWeights.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                        LinkedHashMap::new));
        result.entrySet().stream().forEach(e ->  System.out.println(e.getKey() + " " + e.getValue()));
    }
}
