package admin;

public class DiaryComplaintVO {
	private int idx;
	private int diary_Idx;
	private String cpMid;
	private String cpContent;
	
	private String name;
	private String title;
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
	public String getCpMid() {
		return cpMid;
	}
	public void setCpMid(String cpMid) {
		this.cpMid = cpMid;
	}
	public String getCpContent() {
		return cpContent;
	}
	public void setCpContent(String cpContent) {
		this.cpContent = cpContent;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public String toString() {
		return "DiaryComplaintVO [idx=" + idx + ", diary_Idx=" + diary_Idx + ", cpMid=" + cpMid + ", cpContent=" + cpContent
				+ ", name=" + name + ", title=" + title + "]";
	}

	
	
	
}
