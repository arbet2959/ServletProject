package diary;

public class DiaryReplyVO {
	private int idx;
	private int diary_Idx;
	private String mid;
	private String nickName;
	private String Content;
	private int ref;
	private int re_step;
	private int re_level;
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public int getDiary_Idx() {
		return diary_Idx;
	}
	public void setDiary_Idx(int diary_Idx) {
		this.diary_Idx = diary_Idx;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public int getRef() {
		return ref;
	}
	public void setRef(int ref) {
		this.ref = ref;
	}
	public int getRe_step() {
		return re_step;
	}
	public void setRe_step(int re_step) {
		this.re_step = re_step;
	}
	public int getRe_level() {
		return re_level;
	}
	public void setRe_level(int re_level) {
		this.re_level = re_level;
	}
	@Override
	public String toString() {
		return "DiaryReplyVO [idx=" + idx + ", diary_Idx=" + diary_Idx + ", mid=" + mid + ", nickName=" + nickName
				+ ", Content=" + Content + ", ref=" + ref + ", re_step=" + re_step + ", re_level=" + re_level + "]";
	}
	
	
		
}
