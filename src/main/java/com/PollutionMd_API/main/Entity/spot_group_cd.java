package com.PollutionMd_API.main.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.PollutionMd_API.main.CompositeKey.Spot_group_cd_CompositeKey;

@Entity
@IdClass(Spot_group_cd_CompositeKey.class)
@Table(schema="kweather_api",name="spot_group_cd")
public class spot_group_cd
{
	@Id
	private String upper_cd;
	
	@Id
	private String spot_cd;
	
	private String spot_nm;
	private String reg_dt;
	private String reg_id;
	private String chg_dt;
	private String chg_id;

	public spot_group_cd()
	{
		
	}

	public spot_group_cd(String upper_cd, String spot_cd, String spot_nm, String reg_dt, String reg_id, String chg_dt,
			String chg_id) {
		super();
		this.upper_cd = upper_cd;
		this.spot_cd = spot_cd;
		this.spot_nm = spot_nm;
		this.reg_dt = reg_dt;
		this.reg_id = reg_id;
		this.chg_dt = chg_dt;
		this.chg_id = chg_id;
	}

	@Column(name="upper_cd", nullable=false)
	public String getUpper_cd() {
		return upper_cd;
	}

	public void setUpper_cd(String upper_cd) {
		this.upper_cd = upper_cd;
	}

	@ManyToOne
	@JoinColumn(name="spot_cd", nullable=false)
	public String getSpot_cd() {
		return spot_cd;
	}

	public void setSpot_cd(String spot_cd) {
		this.spot_cd = spot_cd;
	}

	@Column(name="spot_nm", nullable=false)
	public String getSpot_nm() {
		return spot_nm;
	}

	public void setSpot_nm(String spot_nm) {
		this.spot_nm = spot_nm;
	}
	
	@Column(name="reg_dt", nullable=false)
	public String getReg_dt() {
		return reg_dt;
	}

	public void setReg_dt(String reg_dt) {
		this.reg_dt = reg_dt;
	}

	@Column(name="reg_id", nullable=false)
	public String getReg_id() {
		return reg_id;
	}

	public void setReg_id(String reg_id) {
		this.reg_id = reg_id;
	}

	@Column(name="chg_dt", nullable=true)
	public String getChg_dt() {
		return chg_dt;
	}

	public void setChg_dt(String chg_dt) {
		this.chg_dt = chg_dt;
	}

	@Column(name="chg_id", nullable=true)
	public String getChg_id() {
		return chg_id;
	}

	public void setChg_id(String chg_id) {
		this.chg_id = chg_id;
	}

	
	@Override
	public String toString() {
		return "{"
				+" upperCd =" + upper_cd +","
				+" spotCd =" + spot_cd + ","
				+" spotNm =" + spot_nm + "}";
		
	}
	
	
}
