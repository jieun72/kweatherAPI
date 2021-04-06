package com.PollutionMd_API.main.CompositeKey;

import java.io.Serializable;

import javax.persistence.Column;

public class Snu_view_md_CompositeKey implements Serializable
{
	@Column(name = "mse_date")
	private String mse_date;
	
	@Column(name = "spot_cd")
	private String spot_cd;
}
