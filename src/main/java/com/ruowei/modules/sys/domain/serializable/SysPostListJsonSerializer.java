package com.ruowei.modules.sys.domain.serializable;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.ruowei.modules.sys.domain.table.SysPost;

import java.io.IOException;
import java.util.List;

/**
 * 把map<SysOffice,SysPost>序列化成
 *     [{sysOfficeId:1,sysPostId:2},{sysOfficeId:3,sysPostId:4}]
 * @author 刘东奇
 * @date 2019/9/24
 */
public class SysPostListJsonSerializer extends JsonSerializer<List<SysPost>> {
    @Override
    public void serialize(List<SysPost> value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        if(value!=null && value.size()>0){
            String[] list = new String[value.size()];
            for(int i=0;i<value.size();i++){
                list[i]=value.get(i).getId().toString();
            }
            jsonGenerator.writeObject(list);
        }else{
            jsonGenerator.writeObject(null);
        }
    }
}
