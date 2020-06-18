import edu.princeton.cs.algs4.StdIn;

public class Permutation {
    public static void main(String[] args){
        int k = Integer.parseInt(args[0]);
        int i = 1;
        RandomizedQueue<String> queue = new RandomizedQueue<>();
        while (!StdIn.isEmpty()){
            if (i <= k){
                queue.enqueue(StdIn.readString());
            }
            else {
                int j = (int)(Math.random() * i);
                    if(j < k){
                        queue.dequeue();
                        queue.enqueue(StdIn.readString());
                    }
                    else{
                        StdIn.readString();
                    }
            }
            i++;
        }
        for (String s : queue){
            System.out.println(s);
        }
    }
}

