/*
ID: vmaudgalya
LANG: JAVA
TASK: beads
*/
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class beads {
  public static void main(String[] args) throws IOException {
    BufferedReader input = new BufferedReader(new FileReader("beads.in"));
    PrintWriter output = new PrintWriter(new BufferedWriter(new FileWriter("beads.out")));
    int numberOfBeads = Integer.parseInt(input.readLine());
    String beads = input.readLine();
    String necklaces = beads + beads;
    int maxBeads = 0;

    wwwbbrwrbrbrrbrbrwrwwrbwrwrrb|wwwbbrwrbrbrrbrbrwrwwrbwrwrrb

    // approach 1
    // if you see white
    // keep going until you find a 'r' or 'b', if you have whites add to accruedWhites
    // blues = 1 + accruedWhites
    // keep looking for blues/whites until you find a 'r' (blues++ in the meantime)
    // once you find an r, keep looking for reds/whites until you find a b
    // compare to maxbeads, if its greater, set it.

    // approach 2
    // scan, if you see red/blue, look for first instance of blue/red.
    // split necklace at that point and count with two pointers until you
    // hit a bead of different color that isn't white
    // remember what you've seen
  }
}