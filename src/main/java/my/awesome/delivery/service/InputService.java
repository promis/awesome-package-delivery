package my.awesome.delivery.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 * Service to process the input.
 *
 * @author Mykhailo Prolagaiev
 *
 */
public class InputService{

    private Logger logger = Logger.getLogger(InputService.class.getName());
    private static Pattern WEIGHT_PATTERN = Pattern.compile("^\\d+(\\.\\d{0,3}+)?$");
    private static Pattern POST_CODE_PATTERN = Pattern.compile("\\b\\d{5}\\b");
    private static String INPUT_DELIMITER = " ";
    private static String QUIT_COMMAND = "quit";

    /**
     * Method to process the line of input and fill the postalCode weights {@link Map}
     *
     * @param input line to process
     * @param postalCodeWeights {@link Map} to fill.
     *
     */
    public void processLine(String input, Map<Integer, Double> postalCodeWeights) {
        if(input == null || input.isEmpty() || postalCodeWeights == null) {
            logger.severe("Input line must not be empty!");
            return;
        }
        String[] inputParameters = input.trim().split(INPUT_DELIMITER);
        if (isQuitCommand(inputParameters)) {
            logger.info("We are done here. Time to go home. Bye!");
            System.exit(0);
        }
        if (isInputValid(inputParameters)) {
            Double weight = Double.parseDouble(inputParameters[0]);
            Integer postalCode = Integer.parseInt(inputParameters[1]);
            Double postalCodeWeight = postalCodeWeights.get(postalCode);
            postalCodeWeight = postalCodeWeight == null ? weight : postalCodeWeight + weight;
            postalCodeWeights.put(postalCode, postalCodeWeight);
        }
    }

    /**
     * Method to initialize the postalCode weights {@link Map} with data from the initial file
     *
     * @param fileName name of the file to process
     * @param postalCodeWeights {@link Map} to fill.
     *
     */
    public void init(String fileName, Map<Integer, Double> postalCodeWeights) {
        if(fileName == null || fileName.isEmpty() || postalCodeWeights == null) {
            return;
        }
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        if(file.exists()) {
            try {
                String fileContent = new String(Files.readAllBytes(file.toPath()));
                String[] initArray = fileContent.split("\\n");
                Arrays.stream(initArray).forEach(line -> processLine(line, postalCodeWeights));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean isQuitCommand(String[] inputParameters) {
        if(inputParameters.length == 1 && QUIT_COMMAND.equals(inputParameters[0])) {
            return true;
        }
        return false;
    }

    private boolean isInputValid(String[] inputParameters) {
        if(inputParameters.length != 2) {
            return handleValidationError("Input should consist of two parameters separated by space!");
        } else if(!WEIGHT_PATTERN.matcher(inputParameters[0]).matches()) {
            return handleValidationError("Weight should be a positive number and might have up to 3 decimal places!");
        } else if(!POST_CODE_PATTERN.matcher(inputParameters[1]).matches()) {
            return handleValidationError("Post code should be a positive number of 5 digits!");
        }
        return true;
    }

    private boolean handleValidationError(String message) {
        logger.severe(message);
        return false;
    }
}
