package com.github.whvixd.testUtil;

import com.github.whvixd.util.HttpClientUtil;
import com.google.common.collect.Maps;
import com.google.common.net.HttpHeaders;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtobufIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.net.URI;
import java.util.Map;

/**
 * Created by wangzhx on 2018/9/9.
 */
public class HttpClientUtilTest {
    @Test
    public void test() {
//        org.apache.http.client.HttpClient httpClient = HttpClients.createDefault();
//        Object.Arg arg = new GetAllTeamMembers.Arg();
//
//        Schema schema = RuntimeSchema.getSchema(GetAllTeamMembers.Arg.class);
//        byte[] bytes = ProtobufIOUtil.toByteArray(arg, schema, LinkedBuffer.allocate(2048));
//
//
//        HttpPost httpPost = new HttpPost();
//        httpPost.setURI(URI.create(""));
//
//
//        BasicHttpEntity entity = new BasicHttpEntity();
//        entity.setContentEncoding("UTF-8");
//        entity.setContentLength(bytes.length);
//        entity.setContent(new ByteArrayInputStream(bytes));
//        httpPost.setEntity(entity);
//
//        httpPost.setEntity(new ByteArrayEntity(bytes));
//
//        httpPost.setHeader(HttpHeaders.CONTENT_TYPE, "application/octet-stream");
//        HttpResponse response = httpClient.execute(httpPost);
//
//        Schema<GetAllTeamMembers.Result> schemaRest = RuntimeSchema.getSchema(GetAllTeamMembers.Result.class);
//        GetAllTeamMembers.Result ret = schemaRest.newMessage();
//        ProtobufIOUtil.mergeFrom(EntityUtils.toByteArray(response.getEntity()), ret, schemaRest);
//        System.out.println(ret);

    }
}
