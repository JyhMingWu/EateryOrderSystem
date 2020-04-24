package com.nanbo.ordersystem.controller;

import com.nanbo.ordersystem.entity.Alliance;
import com.nanbo.ordersystem.entity.Result.Data;
import com.nanbo.ordersystem.entity.Result.Results;
import com.nanbo.ordersystem.server.AllianceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.javassist.bytecode.stackmap.TypeData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@ApiIgnore
@CrossOrigin() //允许跨域
@Api(value="联盟-controller",tags={"高校联盟alliance-controller"},position = 1)
public class AllianceController {

    private static final Logger log = LoggerFactory.getLogger(TypeData.ClassName.class);

    @Autowired
    private AllianceService allianceService;

    @ApiOperation(value="获取所有高校联盟",position = 1,notes = "获取所有高校联盟")
    @RequestMapping(value = "/Alliance/GetAllAlliance",method = RequestMethod.GET)
    public Data GetAllAlliance(){
        Data data=new Data();
        try {
            List<Alliance> AllianceList=allianceService.GetAllAlliance();
            if(AllianceList.size()==0){
                data.setMessage("当前数据为空！");
                data.setStatus(true);
            }else{
                data.setData(AllianceList);
                data.setMessage("高校联盟获取成功！");
                data.setStatus(true);
            }
        }catch (Exception e){
            data.setMessage("高校联盟获取出现异常："+e);
            data.setStatus(false);
        }
        return data;
    }


    //需要--修改联盟权限(因为修改之前需要获得联盟详细数据)
    @ApiOperation(value="获取单条高校联盟数据",position =2,notes = "获取单条高校联盟数据")
    @RequestMapping(value = "/Alliance/GetOneAllianceData",method = RequestMethod.GET)
    @ApiImplicitParam(name="alliance_id",value="高校联盟ID标识",dataType="int", paramType = "query",required = true)
    public Results GetOneAllianceData(int alliance_id){
        Results results=new Results();
        try {
            List<Alliance> AllianceList=allianceService.GetOneAllianceData(alliance_id);
            if(AllianceList.size()==0){
                results.setMessage("当前数据为空！");
                results.setStatus(true);
            }else{
                results.setData(AllianceList);
                results.setMessage("获取成功！");
                results.setStatus(true);
            }
        }catch (Exception e){
            results.setMessage("获取单条高校联盟数据异常："+e);
            results.setStatus(false);
        }
        return results;
    }

