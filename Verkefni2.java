public class Verkefni2{

    public static void main(String[] args) {

        LokudBilTre<String> tree = new LokudBilTre<String>();

        while(!StdIn.isEmpty()){
            String op = "";
            int lo = 0;
            int hi = 0;
            String input = StdIn.readLine();
            String[] command = input.split(" ");
            Interval interval = null;
            try{
                if(command.length == 3){
                    op = command[0];
                    lo = Integer.parseInt(command[1]);
                    hi = Integer.parseInt(command[2]);
                    interval = new Interval(lo, hi);
                }
                else if(command.length == 2){
                    op = command[0];
                    lo = Integer.parseInt(command[1]);
                }
                else{
                    System.out.println("Invlid input!");
                }
            }
            catch(Exception e){
                System.out.println("Invalid input");
            }

            //Setja inn bil
            if(op.equals("+")){
                try{
                    tree.insert(interval, "" + 0);
                }
                catch(Exception e){
                    System.out.println("+ error");
                    System.out.println("Error: " + e);
                }
            }
            //Taka út bil
            else if(op.equals("-")){
                try{
                    tree.remove(interval);
                }
                catch(Exception e){
                    System.out.println("- error");
                    System.out.println("Error: " + e);
                }
            }
            //Finna öll þau bil sem skarast á við bil
            else if(op.equals("?o")){
                try{
                    // Prentar tómt bil ef ekkert bil í tréinu skarast við
                    // viðkomandi bil
                    if(!tree.intersects(interval).iterator().hasNext()){
                        System.out.println(tree.intersects(interval));
                    }
                    else{
                        LokudBilTre.printList(tree.intersects(interval));
                    }
                }
                catch(Exception e){
                    System.out.println("?o error");
                    System.out.println("Error: " + e);
                }
            }
            //Finna öll þau bil sem innihalda bilið [a,b]
            else if(op.equals("?i")){
                try{
                    // Prentar tómt bil ef ekkert bil í tréinu inniheldur
                    // viðkomandi bil
                    if(!tree.includes(interval).iterator().hasNext()){
                        System.out.println(tree.includes(interval));
                    }
                    else{
                        LokudBilTre.printList(tree.includes(interval));
                    }
                }
                catch(Exception e){
                    System.out.println("?i error");
                    System.out.println("Error: " + e);
                }
            }
            //Finna öll þau bil sem innihalda punkt
            else if(op.equals("?p")){
                try{
                    //Prentar tómt bil ef ekkert bil inniheldur punktinn.
                    if(!tree.inside(lo).iterator().hasNext()){
                        System.out.println(tree.inside(lo));
                    }
                    else{
                        LokudBilTre.printList(tree.inside(lo));
                    }                  
                }
                catch(Exception e){
                    System.out.println("?p error");
                    System.out.println("Error: " + e);
                }
            }
        }
    }
}