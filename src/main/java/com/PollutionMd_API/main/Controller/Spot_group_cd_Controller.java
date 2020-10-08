package com.PollutionMd_API.main.Controller;

import static org.junit.Assert.assertEquals;

import java.io.Console;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContexts;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.validation.Valid;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.PollutionMd_API.main.Entity.predict_md1;
import com.PollutionMd_API.main.Entity.spot_group_cd;
import com.PollutionMd_API.main.Exception.ErrorDetails;
import com.PollutionMd_API.main.Exception.GlobalExceptionHandler;
import com.PollutionMd_API.main.Exception.ResourceNotFoundException;
import com.PollutionMd_API.main.Repository.Spot_group_cd_Repository;

@RestController
@RequestMapping("/spotCodeList")
public class Spot_group_cd_Controller
{
	private static final Gson gson = new GsonBuilder()
	           .setPrettyPrinting()
	           .create();
	
	
	@PersistenceContext
	EntityManager em;
	
	@Autowired
	private Spot_group_cd_Repository spot_group_cd_Repository;
	
	@GetMapping("/spot_sgcd")
	public List<spot_group_cd> getAllsgcd()
	{
		return spot_group_cd_Repository.findAll();
	}
	@GetMapping("/getSpotList/All")
	public String getSpotList_All()
	{
		final JsonObject gobj = new JsonObject();
		JSONArray req_array = new JSONArray();
		String json="";
		List<spot_group_cd> list = null;
		TypedQuery<spot_group_cd> query = null; // 결과값 을 저장하기 위한 쿼리
		
		query = em.createQuery("FROM spot_group_cd ", spot_group_cd.class);
		
		JsonArray ja = new JsonArray();
		
		list = query.getResultList();
		// json Object 를 array 에 담은후 해당 내용을 다시 json object 의 하나의 요소로 넣어 뽑기위한 전처리 작업.
		for(int i=0;i<list.size();i++)
		{
			final JsonObject gobj_buff = new JsonObject();
			String[] buff = list.get(i).toString().split(",");
			for(int j=0;j<buff.length;j++)
			{
				if(buff[j].toLowerCase().contains("uppercd"))
				{
					gobj_buff.addProperty("upperCd",buff[j].split("=")[1].toString());
				}
				else if(buff[j].toLowerCase().contains("spotcd"))
				{
					gobj_buff.addProperty("spotCd",buff[j].split("=")[1].toString());
				}
				else if(buff[j].toLowerCase().contains("spotnm"))
				{
					gobj_buff.addProperty("spotNm",buff[j].split("=")[1].toString().split("}")[0].toString());
				}
			}
			ja.add(gobj_buff);
		}
		gobj.addProperty("resultCode", "00");
		gobj.addProperty("resultMsg","SUCCESS");
		gobj.addProperty("count",list.size());
		gobj.add("list",ja);
		
		req_array.add(gobj);
		json= gson.toJson(req_array);
		
		return json;
				
	}
	@GetMapping("/getSpotList")
	public String getSpotList( @RequestParam HashMap<String,String> paramMap)
	{
		boolean err = false;
		String upper_cd_val = paramMap.get("upperCd");
		String spot_cd_val = paramMap.get("spotCd");
		JSONObject obj = new JSONObject();
		final JsonObject gobj = new JsonObject();
		
		JSONArray req_array = new JSONArray();
		String json="";
		
		//들어온 개수를 파악하기 위함 ( err code 발생 조건을 채우기 위함)
		int param_num = paramMap.size();
		
		TypedQuery<spot_group_cd> query = null; // 결과값 을 저장하기 위한 쿼리
		
		List<spot_group_cd> list = null;
		
		if((upper_cd_val!=null)&&(spot_cd_val!=null))
		{
			if(upper_cd_val.length()>3 || spot_cd_val.length()>4 || !spot_cd_val.contains("SH") )
			{
				gobj.addProperty("resultCode", "02");
				gobj.addProperty("resultMsg","ERROR");
				gobj.addProperty("totalCount", "0");
				err = true;
			}
			else
			{
				query = em.createQuery("FROM spot_group_cd  WHERE upper_cd=:upper_cd AND spot_cd=:spot_cd", spot_group_cd.class)
						.setParameter("upper_cd", upper_cd_val)
						.setParameter("spot_cd",spot_cd_val);
				
			}
		}
		else if((upper_cd_val==null)&&(spot_cd_val!=null))
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
				if(spot_cd_val.length()>4 || !spot_cd_val.contains("SH"))
				{
					gobj.addProperty("resultCode", "02");
					gobj.addProperty("resultMsg","ERROR");
					gobj.addProperty("totalCount", "0");
					err = true;
				}
				else
				{
					query = em.createQuery("FROM spot_group_cd  WHERE spot_cd=:spot_cd and upper_cd!=spot_cd", spot_group_cd.class)
							.setParameter("spot_cd",spot_cd_val);
				}
			}
		}
		else if((upper_cd_val!=null)&&(spot_cd_val==null))
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
				if(upper_cd_val.length()>3)
				{
					gobj.addProperty("resultCode", "02");
					gobj.addProperty("resultMsg","ERROR");
					gobj.addProperty("count","0");
					err = true;
				}
				else
				{
					query = em.createQuery("FROM spot_group_cd  WHERE upper_cd=:upper_cd and upper_cd!=spot_cd", spot_group_cd.class)
							.setParameter("upper_cd",upper_cd_val);
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
		
		
		if(!err)
		{
			JsonArray ja = new JsonArray();
			
			list = query.getResultList();
			//json Object 를 array 에 담은후 해당 내용을 다시 json object 의 하나의 요소로 넣어 뽑기위한 전처리 작업.
			for(int i=0;i<list.size();i++)
			{
				final JsonObject gobj_buff = new JsonObject();
				String[] buff = list.get(i).toString().split(",");
				for(int j=0;j<buff.length;j++)
				{
					if(buff[j].toLowerCase().contains("uppercd"))
					{
						gobj_buff.addProperty("upperCd",buff[j].split("=")[1].toString());
					}
					else if(buff[j].toLowerCase().contains("spotcd"))
					{
						gobj_buff.addProperty("spotCd",buff[j].split("=")[1].toString());
					}
					else if(buff[j].toLowerCase().contains("spotnm"))
					{
						gobj_buff.addProperty("spotNm",buff[j].split("=")[1].toString().split("}")[0].toString());
					}
				}
				ja.add(gobj_buff);
			}
			
			
			gobj.addProperty("resultCode", "00");
			gobj.addProperty("resultMsg","SUCCESS");
			gobj.addProperty("count",list.size());
			gobj.add("list",ja);
			
		}
		req_array.add(gobj);
		json= gson.toJson(req_array);
		
		return json;
		
		
	}
}
