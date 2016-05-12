/**********************************
* @author Varun Maudgalya
* @problemName Dragon of Loowater
* @problemId 11292
* @verdict Accepted
* @runtime 0.369
* @judge http://uva.onlinejudge.org/
* @category simulation
* @level easy
* @date 09/24/2015
* @time O(n)
* @space O(1)
***********************************/
import java.util.Scanner;
import java.util.Arrays;
public class UVa_11292 {
  private static final String MESSAGE = "Loowater is doomed!";
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int numberHeads = in.nextInt();
    int numberKnights = in.nextInt();
    int[] heads = new int[numberHeads];
    int[] knights = new int[numberKnights];
    while (numberKnights != 0 && numberHeads != 0) {
      heads = populate(in, numberHeads);
      knights = populate(in, numberKnights);
      if (heads.length > knights.length) {
        System.out.println(MESSAGE);
        numberHeads = in.nextInt();
        numberKnights = in.nextInt();
      } else {
        int headIndex = 0;
        int coins = 0;
        for (int i = 0; i < numberKnights && headIndex < numberHeads; i++) {
          if (knights[i] >= heads[headIndex]) {
            coins += knights[i];
            headIndex++;
          }
        }
        if (headIndex < numberHeads) {
          System.out.println(MESSAGE);
          numberHeads = in.nextInt();
          numberKnights = in.nextInt();
        } else {
          System.out.println(coins);
          numberHeads = in.nextInt();
          numberKnights = in.nextInt();
        }
      }
    }
  }
  private static int[] populate(Scanner in, int size) {
    int[] values = new int[size];
    for (int i = 0; i < size; i++) {
      values[i] = in.nextInt();
    }
    Arrays.sort(values);
    return values;
  }
}

