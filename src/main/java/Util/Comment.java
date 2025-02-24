package Util;

public class Comment {
	private String name;
	private String datestamp;
	private String content;
	private int commentid;
	
	public Comment() {}
	
	public Comment(String cname, String cdatestamp, String ccontent, int ccommentid) {
		setName(cname);
		setDatestamp(cdatestamp);
		setContent(ccontent);
		setCommentid(ccommentid);
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

	public int getCommentid() {
		return commentid;
	}

	public void setCommentid(int commentid) {
		this.commentid = commentid;
	}
}