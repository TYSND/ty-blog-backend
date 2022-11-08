package com.ty_home_backend.controller;

import com.ty_home_backend.model.article.ArticleModel;
import com.ty_home_backend.model.article.ArticleModelParams;
import com.ty_home_backend.service.article.ArticleService;
import com.ty_home_backend.utils.Response.Pager;
import com.ty_home_backend.utils.Response.ResponseMessage.ResponseMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/article")
@CrossOrigin("*")
@Api(tags = "文章相关")
public class ArticleController {
    @Resource
    ArticleService articleService;

    @GetMapping("/test")
    @ResponseBody
    public String test(){
        return "你好，Spring Boot";
    }

    @ApiOperation("后台-获取全部文章（包括隐藏和未发布的文章）-需要token")
    @PostMapping("/get-all-article-list-page")
    public ResponseMessage getAuthorArticleListPage(@RequestBody ArticleModelParams articleModelParams) {
        return articleService.searchAuthorListByPage(articleModelParams);
    }

    @ApiOperation("前台-获取已发布且未隐藏的文章-不需要token")
//    @ApiImplicitParams({@ApiImplicitParam(name = "userName",value = "用户名",required = true,dataType = "String",paramType="query"),
//            @ApiImplicitParam(name = "password",value = "密码",required = true,dataType = "String",paramType="query")})
    @PostMapping("/get-publish-article-list-page")
    public ResponseMessage getReaderArticleListPage(@RequestBody ArticleModelParams articleModelParams) {
//        System.out.println(pager);
        return articleService.searchReaderListByPage(articleModelParams);
    }
    @PostMapping("/get-all-article-list-all")
    public ResponseMessage getAuthorArticleListAll(@RequestBody ArticleModelParams articleModelParams) {
//        System.out.println(pager);
        return articleService.searchAuthorListByAll(articleModelParams);
    }

//    title, content, create_time, update_time, is_publish, anthology_id
    @PostMapping("/new-article")
    public ResponseMessage newArticle(@RequestBody ArticleModel articleModel) {
        return articleService.newArticle(articleModel);
    }

    @PostMapping("/save-article")
    public ResponseMessage saveArticle(@RequestBody ArticleModel articleModel) {
        return articleService.saveArticle(articleModel);
    }

    @PostMapping("/publish-article")
    public ResponseMessage publishArticle(@RequestBody ArticleModel articleModel) {
        return articleService.publishArticle(articleModel);
    }

    @PutMapping("/move-article")
    public ResponseMessage moveArticle(@RequestParam Integer articleId, @RequestParam Integer targetAnthologyId) {
        return articleService.moveArticle(articleId, targetAnthologyId);
    }

    @DeleteMapping("/delete-article")
    public ResponseMessage deleteArticle(@RequestParam Integer articleId) {
        return articleService.deleteArticle(articleId);
    }

    @GetMapping("/get-article-by-id")
    public ResponseMessage getArticleById(@RequestParam Integer articleId) {
        return articleService.searchArticleDetailById(articleId);
    }
}