    //需要--增加高校联盟权限
//    @RequiresPermissions("addAlliance")
    @ApiOperation(value="增加高校联盟",position =3,notes = "***必看:该接口文档框架中只提供单文件上传" +
            "另外，该接口还包含着命名为：alliance_name(联盟名称【String类型】)和alliance_address(联盟地址【true/false】)和alliance_telephone(联盟联系电话【String类型】)，可通过使用JS的formData提交方式进行提交)，此几项都是必传的***")
    @RequestMapping(value = "/Academy/AddAlliance",method = RequestMethod.POST)
    @ApiImplicitParam(name="file",value="formData类型参数【详见-接口描述】",dataType="formData", paramType = "query",required = true)
    public Results AddAlliance(@RequestParam("file") MultipartFile file, HttpServletRequest request){
        Results results=new Results();
        Alliance alliance=new Alliance();
        String alliance_name =  request.getParameter("alliance_name");
        String alliance_address =  request.getParameter("alliance_address");
        String alliance_telephone =  request.getParameter("alliance_telephone");

        if(alliance_name.equals("")||alliance_address.equals("")||alliance_telephone.equals("")){
            results.setStatus(false);
            results.setMessage("输入参数不正常！缺少带有参数项");
        }else{
            alliance.setAlliance_name(alliance_name);
            alliance.setAlliance_address(alliance_address);
            alliance.setAlliance_telephone(alliance_telephone);

            if (file.isEmpty()) {
                results.setMessage("上传的文件不能为空");
                return results;
            }
            try {
                //1、先获取jar所在同级目录
                File path = new File(ResourceUtils.getURL("classpath:").getPath());
                if(!path.exists()){
                    path = new File("");
                }
                System.out.println("path:"+path.getAbsolutePath());
                //2、如果上传目录为/static/images/upload/，则可以如下获取：
                File upload = new File(path.getAbsolutePath(),"static/images/upload/Alliance_img/");
                if(!upload.exists()){
                    upload.mkdirs();
                }
                System.out.println("上传的路径 url:"+upload.getAbsolutePath());

                //3、文件前缀追加时间标志
                Date d = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-kk-mm-ss");
                String DateNow = sdf.format(d);
                // 如果不存在该路径就创建
                String uploadPath=upload+"/";
                uploadPath=uploadPath.replace("\\","/");
                File dir = new File(uploadPath +DateNow+"_"+ file.getOriginalFilename());
                // 文件写入
                file.transferTo(dir);
                //在开发测试模式时，得到的地址为：{项目根目录}/target/static/images/upload/
                //在打包成jar正式发布时，得到的地址为：{发布jar包目录}/static/images/upload/
                String[] imgURL=(uploadPath +DateNow+"_"+  file.getOriginalFilename()).split("static");
                alliance.setAlliance_img("http://172.16.1.79:9417"+imgURL[1]);
                int i=allianceService.AddAlliance(alliance);
                if(i==1){
                    results.setStatus(true);
                    results.setMessage("高校联盟增加成功");
                }else{
                    results.setStatus(false);
                    results.setMessage("高校联盟增加失败！");
                }

            } catch (Exception e) {
                e.printStackTrace();
                results.setStatus(false);
                results.setMessage("高校联盟增加异常："+e);
            }
        }
        return results;
    }

//    //需要--更改高校联盟数据权限--需要上传图片，最好不用拦截
//    @RequiresPermissions("updateAlliance")
    @ApiOperation(value="更改高校联盟数据",position =4,notes = "***必看:该接口文档框架中只提供单文件上传" +
            "另外，该接口还包含着命名为：alliance_id【必要】和alliance_name(联盟名称【String类型】)和alliance_address(联盟地址【true/false】)和alliance_telephone(联盟联系电话【String类型】)，可通过使用JS的formData提交方式进行提交)，此几项都是必传的***")
    @RequestMapping(value = "/Academy/UpdateAllianceData",method = RequestMethod.POST)
    @ApiImplicitParam(name="file",value="formData类型参数【详见-接口描述】",dataType="formData", paramType = "query",required = true)
    public Results UpdateAllianceData(MultipartFile file, HttpServletRequest request){
        Results results=new Results();
        Alliance alliance=new Alliance();
        int alliance_id = Integer.parseInt(request.getParameter("alliance_id"));
        String alliance_name =  request.getParameter("alliance_name");
        String alliance_address =  request.getParameter("alliance_address");
        String alliance_telephone =  request.getParameter("alliance_telephone");

        if(alliance_name.equals("")||alliance_address.equals("")||alliance_telephone.equals("")){
            results.setStatus(false);
            results.setMessage("输入参数不正常！缺少带有参数项");
        }else {
            alliance.setAlliance_id(alliance_id);
            alliance.setAlliance_name(alliance_name);
            alliance.setAlliance_address(alliance_address);
            alliance.setAlliance_telephone(alliance_telephone);
//        System.out.println("输出1："+file);
            if (file == null) {
//            results.setMessage("上传的文件不能为空");
//            return results;
//            System.out.println("输出2："+file);
                alliance.setAlliance_img(null);
            } else {
                try {
//                System.out.println("输出3："+file);
                    //1、先获取jar所在同级目录
                    File path = new File(ResourceUtils.getURL("classpath:").getPath());
                    if (!path.exists()) {
                        path = new File("");
                    }
                    System.out.println("path:" + path.getAbsolutePath());
                    //2、如果上传目录为/static/images/upload/，则可以如下获取：
                    File upload = new File(path.getAbsolutePath(), "static/images/upload/Alliance_img/");
                    if (!upload.exists()) {
                        upload.mkdirs();
                    }
                    System.out.println("上传的路径 url:" + upload.getAbsolutePath());

                    //3、文件前缀追加时间标志
                    Date d = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-kk-mm-ss");
                    String DateNow = sdf.format(d);
                    // 如果不存在该路径就创建
                    String uploadPath = upload + "/";
                    uploadPath = uploadPath.replace("\\", "/");
                    File dir = new File(uploadPath + DateNow + "_" + file.getOriginalFilename());
                    // 文件写入
                    file.transferTo(dir);
                    //在开发测试模式时，得到的地址为：{项目根目录}/target/static/images/upload/
                    //在打包成jar正式发布时，得到的地址为：{发布jar包目录}/static/images/upload/
                    String[] imgURL = (uploadPath + DateNow + "_" + file.getOriginalFilename()).split("static");
                    alliance.setAlliance_img("http://172.16.1.79:9417" + imgURL[1]);
                } catch (Exception e) {
                    e.printStackTrace();
                    results.setStatus(false);
                    results.setMessage("更改高校联盟数据异常：" + e);
                }
            }
            int i = allianceService.UpdateAllianceData(alliance);
            if (i == 1) {
                results.setStatus(true);
                results.setMessage("更改高校联盟数据成功");
            } else {
                results.setStatus(false);
                results.setMessage("更改高校联盟数据失败！");
            }
        }
        return results;
    }

