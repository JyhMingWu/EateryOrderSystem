package com.nanbo.ordersystem.controller;

import com.nanbo.ordersystem.constant.CommonConstant;
import com.nanbo.ordersystem.entity.Alliance;
import com.nanbo.ordersystem.entity.EateryOrderSystem.*;
import com.nanbo.ordersystem.entity.Result.Results;
import com.nanbo.ordersystem.entity.U_Roles.*;
import com.nanbo.ordersystem.server.FoodService;
import com.nanbo.ordersystem.util.tool.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RestController
@CrossOrigin() //允许跨域
@Api(value="配置controller",tags={"后台配置操作"},position = 1)
public class ConfigController {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private FoodService foodService;

    @ApiIgnore()
    @RequestMapping("/Config/test")
    public Results test(){
        Results results=new Results();
        results.setMessage("测试成功！");
        results.setStatus(true);
        return results;
    }

    /**
     * 上传配置：即不走config.json，模拟config.json里的内容，解决后端配置项不正确，无法上传的问题
     * @return
     */
    @ApiOperation(value="ueditor的配置数据，不可修改以及上传",position =20)
    @RequestMapping(value = "/Config/ueditor/config",method = RequestMethod.GET)
    @ResponseBody
    @ApiIgnore()
    public String uploadConfig(String action,String noCache) {
        System.out.println("action="+action+"   callback="+noCache);
        String s = "{\n" +
                "            \"basePath\": \"C:/\",\n" +
                "            \"imageActionName\": \"uploadimage\",\n" +
                "                \"imageFieldName\": \"upfile\", \n" +
                "                \"imageMaxSize\": 2048000, \n" +
                "                \"imageAllowFiles\": [\".png\", \".jpg\", \".jpeg\", \".gif\", \".bmp\"], \n" +
                "                \"imageCompressEnable\": true, \n" +
                "                \"imageCompressBorder\": 1600, \n" +
                "                \"imageInsertAlign\": \"none\", \n" +
                "                \"imageUrlPrefix\": \"http://172.16.1.79:9417/images/upload/\",\n" +
//                "                \"imageUrlPrefix\": \"http://localhost:9417/images/upload/\",\n" +
                "                \"imagePathFormat\": \"/image/{yyyy}{mm}{dd}/{time}{rand:6}\" }";
        return s;
    }


    /**
     * Ueditor上传文件
     * 这里以上传图片为例，图片上传后，imgPath将存储图片的保存路径，返回到编辑器中做展示
     * @param file
     * @return
     */
    @ApiOperation(value="ueditor的新闻图片上传功能，不可修改以及上传，请勿测试",position =21)
    @RequestMapping(value = "/Config/uploadimage",method = RequestMethod.POST)
    @ResponseBody
    @ApiIgnore()
    public String uploadimage(@RequestParam("upfile") MultipartFile file, HttpServletRequest request, HttpServletResponse response) {

        Results results=new Results();
        //判断非空
        if (file.isEmpty()) {
            return "上传的文件不能为空";
        }
        try {
            //1、先获取jar所在同级目录
            File path = new File(ResourceUtils.getURL("classpath:").getPath());
            if(!path.exists()){
                path = new File("");
            }
            System.out.println("获取jar所在同级目录 path:"+path.getAbsolutePath());
            //2、如果上传目录为/static/images/upload/，则可以如下获取：
            File upload = new File(path.getAbsolutePath(),"static/images/upload/New_img/");
            if(!upload.exists()){
                upload.mkdirs();
            }
            System.out.println("上传目录为/static/images/upload/中---upload url:"+upload.getAbsolutePath());

            //测试MultipartFile接口的各个方法
            System.out.println("[文件类型ContentType] -:"+file.getContentType());
            System.out.println("[文件组件名称Name] -:"+file.getName());
            System.out.println("[文件原名称OriginalFileName] -:"+file.getOriginalFilename());
            System.out.println("[文件大小] -:"+file.getSize());
            System.out.println(this.getClass().getName()+"图片路径："+upload);


            // 如果不存在该路径就创建
            String uploadPath=upload+"\\";
            File dir = new File(uploadPath + file.getOriginalFilename());
            // 文件写入
            file.transferTo(dir);
            //在开发测试模式时，得到的地址为：{项目根目录}/target/static/images/upload/
            //在打包成jar正式发布时，得到的地址为：{发布jar包目录}/static/images/upload/
            results.setMessage("上传单个文件成功");
        } catch (Exception e) {
            e.printStackTrace();
            results.setMessage("上传单个文件失败");
        }

        String result = "";
        if(!file.isEmpty()) {
            String originalFileName = file.getOriginalFilename();
            // 这里写你的文件上传逻辑
            // String imgPath = fileUtil.uploadImg(file);
            String imgPath = "/New_img/"+originalFileName;
            result = "{\n" +
                    "    \"state\": \"SUCCESS\",\n" +
                    "    \"url\": \"" + imgPath + "\",\n" +
                    "    \"title\": \"" + originalFileName + "\",\n" +
                    "    \"original\": \"" + originalFileName + "\"\n" +
                    "}";
        }
        return result;
    }



