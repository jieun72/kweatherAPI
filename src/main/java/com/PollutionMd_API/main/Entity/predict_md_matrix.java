package com.PollutionMd_API.main.Entity;


import com.PollutionMd_API.main.CompositeKey.Predict_md_matrix_CompositeKey;

import javax.persistence.*;
import java.sql.Date;

@Entity
@IdClass(Predict_md_matrix_CompositeKey.class)
@Table(schema="kweather_api",name="predict_md_matrix")
public class predict_md_matrix
{
	@Id
	private String prod_date; // 예측 날짜

	@Id
	private String fcst_date; // 지점 코드

	@Id
	private String spot_lat; // 위도

	@Id
	private String spot_lon; // 경도

	private String spot_cd; // 지점 코드
	private String chk_fine_dust; // 미세먼지
	private String chk_ultrafine_dust; // 초 미세먼지
	private String ozone; //오존
	private String carbon_monoxide; // 일산화 탄소
	private String nitrogen_dioxide; // 이산화 질소
	private String sulfur_dioxide; // 이산화 황
	private Date reg_dt; // 등록일자

	public predict_md_matrix()
	{

	}

	public predict_md_matrix(String prod_date, String fcst_date, String spot_cd, String spot_lat, String spot_lon,
                             String chk_fine_dust, String chk_ultrafine_dust, String ozone, String carbon_monoxide,
                             String nitrogen_dioxide, String sulfur_dioxide, Date reg_dt) {
		super();
		this.prod_date = prod_date;
		this.fcst_date = fcst_date;
		this.spot_cd = spot_cd;
		this.spot_lat = spot_lat;
		this.spot_lon = spot_lon;
		this.chk_fine_dust = chk_fine_dust;
		this.chk_ultrafine_dust = chk_ultrafine_dust;
		this.ozone = ozone;
		this.carbon_monoxide = carbon_monoxide;
		this.nitrogen_dioxide = nitrogen_dioxide;
		this.sulfur_dioxide = sulfur_dioxide;
		this.reg_dt = reg_dt;
	}

	
	@Column(name="prod_date", nullable=false)
	public String getProd_date() {
		return prod_date;
	}

	public void setProd_date(String prod_date) {
		this.prod_date = prod_date;
	}
	
	@Column(name="fcst_date", nullable=false)
	public String getFcst_date() {
		return fcst_date;
	}

	public void setFcst_date(String fcst_date) {
		this.fcst_date = fcst_date;
	}

	@Column(name="spot_cd", nullable=false)
	public String getSpot_cd() {
		return spot_cd;
	}

	public void setSpot_cd(String spot_cd) {
		this.spot_cd = spot_cd;
	}

	@Column(name="spot_lat", nullable=false)
	public String getSpot_lat() {
		return spot_lat;
	}

	public void setSpot_lat(String spot_lat) {
		this.spot_lat = spot_lat;
	}

	@Column(name="spot_lon", nullable=false)
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

	@Column(name="reg_dt", nullable=false)
	public Date getReg_dt() {
		return reg_dt;
	}

	public void setReg_dt(Date reg_dt) {
		this.reg_dt = reg_dt;
	}

	@Override
	public String toString() {
		return "predict_md_matrix [prodDate=" + prod_date + ", fcstDate=" + fcst_date + ", spotNm=" + spot_cd
				+ ", spotLat=" + spot_lat + ", spotLon=" + spot_lon + ", pm10=" + chk_fine_dust
				+ ", pm2p5=" + chk_ultrafine_dust + ", o3=" + ozone + ", co=" + carbon_monoxide
				+ ", no2=" + nitrogen_dioxide + ", so2=" + sulfur_dioxide + ", reg_dt=" + reg_dt + "]";
	}
}
	