package com.pjada.GeneratorPdf.repo;

import com.pjada.GeneratorPdf.models.Frame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FrameRepo extends JpaRepository<Frame,Integer> {
}
