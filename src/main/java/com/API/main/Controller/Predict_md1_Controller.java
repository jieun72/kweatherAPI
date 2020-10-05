package com.API.main.Controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContexts;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.validation.Valid;

import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.API.main.Entity.predict_md1;
import com.API.main.Entity.predict_md2;
import com.API.main.Repository.Predict_md1_Repository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.API.main.Exception.ResourceNotFoundException;
@RestController
@RequestMapping("/timePrdtList")
public class Predict_md1_Controller 
{
	//preety 사용 하기 위한 gson
	private static final Gson gson = new GsonBuilder()
			           .setPrettyPrinting()
			           .create();
			
	@PersistenceContext
	EntityManager em;
	
	@Autowired
	private Predict_md1_Repository predict_md1_Repository;
	
	@GetMapping("/md1")
	public List<predict_md1> getAllmd1()
	{
		return predict_md1_Repository.findAll();
	}
	
	
	@GetMapping("/getMd1List")
	public String getMd1List(@RequestParam HashMap<String,String> paramMap)
	{
		boolean err = false;
		//변수가 제대로 들어왔는지 체크하기 위함
		String spot_cd_val = paramMap.get("spotCd");
		String prd_date_val = paramMap.get("prdDate");
		String prd_time_val = paramMap.get("prdTime");
		
		//obj 에 담기위한 선언
		final JsonObject gobj = new JsonObject();
		//쿼리 사용하기 위한 선언
		List<Object[]> lst=null;
		
		//들어온 개수를 파악하기 위함 ( err code 발생 조건을 채우기 위함)
		int param_num = paramMap.size();
		
		//마지막 에 변환 해주기위한 array
		JSONArray req_array = new JSONArray();
		
		String json="";
		
		
		
		//time 이 1자리숫자로 들어왔을때를 대비
		if(prd_time_val!=null && prd_time_val.length()==1)
		{
			prd_time_val = "0" + prd_time_val;
		}
		
		
		
		if((spot_cd_val!=null)&&(prd_date_val!=null)&&(prd_time_val!=null))
		{
			if(spot_cd_val.length()!=4 || spot_cd_val.length()!=4 || !spot_cd_val.contains("SH") || prd_time_val.length()>2)
			{
				gobj.addProperty("resultCode", "02");
				gobj.addProperty("resultMsg","ERROR");
				gobj.addProperty("totalCount", "0");
				err = true;
			}
			else
			{
				lst = em.createQuery("select cd.spot_nm,md1.prd_date,md1.prd_time,md1.spot_lat,md1.spot_lon,md1.chk_fine_dust,md1.chk_ultrafine_dust,md1.ozone,md1.carbon_monoxide,md1.nitrogen_dioxide,md1.sulfur_dioxide" + 
						"from kweather_api.predict_md1 AS md1, " + 
						"     kweather_api.spot_group_cd AS cd " + 
						"where md1.spot_cd = cd.spot_cd " +
						      "and md1.spot_cd=:spot_cd " +
						      "and md1.prd_date=:prd_date " +
						      "and md1.prd_time=:prd_time ")
						.setParameter("md1.spot_cd", spot_cd_val)
						.setParameter("md1.prd_date",prd_date_val)
						.setParameter("md1.prd_time",prd_time_val).getResultList();
			}
			
		}
		else if((spot_cd_val!=null)&&(prd_date_val==null)&&(prd_time_val!=null))
		{
			if(param_num != 2) // 파라미터는 2개가 들어와야 정상.
			{
				gobj.addProperty("resultCode", "01");
				gobj.addProperty("resultMsg","ERROR");
				gobj.addProperty("count","0");
				err = true;
			}
			else
			{
				if(spot_cd_val.length()!=4 || !spot_cd_val.contains("SH") ||  prd_time_val.length()>2)
				{
					gobj.addProperty("resultCode", "02");
					gobj.addProperty("resultMsg","ERROR");
					gobj.addProperty("totalCount", "0");
					err = true;
				}
				else
				{
					lst = em.createQuery("select cd.spot_nm,md1.prd_date,md1.prd_time,md1.spot_lat,md1.spot_lon,md1.chk_fine_dust,md1.chk_ultrafine_dust,md1.ozone,md1.carbon_monoxide,md1.nitrogen_dioxide,md1.sulfur_dioxide" + 
							"from kweather_api.predict_md1 AS md1, " + 
							"     kweather_api.spot_group_cd AS cd " + 
							"where md1.spot_cd = cd.spot_cd " +
							      "and md1.spot_cd=:spot_cd " +
							      "and md1.prd_time=:prd_time ")
							.setParameter("spot_cd", spot_cd_val)
							.setParameter("prd_time",prd_time_val).getResultList();
				}
			}
			
		}
		else if((spot_cd_val!=null)&&(prd_date_val!=null)&&(prd_time_val==null))
		{
			if(param_num != 2) // 파라미터는 2개가 들어와야 정상.
			{
				gobj.addProperty("resultCode", "01");
				gobj.addProperty("resultMsg","ERROR");
				gobj.addProperty("count","0");
				err = true;
			}
			else
			{
				if(spot_cd_val.length()!=4 || !spot_cd_val.contains("SH") ||  prd_date_val.length()!=8)
				{
					gobj.addProperty("resultCode", "02");
					gobj.addProperty("resultMsg","ERROR");
					gobj.addProperty("totalCount", "0");
					err = true;
				}
				else
				{
					lst = em.createQuery("select cd.spot_nm,md1.prd_date,md1.prd_time,md1.spot_lat,md1.spot_lon,md1.chk_fine_dust,md1.chk_ultrafine_dust,md1.ozone,md1.carbon_monoxide,md1.nitrogen_dioxide,md1.sulfur_dioxide" + 
							"from kweather_api.predict_md1 AS md1, " + 
							"     kweather_api.spot_group_cd AS cd " + 
							"where md1.spot_cd = cd.spot_cd " +
							      "and md1.spot_cd=:spot_cd " +
							      "and md1.prd_date=:prd_date ")
							.setParameter("spot_cd", spot_cd_val)
							.setParameter("prd_date",prd_date_val).getResultList();
				}
			}
			
		}
		else if((spot_cd_val==null)&&(prd_date_val!=null)&&(prd_time_val!=null))
		{
			if(param_num != 2) // 파라미터는 2개가 들어와야 정상.
			{
				gobj.addProperty("resultCode", "01");
				gobj.addProperty("resultMsg","ERROR");
				gobj.addProperty("count","0");
				err = true;
			}
			else
			{
				if(prd_date_val.length()!=8 ||  prd_time_val.length()>2)
				{
					gobj.addProperty("resultCode", "02");
					gobj.addProperty("resultMsg","ERROR");
					gobj.addProperty("totalCount", "0");
					err = true;
				}
				else
				{
					lst = em.createQuery("select cd.spot_nm,md1.prd_date,md1.prd_time,md1.spot_lat,md1.spot_lon,md1.chk_fine_dust,md1.chk_ultrafine_dust,md1.ozone,md1.carbon_monoxide,md1.nitrogen_dioxide,md1.sulfur_dioxide" + 
							"from kweather_api.predict_md1 AS md1," + 
							"     kweather_api.spot_group_cd AS cd " + 
							"where md1.spot_cd = cd.spot_cd " +
							      "and md1.spot_cd=:spot_cd_val " +
							      "and md1.prd_date=:prd_date " +
							      "and md1.prd_time=:prd_time ")
							.setParameter("prd_date",prd_date_val)
							.setParameter("prd_time",prd_time_val).getResultList();
				}
			}
			
			
		}
		else if((spot_cd_val!=null)&&(prd_date_val==null)&&(prd_time_val==null))
		{
			if(param_num != 1) // 파라미터는 1개가 들어와야 정상.
			{
				gobj.addProperty("resultCode", "01");
				gobj.addProperty("resultMsg","ERROR");
				gobj.addProperty("count","0");
				err = true;
			}
			else
			{
				if(spot_cd_val.length()!=4 || !spot_cd_val.contains("SH") )
				{
					gobj.addProperty("resultCode", "02");
					gobj.addProperty("resultMsg","ERROR");
					gobj.addProperty("totalCount", "0");
					err = true;
				}
				else
				{
					lst =em.createQuery("select cd.spot_nm, md1.prd_date, md1.prd_time, md1.spot_lat, md1.spot_lon, md1.chk_fine_dust,md1.chk_ultrafine_dust, md1.ozone, md1.carbon_monoxide, md1.nitrogen_dioxide, md1.sulfur_dioxide " + 
							"from predict_md1 md1," + 
							"     spot_group_cd cd " + 
							"where md1.spot_cd = cd.spot_cd " +
							      "and md1.spot_cd=:spot_cd")
							.setParameter("spot_cd", spot_cd_val).getResultList();
				}
			}
			
		}
		else if((spot_cd_val==null)&&(prd_date_val!=null)&&(prd_time_val==null))
		{
			if(param_num != 1) // 파라미터는 1개가 들어와야 정상.
			{
				gobj.addProperty("resultCode", "01");
				gobj.addProperty("resultMsg","ERROR");
				gobj.addProperty("count","0");
				err = true;
			}
			else
			{
				if(prd_date_val.length()!=8)
				{
					gobj.addProperty("resultCode", "02");
					gobj.addProperty("resultMsg","ERROR");
					gobj.addProperty("totalCount", "0");
					err = true;
				}
				else
				{
					lst = em.createQuery("select cd.spot_nm,md1.prd_date,md1.prd_time,md1.spot_lat,md1.spot_lon,md1.chk_fine_dust,md1.chk_ultrafine_dust,md1.ozone,md1.carbon_monoxide,md1.nitrogen_dioxide,md1.sulfur_dioxide" + 
							"from kweather_api.predict_md1 AS md1," + 
							"     kweather_api.spot_group_cd AS cd " + 
							"where md1.spot_cd = cd.spot_cd " +
							      "and md1.prd_date=:prd_date" )
							.setParameter("prd_date",prd_date_val).getResultList();
				}
			}
			
		}
		else if((spot_cd_val==null)&&(prd_date_val==null)&&(prd_time_val!=null))
		{
			if(param_num != 1) // 파라미터는 1개가 들어와야 정상.
			{
				gobj.addProperty("resultCode", "01");
				gobj.addProperty("resultMsg","ERROR");
				gobj.addProperty("count","0");
				err = true;
			}
			else
			{
				if(prd_time_val.length()>2)
				{
					gobj.addProperty("resultCode", "02");
					gobj.addProperty("resultMsg","ERROR");
					gobj.addProperty("totalCount", "0");
					err = true;
				}
				else
				{
					lst = em.createQuery("select cd.spot_nm,md1.prd_date,md1.prd_time,md1.spot_lat,md1.spot_lon,md1.chk_fine_dust,md1.chk_ultrafine_dust,md1.ozone,md1.carbon_monoxide,md1.nitrogen_dioxide,md1.sulfur_dioxide" + 
							"from kweather_api.predict_md1 AS md1," + 
							"     kweather_api.spot_group_cd AS cd " + 
							"where md1.spot_cd = cd.spot_cd " +
							      "and md1.prd_time=:prd_time")
							.setParameter("prd_time",prd_time_val).getResultList();
				}
			}
		}
		else
		{
			gobj.addProperty("resultCode", "99");
			gobj.addProperty("resultMsg","ERROR");
			gobj.addProperty("count","0");
			
			err = true;
		}
		
		
		
		//에러가 발생 하였다면
		if(!err)
		{
			JsonArray ja = new JsonArray();
			
			
			for(int i=0;i<lst.size();i++)
			{
				final JsonObject gobj_buff = new JsonObject();
				Object[] buff = lst.get(i);
				
				gobj_buff.addProperty("spotNm",buff[0].toString());
				gobj_buff.addProperty("prdDate",buff[1].toString());
				gobj_buff.addProperty("prdTime",buff[2].toString());
				
				gobj_buff.addProperty("spotLat",buff[3].toString());
				gobj_buff.addProperty("spotLon",buff[4].toString());
				gobj_buff.addProperty("pm10",buff[5].toString());
				gobj_buff.addProperty("pm2p5",buff[6].toString());
				gobj_buff.addProperty("o3",buff[7].toString());
				gobj_buff.addProperty("co",buff[8].toString());
				gobj_buff.addProperty("no2",buff[9].toString());
				gobj_buff.addProperty("so2",buff[10].toString());
				
				ja.add(gobj_buff);
			}
			gobj.addProperty("resultCode", "00");
			gobj.addProperty("resultMsg","SUCCESS");
			gobj.addProperty("count",lst.size());
			gobj.add("list",ja);
			
			
			
			//list = query.getResultList();
			//json Object 를 array 에 담은후 해당 내용을 다시 json object 의 하나의 요소로 넣어 뽑기위한 전처리 작업.
			
			
		}
		req_array.add(gobj);
		json= gson.toJson(req_array);
		
		return json;
	}
	
}
