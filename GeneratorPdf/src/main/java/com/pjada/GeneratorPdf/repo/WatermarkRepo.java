package com.pjada.GeneratorPdf.repo;

import com.pjada.GeneratorPdf.models.User;
import com.pjada.GeneratorPdf.models.Watermark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.HashSet;

@Repository
public interface WatermarkRepo extends JpaRepository<Watermark, Integer> {
    HashSet<Watermark> findByUser(User user);
}
