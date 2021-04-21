package model;

public class TimeInterval
{
  private MyDateTime startTime;
  private MyDateTime endTime;

  public TimeInterval(MyDateTime startTime, MyDateTime endTime)
  {
    setStartTime(startTime);
    setEndTime(endTime);
  }

  public MyDateTime getStartTime()
  {
    return startTime;
  }

  public void setStartTime(MyDateTime startTime)
  {
    this.startTime = startTime;
  }

  public MyDateTime getEndTime()
  {
    return endTime;
  }

  public void setEndTime(MyDateTime endTime)
  {
    this.endTime = endTime;
  }

  public String toString()
  {
    return "Start time: " + startTime + " End time: " + endTime;
  }
}
