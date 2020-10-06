package com.PollutionMd_API.main.Controller;

import static org.junit.Assert.assertEquals;

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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.PollutionMd_API.main.Entity.predict_md1;
import com.PollutionMd_API.main.Entity.predict_md2;
import com.PollutionMd_API.main.Entity.spot_group_cd;
import com.PollutionMd_API.main.Exception.ResourceNotFoundException;
import com.PollutionMd_API.main.Repository.Predict_md2_Repository;
@RestController
@RequestMapping("/dayPrdtList")
public class Predict_md2_Controller 
{
	//preety 사용 하기 위한 gson
	private static final Gson gson = new GsonBuilder()
	           .setPrettyPrinting()
	           .create();
	
	
	@PersistenceContext
	EntityManager em;
	
	@Autowired
	private Predict_md2_Repository predict_md2_Repository;
	
	@GetMapping("/md2")
	public List<predict_md2> getAllmd2()
	{
		return predict_md2_Repository.findAll();
	}
	
	@GetMapping("/getMd2List")
	public String getMd2List(@RequestParam HashMap<String,String> paramMap)
	{
		/*
		 * EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
		 * EntityManager em = emf.createEntityManager();
		 */
		boolean err = false;
		//변수가 제대로 들어왔는지 체크하기 위함
		String spot_cd_val = paramMap.get("spotCd");
		String prd_date_val = paramMap.get("prdDate");
		
		//obj 에 담기위한 선언
		final JsonObject gobj = new JsonObject();
		
		//들어온 개수를 파악하기 위함 ( err code 발생 조건을 채우기 위함)
		int param_num = paramMap.size();
		
		//마지막 에 변환 해주기위한 array
		JSONArray req_array = new JSONArray();
		
		String json="";
		
		List<Object[]> lst=null;
		
		
		if((spot_cd_val!=null)&&(prd_date_val!=null))
		{
			if(spot_cd_val.length()!=4 || prd_date_val.length()!=8 || !spot_cd_val.contains("SH"))
			{
				gobj.addProperty("resultCode", "02");
				gobj.addProperty("resultMsg","ERROR");
				gobj.addProperty("count","0");
				err = true;
			}
			else
			{
				lst = em.createQuery("select cd.spot_nm,md2.prd_date,md2.cal_mode,md2.spot_lat,md2.spot_lon,md2.chk_fine_dust,md2.chk_ultrafine_dust,md2.ozone,md2.carbon_monoxide,md2.nitrogen_dioxide,md2.sulfur_dioxide " + 
						"from predict_md2 AS md2, " + 
						"     spot_group_cd AS cd " + 
						"where md2.spot_cd = cd.spot_cd " +
						      "and md2.spot_cd=:spot_cd " +
						      "and md2.prd_date=:prd_date ")
						.setParameter("spot_cd", spot_cd_val)
						.setParameter("prd_date",prd_date_val).getResultList();
			}
			
		}
		else if((spot_cd_val!=null)&&(prd_date_val==null))
		{
			if(param_num >=2 ) // 쿼리는 2개가 들어왔지만 upper_cd_val 은 null 이라 한다. 그러므로 err 발생
			{
				gobj.addProperty("resultCode", "01");
				gobj.addProperty("resultMsg","ERROR");
				gobj.addProperty("count","0");
				err = true;
			}
			else
			{
				if(spot_cd_val.length()!=4 || !spot_cd_val.contains("SH"))
				{
					gobj.addProperty("resultCode", "02");
					gobj.addProperty("resultMsg","ERROR");
					gobj.addProperty("totalCount", "0");
					err = true;
				}
				else
				{
					lst = em.createQuery("select cd.spot_nm, md2.prd_date, md2.cal_mode, md2.spot_lat, md2.spot_lon, md2.chk_fine_dust, md2.chk_ultrafine_dust, md2.ozone, md2.carbon_monoxide, md2.nitrogen_dioxide, md2.sulfur_dioxide " + 
							"from predict_md2 md2, " + 
								"spot_group_cd cd " + 
							"where md2.spot_cd = cd.spot_cd " +
							      "and md2.spot_cd=:spot_cd" )
							.setParameter("spot_cd", spot_cd_val).getResultList();
					
				}
				
			}
			
		}
		else if((spot_cd_val==null)&&(prd_date_val!=null))
		{
			if(param_num >=2 ) // 쿼리는 2개가 들어왔지만 upper_cd_val 은 null 이라 한다. 그러므로 err 발생
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
					gobj.addProperty("count","0");
					err = true;
				}
				lst = em.createQuery("select cd.spot_nm,md2.prd_date,md2.cal_mode,md2.spot_lat,md2.spot_lon,md2.chk_fine_dust, md2.chk_ultrafine_dust, md2.ozone,md2.carbon_monoxide,md2.nitrogen_dioxide,md2.sulfur_dioxide " + 
						"from predict_md2 AS md2, " + 
						"     spot_group_cd AS cd " + 
						"where md2.spot_cd = cd.spot_cd " +
						      "and md2.prd_date=:prd_date ")
						.setParameter("prd_date",prd_date_val).getResultList();
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
				gobj_buff.addProperty("calMode",buff[2].toString());
				
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
			
		}
		req_array.add(gobj);
		json= gson.toJson(req_array);
		
		return json;
	}
}
