package com.PollutionMd_API.main.CompositeKey;

import javax.persistence.Column;
import java.io.Serializable;


public class Predict_md_matrix_CompositeKey implements Serializable
{
	@Column(name = "prod_date")
	private String prod_date;
	
	@Column(name = "fcst_date")
	private String fcst_date;

	@Column(name = "spot_lat")
	private String spot_lat;

	@Column(name = "spot_lon")
	private String spot_lon;
}
