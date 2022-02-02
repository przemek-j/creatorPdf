package com.pjada.GeneratorPdf.repo;

import com.pjada.GeneratorPdf.models.User;
import com.pjada.GeneratorPdf.models.Watermark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;

@Repository
public interface WatermarkRepo extends JpaRepository<Watermark, Integer> {
    HashSet<Watermark> findByUser(User user);

    List<Watermark> findAllByName(String watermark);
    List<Watermark> findAllByUser_Id(Integer id);
}