    @ApiOperation(value="菜品图上传",position = 2,notes = "因该接口文档框架中只提供单文件上传，暂时无法选择多文件，请开发人员在html中，可用多个(input type='file' name='name')进行测试，注意:每个file类型的input必须要同名name")
    @RequestMapping(value = "/OrderManagementSystem/multipleCarouselFiles",method = RequestMethod.POST)
    public Results MultipleCarouselFiles(@RequestParam("file") MultipartFile[] files, HttpServletRequest request){
        Results results=new Results();
        System.out.println("输出--files："+files[0].getSize());
        //获取菜品名称
        String food_name =  request.getParameter("food_name");
        //获取菜品类型
        String food_type = request.getParameter("food_type");
        String food_price = request.getParameter("food_price");
        String food_content = request.getParameter("food_content");
        String food_status = request.getParameter("food_status");
        String food_com_guid = request.getParameter("food_com_guid");
        String food_upload_people = request.getParameter("food_upload_people");
        String food_remarks = request.getParameter("food_remarks");
        String food_taste = request.getParameter("food_taste");
        System.out.println("输出--文件大小："+files.length+"文件是否存在："+files);
        System.out.println("food："+food_name+" "+food_type+" "+food_price+" "+food_content+" "+food_status+" "+food_com_guid+" "+food_upload_people+" "+food_remarks+" "+food_taste);
        if (null == files && files[0].getSize() == 0) {
            results.setStatus(false);
            results.setMessage("文件为空，上传失败");
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
            File upload = new File(path.getAbsolutePath(),"static/images/upload/Food_img/");
            if(!upload.exists()){
                upload.mkdirs();
            }
            System.out.println("upload url:"+upload.getAbsolutePath());

            //3、文件前缀追加时间标志
            Date d = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-kk-mm-ss");
            String DateNow = sdf.format(d);
            String uploadPath=upload+"/";
            //累计上传成功的文件数量
            int count_success=0;
//            List<Food> listCarousel=new ArrayList<>();
            Food food=new Food();
            for (MultipartFile mf : files) {
                //文件名称
                String filename = mf.getOriginalFilename();
                if (mf.isEmpty()) {
                    //空文件+1
//                    count_fail++;
                    results.setMessage("您上传的文件存在空文件，请重新上传！");
                    return results;
                }
                //路径替换下符号
                uploadPath=uploadPath.replace("\\","/");
                System.out.println("路径："+uploadPath);
                // 如果不存在该路径就创建
                File dir = new File(uploadPath +DateNow+"_"+  filename);
                try {
                    //写入文件
                    mf.transferTo(dir);
                    count_success++;
                    //创建轮播图对象
//                    Food food=new Food();
                    String[] imgURL=(uploadPath +DateNow+"_"+  filename).split("static");
                    //格式化时间
                    SimpleDateFormat toCarousel_time = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
                    //设置上传时间
                    food.setFoodUploadDate(toCarousel_time.format(d));
                    //设置上传者
                    food.setFoodUploadPeople(food_upload_people);
                    //设置图片路径
                    food.setFoodImage("http://http://172.16.1.79:9417"+imgURL[1]);
                    //设置使用状态
                    int a=1;
                    if(!food_status.equals("true")){
                        a=0;
                    }
                    food.setFoodStatus(a);
                    //设置类型
                    food.setFoodType(food_type);
                    //设置菜品名称
                    food.setFoodName(food_name);
                    //设置所属商户ID
                    food.setFoodComGuid(food_com_guid);
                    //设置使用状态
                    food.setFoodContent(food_content);
                    //设置价格
                    BigDecimal bd=new BigDecimal(food_price);
                    bd=bd.setScale(2, BigDecimal.ROUND_HALF_UP);
                    food.setFoodPrice(bd);
                    //设置备注
                    food.setFoodRemarks(food_remarks);
                    //设置口味
                    food.setFoodTaste(food_taste);
//                    listCarousel.add(food);
                } catch (IOException e) {
                    System.out.println(e.toString());
                    results.setStatus(false);
                    results.setMessage("文件名称：" + filename + "上传失败");
                }
            }
            System.out.println("food为："+food.toString());
            int num=foodService.AddFood(food);
            results.setStatus(true);
            results.setMessage("上传成功数量:"+num);

        }catch (Exception e){
            e.printStackTrace();
            results.setStatus(false);
            results.setMessage("上传多个文件失败");
        }
        return results;
    }



