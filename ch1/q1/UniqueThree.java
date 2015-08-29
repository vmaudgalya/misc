/***************************************************************
* Implement an algorithm to determine if a 
* string has all unique characters. What if you
* can not use additional data structures?
*
* Time: O(nlog(n))
* [Worst case is O(n^2) but probability of that case is 1/(n!)]
* Space: O(n)
****************************************************************/
import java.util.Random;

// without using java's built in sort/data structures
public class UniqueThree {
  
  // Knuth's O(n) in-place Uniform Shuffling Algorithm
  public static void shuffle(char[] a) {
    Random generator = new Random();
    int index = 0;
    for (int i = 1; i < a.length; i++) {
      index = generator.nextInt(i+1);
      if (i != index) {
        swap(a, i, index);
      }
    }
  }

  public static void swap(char[] a, int i, int j) {
    char tmp = a[i];
    a[i] = a[j];
    a[j] = tmp;
  }

  public static void quicksort(char[] a) {
    shuffle(a); // minimize probability of the O(n^2) worst case
    quicksort(a, 0, a.length-1);
  }

  // Dijkstra's 3-way partitioning quicksort
  public static void quicksort(char[] a, int start, int end) {
    if (end-start <= 1) {
      return;
    }
    int i = start; // partitioning element
    int lo = start + 1;
    int hi = end;
    while (lo <= hi) {
      if (a[i] > a[lo]) {
        swap(a, i, lo);
        i++;
        lo++;
      } else if (a[i] < a[lo]) {
        swap(a, lo, hi);
        hi--;
      } else {
        lo++; //partitioning element is equal to lower bound
      }
    }
    quicksort(a, start, i-1);
    quicksort(a, lo, end);
  }

  public static boolean hasDuplicates(char[] letters) {
    for (int i = 1; i < letters.length; i++) {
      if (letters[i-1] == letters[i]) {
        return true;
      }
    }
    return false;
  }

  public static void validateInput(String word) {
    if (word.equals("")) {
      throw new RuntimeException("Error: Empty strings are invalid input");
    } else if (word == null) {
      throw new RuntimeException("Error: Null strings are invalid input");
    }
  }

  public static boolean hasAllUnique(String word) {
    validateInput(word); // Assumed that null and empty strings are invalid.
    char[] letters = word.toCharArray();
    quicksort(letters); // Bottleneck O(nlog(n)) operation
    if (hasDuplicates(letters)) {
      return false;
    } else {
      return true;
    }
  }

  public static void main(String[] args) {
    String[] words = {"tuba", "firetruck"};
    for (String word : words) {
      System.out.println("Does " + word + " contain all unique characters? " + hasAllUnique(word));
    }
  }

}