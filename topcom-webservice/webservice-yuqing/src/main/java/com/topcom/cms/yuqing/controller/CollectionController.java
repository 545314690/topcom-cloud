package com.topcom.cms.yuqing.controller;

import com.topcom.cms.base.web.spring.controller.GenericController;
import com.topcom.cms.domain.User;
import com.topcom.cms.web.bind.annotation.CurrentUser;
import com.topcom.cms.yuqing.domain.Collection;
import com.topcom.cms.yuqing.domain.MediaType;
import com.topcom.cms.yuqing.service.CollectionManager;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/collection/")
public class CollectionController extends
        GenericController<Collection, Long, CollectionManager> {
    CollectionManager collectionManager;

    @Autowired
    public void setCollectionManager(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
        this.manager = collectionManager;
    }

    /**
     * 查询当前登录用户的Keywords
     *
     * @return 分页数据
     */
    @RequestMapping(value = "/findByUser", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Page<Collection> findByUserId(@CurrentUser User user, @RequestBody com.topcom.cms.common.page.PageRequest pageRequest) {
        return collectionManager.findByUserId(pageRequest.pageable(), user.getId());
    }

    /**
     * 根据输入，返回分页结果中的当前页，包括当前页信息和其中的实体对象集合
     *
     * @param request
     * @param response
     * @param type
     * @return
     */
    @RequestMapping(value = "/findByType.json", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Page<Collection> findByType(HttpServletRequest request,
                                       HttpServletResponse response, MediaType type) {
        String page = request.getParameter("page");
        String limit = request.getParameter("limit");
        String keywords = request.getParameter("keywords");
        Page<Collection> cPage = null;
        if (StringUtils.isNotBlank(page)) {
            this.pageNumber = Integer.valueOf(page) - 1;
        } else {
            this.pageNumber = 0;
        }
        if (StringUtils.isNotBlank(limit)) {
            this.pageSize = Integer.valueOf(limit);
        }
        this.pageable = new PageRequest(this.pageNumber, this.pageSize,
                new Sort(Direction.DESC, "dateCreated"));
        if (StringUtils.isBlank(keywords)) {
            keywords = "";
        }
        cPage = this.manager.findByType(this.pageable, type, keywords);
        logger.info(cPage);
        return cPage;
    }

    /**
     * 根据输入，返回分页结果中的当前页，包括当前页信息和其中的实体对象集合
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/isCollected.json", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public boolean isCollected(HttpServletRequest request,
                               HttpServletResponse response, String oId) {
        boolean isCollected = false;
        Collection collection = this.collectionManager.findByOId(oId);
        if (collection != null) {
            isCollected = true;
        }
        return isCollected;
    }
}
