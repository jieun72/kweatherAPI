package com.PollutionMd_API.main.Repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.PollutionMd_API.main.CompositeKey.Predict_md1_CompositeKey;
import com.PollutionMd_API.main.Entity.predict_md1;

@Repository
public interface Predict_md1_Repository extends JpaRepository<predict_md1, Predict_md1_CompositeKey>
{
}