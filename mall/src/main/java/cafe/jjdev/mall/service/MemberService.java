package cafe.jjdev.mall.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cafe.jjdev.mall.component.EmailComponent;
import cafe.jjdev.mall.mapper.MemberMapper;
import cafe.jjdev.mall.vo.EmailContents;
import cafe.jjdev.mall.vo.Member;

@Service
@Transactional
public class MemberService {
	@Autowired
	private MemberMapper memberMapper;
	@Autowired
	private EmailComponent emailComponent;
	
	public Member getMember(Member member) {
		return memberMapper.selectMember(member);
	}
	
	public int addMember(Member member) {
		memberMapper.insertMember(member);
		memberMapper.insertMemberId(member.getMemberId());
		return memberMapper.insertConsumerNo(member.getMemberNo());
	}
	
	public Member getMemberByMemberNo(int memberNo) {
		return memberMapper.selectMemberByMemberNo(memberNo);
	}
	
	public int modifyMemberPw(Map<String, Object> map) {
		return memberMapper.updateMemberPw(map);
	}
	
	public int modifyMember(Member member) {
		return memberMapper.updateMember(member);
	}
	
	public boolean removeMember(Member member) {
		boolean check = false;
		memberMapper.deleteMember(member);
		if( memberMapper.selectMemberByMemberNo(member.getMemberNo()) == null) {
			System.out.println("member 테이블 및 member_conuser 테이블 데이터 삭제");
			check = true;
		} else {
			System.out.println("삭제 실패");
		}
		return check;
	}
	
	public boolean idOverlapCheck(String memberId) {
		boolean check = false;
		if(memberMapper.selectIdOverlapCheck(memberId) == null) {
			System.out.println("ID 사용가능");
			check = true;
		}
		return check;
	}
	
	public boolean searchId(String memberEmail) {
		boolean check = false;
		if(memberMapper.selectMemberIdByMemberEmail(memberEmail) != null) {
			Member member = memberMapper.selectMemberIdByMemberEmail(memberEmail);
			EmailContents emailContents = new EmailContents();
			emailContents.setToEmailAddress(member.getMemberEmail());
			emailContents.setSubject("요청하신 ID 입니다.");
			emailContents.setText(member.getMemberId());
			emailComponent.searchId(emailContents);
			check = true;
		}
		return check;

	}
	
	public boolean searchPw(Member member) {
		boolean check = false;
		if(memberMapper.selectMemberPwByMemberEmailAndMemberId(member) != null) {
			Member resultMember = memberMapper.selectMemberPwByMemberEmailAndMemberId(member);
			EmailContents emailContents = new EmailContents();
			emailContents.setToEmailAddress(resultMember.getMemberEmail());
			emailContents.setSubject("요청하신 비번 입니다.");
			emailContents.setText(resultMember.getMemberPw());
			emailComponent.searchId(emailContents); 
			check = true;
		}
		return check;
	}
}
