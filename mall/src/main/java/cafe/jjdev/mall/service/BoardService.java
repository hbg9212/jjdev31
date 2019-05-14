package cafe.jjdev.mall.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import cafe.jjdev.mall.common.PathStr;
import cafe.jjdev.mall.mapper.BoardCommentMapper;
import cafe.jjdev.mall.mapper.BoardFileMapper;
import cafe.jjdev.mall.mapper.BoardMapper;
import cafe.jjdev.mall.vo.Board;
import cafe.jjdev.mall.vo.BoardComment;
import cafe.jjdev.mall.vo.BoardFile;
import cafe.jjdev.mall.vo.BoardRequest;

@Service
@Transactional
public class BoardService {
	@Autowired
	private BoardMapper boardMapper;
	@Autowired
	private BoardCommentMapper boardCommentMapper;
	@Autowired
	private BoardFileMapper boardFileMapper;
	
	public int modifyBoard(Board board) {//게시글 수정
		return boardMapper.updateBoard(board);
	}
	
	public int modifyBoardFile(BoardRequest boardRequest) {//첨부파일 수정
		MultipartFile multipartFile = boardRequest.getBoardFile().get(0);
		BoardFile boardFile = boardFileMapper.selectBoardFile(boardRequest.getBoardFileNo());
		if(multipartFile.getSize() != 0) {
			String saveName = boardFile.getBoardFileSaveName();
			String ext = boardFile.getBoardFileExt();
			File file = new File(PathStr.UPLOAD_PATH+"/"+saveName+"."+ext);
	        if(file.exists()) {
	        	if(file.delete()) {
	        		System.out.println("파일삭제 성공");
	        		} else {
	        			System.out.println("파일삭제 실패");
	        		}
	        } else {
	        	System.out.println("파일이 존재하지 않습니다.");
	        }

			String originFileName = multipartFile.getOriginalFilename();
			int i = originFileName.lastIndexOf(".");
			String originName = multipartFile.getOriginalFilename().substring(0, i);
			ext = multipartFile.getOriginalFilename().substring(i+1).toLowerCase();
			UUID uuid = UUID.randomUUID();
			saveName = uuid.toString().replace("-", "");
			boardFile.setBoardNo(boardRequest.getBoardNo());
			boardFile.setBoardFileNo(boardRequest.getBoardFileNo());
			boardFile.setBoardFileSize(multipartFile.getSize());
			boardFile.setBoardFileType(multipartFile.getContentType());
			boardFile.setBoardFileOriginName(originName);
			boardFile.setBoardFileSaveName(saveName);
			boardFile.setBoardFileExt(ext);
			System.out.println(boardFile);
			
			//폴더에 저장
			file = new File(PathStr.UPLOAD_PATH+"/"+saveName+"."+ext);
	
			try {
				multipartFile.transferTo(file);
			} catch (Exception e) {
				e.printStackTrace();
				//catch에서 예외를 삼키므로 예외를 발생해야 한다.
				throw new RuntimeException();//RuntimeException예러 발생.
			}
		}
		return boardFileMapper.updateBoardFile(boardFile);
	}
	
	public int removeBoardFile(int boardFileNo) {//첨부파일 한개 삭재
		BoardFile boardFile = boardFileMapper.selectBoardFile(boardFileNo);
		String saveName = boardFile.getBoardFileSaveName();
		String ext = boardFile.getBoardFileExt();
		File file = new File(PathStr.UPLOAD_PATH+"/"+saveName+"."+ext);
        if(file.exists()) {
        	if(file.delete()) {
        		System.out.println("파일삭제 성공");
        		} else {
        			System.out.println("파일삭제 실패");
        		}
        } else {
        	System.out.println("파일이 존재하지 않습니다.");
        }
		return boardFileMapper.deletBoardFileByFileNo(boardFileNo);
	}
	
	public int removeBoard(Board board) {//글, 댓글, 첨부파일 삭제
		boardCommentMapper.deletBoardCommentByBoardNo(board.getBoardNo());
		List<BoardFile> fileList = boardFileMapper.selectBoardFileListByBoardNo(board.getBoardNo());
		for(int i=0; i<fileList.size(); i++) {
			BoardFile boardFile = fileList.get(i);
			String saveName = boardFile.getBoardFileSaveName();
			String ext = boardFile.getBoardFileExt();
			File file = new File(PathStr.UPLOAD_PATH+"/"+saveName+"."+ext);
	        if(file.exists()) {
	            if(file.delete()) {
	                System.out.println("파일삭제 성공");
	            } else {
	                System.out.println("파일삭제 실패");
	            }
	        } else {
	            System.out.println("파일이 존재하지 않습니다.");
	        }
		}
		boardFileMapper.deletBoardFileByBoardNo(board.getBoardNo());
		return boardMapper.deleteBoard(board);
	}
	
	public int removeBoardComment(BoardComment boardComment) {//댓글삭제
		return boardCommentMapper.deletBoardCommentByCommentNo(boardComment.getBoardCommentNo());
	}
	
