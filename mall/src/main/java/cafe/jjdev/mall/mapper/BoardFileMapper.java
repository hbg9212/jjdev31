package cafe.jjdev.mall.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cafe.jjdev.mall.vo.BoardFile;

@Mapper
public interface BoardFileMapper {
	public int insertBoardFile(BoardFile boardFile);
	public List<BoardFile> selectBoardFileListByBoardNo(int boardNo);
	public BoardFile selectBoardFile(int boardFileNo);
	public int deletBoardFileByFileNo(int boardFileNo);
	public int deletBoardFileByBoardNo(int boardNo);
	public int updateBoardFile(BoardFile boardFile);
}
