package com.PollutionMd_API.main.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.PollutionMd_API.main.CompositeKey.Predict_md_matrix_CompositeKey;
import com.PollutionMd_API.main.Entity.predict_md_matrix;

@Repository
public interface Predict_md_matrix_Repository extends JpaRepository<predict_md_matrix, Predict_md_matrix_CompositeKey>
{
}