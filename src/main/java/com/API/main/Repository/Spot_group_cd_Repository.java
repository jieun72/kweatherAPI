package com.API.main.Repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.API.main.Entity.spot_group_cd;

@Repository
public interface Spot_group_cd_Repository extends JpaRepository<spot_group_cd, String>
{
}