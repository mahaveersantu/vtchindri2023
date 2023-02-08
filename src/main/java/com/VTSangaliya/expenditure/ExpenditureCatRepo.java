package com.VTSangaliya.expenditure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenditureCatRepo extends JpaRepository<ExpenditureCatEntity, Integer> {

}
