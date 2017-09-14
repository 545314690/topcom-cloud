package com.topcom.cms.yuqing.controller;

import com.topcom.cms.base.web.spring.controller.GenericController;
import com.topcom.cms.base.web.spring.controller.GenericTreeController;
import com.topcom.cms.common.page.PageRequest;
import com.topcom.cms.data.domain.ESBaseDomain;
import com.topcom.cms.domain.User;
import com.topcom.cms.web.bind.annotation.CurrentUser;
import com.topcom.cms.yuqing.domain.Contact;
import com.topcom.cms.yuqing.domain.Library;
import com.topcom.cms.yuqing.domain.Word;
import com.topcom.cms.yuqing.service.ContactManager;
import com.topcom.cms.yuqing.service.LibraryManager;
import com.topcom.cms.yuqing.service.WordManager;
import com.topcom.cms.yuqing.vo.request.ContactRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/library/")
@Api("词库管理接口")
public class LibraryController extends
       GenericTreeController<Library, Long, LibraryManager>
{
    LibraryManager libraryManager;

    @Autowired
    WordManager wordManager;

    @Autowired
    public void setLibraryManager(LibraryManager libraryManager) {
        this.libraryManager = libraryManager;
        this.manager = libraryManager;
        this.treeManager = libraryManager;
    }

    /**
     * 根据类型查询当前登录用户的词库
     *
     * @return 分页数据
     */
    @RequestMapping(value = "/findByUser", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Page<Library> findByUserId(@CurrentUser User user, @RequestBody PageRequest pageRequest) {
        return libraryManager.findByUserId(pageRequest.pageable(), user.getId());
    }


    @RequestMapping(value = "/findByName/{name}", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Object findByName(@CurrentUser User user, @PathVariable String name, @RequestBody PageRequest pageRequest) {
        return libraryManager.findByName(pageRequest.pageable(),name,user.getId());
    }

    @ApiOperation("getTreeByUser")
    @RequestMapping(value = "/getTreeByUser/", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<Library> getTreeByUser(@CurrentUser User user,
                              @ApiParam("id") @RequestParam(required = false) Long id) {
        if (id==null){
            return this.libraryManager.getRootByUserId(user.getId());
        }else {
            return this.libraryManager.findByIdAndUserId(id,user.getId()).getChildren();
        }
    }


}
