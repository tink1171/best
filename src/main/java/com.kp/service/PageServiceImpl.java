package com.kp.service;

import com.kp.model.site.Page;
import com.kp.repository.PageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by user on 8/12/16.
 */
@Service
@Transactional
public class PageServiceImpl implements PageService {

    @Autowired
    private PageRepository pageRepository;

    @Override
    public void savePage(Page page) {
        pageRepository.save(page);
    }
}