    //需要--删除联盟权限
    @ApiOperation(value="删除单条联盟数据",position = 5,notes = "删除单条联盟数据")
    @RequestMapping(value = "/Alliance/DeleteAlliance",method = RequestMethod.GET)
    @ApiImplicitParam(name="alliance_id",value="联盟标识id",dataType="int", paramType = "query",required = true)
    public Results DeleteAlliance(int alliance_id){
        Results results=new Results();
        try{
            int i=allianceService.DeleteAlliance(alliance_id);
            if(i==1){
                results.setStatus(true);
                results.setMessage("删除单条联盟数据成功！");
            }else{
                results.setStatus(false);
                results.setMessage("删除单条联盟数据失败！");
            }
        }catch (Exception e){
            results.setStatus(false);
            results.setMessage("删除单条联盟数据异常---失败："+e);
        }
        return results;
    }

    //需要--删除联盟权限
    @ApiOperation(value="批量删除联盟数据",position = 6,notes = "批量删除联盟数据")
    @RequestMapping(value = "/Alliance/BatchDeleteAlliance",method = RequestMethod.GET)
    @ApiImplicitParam(name="aidList",value="联盟标识ID数组,以逗号分隔",dataType="String[]", paramType = "query",required = true)
    public Results BatchDeleteAlliance(String aidList){
        Results results=new Results();
        String regex="[\\[ \\] \\-\" -]";
        String strArr=aidList.replaceAll(regex,"");//去除: [ ] \ " 号
        String[] stringArr = strArr.split(",");
        //字符串数组转化为List集合
        List<String> aidFromList = new ArrayList<>(Arrays.asList(stringArr));
        //删除List集合中的空元素
        aidFromList.removeAll(Collections.singletonList(""));
        try{
            int i=allianceService.BatchDeleteAlliance(aidFromList);
            if(i!=0){
                results.setStatus(true);
                results.setMessage("成功删除"+i+"条指定联盟数据");
            }else{
                results.setStatus(false);
                results.setMessage("删除指定联盟数据失败！");
            }
        }catch (Exception e){
            results.setStatus(false);
            results.setMessage("删除指定联盟数据异常---失败："+e);
        }
        return results;
    }



    //---以下前端调用
    @ApiOperation(value="获取所有高校联盟",position = 1,notes = "获取所有高校联盟",tags ="高校联盟--客户端调用")
    @RequestMapping(value = "/ClientData/Alliance/GetAlliance",method = RequestMethod.GET)
    public Results GetAlliance(){
        Results results=new Results();
        try {
            List<Alliance> AllianceList=allianceService.GetAllAlliance();
            if(AllianceList.size()==0){
                results.setMessage("当前数据为空！");
                results.setStatus(true);
            }else{
                results.setData(AllianceList);
                results.setMessage(" 高校联盟获取成功！");
                results.setStatus(true);
            }
        }catch (Exception e){
            results.setMessage("高校联盟获取出现异常："+e);
            results.setStatus(false);
        }
        return results;
    }
}
