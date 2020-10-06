package com.PollutionMd_API.main.Repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.PollutionMd_API.main.CompositeKey.Predict_md2_CompositeKey;
import com.PollutionMd_API.main.Entity.predict_md2;

@Repository
public interface Predict_md2_Repository extends JpaRepository<predict_md2, Predict_md2_CompositeKey>
{
}