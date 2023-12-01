package friend;

public class FriendVO {
	private String myId;
	private String friendId;
	public String getMyId() {
		return myId;
	}
	public void setMyId(String myId) {
		this.myId = myId;
	}
	public String getFriendId() {
		return friendId;
	}
	public void setFriendId(String friendId) {
		this.friendId = friendId;
	}
	@Override
	public String toString() {
		return "FriendVO [myId=" + myId + ", friendId=" + friendId + "]";
	}
	
	
		
}
