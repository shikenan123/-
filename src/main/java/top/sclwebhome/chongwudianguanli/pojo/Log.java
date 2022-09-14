package top.sclwebhome.chongwudianguanli.pojo;

public class Log {
	private int ID;
	private String UserID;
	private String event;
	private String time;
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getUserID() {
		return UserID;
	}
	public void setUserID(String userID) {
		UserID = userID;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "Log [ID=" + ID + ", UserID=" + UserID + ", event=" + event + ", time=" + time + "]";
	}
	  public Log() {
	    }
	    public Log(int ID, String UserID,String time,String event) {
	        this.ID = ID;
	        this.UserID = UserID;
	        this.time = time;
	        this.event = event;
	    }
}
