package com.ruowei.modules.sys.domain.serializable;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.ruowei.modules.sys.domain.table.SysOffice;
import com.ruowei.modules.sys.domain.table.SysPost;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 把map<SysOffice,SysPost>序列化成
 *     [{sysOfficeId:1,sysPostId:2},{sysOfficeId:3,sysPostId:4}]
 * @author 刘东奇
 * @date 2019/9/24
 */
public class SysOfficeSysPostMapJsonSerializer extends JsonSerializer<Map<SysOffice, SysPost>> {
    @Override
    public void serialize(Map<SysOffice, SysPost> value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {

        Map<String,String> map = new HashMap<>();
        for(Map.Entry<SysOffice,SysPost> entry:value.entrySet()){
            SysOffice sysOffice = entry.getKey();
            SysPost sysPost = entry.getValue();
            map.put(sysOffice.getId().toString(),sysPost.getId().toString());
        }
        jsonGenerator.writeObject(map);
    }
}
