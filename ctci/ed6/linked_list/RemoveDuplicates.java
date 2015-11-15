
import java.util.Set;
import java.util.HashSet;

public class RemoveDuplicates {

  public static List<String> removeDuplicatesQuickly(List<String> list) {
    Set<String> uniqueElements = new HashSet<String>();
    for (String element : list) {
      if (uniqueElements.contains(element)) {
        list.remove(element);
      } else {
        uniqueElements.add(element);
      }
    }
    return list;
  }

  public static List<String> removeDuplicatesInPlace(List<String> list) {

  }


  public static void main(String[] args) {
    removeDuplicatesTest();
  }

  public static void removeDuplicatesTest() {
    final String[] countries = {"America", "Egypt", "Australia", 
                                "Russia", "Africa", "China", "Bolivia", "America", "Egypt"};
    final int initialSize = countries.length;
    final LinkedList<String> countryNamesWithDuplicates = new LinkedList<String>();
    for (String country : countries) {
      countryNamesWithDuplicates.addLast(country);
    }
    final LinkedList<String> countryNames = removeDuplicatesQuickly(countryNamesWithDuplicates);
    assert countryNames.size() == (initialSize - 2);
  }


}