package com.PollutionMd_API.main.CompositeKey;

import java.io.Serializable;

import javax.persistence.Column;



public class Spot_group_cd_CompositeKey implements Serializable
{
	@Column(name = "upper_cd")
	private String upper_cd;
	
	@Column(name = "spot_cd")
	private String spot_cd;
}
