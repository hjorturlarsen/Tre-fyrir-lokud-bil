/*************************************************************************
 *  Tre fyrir lokud bil
 *
 *  Hlutir af tagi LokufBilTre eru hnutar i tvileitartre af strengjum.
 *  Tilvisun i hlut af tagi LokudBilTre getur thvi stadid fyrir heilt tvileitartre 
 *
 *  Thyding:                javac LokudBilTre.java
 *  Keyrsla:               java LokudBilTre
 *  Naudsynleg fylgiskjol: Interval.java
 *  
 *************************************************************************/
import java.util.LinkedList;
import java.util.*;

public class LokudBilTre<Value>  {
    private Node root;

    // Fastayrding gagna:
    //    LokudBilTre er tilvisun a hlut af thessu tagi.
    //    Tom tilvisun (null) stendur fyrir tomt tre.
    //    Ef tilvisunin er ekki tom visar hun a hlut
    //    sem stendur fyrir rot (undir)tresins.  Tred uppfyllir
    //    annars vegar tvileitartrjaaskilyrdi:
    //    Oll gildi i vinstra undirtre eru alltaf (fyrir
    //    oll undirtre) <= rotargildi og oll gildi i
    //    haegra undirtre eru >= rotargildi.
    //    Rot heildartresins hefur root==null,
    //    en allir adrir hnutar hafa root sem visar a
    //    annan LokudBilTre hlut.
    private class Node {
        Interval interval;        
        Value value;              
        Node left, right;         // vinstra/haegra undirtre
        int N;                    // Staerd undirtres i thessum hnut
        int max;                  // Staersta gildi undirtres thessa hnuts

        Node(Interval interval, Value value) {
            this.interval = interval;
            this.value    = value;
            this.N        = 1;
            this.max      = interval.high;
        }
    }

    // Notkun: x = tree.get();
    // Fyrir:  tree er ekki-tomt tre
    // Eftir:  x er bilid i rotinni a tree
    public Value get(Interval interval) {
        return get(root, interval);
    }

    private Value get(Node x, Interval interval) {
        if(x == null){
            return null;
        }
        int cmp = interval.compareIntervals(x.interval);
        if(cmp < 0){
            return get(x.left, interval);
        }
        else if(cmp > 0){
            return get(x.right, interval);
        }
        else{
            return x.value;
        } 
    }

   /**
   + adgerdin
   */
    // Notkun: tree.insert(i,v);
    // Fyrir:  v er strengur, i er Interval (ma vera tomt)
    // Eftir:  tree er tred sem ut kemur thegar i er baett i hnut med gildid v
    public void insert(Interval interval, Value value) {
        root = rootInsert(root, interval, value);
    }

    private Node rootInsert(Node x, Interval interval, Value value) {
        if (x == null) return new Node(interval, value);
        int cmp = interval.compareIntervals(x.interval);
        if (cmp < 0) { 
            x.left  = rootInsert(x.left,  interval, value);
            x = rotateRight(x); 
        }
        else{
            x.right = rootInsert(x.right, interval, value);
            x = rotateLeft(x); 
        }
        return x;
    }

    // Notkun: x = joinLeftRight(l,r);
    // Fyrir:  Oll gildi i left eru <= oll gildi i right
    // Eftir:  x inniheldur alla hnuta ur left og right, i rettri rod.
    //         l og r eru nu ogild (ordin ad innri hnutum i tre).
    private Node joinLeftRight(Node a, Node b) { 
        if (a == null) return b;
        if (b == null) return a;

        if (Math.random() * (size(a) + size(b)) < size(a))  {
            a.right = joinLeftRight(a.right, b);
            fix(a);
            return a;
        }
        else {
            b.left = joinLeftRight(a, b.left);
            fix(b);
            return b;
        }
    }
    /**
    - adgerdin
    */
    // Notkun: tree.remove(i);
    // Fyrir:  i er Interval i treinu og tree er tre
    // Eftir:  i hefur verid fjaraegt ur tree
    public Value remove(Interval interval) {
        Value value = get(interval);
        root = remove(root, interval);
        return value;
    }

    private Node remove(Node h, Interval interval) {
        if (h == null) return null;
        int cmp = interval.compareIntervals(h.interval);
        if      (cmp < 0) h.left  = remove(h.left,  interval);
        else if (cmp > 0) h.right = remove(h.right, interval);
        else              h = joinLeftRight(h.left, h.right);
        fix(h);
        return h;
    }


    /**
    ?o adgerdin
    */
    // running time is proportional to R log N, where R is the number of intersections
    // Notkun: l = x.intersects(i);
    // Fyrir:  x er tre og i er Interval
    // Eftir:  l er listi af Interval hlutum tresins sem skarast vid lokada bilid i
    public LinkedList intersects(Interval interval) {
        LinkedList<Interval> list = new LinkedList<Interval>();
        intersects(root, interval, list);
        return list;
    }

