package com.projek.DAO;

import com.projek.Entity.Exporter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExporterRepository extends JpaRepository<Exporter, Integer> {

}
