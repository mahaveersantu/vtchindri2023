package com.VTSangaliya.samitiMember;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "tbl_samiti_member")
public class SamitiMemberEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Transient
	private int srNo;

	//@Column(name = "name")
	private String memberName;
	//@Column(name)
	@Column(name="expd_msg_send")
	private char expdMsgSend;

	private String memberAddress;
	private String memberMobile;
	private String memberDesig;
	private int memberPriority;



	public char getExpdMsgSend() {
		return expdMsgSend;
	}
	public void setExpdMsgSend(char expdMsgSend) {
		this.expdMsgSend = expdMsgSend;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public int getSrNo() {
		return srNo;
	}
	public void setSrNo(int srNo) {
		this.srNo = srNo;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberAddress() {
		return memberAddress;
	}
	public void setMemberAddress(String memberAddress) {
		this.memberAddress = memberAddress;
	}

	public String getMemberMobile() {
		return memberMobile;
	}
	public void setMemberMobile(String memberMobile) {
		this.memberMobile = memberMobile;
	}
	public String getMemberDesig() {
		return memberDesig;
	}
	public void setMemberDesig(String memberDesig) {
		this.memberDesig = memberDesig;
	}
	public int getMemberPriority() {
		return memberPriority;
	}
	public void setMemberPriority(int memberPriority) {
		this.memberPriority = memberPriority;
	}

}