    @ApiOperation(value="更改菜品数据",position =4,notes = "***必看:该接口文档框架中只提供单文件上传" +
            "另外，该接口还包含着命名为：alliance_id【必要】和alliance_name(联盟名称【String类型】)和alliance_address(联盟地址【true/false】)和alliance_telephone(联盟联系电话【String类型】)，可通过使用JS的formData提交方式进行提交)，此几项都是必传的***")
    @RequestMapping(value = "/OrderManagementSystem/UpdateFoodData",method = RequestMethod.POST)
    @ApiImplicitParam(name="file",value="formData类型参数【详见-接口描述】",dataType="formData", paramType = "query",required = true)
    public Results UpdateFoodData(MultipartFile file, HttpServletRequest request){
        Results results=new Results();
        Food food=new Food();
        String food_guid =  request.getParameter("food_guid");
        //获取菜品名称
        String food_name =  request.getParameter("food_name");
        //获取菜品类型
        String food_type = request.getParameter("food_type");
        String food_price = request.getParameter("food_price");
        String food_content = request.getParameter("food_content");
        String food_status = request.getParameter("food_status");
        String food_com_guid = request.getParameter("food_com_guid");
        String food_upload_people = request.getParameter("food_upload_people");
        String food_remarks = request.getParameter("food_remarks");
        String food_taste = request.getParameter("food_taste");

        if(food_guid.equals("")||food_name.equals("")||food_type.equals("")||food_price.equals("")||food_content.equals("")||food_status.equals("")||food_com_guid.equals("")||food_upload_people.equals("")||food_remarks.equals("")||food_taste.equals("")){
            results.setStatus(false);
            results.setMessage("输入参数不正常！缺少带有参数项");
        }else {
            food.setFoodGuid(food_guid);
            food.setFoodName(food_name);
            food.setFoodType(food_type);
            BigDecimal bd=new BigDecimal(food_price);
            bd=bd.setScale(2, BigDecimal.ROUND_HALF_UP);
            food.setFoodPrice(bd);
            food.setFoodContent(food_content);
            if(food_status.equals("true")){
                food.setFoodStatus(1);
            }else{
                food.setFoodStatus(0);
            }
            food.setFoodComGuid(food_com_guid);
            food.setFoodUploadPeople(food_upload_people);
            food.setFoodRemarks(food_remarks);
            food.setFoodTaste(food_taste);
            if (file == null) {
                //空文件，则不进行修改image的路径
                food.setFoodImage(null);
            } else {
                try {
                    //1、先获取jar所在同级目录
                    File path = new File(ResourceUtils.getURL("classpath:").getPath());
                    if (!path.exists()) {
                        path = new File("");
                    }
                    System.out.println("path:" + path.getAbsolutePath());
                    //2、如果上传目录为/static/images/upload/，则可以如下获取：
                    File upload = new File(path.getAbsolutePath(), "static/images/upload/Food_img/");
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
                    food.setFoodImage("http://http://172.16.1.79t:9417" + imgURL[1]);
                } catch (Exception e) {
                    e.printStackTrace();
                    results.setStatus(false);
                    results.setMessage("更改菜品数据异常：" + e);
                }
            }
            int i = foodService.UpdateFoodData(food);
            if (i == 1) {
                results.setStatus(true);
                results.setMessage("更改菜品数据成功");
            } else {
                results.setStatus(false);
                results.setMessage("更改菜品数据失败！");
            }
        }
        return results;
    }



































