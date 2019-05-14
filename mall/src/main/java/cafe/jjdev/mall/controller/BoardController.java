package cafe.jjdev.mall.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cafe.jjdev.mall.common.PathStr;
import cafe.jjdev.mall.service.BoardService;
import cafe.jjdev.mall.vo.Board;
import cafe.jjdev.mall.vo.BoardComment;
import cafe.jjdev.mall.vo.BoardFile;
import cafe.jjdev.mall.vo.BoardRequest;


@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/board/removeBoardFile")
	public String boardRemoveBoardFile(BoardFile boardFile) {
		boardService.removeBoardFile(boardFile.getBoardFileNo());
		return "redirect:" + "/board/getBoard?boardNo="+boardFile.getBoardNo();
	}

    @PostMapping(value="/addBoard")//게시판 글 작성
    public String boardAdd(BoardRequest boardRequest) {
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("boardRequest", boardRequest);
    	map.put("path", PathStr.UPLOAD_PATH);
    	boardService.addBoard(map);
        return "redirect:"+ "/getBoardList";
    }

    @GetMapping(value="/addBoard")//게시판 글 작성폼
    public String boardAdd() {
        return "/board/addBoard";
    }
	
	@GetMapping("/board/removeBoard")//게시판 글 삭제폼
	public String removeBoard(Model model
			,@RequestParam(value="boardNo", required=true) int boardNo) {
		Board board = boardService.getBoard(boardNo);
		model.addAttribute("board", board);
	return "/board/removeBoard";
	}
	
	@PostMapping("/board/removeBoard")//게시판 글 삭제
    public String removeBoard(Board board){
    	boardService.removeBoard(board);
        return "redirect:" + "/getBoardList";
    }
	
	@PostMapping("/board/removeBoardComment")//댓글 삭제
	public String removeBoardComment(BoardComment boardComment) {
		boardService.removeBoardComment(boardComment);
		return "redirect:" + "/board/getBoard?boardNo="+boardComment.getBoardNo();
	}
	
	@GetMapping("/board/modifyBoardFile")//게시판 파일 수정폼
	public String modifyBoardFile(Model model
			,@RequestParam(value="boardNo", required=true) int boardNo
			,@RequestParam(value="boardFileNo", required=true) int boardFileNo) {
		BoardFile boardFile = boardService.getBoardFile(boardFileNo);
		model.addAttribute("boardFile", boardFile);
		return "/board/modifyBoardFile";
	}
	
    @PostMapping("/board/modifyBoardFile")//게시판 파일 수정
    public String boardModifyFlie(BoardRequest boardRequest){
    	System.out.println(boardRequest);
    	boardService.modifyBoardFile(boardRequest);
        return "redirect:" + "/board/getBoard?boardNo="+boardRequest.getBoardNo();
    }
	
	@GetMapping("/board/modifyBoard")//게시판 글 수정폼
	public String modifyBoard(Model model
			,@RequestParam(value="boardNo", required=true) int boardNo) {
		Board board = boardService.getBoard(boardNo);
		model.addAttribute("board", board);
		return "/board/modifyBoard";
	}
	
    @PostMapping("/board/modifyBoard")//게시판 글 수정
    public String boardModify(Board board){
    	boardService.modifyBoard(board);
        return "redirect:" + "/board/getBoard?boardNo="+board.getBoardNo();
    }
	
	@GetMapping("/getBoardList")//게시판 글 리스트
	public String getBoardList(
			Model model
			,@RequestParam(value="currentPage", required = false, defaultValue = "1") int currentPage) {
		Map<String, Object> map = boardService.getBoardList(currentPage);
		model.addAttribute("list", map.get("list"));
		model.addAttribute("currentTenPage", map.get("currentTenPage"));
		model.addAttribute("lastTenPage", map.get("lastTenPage"));
		model.addAttribute("repetitionPage", map.get("repetitionPage"));
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("boardCount", map.get("boardCount"));
		return "/board/getBoardList";
	}

	@GetMapping("/board/getBoard")//게시판 글 상세보기
	public String getBoard(Model model
			,@RequestParam(value="boardNo", required = true) int boardNo) {
		Map<String, Object> map = boardService.getBoardAndCommentList(boardNo);
		model.addAttribute("board", map.get("board"));
		model.addAttribute("boardCommentList", map.get("boardCommentList"));
		model.addAttribute("boardFileList", map.get("boardFileList"));
		return "/board/getBoard";
	}
}
