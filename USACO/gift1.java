/*
ID: vmaudgalya
LANG: JAVA
TASK: gift1
*/
import java.util.Map;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.LinkedHashMap;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.FileWriter;

class gift1 {

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader("gift1.in"));
    int numPeople = Integer.parseInt(reader.readLine());
    Map<String, Integer> people = getPeople(numPeople, reader);
    
    String line = null;
    while ((line = reader.readLine()) != null) {
      String distributor = line;
      StringTokenizer st = new StringTokenizer(reader.readLine());
      int amount = Integer.parseInt(st.nextToken());
      int peopleToGive = Integer.parseInt(st.nextToken());
      int amountGivenPerPerson = (peopleToGive > 0 ? amount / peopleToGive : 0);
      int remainder = (peopleToGive > 0 ? amount % peopleToGive : 0);
      people.put(distributor, people.get(distributor) + remainder - amount);
      people = distributeMoney(amountGivenPerPerson, peopleToGive, reader, people);
    }
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("gift1.out")));
    for (Entry<String, Integer> entry : people.entrySet()) {
      out.println(entry.getKey() + " " + entry.getValue());
    }
    out.close();
  }

  private static Map<String, Integer> distributeMoney(int amount, int numPeople, BufferedReader reader, Map<String, Integer> people) throws IOException {
    String person = null;
    for (int i = 0; i < numPeople; i++) {
      person = reader.readLine();
      people.put(person, people.get(person) + amount);
    }
    return people;
  }

  private static Map<String, Integer> getPeople(int numPeople, BufferedReader reader) throws IOException {
    Map<String, Integer> people = new LinkedHashMap<String, Integer>();
    for (int i = 0; i < numPeople; i++) {
      people.put(reader.readLine(), 0);
    }
    return people;
  }

}