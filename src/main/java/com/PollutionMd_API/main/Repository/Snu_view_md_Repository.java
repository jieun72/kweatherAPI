package com.PollutionMd_API.main.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.PollutionMd_API.main.CompositeKey.Snu_view_md_CompositeKey;
import com.PollutionMd_API.main.Entity.snu_view_md;

public interface Snu_view_md_Repository extends JpaRepository<snu_view_md, Snu_view_md_CompositeKey>
{

}
