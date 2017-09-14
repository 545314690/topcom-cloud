package com.topcom.cms.yuqing.controller;

import com.topcom.cms.base.web.spring.controller.GenericController;
import com.topcom.cms.common.page.PageRequest;
import com.topcom.cms.domain.User;
import com.topcom.cms.exception.NoHandlerFoundException;
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
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/word/")
@Api("词库词语管理接口")
public class WordController extends
        GenericController<Word, Long, WordManager> {
    WordManager wordManager;

    @Autowired
    LibraryManager libraryManager;

    @Autowired
    public void setWordManager(WordManager wordManager) {
        this.wordManager = wordManager;
        this.manager = wordManager;
    }


    @ApiOperation("查询must、should、mustnot")
    @RequestMapping(value = "/searchWordByStatus", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Page<Word> searchWordByStatus(@CurrentUser User user,
                                     @ApiParam("libraryId") @RequestParam(required = false) Long libraryId,
                                     @ApiParam("(MUST SHOULD MUSTNOT)") @RequestParam(required = false) Word.Status status,
                                     @RequestBody PageRequest pageRequest) {
        if (libraryId==null){
            if (status==null){

                return this.wordManager.findByUser(pageRequest.pageable(),user.getId());
            }
            return wordManager.searchWordByStatusAndUser(pageRequest.pageable(),status,user.getId());
        }else {
            if (status==null){
                return this.wordManager.findByLibraryId(pageRequest.pageable(),libraryId,user.getId());
            }
            return wordManager.searchWordByStatus(pageRequest.pageable(),status,libraryId,user.getId());
        }
    }


    @ApiOperation("添加words")
    @RequestMapping(
            value = {"addWordToLibrary"},
            method = {RequestMethod.GET}
    )
    @ResponseBody
    public List<Word> addWordToLibrary(HttpServletRequest request, HttpServletResponse response,
                                      @ApiParam("wordIds") @RequestParam(required = true) Long[] ids,
                                      @ApiParam("LibraryId") @RequestParam(required = true) Long[] libraryIds) {
        List<Word> result = new ArrayList<>();
        if (ids.length>0&&libraryIds.length>0){
            for (int i=0;i<ids.length;i++){
                Long id = ids[i];
                Word word = wordManager.findById(id);
                for (int j=0;j<libraryIds.length;j++){
                    Long libraryId = libraryIds[j];
                    Library library = this.libraryManager.findById(libraryId);
                    word.addLibrary(library);
                    word.setId(Long.valueOf(id.toString()));
                    word.setDateModified(new Date());
                }
                this.wordManager.save(word);
                result.add(word);
            }
        }
        return result;
    }


    @ApiOperation("删除word")
    @RequestMapping(
            value = {"removeWordToLibrary"},
            method = {RequestMethod.GET}
    )
    @ResponseBody
    public List<Word> removeWordToLibrary(HttpServletRequest request, HttpServletResponse response,
                                   @ApiParam("wordIds") @RequestParam(required = true) Long[] ids,
                                   @ApiParam("LibraryId") @RequestParam(required = true) Long[] libraryIds) {

        List<Word> result = new ArrayList<>();
        if (ids.length>0&&libraryIds.length>0){
            for (int i=0;i<ids.length;i++){
                Long id = ids[i];
                Word word = wordManager.findById(id);
                for (int j=0;j<libraryIds.length;j++){
                    Long libraryId = libraryIds[j];
                    Library library = this.libraryManager.findById(libraryId);
                    word.removeLibrary(library);
                    word.setId(Long.valueOf(id.toString()));
                    word.setDateModified(new Date());
                }
                this.wordManager.save(word);
                result.add(word);
            }
        }
        return result;
    }


    @RequestMapping(value = "/findByLibraryName/{name}", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Object findByLibraryName(@CurrentUser User user, @PathVariable String name, @RequestBody PageRequest pageRequest) {
        return this.wordManager.findByLibraryName(pageRequest.pageable(),name,user.getId());
    }


    @RequestMapping(value = "/findByWordName/{name}", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Object findByWordName(@CurrentUser User user, @PathVariable String name, @RequestBody PageRequest pageRequest) {
        return this.wordManager.findByWordName(pageRequest.pageable(),name,user.getId());
    }

    @ApiOperation("查询pinyin(首字母)")
    @RequestMapping(value = "/searchWordByPinyin/{libraryId}", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Object searchWordByPinyin(@CurrentUser User user,
                                     @PathVariable Long libraryId,
                                     @ApiParam("首字母") @RequestParam(required = false) char firstPinyin,
                                     @RequestBody PageRequest pageRequest) {
        return wordManager.searchWordByFirstPinyin(pageRequest.pageable(),firstPinyin,libraryId,user.getId());
    }
}
