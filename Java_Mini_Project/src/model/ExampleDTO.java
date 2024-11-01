package model;

public class ExampleDTO {
	Integer idx;
	String lan;		// 언어 타입
	String content;	// 글 내용
	
	public Integer getIdx() {
		return idx;
	}
	public void setIdx(Integer idx) {
		this.idx = idx;
	}
	public String getLan() {
		return lan;
	}
	public void setLan(String lan) {
		this.lan = lan;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	@Override
	public String toString() {
		return "ExampleDTO [idx=" + idx + ", lan=" + lan + ", content=" + content + "]";
	}
	
}
