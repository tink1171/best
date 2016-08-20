package com.kp.repository;

import com.kp.model.site.Page;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by user on 8/12/16.
 */

public interface PageRepository extends JpaRepository<Page , Long>{
    Page findById(long id);
}
