/*
ID: vmaudgalya
LANG: JAVA
TASK: friday
*/
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;


class friday {

  private static class CircularLinkedList {
    class Node {
      Node next;
      String day;
      public Node(String day) {
        this.day = day;
      }
      public String toString() {
        return this.day + " " + this.next;
      }
    }

    private int size;
    private Node head;
    private boolean firstTime;
    private Node current;


    public CircularLinkedList(String[] week) {
      for (String day : week) {
        this.append(day);
      }
      firstTime = true;
    }

    public void append(String day) {
      if (head == null) {
        head = new Node(day);
        head.next = head;
      } else {
        Node temp = head;
        while (temp.next != head) {
          temp = temp.next;
        }
        temp.next = new Node(day);
        temp.next.next = head;
      }
      size++;
    }

    public String moveToNext() {
      if (firstTime) {
        firstTime = false;
        current = head;
        return current.day;
      } else {
        current = current.next;
        return current.day;
      }
    }

    public String getCurrentDay() {
      return current.day;
    }

    public String toString() {
      if (size == 1) {
        return head.day;
      } else {
        StringBuilder builder = new StringBuilder("[ ");
        Node temp = head;
        while (temp.next != head) {
          builder.append(temp.day + " ");
          temp = temp.next;
        }
        builder.append(temp.day + " ]");
        return builder.toString();
      }
    }

    public int size() {
      return size;
    }


  }

  public static void main(String[] args) throws IOException {
    String[] week = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
    CircularLinkedList theWeek = new CircularLinkedList(week);
    BufferedReader input = new BufferedReader(new FileReader("friday.in"));
    int endYear = Integer.parseInt(input.readLine()) + 1900;
    Map<String, Integer> counts = initializeMap(week);
    for (int year = 1900; year < endYear; year++) {
      for (int month = 0; month < 12; month++) {
        int days = getDaysInMonth(month, year);
        for (int day = 1; day <= days; day++) {
          if (day == 13) {
            counts = incrementDay(theWeek.getCurrentDay(), counts);
          }
          theWeek.moveToNext();
        }
      }
    }
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("friday.out")));
    printOutput(counts, out);
    closeStreams(input, out);
  }

  private static void printOutput(Map<String, Integer> counts, PrintWriter out) {
    String[] week = {"Friday", "Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday"};
    for (String day : week) {
      if (day.equals("Thursday")) {
        out.print(counts.get(day) + "\n");
      } else {
        out.print(counts.get(day) + " ");
      }
    }
  }

  private static void closeStreams(BufferedReader in, PrintWriter out) throws IOException {
    in.close();
    out.close();
  }

  private static Map<String, Integer> incrementDay(String day, Map<String, Integer> counts) {
    counts.put(day, counts.get(day) + 1);
    return counts;
  }

  private static Map<String, Integer> initializeMap(String[] week) {
    Map<String, Integer> counts = new HashMap<String, Integer>();
    for (String day : week) {
      counts.put(day, 0);
    }
    return counts;
  }

  private static int getDaysInMonth(int month, int year) {
    if (month == 1) {
      if (isLeapYear(year)) {
        return 29;
      }
      return 28;
    }
    if (month == 3 || month == 5 || month == 8 || month == 10) {
      return 30;
    }
    return 31;
  }

  private static boolean isCenturyYear(int year) {
    return (year % 100 == 0);
  }

  private static boolean isLeapYear(int year) {
    if (isCenturyYear(year)) {
      return (year % 400 == 0);
    }
    return (year % 4 == 0);
  }
}