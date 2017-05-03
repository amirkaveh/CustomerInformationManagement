package utils;

/**
 * Created by $Hamid on 5/3/2017.
 */
public class MathUtils {
    public static <T extends Comparable<T> > Boolean between(T x, T min, T max){
        return x.compareTo(min) >= 0 && x.compareTo(max) <= 0;
    }
}
