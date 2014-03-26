import java.util.*;

public class IntervalComparator implements Comparator<Interval> {

    // Notkun: x = compare(i1, i2)
    // Fyrir:  i1 og i2 eru bil 
    // Eftir:  x = 1 ef fremsta stak i1 > fremsta stak i2
    //         x = -1 ef fremsta stak i1 < fremsta stak i2
    //         x = 0 annars
    public int compare(Interval x, Interval y) {
        if (x.getStart() > y.getStart()){
        	return 1;
        }
        else if(x.getStart() < y.getStart()){
        	return -1;
        }
        else{
        	return 0;
        }
    }
}