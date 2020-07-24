package my.awesome.delivery.service;

import org.junit.Test;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.Assert.assertEquals;

public class InputServiceTest {

    private static final String CORRECT_INPUT = "3.4 08801, 3.6 08801, 2 90005, 3.77 90005, 1.33 90005, 3.333 09300";
    private static final String CORRECT_INPUT_RESULT = "{8801=7.0, 90005=7.1, 9300=3.333}";
    private static final String HIGHER_DECIMAL_PLACES_COUNT = "3.4 08801, 3.64444 08801, 2 90005, 3.77 90005, 1.33 90005, 3.333 09300";
    private static final String HIGHER_DECIMAL_PLACES_COUNT_RESULT = "{8801=3.4, 90005=7.1, 9300=3.333}";
    private static final String NEGATIVE_WEIGHT = "3.4 08801, 3.6 08801, 2 90005, -3.77 90005, 1.33 90005, 3.333 09300";
    private static final String NEGATIVE_WEIGHT_RESULT = "{8801=7.0, 90005=3.33, 9300=3.333}";
    private static final String WRONG_WEIGHT_FORMAT = ".4 08801, 3.6 08801, 2ff 90005, 3.77 90005, 1.33 90005, 3.333 09300";
    private static final String WRONG_WEIGHT_FORMAT_RESULT = "{8801=3.6, 90005=5.1, 9300=3.333}";
    private static final String WRONG_POST_CODE_FORMAT = "3.4 08801fsadfas, 3.6 08801, 2 90005, 3.77 90005, 1.33 90005, 3.333 09300";
    private static final String WRONG_POST_CODE_FORMAT_RESULT = "{8801=3.6, 90005=7.1, 9300=3.333}";
    private static final String NEGATIVE_POST_CODE = "3.4 08801, 3.6 08801, 2 90005, 3.77 90005, 1.33 -90005, 3.333 09300";
    private static final String NEGATIVE_POST_CODE_RESULT = "{8801=7.0, 90005=5.77, 9300=3.333}";
    private static final String POST_CODE_HIGHER_DIGIT_COUNT = "3.4 08801, 3.6 08801, 2 900056, 3.77 9000578, 1.33 5490005, 3.333 09300";
    private static final String POST_CODE_HIGHER_DIGIT_COUNT_RESULT = "{8801=7.0, 9300=3.333}";
    private static final String POST_CODE_LOWER_DIGIT_COUNT = "3.4 08801, 3.6 08801, 2 9, 3.77 90, 1.33 90005, 3.333 0930";
    private static final String POST_CODE_LOWER_DIGIT_COUNT_RESULT = "{8801=7.0, 90005=1.33}";
    private static final String ZERO_WEIGHT = "3.4 08801, 3.6 08801, 0 90005, 1.33 90005";
    private static final String ZERO_WEIGHT_RESULT = "{8801=7.0, 90005=1.33}";
    private static final String WRONG_ARGUMENT_COUNT = "3.4 08801 test, 3.6 08801, 2 9, 0 90005, 1.33 90005";
    private static final String WRONG_ARGUMENT_COUNT_RESULT = "{8801=3.6, 90005=1.33}";

    InputService inputService = new InputService();

    @Test
    public void correctInputTest() {
        Map<Integer, Double> postalCodeWeights = new ConcurrentHashMap();
        String[] testArray = CORRECT_INPUT.split(",");
        Arrays.stream(testArray).forEach(line -> inputService.processLine(line, postalCodeWeights));
        assertEquals(CORRECT_INPUT_RESULT, postalCodeWeights.toString());
    }

    @Test
    public void higherDecimalPlacesCountTest() {
        Map<Integer, Double> postalCodeWeights = new ConcurrentHashMap();
        String[] testArray = HIGHER_DECIMAL_PLACES_COUNT.split(",");
        Arrays.stream(testArray).forEach(line -> inputService.processLine(line, postalCodeWeights));
        assertEquals(HIGHER_DECIMAL_PLACES_COUNT_RESULT, postalCodeWeights.toString());
    }

    @Test
    public void negativeWeightTest() {
        Map<Integer, Double> postalCodeWeights = new ConcurrentHashMap();
        String[] testArray = NEGATIVE_WEIGHT.split(",");
        Arrays.stream(testArray).forEach(line -> inputService.processLine(line, postalCodeWeights));
        assertEquals(NEGATIVE_WEIGHT_RESULT, postalCodeWeights.toString());
    }

    @Test
    public void wrongWeightFormatTest() {
        Map<Integer, Double> postalCodeWeights = new ConcurrentHashMap();
        String[] testArray = WRONG_WEIGHT_FORMAT.split(",");
        Arrays.stream(testArray).forEach(line -> inputService.processLine(line, postalCodeWeights));
        assertEquals(WRONG_WEIGHT_FORMAT_RESULT, postalCodeWeights.toString());
    }

    @Test
    public void wrongPostCodeFormatTest() {
        Map<Integer, Double> postalCodeWeights = new ConcurrentHashMap();
        String[] testArray = WRONG_POST_CODE_FORMAT.split(",");
        Arrays.stream(testArray).forEach(line -> inputService.processLine(line, postalCodeWeights));
        assertEquals(WRONG_POST_CODE_FORMAT_RESULT, postalCodeWeights.toString());
    }

    @Test
    public void negativePostCodeTest() {
        Map<Integer, Double> postalCodeWeights = new ConcurrentHashMap();
        String[] testArray = NEGATIVE_POST_CODE.split(",");
        Arrays.stream(testArray).forEach(line -> inputService.processLine(line, postalCodeWeights));
        assertEquals(NEGATIVE_POST_CODE_RESULT, postalCodeWeights.toString());
    }

    @Test
    public void postCodeHigherDigitCountTest() {
        Map<Integer, Double> postalCodeWeights = new ConcurrentHashMap();
        String[] testArray = POST_CODE_HIGHER_DIGIT_COUNT.split(",");
        Arrays.stream(testArray).forEach(line -> inputService.processLine(line, postalCodeWeights));
        assertEquals(POST_CODE_HIGHER_DIGIT_COUNT_RESULT, postalCodeWeights.toString());
    }

    @Test
    public void postCodeLowerDigitCountTest() {
        Map<Integer, Double> postalCodeWeights = new ConcurrentHashMap();
        String[] testArray = POST_CODE_LOWER_DIGIT_COUNT.split(",");
        Arrays.stream(testArray).forEach(line -> inputService.processLine(line, postalCodeWeights));
        assertEquals(POST_CODE_LOWER_DIGIT_COUNT_RESULT, postalCodeWeights.toString());
    }

    @Test
    public void zeroWeightTest() {
        Map<Integer, Double> postalCodeWeights = new ConcurrentHashMap();
        String[] testArray = ZERO_WEIGHT.split(",");
        Arrays.stream(testArray).forEach(line -> inputService.processLine(line, postalCodeWeights));
        assertEquals(ZERO_WEIGHT_RESULT, postalCodeWeights.toString());
    }

    @Test
    public void wrongArgumentCountTest() {
        Map<Integer, Double> postalCodeWeights = new ConcurrentHashMap();
        String[] testArray = WRONG_ARGUMENT_COUNT.split(",");
        Arrays.stream(testArray).forEach(line -> inputService.processLine(line, postalCodeWeights));
        assertEquals(WRONG_ARGUMENT_COUNT_RESULT, postalCodeWeights.toString());
    }



}