    // leitad i undirtre med rot x
    public boolean intersects(Node x, Interval interval, LinkedList<Interval> list) {
        boolean found1 = false;
        boolean found2 = false;
        boolean found3 = false;
        if (x == null)
            return false;
        if (x.interval.intersects(interval)) {
            list.add(x.interval);
            found1 = true;
        }
        if (x.left != null && x.left.max >= interval.low)
            found2 = intersects(x.left, interval, list);
        if (found2 || x.left == null || x.left.max < interval.low)
            found3 = intersects(x.right, interval, list);
        return found1 || found2 || found3;
    }

    /**
    ?i adgerdin
    */
    // Notkun: l = x.includes(i);
    // Fyrir:  x er tre og i er Interval
    // Eftir:  l er listi af Interval hlutum tresins sem innihalda lokada bilid i
    public LinkedList includes(Interval interval) {
        LinkedList<Interval> list = new LinkedList<Interval>();
        includes(root, interval, list);
        return list;
    }

    public boolean includes(Node x, Interval interval, LinkedList<Interval> list) {
        boolean found1 = false;
        boolean found2 = false;
        boolean found3 = false;
        if (x == null)
            return false;
        if (x.interval.includes(interval)) {
            list.add(x.interval);
            found1 = true;
        }
        if (x.left != null && x.left.max >= interval.low)
            found2 = includes(x.left, interval, list);
        if (found2 || x.left == null || x.left.max < interval.low)
            found3 = includes(x.right, interval, list);
        return found1 || found2 || found3;
    }
    /**
    ?p adgerdin
    */
    // Notkun: l = x.inside(p);
    // Fyrir:  x er tre og p er heiltala
    // Eftir:  l er listi af Interval hlutum tresins sem innihalda heiltoluna p
    public LinkedList inside(int p) {
        Interval interval = new Interval(p, p);
        LinkedList<Interval> list = new LinkedList<Interval>();
        inside(root, p, list, interval);
        return list;
    }

    public boolean inside(Node x, int p, LinkedList<Interval> list, Interval interval) {
        boolean found1 = false;
        boolean found2 = false;
        boolean found3 = false;
        if (x == null)
            return false;
        if (x.interval.findPoint(p)) {
            list.add(x.interval);
            found1 = true;
        }
        if (x.left != null && x.left.max >= p)
            found2 = inside(x.left, p, list, interval);
        if (found2 || x.left == null || x.left.max < p)
            found3 = inside(x.right, p, list, interval);
        return found1 || found2 || found3;
    }
/**

*/
    // Notkun: x = size(t)
    // Fyrir:  t er hnutur
    // Eftir:  x er fjoldi hnuta hluttres sem hefur rot i t
    public int size() { return size(root); }
    private int size(Node x) { 
        if (x == null) return 0;
        else           return x.N;
    }

   
    // fix auxilliar information (subtree count and max fields)
    // Notkun: fix(t)
    // Fyrir:  t er hnutur
    // Eftir:  uppfaerir fjolda hluttrjaa og staersta gildi undirtres hnutsins
    private void fix(Node x) {
        if (x == null) return;
        x.N = 1 + size(x.left) + size(x.right);
        x.max = max3(x.interval.high, max(x.left), max(x.right));
    }

    // Notkun: x = max(t)
    // Fyrir:  t er hnutur
    // Eftir:  x er staersta gildi undirtres t
    private int max(Node x) {
        if (x == null) return Integer.MIN_VALUE;
        return x.max;
    }

    // Notkun: x = max3(t)
    // Fyrir:  a,b,c eru heiltolur og a ma ekki vera null
    // Eftir:  x er staersta talan af theim 3 sem eru i inntakinu
    private int max3(int a, int b, int c) {
        return Math.max(a, Math.max(b, c));
    }

    // Notkun: x = rotateRight(t)
    // Fyrir:  t og x eru hnutar
    // Eftir:  hnutnum hefur verid snuid til haegri
    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        fix(h);
        fix(x);
        return x;
    }

    // Notkun: x = rotateRight(t)
    // Fyrir:  t og x eru hnutar
    // Eftir:  hnutnum hefur verid snuid til vinstri
    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        fix(h);
        fix(x);
        return x;
    }

    // Notkun: printList(l)
    // Fyrir:  l er listi af bilum (Intervals)
    // Eftir:  Listanum l hefur verid radad eftir minnsta staki hvers bils.
    //         A medan listinn er ekki tomur er fremsta stak hans prentad. 
    public static void printList(LinkedList list){
        Collections.sort(list, new IntervalComparator());
        while(!list.isEmpty()){
            System.out.print(list.removeFirst() + " ");
        }
        System.out.println();
    }
}