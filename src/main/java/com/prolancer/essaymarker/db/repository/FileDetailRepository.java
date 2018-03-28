package com.prolancer.essaymarker.db.repository;

import com.prolancer.essaymarker.db.model.FileDetail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileDetailRepository extends CrudRepository<FileDetail, Integer> {

}
