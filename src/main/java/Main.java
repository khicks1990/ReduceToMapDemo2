import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/** 
    This program demonstrates the Collectors.toMap method.
 */

public class Main
{    
     public static void main(String [] args)
    {
        // This is the stream to reduce to a map.
        Stream<String> names = Stream.of("Larry", "Curly", "Moe", "Al");

        // The key for a string is the length of the string.
        Function<String, Integer> keyMapper  = x -> x.length();

        // The value for a string is a list of all strings in 
        // the stream with the same length. Every string is initially 
        // mapped to list containing just that string.
        Function<String, List<String>> valueMapper =  x->
        {
           List<String> listOfOne = new ArrayList<>();
           listOfOne.add(x);
           return listOfOne;             
        };

        // When two values have the same key, the merge function
        // is used to merge the two values and the merged result 
        // becomes the value of the key.
        BinaryOperator<List<String>> mergeFunction = (list1, list2) ->
        {
           list1.addAll(list2);
           return list1;
        };

        //Reduce the stream and get a map.
        Map<Integer, List<String>> namesMap = 
        names.collect(Collectors.toMap(
                                        keyMapper, valueMapper, 
                                        mergeFunction));

        // Print the map.
        System.out.printf("The map of names is %s\n", namesMap);
    } 
}