	public Board getBoard(int boardNo) {//글 상세보기
		return boardMapper.selectBoard(boardNo);
	}
	
	public List<BoardFile> getFileList(int boardNo) {//첨부파일 리스트
		return boardFileMapper.selectBoardFileListByBoardNo(boardNo);
	}
	
	public Map<String, Object> getBoardAndCommentList(int boardNo) {
		Board board = boardMapper.selectBoard(boardNo);
		List<BoardComment> boardCommentList = boardCommentMapper.selectBoardCommentListByBoardNo(boardNo);
		List<BoardFile> boardFileList = boardFileMapper.selectBoardFileListByBoardNo(boardNo);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("board", board);
		map.put("boardCommentList", boardCommentList);
		map.put("boardFileList", boardFileList);
		return map;
	}
	
	public Map<String, Object> getBoardList(int currentPage) {
		//selectBoardList 쿼리를 실행하기 위하여 map에 beginRow와 rowPerPage put 시작
		final int ROW_PER_PAGE = 10;
		final int BEGIN_ROW = (currentPage-1)*ROW_PER_PAGE;
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("beginRow", BEGIN_ROW);
		map.put("rowPerPage", ROW_PER_PAGE);
		//selectBoardList 쿼리를 실행하기 위하여 map에 beginRow와 rowPerPage put 종료
		
		//selectBoardList 쿼리와 selectBoardCount 쿼리 실행 시작
		List<Board> list = boardMapper.selectBoardList(map);
		final int BOARD_COUNT = boardMapper.selectBoardCount();
		//selectBoardList 쿼리와 selectBoardCount 쿼리 실행 종료
		
		//selectBoardList 쿼리와 selectBoardCount 쿼리 실행 결과를 가공 시작
		int currentTenPage = currentPage/10;
		if(currentPage%10 == 0) {
			currentTenPage--;
		}
		int lastPage = BOARD_COUNT/ROW_PER_PAGE;
		if(BOARD_COUNT%ROW_PER_PAGE !=0) {
			lastPage++;
		}
		int lastTenPage = lastPage/10;
		if(lastTenPage%10 == 0) {
			lastTenPage--;
		}
		int repetitionPage = 10;
		if((lastPage - currentTenPage*10) < 10  ) {
			repetitionPage = lastPage % 10 ;
		}		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("list", list);
		returnMap.put("currentTenPage", currentTenPage);
		returnMap.put("lastTenPage", lastTenPage);
		returnMap.put("repetitionPage", repetitionPage);
		returnMap.put("boardCount", BOARD_COUNT);
		//selectBoardList 쿼리와 selectBoardCount 쿼리 실행 결과를 가공 종료
		return returnMap;//가공된 데이터를 controller에 리턴
	}
	
	public void addBoard(Map<String, Object> map) {
		BoardRequest boardRequest = (BoardRequest)map.get("boardRequest");
		String path = (String)map.get("path");
		// 1. BoardRequest -> Board
		Board board = new Board();
		board.setBoardTitle(boardRequest.getBoardTitle());
		board.setBoardPw(boardRequest.getBoardPw());
		board.setBoardUser(boardRequest.getBoardUser());
		board.setBoardContent(boardRequest.getBoardContent());
		System.out.println(board);//board 출력
		boardMapper.insertBoard(board);
		
		// 2. BoardRequest -< BoardFile
		List<MultipartFile> FileList = boardRequest.getBoardFile();
		for(int n=0; n<FileList.size(); n++) {
			MultipartFile multipartFile = FileList.get(n); 
			if(multipartFile.getSize() != 0) {
				String originFileName = multipartFile.getOriginalFilename();
				int i = originFileName.lastIndexOf(".");
				String originName = multipartFile.getOriginalFilename().substring(0, i);
				String ext = multipartFile.getOriginalFilename().substring(i+1).toLowerCase();
				UUID uuid = UUID.randomUUID();
				String saveName = uuid.toString().replace("-", "");
				BoardFile boardFile = new BoardFile(); 
				boardFile.setBoardNo(board.getBoardNo());
				boardFile.setBoardFileSize(multipartFile.getSize());
				boardFile.setBoardFileType(multipartFile.getContentType());
				boardFile.setBoardFileOriginName(originName);
				boardFile.setBoardFileSaveName(saveName);
				boardFile.setBoardFileExt(ext);
				System.out.println(boardFile);
				
				//폴더에 저장
				File file = new File(path+"/"+saveName+"."+ext);

				try {
					multipartFile.transferTo(file);
				} catch (Exception e) {
					e.printStackTrace();
					//catch에서 예외를 삼키므로 예외를 발생해야 한다.
					throw new RuntimeException();//RuntimeException예러 발생.
				}
				boardFileMapper.insertBoardFile(boardFile);
			}
		}
		
	}
	
	public BoardFile getBoardFile(int boardFileNo) {
		return boardFileMapper.selectBoardFile(boardFileNo);
	}
	
		
}
