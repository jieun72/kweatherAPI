package com.PollutionMd_API.main.Entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.PollutionMd_API.main.CompositeKey.Snu_view_md_CompositeKey;

@Entity
@IdClass(Snu_view_md_CompositeKey.class)
@Table(schema="kweather_api",name="snu_view_md")
public class snu_view_md
{
	@Id
	String mse_date;
	@Id
	String spot_cd;

	String spot_lat;
	String spot_lon;
	String heat_val;
	String bio_val;
	String seasalt_val;
	String oil_val;
	String sulfate2th_val;
	String road_val;
	String nitrate2th_val;
	String soil_val;
	String industry_val;
	String coal_val;
	String pm2p5_val;
	Date reg_dt;
	String reg_id;
	Date chg_dt;
	String chg_id;
	String mobile_val;
	String etcl_val;

	public snu_view_md()
	{

	}

	public snu_view_md(String mse_date, String spot_cd, String spot_lat, String spot_lon, String heat_val,
			String bio_val, String seasalt_val, String oil_val, String sulfate2th_val, String road_val,
			String nitrate2th_val, String soil_val, String industry_val, String coal_val, String pm2p5_val,
			Date reg_dt, String reg_id, Date chg_dt, String chg_id, String mobile_val, String etcl_val) {
		super();
		this.mse_date = mse_date;
		this.spot_cd = spot_cd;
		this.spot_lat = spot_lat;
		this.spot_lon = spot_lon;
		this.heat_val = heat_val;
		this.bio_val = bio_val;
		this.seasalt_val = seasalt_val;
		this.oil_val = oil_val;
		this.sulfate2th_val = sulfate2th_val;
		this.road_val = road_val;
		this.nitrate2th_val = nitrate2th_val;
		this.soil_val = soil_val;
		this.industry_val = industry_val;
		this.coal_val = coal_val;
		this.pm2p5_val = pm2p5_val;
		this.reg_dt = reg_dt;
		this.reg_id = reg_id;
		this.chg_dt = chg_dt;
		this.chg_id = chg_id;
		this.mobile_val = mobile_val;
		this.etcl_val = etcl_val;
	}

	@Column(name="mse_date", nullable=false)
	public String getMse_date() {
		return mse_date;
	}

	public void setMse_date(String mse_date) {
		this.mse_date = mse_date;
	}

	@Column(name="spot_cd", nullable=false)
	public String getSpot_cd() {
		return spot_cd;
	}

	public void setSpot_cd(String spot_cd) {
		this.spot_cd = spot_cd;
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

	@Column(name="heat_val", nullable=true)
	public String getHeat_val() {
		return heat_val;
	}

	public void setHeat_val(String heat_val) {
		this.heat_val = heat_val;
	}

	@Column(name="bio_val", nullable=true)
	public String getBio_val() {
		return bio_val;
	}

	public void setBio_val(String bio_val) {
		this.bio_val = bio_val;
	}

	@Column(name="seasalt_val", nullable=true)
	public String getSeasalt_val() {
		return seasalt_val;
	}

	public void setSeasalt_val(String seasalt_val) {
		this.seasalt_val = seasalt_val;
	}

	@Column(name="oil_val", nullable=true)
	public String getOil_val() {
		return oil_val;
	}

	public void setOil_val(String oil_val) {
		this.oil_val = oil_val;
	}

	@Column(name="sulfate2th_val", nullable=true)
	public String getSulfate2th_val() {
		return sulfate2th_val;
	}

	public void setSulfate2th_val(String sulfate2th_val) {
		this.sulfate2th_val = sulfate2th_val;
	}

	@Column(name="road_val", nullable=true)
	public String getRoad_val() {
		return road_val;
	}

	public void setRoad_val(String road_val) {
		this.road_val = road_val;
	}

	@Column(name="nitrate2th_val", nullable=true)
	public String getNitrate2th_val() {
		return nitrate2th_val;
	}

	public void setNitrate2th_val(String nitrate2th_val) {
		this.nitrate2th_val = nitrate2th_val;
	}

	@Column(name="soil_val", nullable=true)
	public String getSoil_val() {
		return soil_val;
	}

	public void setSoil_val(String soil_val) {
		this.soil_val = soil_val;
	}

	@Column(name="industry_val", nullable=true)
	public String getIndustry_val() {
		return industry_val;
	}

	public void setIndustry_val(String industry_val) {
		this.industry_val = industry_val;
	}

	@Column(name="coal_val", nullable=true)
	public String getCoal_val() {
		return coal_val;
	}

	public void setCoal_val(String coal_val) {
		this.coal_val = coal_val;
	}

	@Column(name="pm2p5_val", nullable=true)
	public String getPm2p5_val() {
		return pm2p5_val;
	}

	public void setPm2p5_val(String pm2p5_val) {
		this.pm2p5_val = pm2p5_val;
	}

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

	@Column(name="mobile_val", nullable=true)
	public String getMobile_val() {
		return mobile_val;
	}

	public void setMobile_val(String mobile_val) {
		this.mobile_val = mobile_val;
	}

	@Column(name="etcl_val", nullable=true)
	public String getEtcl_val() {
		return etcl_val;
	}

	public void setEtcl_val(String etcl_val) {
		this.etcl_val = etcl_val;
	}

	@Override
	public String toString() {
		return "{mse_date=" + mse_date + ", spot_cd=" + spot_cd + ", spot_lat=" + spot_lat + ", spot_lon="  + spot_lon
				+ ", industry_val=" + industry_val
				+ ", coal_val=" + coal_val
				+ ", sulfate2th_val=" + sulfate2th_val
				+ ", mobile_val=" + mobile_val
				+ ", nitrate2th_val=" + nitrate2th_val
				+ ", soil_val=" + soil_val
				+ ", seasalt_val=" + seasalt_val
				+ ", etcl_val=" + etcl_val
				+ ", pm2p5_val=" + pm2p5_val
				+ "}";
	}

}
