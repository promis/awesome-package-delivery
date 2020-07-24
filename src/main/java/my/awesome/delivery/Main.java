package my.awesome.delivery;

import my.awesome.delivery.service.InputService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * The entry point to tha application
 * Application reads init file if file exists and starts two threads
 * first thread is to read and process the input, second one is to process the output.
 *
 * @author Mykhailo Prolagaiev
 *
 */
public class Main {

    public static void main(String[] args) {
        Map<Integer, Double> postalCodeWeights = new ConcurrentHashMap();
        if(args != null && args.length > 0 ) {
            new InputService().init(args[0], postalCodeWeights);
        }
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
        executor.schedule(new InputWorker(postalCodeWeights), 0, TimeUnit.SECONDS);
        executor.scheduleAtFixedRate(new OutputWorker(postalCodeWeights), 0, 1, TimeUnit.MINUTES);
    }
}
