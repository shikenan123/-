package top.sclwebhome.chongwudianguanli.pojo;

public class Problem {
	    private   int ID;
	    private String title;
	    private String body;
	    private String pname;
	    private String c;
	    private String isi;
	    private String timess;
		public int getID() {
			return ID;
		}
		public void setID(int iD) {
			ID = iD;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getBody() {
			return body;
		}
		public void setBody(String body) {
			this.body = body;
		}
		public String getPname() {
			return pname;
		}
		public void setPname(String pname) {
			this.pname = pname;
		}
		public String getC() {
			return c;
		}
		public void setC(String c) {
			this.c = c;
		}
		public String getIsi() {
			return isi;
		}
		public void setIsi(String isi) {
			this.isi = isi;
		}
		public String getTimess() {
			return timess;
		}
		public void setTimess(String timess) {
			this.timess = timess;
		}
		@Override
		public String toString() {
			return "Problem [ID=" + ID + ", title=" + title + ", body=" + body + ", pname=" + pname + ", c=" + c
					+ ", isi=" + isi + ", timess=" + timess + "]";
		}
		   public Problem() {
		    }
		    public Problem(int ID, String title, String body, String pname, String c, String isi, String timess) {
		        this.ID = ID;
		        this.title = title;
		        this.body = body;
		        this.pname = pname;
		        this.c = c;
		        this.isi = isi;
		        this.timess = timess;
		    }


}
