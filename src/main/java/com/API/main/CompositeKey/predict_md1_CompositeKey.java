package com.API.main.CompositeKey;

import java.io.Serializable;

import javax.persistence.Column;

public class predict_md1_CompositeKey implements Serializable
{
	@Column(name = "spot_cd")
	private String spot_cd; // 지점 코드
	
	@Column(name = "prd_date")
	private String prd_date; // 예측 날짜
	
	@Column(name = "prd_time")
	private String prd_time; // 계측 방식
}
