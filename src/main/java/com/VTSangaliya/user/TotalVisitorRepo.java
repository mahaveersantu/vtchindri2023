package com.VTSangaliya.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TotalVisitorRepo extends JpaRepository<TotalVisitorsEntity, Integer>{

}
