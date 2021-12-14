package com.PollutionMd_API.main.Controller;

import com.PollutionMd_API.main.Entity.predict_md_matrix;
import com.PollutionMd_API.main.Repository.Predict_md_matrix_Repository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping(value = "/mdMatrixList",produces = "application/json; charset=utf8")
public class Predict_md_matrix_Controller
{
	//preety 사용 하기 위한 gson
	private static final Gson gson = new GsonBuilder()
	           .setPrettyPrinting()
	           .create();

	@PersistenceContext
	EntityManager em;
	
	@Autowired
	private Predict_md_matrix_Repository predict_md_matrix_Repository;

	//파라미터 값체크용 어레이
	private static final String[] TYPE_ARRAY = {"pm10","pm2p5","o3","co","no2","so2"};
	
	//검색용 컬럼 맵
	private static final Map<String,String> COLUMN_HASH;
	static {
		Hashtable<String,String> tmp =
				new Hashtable<String,String>();
		tmp.put("pm10","chk_fine_dust");
		tmp.put("pm2p5","chk_ultrafine_dust");
		tmp.put("o3","ozone");
		tmp.put("co","carbon_monoxide");
		tmp.put("no2","nitrogen_dioxide");
		tmp.put("so2","sulfur_dioxide");
		COLUMN_HASH = Collections.unmodifiableMap(tmp);
	}

	@GetMapping("/getMdMatrixList")
	public String getMdMatrixList(@RequestParam HashMap<String,String> paramMap)
	{
		/*
		 * EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
		 * EntityManager em = emf.createEntityManager();
		 */
		boolean err = false;
		//변수가 제대로 들어왔는지 체크하기 위함
		String prd_date_val = paramMap.get("prdDate");
		String poll_type_val = paramMap.get("pollType");

		//obj에 담기위한 선언
		JsonObject gobj = new JsonObject();

		//들어온 개수를 파악하기 위함 (err code 발생 조건을 채우기 위함)
		int param_num = paramMap.size();

		//마지막에 변환 해주기 위한 array
		JSONArray req_array = new JSONArray();

		//json 내용을 string 으로 담기 위함
		String json="";

		//쿼리를 사용하기 위한 선언
		List<Object[]> lst=null;

		//파라미터 체크
		if(param_num != 2)
		{
			//파라미터 갯수 체크
			gobj = errReturn("01");
			err = true;
		}
		else if(prd_date_val==null || prd_date_val.equals("") ||
				poll_type_val==null || poll_type_val.equals(""))
		{
			//파라미터 필수체크
			gobj = errReturn("01");
			err = true;
		}
		else if(prd_date_val.length()!=10 || !(chkType(poll_type_val)))
		{
			//파라미터 값체크
			gobj = errReturn("02");
			err = true;
		}

		try
		{
			if(!err)
			{
				String columnName = COLUMN_HASH.get(poll_type_val);

				lst = em.createQuery("select cd.spot_nm, TO_CHAR(md.fcst_date,'YYYYMMDDHH'), " +
						"md.spot_lat, md.spot_lon, md." + columnName + " " +
						"from predict_md_matrix AS md, " +
						"     spot_group_cd AS cd " +
						"where md.spot_cd = cd.spot_cd " +
						"and md.fcst_date = TO_TIMESTAMP(:fcst_date, 'YYYYMMDDHH') ")
						.setParameter("fcst_date", prd_date_val).getResultList();
			}
		}
		catch (Exception e)
		{
			//그 외 에러
			gobj = errReturn("99");
			err = true;
		}

		//에러가 발생 하지 않았다면
		if(!err)
		{
			
			JsonArray ja = new JsonArray();
			
			for(int i=0;i<lst.size();i++)
			{
				final JsonObject gobj_buff = new JsonObject();
				Object[] buff = lst.get(i);

				gobj_buff.addProperty("spotNm",buff[0].toString());
				gobj_buff.addProperty("fcstDate",buff[1].toString());
				gobj_buff.addProperty("spotLat",buff[2].toString());
				gobj_buff.addProperty("spotLon",buff[3].toString());
				gobj_buff.addProperty(poll_type_val,buff[4].toString());
				
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

	//대기오염 종류체크
	public boolean chkType(String type) {
		if(Arrays.asList(TYPE_ARRAY).contains(type))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	//에러 시의 리턴처리 공통화
	public JsonObject errReturn(String resultCode) {
		JsonObject gobj  = new JsonObject();
		gobj.addProperty("resultCode", resultCode);
		gobj.addProperty("resultMsg","ERROR");
		gobj.addProperty("totalCount","0");
		return gobj;
	}
}
