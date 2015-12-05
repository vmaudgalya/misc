import java.util.ArrayDeque;
import java.util.Deque;
import java.util.NoSuchElementException;

public class AnimalShelter {
  private Deque<Cat> cats;
  private Deque<Dog> dogs;

  public AnimalShelter() {
    cats = new ArrayDeque<Cat>();
    dogs = new ArrayDeque<Dog>();
  }

  public void enqueue(Animal animal) {
    if (animal == null) {
      throw new NullPointerException("Null elements are not permitted");
    }
    animal.setArrivalTime(System.nanoTime());
    if (animal instanceof Cat) {
      cats.add((Cat) animal);
    } else {
      dogs.add((Dog) animal);
    }
  }

  public Animal dequeueAny() {
    if (cats.isEmpty() && dogs.isEmpty()) {
      throw new NoSuchElementException("The animal shelter is empty");
    } else if (cats.isEmpty()) {
      return dogs.remove();
    } else if (dogs.isEmpty()) {
      return cats.remove();
    }

    if (cats.peek().getArrivalTime() < dogs.peek().getArrivalTime()) {
      return cats.remove();
    } else {
      return dogs.remove();
    }

  }

  public Cat dequeueCat() {
    if (cats.isEmpty()) {
      throw new NoSuchElementException("There are no cats in the animal shelter");
    }
    return cats.remove();
  }

  public Dog dequeueDog() {
    if (dogs.isEmpty()) {
      throw new NoSuchElementException("There are no dogs in the animal shelter");
    }
    return dogs.remove();
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("===Animal Shelter===\n");
    builder.append("cats: " + cats + "\ndogs: " + dogs + "\n");
    builder.append("====================\n");
    return builder.toString();
  }

  public static void main(String[] args) {
    AnimalShelter shelter = new AnimalShelter();
    for (int i = 0; i < 20; i++) {
      if (i % 2 == 0) {
        shelter.enqueue(new Cat("Cat" + i));
      } else {
        shelter.enqueue(new Dog("Dog" + i));
      }
    }
    System.out.println(shelter);
    for (int i = 20; i < 30; i++) {
      if (i % 2 == 0) {
        System.out.println("Removing any: " + shelter.dequeueAny());
      } else if (i % 3 == 0) {
        System.out.println("Removing dog: " + shelter.dequeueDog());
      } else {
        System.out.println("Removing cat: " + shelter.dequeueCat());
      }
    }
    System.out.println(shelter);
  }

}
