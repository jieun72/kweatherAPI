package com.API.main.Repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.API.main.Entity.predict_md2;

@Repository
public interface Predict_md2_Repository extends JpaRepository<predict_md2, String>
{
}