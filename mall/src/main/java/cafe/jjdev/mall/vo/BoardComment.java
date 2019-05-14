package cafe.jjdev.mall.vo;

public class BoardComment {
	private int BoardCommentNo;
	private int BoardNo;
	private String BoardCommentContent;
	private String BoardCommentUser;
	public int getBoardCommentNo() {
		return BoardCommentNo;
	}
	public void setBoardCommentNo(int boardCommentNo) {
		BoardCommentNo = boardCommentNo;
	}
	public int getBoardNo() {
		return BoardNo;
	}
	public void setBoardNo(int boardNo) {
		BoardNo = boardNo;
	}
	public String getBoardCommentContent() {
		return BoardCommentContent;
	}
	public void setBoardCommentContent(String boardCommentContent) {
		BoardCommentContent = boardCommentContent;
	}
	public String getBoardCommentUser() {
		return BoardCommentUser;
	}
	public void setBoardCommentUser(String boardCommentUser) {
		BoardCommentUser = boardCommentUser;
	}
	@Override
	public String toString() {
		return "BoardComment [BoardCommentNo=" + BoardCommentNo + ", BoardNo=" + BoardNo + ", BoardCommentContent="
				+ BoardCommentContent + ", BoardCommentUser=" + BoardCommentUser + "]";
	}
	
}
