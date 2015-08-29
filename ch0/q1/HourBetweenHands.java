/***********************************
* Given a time, calculate the angle 
* between the hour and minute hands.
*
* Time: O(1)
* Space: O(1)
************************************/
public class HourBetweenHands {

  public static double findAngle(int hours, int minutes) {
    int minuteAngle = minutes * 6;
    double hourAngle = 30*hours + 0.5*minutes;
    return Math.abs(hourAngle-minuteAngle);
  }

  public static void main(String[] args) {
    int hours = 3;
    int minutes = 27;
    System.out.println(findAngle(3, 27));
  }
}