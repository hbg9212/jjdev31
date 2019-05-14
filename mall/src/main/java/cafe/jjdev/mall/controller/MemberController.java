package cafe.jjdev.mall.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cafe.jjdev.mall.service.MemberService;
import cafe.jjdev.mall.vo.Member;

@Controller
public class MemberController {
	@Autowired
	private MemberService memberService;
	
	//로그인 폼
	@GetMapping("/member/login")
	public String getMember(HttpSession session){
		if(session.getAttribute("memberId") != null){
			return "redirect:" + "/";
		} else{
			return "/member/login";
		}
				
	}
	
	//로그인 액션
	@PostMapping("/member/login")
	public String getMember(HttpSession seesion, Member member) {
		Member loginMember = memberService.getMember(member);
		if(loginMember == null) {
			return "redirect:" + "/";
		} else {
			System.out.println("로그인 성공");
			seesion.setAttribute("memberNo", loginMember.getMemberNo());
			seesion.setAttribute("memberId", loginMember.getMemberId());
			seesion.setAttribute("memberName", loginMember.getMemberName());
			seesion.setAttribute("memberLevel", loginMember.getMemberLevel());
			return "redirect:" + "/";
		}
		
	}
	
	//로그아웃
	@GetMapping("/member/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:" + "/";
	}
	
	//회원가입 폼 Get addmember
	@GetMapping("/member/addMember")
	public String addMember() {
		return "/member/addMember";
	}
	
	//회원가입 액션 Post addmember
	@PostMapping("/member/addMember")
	public String addMember(Member member) {
		memberService.addMember(member);
		return "redirect:" + "/";
	}
	
	//개인정보확인
	@GetMapping("/member/myInformation")
	public String myInformation(HttpSession session, Model model
			,@RequestParam(value="memberNo", required=true) int memberNo) {
		if(session.getAttribute("memberId") != null){
			Member member = memberService.getMemberByMemberNo(memberNo);
			model.addAttribute("member", member);
			return "/member/myInformation";
		} else{
			return "/member/login";
		}

	}
	
	//비밀번호 수정 폼
	@GetMapping("/member/modifyMemberPw")
	public String modifyMemberPw(HttpSession session) {
		if(session.getAttribute("memberId") != null){
			return "/member/modifyMemberPw";
		} else{
			return "/member/login";
		}

	}
	
	//비밀번호 수정 액션
	@PostMapping("/member/modifyMemberPw")
	public String modifyMemberPw(HttpSession session
			,@RequestParam(value="memberNo", required=true) int memberNo
			,@RequestParam(value="previousMemberPw", required=true) String previousMemberPw
			,@RequestParam(value="newMemberPw", required=true) String newMemberPw) {
		if(session.getAttribute("memberId") != null){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("memberNo", memberNo);
			map.put("previousMemberPw", previousMemberPw);
			map.put("newMemberPw", newMemberPw);
			memberService.modifyMemberPw(map);
			return "redirect:" + "/member/myInformation?memberNo=" + memberNo;
		} else{
			return "/member/login";
		}

	}
	
	//회원정보 수정 폼
	@GetMapping("/member/modifyMember")
	public String modifyMember(HttpSession session, Model model
			,@RequestParam(value="memberNo", required=true) int memberNo) {
		if(session.getAttribute("memberId") != null){
			Member member = memberService.getMemberByMemberNo(memberNo);
			model.addAttribute("member", member);
			return "/member/modifyMember";
		} else{
			return "/member/login";
		}

	}
	
	//회원정보 수정 액션
	@PostMapping("/member/modifyMember")
	public String modifyMember(HttpSession session, Member member) {
		if(session.getAttribute("memberId") != null){
			memberService.modifyMember(member);
			return "redirect:" + "/member/myInformation?memberNo=" + member.getMemberNo();
		
		} else{
			return "/member/login";
		}
	}
	
	//회원탈퇴 폼
	@GetMapping("/member/removeMember")
	public String removeMember(HttpSession session) {
		if(session.getAttribute("memberId") != null){
			return "/member/removeMember";
		} else{
			return "/member/login";
		}

	}
	
	//회원탈퇴 액션
	@PostMapping("/member/removeMember")
	public String removeMember(HttpSession session
							, Model model
							, Member member) {
		String re =  "/member/login";
		if(session.getAttribute("memberId") != null){
			if (memberService.removeMember(member) == true) {
				session.invalidate();
				re = "redirect:" + "/";
			} else {
				String message = "비밀번호 오류";
				model.addAttribute("message", message);
				re = "/member/removeMember";
			}
			
		}
		return re;
	}
	
	//아이중복채크 폼
	@GetMapping("/member/idOverlapCheck")
	public String idOverlapCheck() {
		return "/member/idOverlapCheck";
	}
	
	//아이디 중복채크
	@PostMapping("/member/idOverlapCheck")
	public String idOverlapCheck(Model model, Member member) {
		System.out.println("입력받은 ID\t"+member.getMemberId());
		boolean check = memberService.idOverlapCheck(member.getMemberId());
		String message = "";
		String re =  "/member/idOverlapCheck";
		if (check == false) {
			System.out.println("ID 중복사용");
			message = "이미 사용중인 ID 입니다.";
		} else {
			re = "/member/addMember";
		}
		model.addAttribute("member", member);
		model.addAttribute("message", message);
		return re;
	}
	
	//아이디 찾기 폼
	@GetMapping("/member/searchId")
	public String searchId() {
		return "/member/searchId";
	}
	
	//아이디 찾기 액션
	@PostMapping("/member/searchId")
	public String searchId(Model model, Member member) {
		String message = "";
		String re =  "/member/searchId";
		if (memberService.searchId(member.getMemberEmail()) == false) {
			message = "입력하신 이메일로 가입한 아이디가 없습니다.";
		} else {
			re = "redirect:" + "/";
		}
		model.addAttribute("message", message);
		return re;
	}
	//비밀번호 찾기 폼
	@GetMapping("/member/searchPw")
	public String searchPw() { 
		return "/member/searchPw";
	}
	
	//비밀번호 찾기 액션
	@PostMapping("/member/searchPw") 
	public String searchPw(Model model, Member member) {
		String message = "";
		String re =  "/member/searchPw";
		if (memberService.searchPw(member) == false) {
			message = "아이디와 이메일을 찾을수 업습니다.";
		} else {
			re = "redirect:" + "/";
		}
		model.addAttribute("message", message);
		return re;
	}
		 
	//member_out_id 테이블 생성 후 탈퇴시 사용ID 저장
	
	
}
