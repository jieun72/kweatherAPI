package com.PollutionMd_API.main.Entity;


import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.PollutionMd_API.main.CompositeKey.Predict_md2_CompositeKey;

@Entity
@IdClass(Predict_md2_CompositeKey.class)
@Table(schema="kweather_api",name="predict_md2")
public class predict_md2 
{
	@Id
	private String spot_cd; // 지점 코드
	
	@Id
	private String prd_date; // 예측 날짜
	
	@Id
	private String cal_mode; // 계측 방식
	
	private String spot_lat; // 위도
	private String spot_lon; // 경도
	private String chk_fine_dust; // 미세먼지
	private String chk_ultrafine_dust; // 초 미세먼지
	private String ozone; //오존
	private String carbon_monoxide; // 일산화 탄소
	private String nitrogen_dioxide; // 이산화 질소
	private String sulfur_dioxide; // 이산화 황
	private Date reg_dt; // 등록일자
	private String reg_id; // 등록아이디
	private Date chg_dt; // 수정일자
	private String chg_id; // 수정아이디
	
	public predict_md2()
	{
		
	}

	public predict_md2(String spot_cd, String prd_date, String cal_mode, String spot_lat, String spot_lon,
			String chk_fine_dust, String chk_ultrafine_dust, String ozone, String carbon_monoxide,
			String nitrogen_dioxide, String sulfur_dioxide, Date reg_dt, String reg_id, Date chg_dt, String chg_id) {
		super();
		this.spot_cd = spot_cd;
		this.prd_date = prd_date;
		this.cal_mode = cal_mode;
		this.spot_lat = spot_lat;
		this.spot_lon = spot_lon;
		this.chk_fine_dust = chk_fine_dust;
		this.chk_ultrafine_dust = chk_ultrafine_dust;
		this.ozone = ozone;
		this.carbon_monoxide = carbon_monoxide;
		this.nitrogen_dioxide = nitrogen_dioxide;
		this.sulfur_dioxide = sulfur_dioxide;
		this.reg_dt = reg_dt;
		this.reg_id = reg_id;
		this.chg_dt = chg_dt;
		this.chg_id = chg_id;
	}

	@Column(name="spot_cd", nullable=false)
	public String getSpot_cd() {
		return spot_cd;
	}

	public void setSpot_cd(String spot_cd) {
		this.spot_cd = spot_cd;
	}
	
	@Column(name="prd_date", nullable=false)
	public String getPrd_date() {
		return prd_date;
	}

	public void setPrd_date(String prd_date) {
		this.prd_date = prd_date;
	}
	
	@Column(name="cal_mode", nullable=false)
	public String getCal_mode() {
		return cal_mode;
	}

	public void setCal_mode(String cal_mode) {
		this.cal_mode = cal_mode;
	}
	
	@Column(name="spot_lat", nullable=true)
	public String getSpot_lat() {
		return spot_lat;
	}

	public void setSpot_lat(String spot_lat) {
		this.spot_lat = spot_lat;
	}

	@Column(name="spot_lon", nullable=true)
	public String getSpot_lon() {
		return spot_lon;
	}

	public void setSpot_lon(String spot_lon) {
		this.spot_lon = spot_lon;
	}
	
	@Column(name="chk_fine_dust", nullable=true)
	public String getChk_fine_dust() {
		return chk_fine_dust;
	}

	public void setChk_fine_dust(String chk_fine_dust) {
		this.chk_fine_dust = chk_fine_dust;
	}

	@Column(name="chk_ultrafine_dust", nullable=true)
	public String getChk_ultrafine_dust() {
		return chk_ultrafine_dust;
	}

	public void setChk_ultrafine_dust(String chk_ultrafine_dust) {
		this.chk_ultrafine_dust = chk_ultrafine_dust;
	}

	@Column(name="ozone", nullable=true)
	public String getOzone() {
		return ozone;
	}

	public void setOzone(String ozone) {
		this.ozone = ozone;
	}

	@Column(name="carbon_monoxide", nullable=true)
	public String getCarbon_monoxide() {
		return carbon_monoxide;
	}

	public void setCarbon_monoxide(String carbon_monoxide) {
		this.carbon_monoxide = carbon_monoxide;
	}

	@Column(name="nitrogen_dioxide", nullable=true)
	public String getNitrogen_dioxide() {
		return nitrogen_dioxide;
	}

	public void setNitrogen_dioxide(String nitrogen_dioxide) {
		this.nitrogen_dioxide = nitrogen_dioxide;
	}
	
	@Column(name="sulfur_dioxide", nullable=true)
	public String getSulfur_dioxide() {
		return sulfur_dioxide;
	}

	public void setSulfur_dioxide(String sulfur_dioxide) {
		this.sulfur_dioxide = sulfur_dioxide;
	}
	
/////////////////////////////////////
	
	@Column(name="reg_dt", nullable=false)
	public Date getReg_dt() {
		return reg_dt;
	}

	public void setReg_dt(Date reg_dt) {
		this.reg_dt = reg_dt;
	}
	
	@Column(name="reg_id", nullable=false)
	public String getReg_id() {
		return reg_id;
	}

	public void setReg_id(String reg_id) {
		this.reg_id = reg_id;
	}
///////////////////////////////
	
	@Column(name="chg_dt", nullable=true)
	public Date getChg_dt() {
		return chg_dt;
	}

	public void setChg_dt(Date chg_dt) {
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
		return "predict_md2 [spotNm=" + spot_cd + ", prdDate=" + prd_date + ", calMode=" + cal_mode + ", spotLat="
				+ spot_lat + ", spotLon=" + spot_lon + ", pm10=" + chk_fine_dust + ", pm2p5="
				+ chk_ultrafine_dust + ", o3=" + ozone + ", co=" + carbon_monoxide
				+ ", no2=" + nitrogen_dioxide + ", so2=" + sulfur_dioxide + ", reg_dt=" + reg_dt
				+ ", reg_id=" + reg_id + ", chg_dt=" + chg_dt + ", chg_id=" + chg_id + "]";
	}

	
	
	
	
}
	