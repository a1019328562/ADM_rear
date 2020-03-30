package org.zju.adm.api.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.zju.adm.common.CommonResult;
import org.zju.adm.common.constrant.GlobalConstant;
import org.zju.adm.common.exception.CommonError;
import org.zju.adm.pojo.Data;
import org.zju.adm.pojo.DataType;
import org.zju.adm.pojo.ModelType;
import org.zju.adm.service.DataService;
import org.zju.adm.service.ModelTypeService;
import springfox.documentation.spring.web.json.Json;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Api(value = "模型操作", tags = {"模型相关接口"})
@RestController
@RequestMapping("/model")
public class ModelTypeController {

    @Autowired
    private ModelTypeService modelTypeService;

    @Autowired
    private DataService dataService;

    @ApiOperation(value = "获取模型", notes = "获取模型", httpMethod = "GET")
    @RequestMapping(value = "/selectAllModel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResult selectAllModel(){
        List<ModelType> result = modelTypeService.selectAllModelType();
        return CommonResult.success(result);
    }

    @ApiOperation(value = "运行模型", notes = "指定数据集以及模型类型，进行异常检测", httpMethod = "Post")
    @RequestMapping(value = "/runModel", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResult runModel(@RequestBody DataType dataType,@RequestBody ModelType modelType){
        List<Data> data = dataService.selectAllData();
        List<Integer> predictList = callAlgorithm(dataType,data,modelType);
        for(int i = 0;i< predictList.size();i++){
            data.get(i).setDataPredictedLabelId((byte)predictList.get(i).intValue());
        }
        return CommonResult.success(data);
    }

    public static List<Integer> callAlgorithm(DataType dataType, List<Data> data, ModelType model){
        HashMap<String,Object> input = new HashMap<String,Object>();
        input.put("data_type",dataType.getName());
        input.put("model_type",model.getName());
        input.put("data",data);
        String res = callAlgorithm0(JSONArray.toJSONString(input));
        return JSON.parseArray(res,Integer.class);
    }

    public static String callAlgorithm0(String data){
        // TODO Auto-generated method stub
        Socket socket = null;
        String res = null;
        try {

            socket = new Socket(GlobalConstant.SERVICE_IP,GlobalConstant.SERVICE_PORT);
            // 获取输出流对象
            OutputStream os = socket.getOutputStream();
            PrintStream out = new PrintStream(os);
            // 发送内容
            out.print(data);
            // 告诉服务进程，内容发送完毕，可以开始处理
            out.print("over");

            // 获取服务进程的输入流
            InputStream is = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is,"utf-8"));
            String tmp = null;
            StringBuilder sb = new StringBuilder();
            // 读取内容
            while((tmp=br.readLine())!=null)
                sb.append(tmp).append('\n');
            res = sb.toString();
            // 解析结果
//			res = JSON.parseArray(sb.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {if(socket!=null) socket.close();} catch (IOException e) {}
            System.out.print("远程接口调用结束.");
        }
        return res;
    }

    public static void main(String[] args) {
        List<Data> data = new ArrayList<>();

        Data val = new Data();
        val.setId(1);
        val.setTimestamp(new Date());
        val.setValue(100.0);
        data.add(val);
        val = new Data();
        val.setId(2);
        val.setTimestamp(new Date());
        val.setValue(1000.0);
        data.add(val);
        String ss = JSONArray.toJSONString(data);
        List<Data> data2 = JSON.parseArray(ss,Data.class);
        System.out.println(data2.get(0));
        System.out.println(ss);
    }
}
