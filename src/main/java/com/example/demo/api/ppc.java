package com.example.demo.api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.*;
import cn.hutool.core.util.RandomUtil;
import org.springframework.web.multipart.MultipartFile;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
@ResponseBody
@RestController
@CrossOrigin
@RequestMapping(method = {RequestMethod.POST,})
public class ppc {
    @Autowired
    JdbcTemplate jdbcTemplate = new JdbcTemplate();
    @ApiOperation(value = "", notes = "登陆验证")
    @RequestMapping("/denglu")//登陆验证
    public boolean denglu(HttpServletResponse response, String username, String pwd){
        try {
            Map<String, Object> res = jdbcTemplate.queryForMap("select * from accounter where username=? and pwd=?", username, pwd);
            return res.size()>0;
        }catch (Exception E){
            E.printStackTrace();
            return false;
        }
    }
    @ApiOperation(value = "", notes = "发送邮件")
    @RequestMapping("/sendmail")//发送邮件
    public boolean sendmails(String email, String code)throws Exception{
        return  util.send(email,code);
    }
    @ApiOperation(value = "", notes = "修改密码")
    @RequestMapping("/xiugai")//修改密码
    public boolean xiugai(String username,String pwd){
        try {
            int update = jdbcTemplate.update("update accounter set pwd=? where username=?", pwd, username);
            return update>0;
        }catch (Exception E){
            E.printStackTrace();
            return false;
        }
    }
    @ApiOperation(value = "", notes = "注册账号")
    @RequestMapping("/zhuce")//注册账号
    public boolean zhuce(String username,String pwd){
        try {
            int update = jdbcTemplate.update("insert into accounter (username,pwd) values (?,?)", username, pwd);
            return update>0;
        }catch (Exception E){
            E.printStackTrace();
            return false;
        }
    }
    @ApiOperation(value = "", notes = "获取头像")
    @RequestMapping("/gte")//获取头像
    public String touxiang(String username)throws Exception{
        Map<String, Object> res = jdbcTemplate.queryForMap("select * from accounter WHERE username=?", username);
        return (String) res.get("picture");
    }
    @ApiOperation(value = "", notes = "查找视频")
    @RequestMapping("/sp1")//查找视频
    public java.util.List<Map<String, Object>> shipin()throws Exception{
      return jdbcTemplate.queryForList("select * from sp");
    }
    @ApiOperation(value = "", notes = "更新浏览量")
    @RequestMapping("/xiangqing1")//更新浏览量
    public int fangwen(int id)throws Exception {
        return jdbcTemplate.update("UPDATE sp set look=look+1 where id=? ", id);
    }
    @ApiOperation(value = "", notes = "更新点赞数")
    @RequestMapping("/xiangqing2")//更新点赞数
    public int good(int id)throws Exception {
        return jdbcTemplate.update("UPDATE sp set good=good+1 where id=? ", id);
    }
    @ApiOperation(value = "", notes = "更新被踩数")
    @RequestMapping("/xiangqing3")//更新被踩数
    public int bad(int id)throws Exception {
        return jdbcTemplate.update("UPDATE sp set bad=bad+1 where id=? ", id);
    }
    @ApiOperation(value = "", notes = "获取评论")
    @RequestMapping("/pl1")//获取评论
    public java.util.List<Map<String, Object>> pinglunlist(int spid)throws Exception{
        return jdbcTemplate.queryForList("select * from pinglun WHERE spid=?",spid);
    }
    @ApiOperation(value = "", notes = "发送评论")
    @RequestMapping("/pl2")//发送评论
    public boolean pinglun(int spid,String yhname,String yhtouxiang,String plneirong,String shijian)throws Exception {
        try {
            int update1= jdbcTemplate.update("insert into pinglun (spid,yhname,yhtouxiang,plneirong,shijian) values (?,?,?,?,?)",spid,yhname,yhtouxiang,plneirong,shijian);
            return update1>0;
        }catch (Exception E){
            E.printStackTrace();
            return false;
        }
    }
    @ApiOperation(value = "", notes = "获取小说")
    @RequestMapping("/xs")//获取小说
    public java.util.List<Map<String, Object>> xiaoshuolist()throws Exception{
        return jdbcTemplate.queryForList("select * from xiaoshuo");
    }
    @ApiOperation(value = "", notes = "发表文章")
    @RequestMapping("/fwz")//发表文章
    public boolean fawen(String xsbiaoti,String xszhengwen,String xstouxiang,String xsjj,String xszuozhe,String xstp){
        try {
            int update = jdbcTemplate.update("insert into xiaoshuo (xsbiaoti,xszhengwen,xstouxiang,xsjj,xszuozhe,xstp) values (?,?,?,?,?,?)",xsbiaoti,xszhengwen, xstouxiang,xsjj,xszuozhe,xstp);
            return update>0;
        }catch (Exception E){
            E.printStackTrace();
            return false;
        }
    }
    @ApiOperation(value = "", notes = "添加收藏")
    @RequestMapping("/sc1")//添加收藏
    public boolean shoucang1(String scname,String scjj,String scsj,String sctupian,String scren,String sczhengwen,int scliulan,int sczan,int sccai,int scspid,String screntx)throws Exception {
        try {
            List<Map<String, Object>> res = jdbcTemplate.queryForList("select * from shoucang WHERE scname=? and scren=?", scname, scren);
            int update;
            if(res.size()<=0) {
                update = jdbcTemplate.update("insert into shoucang (scname,scjj,scsj,sctupian,scren,sczhengwen,scliulan,sczan,sccai,scspid,screntx) values (?,?,?,?,?,?,?,?,?,?,?)", scname, scjj, scsj, sctupian, scren, sczhengwen,scliulan,sczan,sccai,scspid,screntx);
                return update>0;
            }
            else {
                return false;
            }
        }catch (Exception E){
            E.printStackTrace();
            return false;
        }
    }
    @ApiOperation(value = "", notes = "获取收藏")
    @RequestMapping("/sc2")//获取收藏
    public java.util.List<Map<String, Object>> shoucanglist(String scren)throws Exception{
        return jdbcTemplate.queryForList("select * from shoucang WHERE scren=?",scren);
    }
    @ApiOperation(value = "", notes = "添加关注")
    @RequestMapping("/gz1")//添加关注
    public boolean gaunzhu(String woname,String gzname,String wotouxiang,String gztouxiang){
        try {
            List<Map<String, Object>> res = jdbcTemplate.queryForList("select * from guanzhu WHERE gzname=? and woname=?", gzname, woname);
            int update;
            if(res.size()<=0) {
                 update = jdbcTemplate.update("insert into guanzhu (woname,gzname,wotouxiang,gztouxiang,gzsj) values (?,?,?,?,?)", woname, gzname, wotouxiang, gztouxiang, new Date().toString());
                return update > 0;
            }
            else {
                return false;
            }
        }catch (Exception E){
            E.printStackTrace();
            return false;
        }
    }
    @ApiOperation(value = "", notes = "查询粉丝")
    @RequestMapping("/gz2")//查询粉丝
    public java.util.List<Map<String, Object>> fensilist(String gzname)throws Exception{
        return jdbcTemplate.queryForList("select * from guanzhu WHERE gzname=?",gzname);
    }
    @ApiOperation(value = "", notes = "查询关注")
    @RequestMapping("/gz3")//查询关注
    public java.util.List<Map<String, Object>> guaznhulist(String woname)throws Exception{
        return jdbcTemplate.queryForList("select * from guanzhu WHERE woname=?",woname);
    }
    @ApiOperation(value = "", notes = "更新头像")
    @RequestMapping("/gx1")//更新头像
    public boolean gxtouxiang(String picture,String username){
        try {
            int update = jdbcTemplate.update("update accounter set picture=? where username=?",picture,username);
            jdbcTemplate.update("update guanzhu set wotouxiang=? where woname=?",picture,username);
            jdbcTemplate.update("update guanzhu set gztouxiang=? where gzname=?",picture,username);
            jdbcTemplate.update("update pinglun set yhtouxiang=? where yhname=?",picture,username);
            jdbcTemplate.update("update shoucang set screntx=? where scren=?",picture,username);
            jdbcTemplate.update("update xiaoshuo set xstp=? where xszuozhe=?",picture,username);
            return update>0;
        }catch (Exception E){
            E.printStackTrace();
            return false;
        }
    }
    @ApiOperation(value = "", notes = "更新名字")
    @RequestMapping("/gx2")//更新名字
    public boolean gxzhanghu(String username,String picture){
        try {
            System.out.println(username+picture);
            int update = jdbcTemplate.update("update accounter set username=? where picture=?",username,picture);
            jdbcTemplate.update("update guanzhu set woname=? where wotouxiang=?",username,picture);
            jdbcTemplate.update("update guanzhu set gzname=? where gztouxiang=?",username,picture);
            jdbcTemplate.update("update pinglun set yhname=? where yhtouxiang=?",username,picture);
            jdbcTemplate.update("update shoucang set scren=? where screntx=?",username,picture);
            jdbcTemplate.update("update xiaoshuo set xszuozhe=? where xstp=?",username,picture);
            return update>0;
        }catch (Exception E){
            E.printStackTrace();
            return false;
        }
    }
    @ApiOperation(value = "", notes = "我的评论")
    @RequestMapping("/plw")//我的评论
    public java.util.List<Map<String, Object>> wopinglunlist(String yhname)throws Exception{
        return jdbcTemplate.queryForList("select * from pinglun WHERE yhname=?",yhname);
    }
    @ApiOperation(value = "", notes = "我的发表")
    @RequestMapping("/xsw")//我的发表
    public java.util.List<Map<String, Object>> xiaoshuolist(String xszuozhe)throws Exception{
        return jdbcTemplate.queryForList("select * from xiaoshuo WHERE xszuozhe=?",xszuozhe);
    }
    @ApiOperation(value = "", notes = "内容搜索")
    @RequestMapping("/ss1")//内容搜素
    public java.util.List<Map<String, Object>> sousuolist1(String ssnr)throws Exception{
        return jdbcTemplate.queryForList("select * from xiaoshuo WHERE xsbiaoti LIKE '%"+ssnr+"%' or xszhengwen LIKE '%"+ssnr+"%'");
    }
    @ApiOperation(value = "", notes = "内容搜索")
    @RequestMapping("/ss2")//内容搜素
    public java.util.List<Map<String, Object>> sousuolist2(String ssnr)throws Exception{
        return jdbcTemplate.queryForList("select * from sp WHERE name LIKE '%"+ssnr+"%'");
    }
    @ApiOperation(value = "", notes = "文件上传")
    @RequestMapping("/upload")
    public String SingleFileUpLoad(@RequestParam("myfile") MultipartFile file){
    Map<String, String> header = new HashMap<>();
        header.put("origin","https://catbox.moe");
        header.put("referer","https://catbox.moe/");
        HttpResponse execute = HttpUtil.createPost("https://catbox.moe/user/api.php").addHeaders(header).form("reqtype", "fileupload").form("userhash", "").form("fileToUpload", file).form("cookie", "PHPSESSID=" + RandomUtil.randomString(16)).execute();
        return execute.body();
    }
    
}
