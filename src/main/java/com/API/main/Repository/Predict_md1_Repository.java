package com.API.main.Repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.API.main.Entity.predict_md1;

@Repository
public interface Predict_md1_Repository extends JpaRepository<predict_md1, String>
{
}