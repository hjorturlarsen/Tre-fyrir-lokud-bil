/*************************************************************************
 *  Klasi fyrir lokud heiltalnabil asamt helstu adferdum sem haegt er 
 *  ad beita a hlutinn  
 *  
 *
 *  Thyding:  javac Interval.java
 *  Keyrsla:    java Interval  
 *
 *************************************************************************/
public class Interval implements Comparable<Interval>{
    public final int low;   // Stakid lengst til vinstri 
    public final int high;  // Stakid lengst til haegri

    // Notkun: x = Interval(l, r)
    // Fyrir:  l <= r
    // Eftir:  Til er bilid [l, r]
    public Interval(int left, int right) {
        if (left <= right) {
            this.low  = left;
            this.high = right;
        }
        else throw new RuntimeException("Illegal interval");
    }

    // Notkun: s = x.getStart()
    // Fyrir:  x er bil 
    // Eftir:  s er minnsta stak bilsins x
    public int getStart(){
        return this.low;
    }

    // Notkun: x.intersects(y)
    // Fyrir:  x og y eru heiltölu bil
    // Eftir:  Skilar False ef snidmengi x og y er tomt en True annars
    public boolean intersects(Interval that) {
        if (that.low <= this.high && that.low >= this.low  && that.high >= this.high){ 
            return true; 
        }
        if (that.low <= this.low && that.high >= this.low && that.high <= this.high ){
            return true;
        }
        if (that.low >= this.low && that.high <= this.high){ 
            return true;
        }
        if (that.low <= this.low && that.high >= this.high){ 
            return true;
        }
        return false;
    }

    // Notkun: x.includes(y)
    // Fyrir:  x og y eru heiltölu bil
    // Eftir:  Skilar True eingöngu ef x inniheldur y
    public boolean includes(Interval that){
        if (that.low >= this.low && that.high <= this.high){ 
            return true;
        }
        return false;
    }

    // Notkun: x.findPoint(p)
    // Fyrir:  x er heiltölu bil og p er heiltala
    // Eftir:  Skilar True eingöngu ef p er innan lokada bilsins x
    public boolean findPoint(int point) {

        return (this.low <= point) && (point <= this.high);
    }

    // Notkun: x.compareIntervals(y)
    // Fyrir:  x og y eru heiltölubil
    // Eftir:  Gefur til kynna hvort fara eigi til vinstri eda haegri
    public int compareIntervals(Interval that) {
        if      (this.low  < that.low)  return -1;
        else if (this.low  > that.low)  return +1;
        else if (this.high < that.high) return -1;
        else if (this.high < that.high) return +1;
        else                            return  0;
    }
    // Naudsynlegt hjalparfall fyrir Comparator sem
    // hjalpar okkur ad rada LinkedList
    // Notkun: x.comparTo(i)
    // Fyrir:  x og y er heiltölubil
    // Eftir:  Ef fremsta stak x < fremsta stak y tha -1
    //         Ef fremsta stak y < fremsta stak x tha  1
    //         Annars 0
    public int compareTo(Interval that){
        if (this.getStart() < that.getStart()) return -1;
        else if (this.getStart() > that.getStart()) return 1;
        else return 0;
    }

    // Notkun: s = x.toString()
    // Fyrir:  x er bil heiltalna
    // Eftir:  s er heiltölubil reprecentad sem Strengur
    public String toString() {
        return "[" + low + ", " + high + "]";
    }
}
