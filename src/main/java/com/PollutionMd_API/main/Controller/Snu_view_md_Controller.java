package com.PollutionMd_API.main.Controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.PollutionMd_API.main.Entity.snu_view_md;
import com.PollutionMd_API.main.Entity.spot_group_cd;
import com.PollutionMd_API.main.Repository.Snu_view_md_Repository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@RestController
@RequestMapping(value = "/pollTraceList",produces = "application/json; charset=utf8")
public class Snu_view_md_Controller 
{
	//preety 사용 하기 위한 gson
	private static final Gson gson = new GsonBuilder()
		           .setPrettyPrinting()
		           .create();
	
	@PersistenceContext
	EntityManager em;
	
	@Autowired
	private Snu_view_md_Repository snu_md2_repository;
	
	@GetMapping("/getPollTraceList")
	public String getMd2List(@RequestParam HashMap<String,String> paramMap)
	{
		String json = "";
		
		boolean err = false;
		//변수가 제대로 들어왔는지 체크하기 위함
		String mse_start_date = paramMap.get("mseStartDate"); // 무조건 들어와야하는 key
		String mse_end_date = paramMap.get("mseEndDate"); // 무조건 들어와야 하는 key
		String spot_cd_val = paramMap.get("spotCd");
		
		//obj 에 담기위한 선언
		final JsonObject gobj = new JsonObject();
		
		//들어온 개수를 파악하기 위함 ( err code 발생 조건을 채우기 위함)
		int param_num = paramMap.size();
		TypedQuery<snu_view_md> query = null; // 결과값 을 저장하기 위한 쿼리
		//마지막 에 변환 해주기위한 array
		JSONArray req_array = new JSONArray();
		
		List<snu_view_md> lst = null;
		
		if(param_num>=2)
		{
			//필수값 2개는 무조건 들어와야 함으로.
			if(mse_start_date != "" && mse_start_date != null && mse_end_date !="" && mse_end_date !=null)
			{
				if(mse_start_date.length() != 8 || mse_end_date.length()!=8)
				{
					int a = mse_start_date.length();
					
					//설정된 길이수가 맞지않음으로 err
					gobj.addProperty("resultCode", "02");
					gobj.addProperty("resultMsg","ERROR");
					gobj.addProperty("totalCount","0");
					err = true;
				}
				else
				{
					try
					{
						//8자리씩 들어왔으나 start 가 end 보다 느릴때... 즉 (start - end) 가 + 가 되면 안된다
						if(Integer.parseInt(mse_start_date) - Integer.parseInt(mse_end_date)<=0)
						{
							if(param_num == 2)
							{
								
								query = em.createQuery("FROM snu_view_md "
						        		+ "WHERE mse_date between :mse_start_date and :mse_end_date ",snu_view_md.class)
										.setParameter("mse_start_date", mse_start_date)
										.setParameter("mse_end_date", mse_end_date);
							}
							else if(param_num == 3)
							{
								if(mse_start_date.length() != 8 || mse_end_date.length()!=8 || spot_cd_val.length()!=1)
								{
									//설정된 길이수가 맞지않음으로 err
									gobj.addProperty("resultCode", "02");
									gobj.addProperty("resultMsg","ERROR");
									gobj.addProperty("totalCount","0");
									err = true;
								}
								
								query = em.createQuery("FROM snu_view_md "
						        		+ "WHERE spot_cd=:spot_cd and mse_date between :mse_start_date and :mse_end_date ",snu_view_md.class)
										.setParameter("spot_cd", spot_cd_val)
										.setParameter("mse_start_date", mse_start_date)
										.setParameter("mse_end_date", mse_end_date);
							}
						}
						else
						{
							gobj.addProperty("resultCode", "02");
							gobj.addProperty("resultMsg","ERROR");
							gobj.addProperty("totalCount","0");
							err = true;
						}
					}
					catch(Exception e)
					{
						gobj.addProperty("resultCode", "02");
						gobj.addProperty("resultMsg","ERROR");
						gobj.addProperty("totalCount","0");
						err = true;
					}
					
					
				}
			}
			else
			{
				//필수값 2개가 들어오지 않고 다른 2개가 들어왔음으로 err
				gobj.addProperty("resultCode", "01");
				gobj.addProperty("resultMsg","ERROR");
				gobj.addProperty("totalCount","0");
				err = true;
			}
			
		}
		else
		{
			//필수로 들어와야하는 항목이 2개. 하지만 최소 중족조건인 2개가 들어오질 않았음으로 err
			gobj.addProperty("resultCode", "01");
			gobj.addProperty("resultMsg","ERROR");
			gobj.addProperty("totalCount","0");
			err = true;
		}
		
		
		
		
		//에러가 발생하지 않았다면
		if(!err)
		{		
			JsonArray ja = new JsonArray();
			
			lst = query.getResultList();
			
			for(int i=0;i<lst.size();i++)
			{
				final JsonObject gobj_buff = new JsonObject();
				String[] buff = lst.get(i).toString().split(",");
				for(int j=0;j<buff.length;j++)
				{
					if(buff[j].toLowerCase().contains("mse_date"))
					{
						gobj_buff.addProperty("mseDate",buff[j].split("=")[1].toString());
					}
					else if(buff[j].toLowerCase().contains("spot_cd"))
					{
						gobj_buff.addProperty("spotCd",buff[j].split("=")[1].toString());
					}
					else if(buff[j].toLowerCase().contains("spot_lat"))
					{
						gobj_buff.addProperty("spotLat",buff[j].split("=")[1].toString());
					}
					else if(buff[j].toLowerCase().contains("spot_lon"))
					{
						gobj_buff.addProperty("spotLon",buff[j].split("=")[1].toString());
					}
					else if(buff[j].toLowerCase().contains("heat_val"))
					{
						gobj_buff.addProperty("heatVal",buff[j].split("=")[1].toString());
					}
					else if(buff[j].toLowerCase().contains("bio_val"))
					{
						gobj_buff.addProperty("bioVal",buff[j].split("=")[1].toString());
					}
					else if(buff[j].toLowerCase().contains("seasalt_val"))
					{
						gobj_buff.addProperty("seasaltVal",buff[j].split("=")[1].toString());
					}
					else if(buff[j].toLowerCase().contains("oil_val"))
					{
						gobj_buff.addProperty("oilVal",buff[j].split("=")[1].toString());
					}
					else if(buff[j].toLowerCase().contains("sulfate2th_val"))
					{
						gobj_buff.addProperty("sulfate2thVal",buff[j].split("=")[1].toString());
					}
					else if(buff[j].toLowerCase().contains("road_val"))
					{
						gobj_buff.addProperty("roadVal",buff[j].split("=")[1].toString());
					}
					else if(buff[j].toLowerCase().contains("nitrate2th_val"))
					{
						gobj_buff.addProperty("nitrate2thVal",buff[j].split("=")[1].toString());
					}
					else if(buff[j].toLowerCase().contains("soil_val"))
					{
						gobj_buff.addProperty("soilVal",buff[j].split("=")[1].toString());
					}
					else if(buff[j].toLowerCase().contains("industry_val"))
					{
						gobj_buff.addProperty("industryVal",buff[j].split("=")[1].toString());
					}
					else if(buff[j].toLowerCase().contains("coal_val"))
					{
						gobj_buff.addProperty("coalVal",buff[j].split("=")[1].toString());
					}
					else if(buff[j].toLowerCase().contains("pm2p5_val"))
					{
						gobj_buff.addProperty("pm2p5Val",buff[j].split("=")[1].toString().split("}")[0].toString());
					}
				}
				ja.add(gobj_buff);	
			}
			gobj.addProperty("resultCode", "00");
			gobj.addProperty("resultMsg","SUCCESS");
			gobj.addProperty("totalCount",lst.size());
			gobj.add("list",ja);
		}
		req_array.add(gobj);
		json= gson.toJson(req_array);
		return json;
	}
}
