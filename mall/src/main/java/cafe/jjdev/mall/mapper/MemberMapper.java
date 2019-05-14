package cafe.jjdev.mall.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import cafe.jjdev.mall.vo.Member;

@Mapper
public interface MemberMapper {
	public Member selectIdOverlapCheck(String memberId);
	public int insertMember(Member member);	
	public int insertConsumerNo(int memberNo);
	public int insertMemberId(String memberId);
	public Member selectMemberIdByMemberEmail(String memberEmail);
	public Member selectMemberPwByMemberEmailAndMemberId(Member member);
	public Member selectMember(Member member);
	public Member selectMemberByMemberNo(int memberNo);
	public int updateMemberPw(Map<String, Object> map);
	public int updateMember(Member member);
	public int deleteMember(Member member);
}
