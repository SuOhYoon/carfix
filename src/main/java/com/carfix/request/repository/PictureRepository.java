package com.carfix.request.repository;

import com.carfix.request.entity.PictureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PictureRepository extends JpaRepository<PictureEntity, Long> {
    @Query(value = "select * from picture where :reqidx = reqidx limit 1", nativeQuery = true)
    PictureEntity findPictureByIdOne(int reqidx);

    @Query(value = "select * from picture where :reqidx = reqidx", nativeQuery = true)
    List<PictureEntity> findPictureById(int reqidx);
}
