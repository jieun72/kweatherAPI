package com.API.main.Controller;

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
import com.API.main.Repository.Predict_md1_Repository;
import com.API.main.Exception.ResourceNotFoundException;
@RestController
@RequestMapping("/KESTI/v1.1")
public class Predict_md1_Controller 
{
	@PersistenceContext
	EntityManager em;
	
	@Autowired
	private Predict_md1_Repository predict_md1_Repository;
	
	@GetMapping("/md1")
	public List<predict_md1> getAllmd1()
	{
		return predict_md1_Repository.findAll();
	}
	@GetMapping("/md1/search")
	public List<predict_md1> test(@RequestParam(value="spot_cd",required = false) String spot_cd_val, @RequestParam(value="prd_date",required = false) String prd_date_val, @RequestParam(value="prd_time",required = false) String prd_time_val)
	{
		/*
		 * EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
		 * EntityManager em = emf.createEntityManager();
		 */
		TypedQuery<predict_md1> query = null;
		
		if((spot_cd_val!=null)&&(prd_date_val!=null)&&(prd_time_val!=null))
		{
			query = em.createQuery("FROM predict_md1  WHERE spot_cd=:spot_cd AND prd_date=:prd_date AND prd_time=:prd_time", predict_md1.class)
					.setParameter("spot_cd", spot_cd_val)
					.setParameter("prd_date",prd_date_val)
					.setParameter("prd_time",prd_time_val);
		}
		else if((spot_cd_val!=null)&&(prd_date_val==null)&&(prd_time_val!=null))
		{
			query = em.createQuery("FROM predict_md1  WHERE spot_cd=:spot_cd AND cal_mode=:cal_mode", predict_md1.class)
					.setParameter("spot_cd", spot_cd_val)
					.setParameter("prd_time",prd_time_val);
		}
		else if((spot_cd_val!=null)&&(prd_date_val!=null)&&(prd_time_val==null))
		{
			query = em.createQuery("FROM predict_md1  WHERE spot_cd=:spot_cd AND prd_date=:prd_date", predict_md1.class)
					.setParameter("spot_cd", spot_cd_val)
					.setParameter("prd_date",prd_date_val);
		}
		else if((spot_cd_val==null)&&(prd_date_val!=null)&&(prd_time_val!=null))
		{
			query = em.createQuery("FROM predict_md1  WHERE  prd_date=:prd_date AND prd_time=:prd_time", predict_md1.class)
					.setParameter("prd_date",prd_date_val)
					.setParameter("prd_time",prd_time_val);
		}
		else if((spot_cd_val!=null)&&(prd_date_val==null)&&(prd_time_val==null))
		{
			query = em.createQuery("FROM predict_md1  WHERE spot_cd=:spot_cd", predict_md1.class)
					.setParameter("spot_cd", spot_cd_val);
		}
		else if((spot_cd_val==null)&&(prd_date_val!=null)&&(prd_time_val==null))
		{
			query = em.createQuery("FROM predict_md1  WHERE  prd_date=:prd_date", predict_md1.class)
					.setParameter("prd_date",prd_date_val);
		}
		else if((spot_cd_val==null)&&(prd_date_val==null)&&(prd_time_val!=null))
		{
			query = em.createQuery("FROM predict_md1  WHERE prd_time=:prd_time", predict_md1.class)
					.setParameter("prd_time",prd_time_val);
		}
		
		
		List<predict_md1> list = null;
		if(query != null)
		{
			list = query.getResultList();
		}
		
		return list;
	}
	/*public List<predict_md2> test(@RequestParam("spot_cd") String spot_cd, @RequestParam("prd_date") String prd_date, @RequestParam("cal_mode") String cal_mode)
	{
		if((spot_cd!=null)&&(prd_date!=null)&&(cal_mode!=null))
		{
			if((cal_mode=="AVG")||(cal_mode=="SDD")||(cal_mode=="MIN")||(cal_mode=="MAX"))
			{
				return predict_md2_Repository.findBymdALL(spot_cd, prd_date, cal_mode);
			}
			else
			{
				return null;
			}
		}
		else if((spot_cd!=null)&&(prd_date==null)&&(cal_mode!=null))
		{
			if((cal_mode=="AVG")||(cal_mode=="SDD")||(cal_mode=="MIN")||(cal_mode=="MAX"))
			{
				return predict_md2_Repository.findBycd_mode(spot_cd, cal_mode);
			}
			else
			{
				return null;
			}
		}
		else if((spot_cd!=null)&&(prd_date!=null)&&(cal_mode==null))
		{
			if((cal_mode=="AVG")||(cal_mode=="SDD")||(cal_mode=="MIN")||(cal_mode=="MAX"))
			{
				return predict_md2_Repository.findBycd_date(spot_cd, prd_date);
			}
			else
			{
				return null;
			}
		}
		else if((spot_cd==null)&&(prd_date!=null)&&(cal_mode!=null))
		{
			if((cal_mode=="AVG")||(cal_mode=="SDD")||(cal_mode=="MIN")||(cal_mode=="MAX"))
			{
				return predict_md2_Repository.findBymddate_mode(prd_date, cal_mode);
			}
			else
			{
				return null;
			}
		}
		else if((spot_cd!=null)&&(prd_date==null)&&(cal_mode==null))
		{
			return predict_md2_Repository.findBycd(spot_cd);
		}
		else if((spot_cd==null)&&(prd_date!=null)&&(cal_mode==null))
		{
			return predict_md2_Repository.findBymddate(prd_date);
		}
		else if((spot_cd==null)&&(prd_date==null)&&(cal_mode!=null))
		{
			if((cal_mode=="AVG")||(cal_mode=="SDD")||(cal_mode=="MIN")||(cal_mode=="MAX"))
			{
				return predict_md2_Repository.findBymdmode(cal_mode);
			}
			else
			{
				return null;
			}
		}
		else
		{
			return null;
		}
	}
	
	*/
	/*
	 * @GetMapping("/codegroup/{id}") public ResponseEntity<CodeGroup>
	 * getCodeGroupById(@PathVariable(value = "id") String cd_group_id) throws
	 * ResourceNotFoundException { CodeGroup codegroup =
	 * codegroupRepository.findById(cd_group_id).orElseThrow(()->new
	 * ResourceNotFoundException("CodeGroup not found for this id :: " +
	 * cd_group_id)); return ResponseEntity.ok().body(codegroup); }
	 */
}
