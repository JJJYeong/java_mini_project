package model;

public class LankingDTO {
	Integer idx;
	Integer writeidx;	// 글 인덱스
	String nickname;	// 유저 닉네임
	Long duration;	// 걸린 시간
	
	public Integer getIdx() {
		return idx;
	}
	public void setIdx(Integer idx) {
		this.idx = idx;
	}
	public Integer getWriteidx() {
		return writeidx;
	}
	public void setWriteidx(Integer writeidx) {
		this.writeidx = writeidx;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public Long getDuration() {
		return duration;
	}
	public void setDuration(Long duration) {
		this.duration = duration;
	}
	
}
