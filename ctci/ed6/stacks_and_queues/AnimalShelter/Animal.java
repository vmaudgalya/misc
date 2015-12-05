abstract class Animal {
  private String name;
  private long arrivalTime;

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public long getArrivalTime() {
    return this.arrivalTime;
  }

  public void setArrivalTime(long arrivalTime) {
    this.arrivalTime = arrivalTime;
  }

  @Override
  public String toString() {
    return this.name;
  }

}