    //Redis设置
    @ApiIgnore()
    @RequestMapping(value = "testRedisSet",method = RequestMethod.GET)
    public Results testRedisSet(){
        Results results=new Results();

        System.out.println("设置完成：输出="+redisUtil.set("test","wuzhiming"));
        redisUtil.expire("test",10000);
        System.out.println("获取key-test值:"+redisUtil.get("test"));
        return results;
    }
    //Redis获取
    @ApiIgnore()
    @RequestMapping(value = "getRedisSet",method = RequestMethod.GET)
    public Results getRedisSet(){
        Results results=new Results();
        results.setMessage(""+redisUtil.get("test"));
        System.out.println("获取key-test值:"+redisUtil.get("test"));
        return results;
    }
    //Redis设置过期时间
    @ApiIgnore()
    @RequestMapping(value = "timeRedisSet",method = RequestMethod.GET)
    public Results timeRedisSet(String token){
        Results results=new Results();
        results.setMessage(""+redisUtil.getExpire(CommonConstant.PREFIX_USER_TOKEN + token));
        System.out.println("获取key-test值:"+redisUtil.get(CommonConstant.PREFIX_USER_TOKEN + token));
        return results;
    }
    //Redis删除
    @ApiIgnore()
    @RequestMapping(value = "delRedisSet",method = RequestMethod.GET)
    public Results delRedisSet(){
        Results results=new Results();
        redisUtil.del("test");
        results.setMessage(""+redisUtil.get("test"));
        System.out.println("获取key-test值:"+redisUtil.get("test"));
        return results;
    }

    //建构--商户实体
    @ApiOperation(value="用于生成--商户实体",position =1)
    @RequestMapping(value = "/Commerce",method = RequestMethod.GET)
    public Commerce Commerce(){
        Commerce commerce=new Commerce();
        commerce.setComName("用于生成--商户Commerce");
        return commerce;
    }

    //建构--菜品实体
    @ApiOperation(value="用于生成--菜品实体",position =2)
    @RequestMapping(value = "/Food",method = RequestMethod.GET)
    public Food Food(){
        Food food=new Food();
        food.setFoodName("用于生成--菜品实体food");
        return food;
    }


    //建构--订单实体
    @ApiOperation(value="用于生成--订单实体",position =4)
    @RequestMapping(value = "/Order",method = RequestMethod.GET)
    public Order Order(){
        Order order=new Order();
        order.setOrderPeople("用于生成--订单Order");
        return order;
    }


    //建构--订单明细实体
    @ApiOperation(value="用于生成--订单明细实体",position =5)
    @RequestMapping(value = "/OrderDetail",method = RequestMethod.GET)
    public OrderDetail OrderDetail(){
        OrderDetail orderDetail=new OrderDetail();
        orderDetail.setOrderDFoodName("用于生成--订单明细OrderDetail");
        return orderDetail;
    }
    //建构--地址对象实体
    @ApiOperation(value="用于生成--地址对象实体",position =7)
    @RequestMapping(value = "/UserAddress",method = RequestMethod.GET)
    public UserAddress UserAddress(){
        UserAddress userAddress=new UserAddress();
        userAddress.setuAPeople("用于生成--地址对象UserAddress");
        return userAddress;
    }
    //建构--用户实体
    @ApiOperation(value="用于生成--用户实体",position =3)
    @RequestMapping(value = "/OperUser",method = RequestMethod.GET)
    public SysUser OperUser(){
        SysUser sysUser=new SysUser();
        sysUser.setUsername("用于生成--用户OperUser");
        return sysUser;
    }
    //建构--角色实体
    @ApiOperation(value="用于生成--角色实体",position =6)
    @RequestMapping(value = "/Role",method = RequestMethod.GET)
    public SysRole Role(){
        SysRole sysRole=new SysRole();
        sysRole.setRoleGuid("123");
        sysRole.setRoleId(213);
        sysRole.setRoleName("用于生成--角色Role");
        return sysRole;
    }
    //建构--用户角色实体
    @ApiOperation(value="用于生成--用户角色实体",position =6)
    @RequestMapping(value = "/SysUserRole",method = RequestMethod.GET)
    public SysUserRole SysUserRole(){
        SysUserRole sysUserRole=new SysUserRole();
        sysUserRole.setRoleGuid("123");
        return sysUserRole;
    }
    //建构--权限实体
    @ApiOperation(value="用于生成--权限实体",position =6)
    @RequestMapping(value = "/SysPermission",method = RequestMethod.GET)
    public SysPermission SysPermission(){
        SysPermission sysPermission=new SysPermission();
        sysPermission.setName("用于生成--权限SysPermission");
        return sysPermission;
    }
    //建构--角色权限实体
    @ApiOperation(value="用于生成--角色权限实体",position =6)
    @RequestMapping(value = "/SysRolePermission",method = RequestMethod.GET)
    public SysRolePermission SysRolePermission(){
        SysRolePermission sysRolePermission=new SysRolePermission();
        sysRolePermission.setRoleGuid("用于生成--角色权限SysRolePermission");
        return sysRolePermission;
    }

}
