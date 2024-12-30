package Util;

public class Log {
	private String name;
	private String datestamp;
	private String content;
	
	public Log() {}
	
	public Log(String lname, String ldatestamp, String lcontent) {
		setName(lname);
		setDatestamp(ldatestamp);
		setContent(lcontent);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDatestamp() {
		return datestamp;
	}

	public void setDatestamp(String datestamp) {
		this.datestamp = datestamp;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}