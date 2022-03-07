package com.ruowei.modules.sys.domain.serializable;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruowei.modules.sys.domain.table.SysOffice;
import com.ruowei.modules.sys.domain.table.SysPost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 把[{"sysOfficeId":1,"sysPostId":2},{"sysOfficeId":3,"sysPostId":4}]反序列化成map<SysOffice,SysPost>
 *     把{1:2,3:4}反序列化成map<SysOffice,SysPost>
 * @author 刘东奇
 * @date 2019/9/24
 */
public class SysOfficeSysPostMapJsonDeserializer extends JsonDeserializer<Map<SysOffice, SysPost>> {
    private static final Logger logger = LoggerFactory.getLogger(SysOfficeSysPostMapJsonDeserializer.class);

    @Override
    public Map<SysOffice,SysPost> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String,String> map= objectMapper.readValue(jsonParser,Map.class);
        Map<SysOffice, SysPost> sysOfficeSysPostLinkedHashMap = new LinkedHashMap<>();
        if(map.size()==0){
            return null;
        }
        for(Map.Entry<String,String> entry:map.entrySet()){
            SysOffice sysOffice = new SysOffice();
            String sysOfficeId = entry.getKey();
            sysOffice.setId(Long.parseLong(sysOfficeId));
            SysPost sysPost = new SysPost();
            String sysPostId = entry.getValue();
            sysPost.setId(Long.parseLong(sysPostId));
            sysOfficeSysPostLinkedHashMap.put(sysOffice, sysPost);
        }
        return sysOfficeSysPostLinkedHashMap;
    }
}

