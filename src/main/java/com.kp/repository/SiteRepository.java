package com.kp.repository;

import com.kp.model.site.Site;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by user on 8/20/16.
 */
public interface SiteRepository extends JpaRepository<Site, Long> {
    Site findById(long id);
    Site findBySitename(String sitename);
}
