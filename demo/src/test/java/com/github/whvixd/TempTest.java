package com.github.whvixd;

import cn.hutool.core.date.StopWatch;
import cn.hutool.crypto.digest.MD5;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.github.whvixd.demo.jdk.enumeration.*;
import com.github.whvixd.model.ApprovalComplete;
import com.github.whvixd.model.Bean;
import com.github.whvixd.util.*;
import com.github.whvixd.util.exception.ArgValidationException;
import com.github.whvixd.util.exception.base.BusinessExceptionCode;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.stringtemplate.v4.ST;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotNull;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.net.SocketTimeoutException;
import java.text.NumberFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
public class TempTest {


    //------------------------------------Model------------------------------------
    @Data
    class Node {
        String name;

        Node(String name) {
            this.name = name;
        }

        @Override
        public int hashCode() {
            return name.hashCode();
        }

//        @Override
//        public boolean equals(Object obj) {
//            return this.hashCode()==obj.hashCode();
//        }
    }


    @Data
    public class DataChangeBean {

        private RemoteContext context;
        private String objectId;
        private String entityId;
        private String triggerType;
        private String eventId;
        private Map<String, Object> beforeTriggerData;
        private Map<String, Object> afterTriggerData;
    }

    @Data
    public class RemoteContext {
        private String ea;
        private String tenantId;
        private String appId;
        private String userId;
    }

    public void cons(String ele) {

        System.out.println(ele);
    }


    public boolean isName(String name) {
        return Pattern.matches("[\\w_.-]+", name)
                &&
                !Pattern.matches("^[_.-].*|.*[_.-]$|.*[_.-]{2,}.*", name);
    }


    @Builder
    @ToString
    static class Person {
        private String name;
        @lombok.experimental.Delegate
        private A address;

    }

    static class A {
        private String province;
        final static String aName = "";

        public final String getaName() {
            return aName;
        }
    }


    public String getOneOperation(String entityId) {
        Map<String, List<String>> supportOneOperationObjects = Maps.newHashMap();
        supportOneOperationObjects.put("a", Lists.newArrayList("acc"));
        supportOneOperationObjects.put("b", Lists.newArrayList("acc"));
        supportOneOperationObjects.put("c", Lists.newArrayList());

        for (String operation : supportOneOperationObjects.keySet()) {
            if (supportOneOperationObjects.get(operation).contains(entityId)) {
                return operation;
            }
        }
        return null;
    }

    public class Animal {

        public void print() {
            int a = 10;
            System.out.println(a % 3);
        }
    }

    public class Dog extends Animal {
        private String name;

        public void print() {
            int a = 10;
            System.out.println(a % 1);
        }
    }

    private int hashKey(Object key) {
        int h;
        return key == null ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    @Data
    @AllArgsConstructor
    static class Student {

        @NotNull(message = "name不为空")
        private String name;
        @SerializedName("age_test")
        private int age;
        @SerializedName("desc_test")
        private String desc;
        private transient String other;

        public Student(String name) {
            this.name = name;
        }

        public Student(String name, int age) {
            this.age = age;
            this.name = name;
        }

        public Student() {
        }
    }

//------------------------------------Test------------------------------------

    @Test
    public void test1() {
        Set<String> strings = Sets.newHashSet();
        strings.add("1");
        strings.add("2");
        strings.add("3");

        System.out.println(Joiner.on(",").join(strings));
    }

    @Test
    public void test2() {
        System.out.println(StringUtils.isNotBlank(null));
    }

    @Test
    public void test3() {
        List<String> list = Lists.newArrayList();
        System.out.println(list.contains(""));

        System.out.println(getOneOperation("acc"));
    }

    @Test
    public void tests4() {
        System.out.println(String.format("%s---%s", "2", "3"));
    }

    @Test
    public void test4() {
        AtomicReference<String> atomicReference = new AtomicReference<>();
        atomicReference.set("12");
        atomicReference.lazySet("q12");
        atomicReference.compareAndSet("1", "3");

        System.out.println(atomicReference.get());
    }

    @Test
    public void test5() {

        com.github.whvixd.util.Student student = com.github.whvixd.util.Student.getInstance();
        System.out.println(student.getName());

        Person person = Person.builder()
                .name("321").build();

        System.out.println(person);
        person.getaName();

        Set<String> set = Sets.newHashSet();
        Set<String> afterSet = set.stream().filter(e -> e != null).collect(Collectors.toSet());
        set.forEach(this::cons);

    }

    @Test
    public void test6() {
//        Pattern pattern = Pattern.compile("^[._\\-]|^[._\\-]$|[.\\-_]{2,}");

//        System.out.println(Pattern.matches("[\\w_.-]+", "") && !Pattern.matches("^[_.-]|[_.-]$|.*[_.-]{2,}.*", ""));
//        System.out.println(Pattern.matches("^[\\w]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z0-9]{2,6}$","e21es5@qq.com"));

//        System.out.println(Pattern.matches(".*[_.-]{2,}.*", "111-1.1-1"));
        System.out.println(isName("ewqe21.21-21_1)"));
        System.out.println(Pattern.matches("[+-/*]", "+"));
    }

    @Test
    public void test7() {

        JSONObject jsonObject = new JSONObject();
        Object a = jsonObject.get("a");
        Object o = jsonObject.getOrDefault("a", Lists.newArrayList());
        System.out.println(a);
        System.out.println(o);
        Student student = JSONObject.parseObject("{\"name\":\"张三\",\"age\":21}", new TypeReference<Student>() {
        });
        System.out.println(student);

        String s = "";
        System.out.println(StringUtils.isBlank(s));
        System.out.println(JSONObject.toJSONString(Lists.newArrayList()));
        System.out.println(JSONObject.toJSONString(""));
        System.out.println(JSONObject.toJSON(null));

    }

    @Test
    public void test8() {
        List<DataChangeBean> workflowMQEvents = Lists.newArrayList();
        String bodyString = "{\"body\":[{\"eventId\":\"5b585448830bdb9cd23b3cfc\",\"beforeTriggerData\":{\"field_E123w__c\":\"9.00\",\"last_modified_time\":\"1532515357118\",\"field_q6O7R__c\":null,\"version\":\"10\"},\"context\":{\"appId\":\"CRM\",\"tenantId\":\"2\",\"userId\":\"2383\"},\"entityId\":\"object_D81p7__c\",\"triggerType\":\"u\",\"objectId\":\"5b58149e830bdb58d6b72ca5\",\"afterTriggerData\":{\"field_E123w__c\":null,\"last_modified_time\":\"1532515400773\",\"field_q6O7R__c\":\"1.00\",\"version\":\"11\"}}],\"name\":\"object_data\",\"op\":\"u\",\"tenantId\":\"2\"}\n";
        Map<String, Object> mqMessage = GsonUtil.fromJson(bodyString, Map.class);
        List bodys = (List) mqMessage.get("body");
        bodys.forEach(body -> {
            workflowMQEvents.add(GsonUtil.fromJson(GsonUtil.toJsonWithNull(body), DataChangeBean.class));
        });

        System.out.println(workflowMQEvents);
    }


    @Test
    public void test9() {
        List<Student> list = new ArrayList<>();
        list.add(new Student("20170728120", 1));
        list.add(new Student("20170728119", 3));
        list.add(new Student("20170728119", 2));
//当k相同的情况下会抛IllegalStateException异常
        Map<String, Integer> map = list.stream().collect(
                Collectors.toMap(Student::getName, Student::getAge));


        map.entrySet().stream().forEach(e -> System.out.println(e.getValue()));
    }

    @Test
    public void test10() {
        com.github.whvixd.util.Student student = com.github.whvixd.util.Student.getInstance();
        String name = student.getName();

        System.out.println(name);

        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        multiValueMap.add("a", "1");
        multiValueMap.add("a", "2");
        multiValueMap.add("a", "3");
        System.out.println(multiValueMap.get("a"));
    }

    @Test
    public void test11() {
        Map<String, Integer> map = Maps.newHashMap();
        map.putIfAbsent("a", 1);

        //如果有这个k，则执行function的值，若没有这个k，则返回null
        Integer a = map.compute("b", (k, v) -> {
            return v;
        });
        System.out.println(a);
    }

    @Test
    public void test12() {
        Student student = new Student();
//        student.setName("2");
        student.setAge(21);
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        //获取@NotNull注解，若这字段为空，constraintViolations不为空，则抛出异常
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(student);
        if (CollectionUtils.isEmpty(constraintViolations)) {
            return;
        }
        throw new ArgValidationException(BusinessExceptionCode.ARG_VALIDATE_ERROR);
    }

    @Test
    public void test13() {
        try {
            throw new SocketTimeoutException();
        } catch (Throwable throwable) {
            if (throwable instanceof SocketTimeoutException) {
                System.out.println("SocketTimeoutException");
            }
        }
        System.out.println("a");
    }

    @Test
    public void test14() {
        int j = 0;
        j = j++ + ++j + j++ + j++;//0 + 2 +5

        char c = 66;
        int a = 2;
        System.out.println(c + (char) a);
        System.out.println("" + j);
        Map<String, Student> map = Maps.newHashMap();
        Animal animal = new Dog();
        animal.print();
    }

    @Test
    public void test15() {
        List<String> wellList = getInternalExpressionByWell("${workflowInstanceStartTime}${activity_1534303105931##object_ogYw2__c##out_owner}\n" +
                "    ${activity_1534303105931##object_ogYw2__c##name}\n" +
                "     ${instance##owner}");
        System.out.println(wellList);
        Pattern.matches("", "");
    }

    //${workflowInstanceStartTime}${activity_1534303105931##object_ogYw2__c##out_owner}
    //${activity_1534303105931##object_ogYw2__c##name}
    // ${instance##owner}
    public List<String> getInternalExpressionByWell(String content) {
        List<String> value = Lists.newArrayList();
        Pattern pattern = Pattern.compile("\\$\\{(.+?)}");
        Matcher matcher = pattern.matcher(content);
        String name;
        while (matcher.find()) {
            name = matcher.group(1);
            //业务流程前端会传递${instance##owner}过来,这里只获取变量中包含activity_的字段
            if (name.contains("##") && name.contains("activity_")) {
                value.add(name);
            }
        }
        return value;
    }

    @Test
    public void test16() {
        List<? extends Number> covariantList = new ArrayList<>();    //协变
        List<? super Integer> contravariantList = new ArrayList<>(); //逆变

//        covariantList.add(6); // compiling error
//        Number number = covariantList.get(0);
//
//        contravariantList.add(6);
//        Integer integer = contravariantList.get(0); //compiling error
    }

    @Test
    public void test17() {
        Map map = Maps.newHashMap();
        map.put("name", "张三");
        map.put("age", 2);

        String json = "{\"name\":\"张三\",\"age\":\"2\"}";

//        System.out.println(GsonUtil.fromJson(GsonUtil.toJson(map),Student.class));
        System.out.println(new Gson().fromJson(json, Student.class));
        System.out.println(new Gson().toJson(json));
    }

    @Test
    public void test18() {
        int a = -7200000;
        long b = a;
        System.out.println(b);
        System.out.println(DateFormatUtils.format(-7200000, "HH:mm"));
    }

    @Test
    public void test19() throws BadHanyuPinyinOutputFormatCombination {
        Object a = 4;
        int b = Integer.valueOf(String.valueOf(a));
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        format.setVCharType(HanyuPinyinVCharType.WITH_V);

        System.out.println(Arrays.toString(PinyinHelper.toHanyuPinyinStringArray('我', format)));
    }

    @Test
    public void test20() {
        Locale chinaLocale = new Locale("zh", "CN");
        double money = 2972.29d;
        NumberFormat format = NumberFormat.getCurrencyInstance(chinaLocale);
        System.out.println("中国：" + format.format(money));

    }

    @Test
    public void test21() throws IOException {
        System.out.println(StreamUtil.readFileWithEnglish("tmp.log"));
    }

    @Test
    public void test22() throws IOException {
        ApprovalComplete.Result result = new ApprovalComplete.Result();
        ApprovalComplete.ResultStatus resultStatus = new ApprovalComplete.ResultStatus();
        resultStatus.setSuccess(true);
        result.setResult(resultStatus);

        System.out.println(result);
    }

    @Test
    public void test23() throws IOException {
        System.out.println(Long.parseLong("12"));
    }

    @Test
    public void test24() throws IOException {
        Student student = new Student();
        student.setName("<name>");
        student.setAge(21);
        student.setDesc("<恰去>");


        String tempStudent = JacksonUtil.toJson(student);
        ST st = new ST(tempStudent);

        st.add("name", "tom");
        tempStudent = st.render();
        student = JacksonUtil.fromJson(tempStudent, Student.class);
        System.out.println(student);
    }

    @Test
    public void test25() throws IOException, TemplateException {
//        Configuration cfg = new Configuration();
//        cfg.setDirectoryForTemplateLoading(new File(""));
//        Template template = cfg.getTemplate("","UTF-8");
        FreeMarkerConfigurer freemarkerConfigurer = new FreeMarkerConfigurer();
        StringWriter swriter = new StringWriter();
        Configuration configuration = freemarkerConfigurer.getConfiguration();
        Template template = configuration.getTemplate("/demo.ftl");

        Map<String, Object> model = Maps.newHashMap();
        model.put("name", "tom");
        template.process(model, swriter);
        System.out.println(swriter.toString());
    }

    @Test
    public void test26() {
        Map<String, List<String>> map = Maps.newHashMap();
        map.put("a", Lists.newArrayList("1", "2", "3"));
        map.put("b", Lists.newArrayList("4", "5", "6"));
        Collection<List<String>> values = map.values();
        List<String> c = Lists.newArrayList();
        values.forEach(c::addAll);
        System.out.println(c);

    }

    @Test
    public void test27() {
        GsonUtil.fromJson(null, Map.class);
        GsonUtil.fromJson(null, new TypeToken<List<String>>() {
        }.getType());
        //NPE
        JacksonUtil.fromJson(null, Map.class);
    }

    @Test
    public void test28() {
        //1-31
        System.out.println(Pattern.matches("^((0?[0-9])|(([12])|[0-9])|30|31)$", "2"));
        //0-23
        System.out.println(Pattern.matches("^(\\d)|(1\\d)|(2[0-3])$", "24"));
    }

    @Test
    public void test29() {
        String fieldType = "date";

        System.out.println(!"date".equals(fieldType) && !"date_time".equals(fieldType));
    }


    @Test
    public void test30() {
        String json = "{\"number\":154101700.00}";
        Map map = JacksonUtil.fromJson(json, Map.class);
        System.out.println(map);

        Map<String, Object> toJsonMap = Maps.newHashMap();
        toJsonMap.put("to", 154101700.00);
        System.out.println(JacksonUtil.toJson(toJsonMap));
    }

    @Test
    public void test31() {
        Student student = new Student();
        student.other = "123";
        System.out.println(JacksonUtil.toJson(student));
    }

    @Test
    public void test32() throws IOException {
        String escapeSymbol = IOUtils.toString(getClass().getClassLoader().getResourceAsStream("data/escape_symbol.txt"));
        System.out.println(escapeSymbol);


        String s = com.github.whvixd.util.StringUtils.escape(escapeSymbol);
        System.out.println(s);
//        System.out.println(GsonUtil.toJson(s));

//        System.out.println(GsonUtil.toJson(escapeSymbol));
//        System.out.println(StringEscapeUtils.escapeJson(escapeSymbol));
//        String qwe = StringEscapeUtils.escapeJava(escapeSymbol);
//        System.out.println(StringEscapeUtils.unescapeJson(qwe));
//        System.out.println(StringEscapeUtils.escapeJava("恰 e"));


        List<String> escapeSymbols = Lists.newArrayList();
        for (int i = 0; i < 1; i++) {
            escapeSymbols.add(escapeSymbol);
        }

        long start = System.currentTimeMillis();
        escapeSymbols.forEach(JacksonUtil::toJson);
        long end = System.currentTimeMillis() - start;
        System.out.println(end);
    }

    @Test
    public void test33() {
        String content = "{\"content\":\" \\\">>>>>>><<<<<<<\"}";
        Map result = JSONObject.parseObject(content);
        System.out.println(content);


    }

    @Test
    public void test34() {
        BigDecimal bigDecimal = new BigDecimal("21");
        Map<String, Object> map = Maps.newHashMap();
        map.put("bd", bigDecimal);
        System.out.println(JacksonUtil.toJson(map));

    }

    @Test
    public void test35() {
        List l = Lists.newArrayList();
        List objects = Lists.newArrayList();
        objects.add(null);

        l.addAll(objects);
        System.out.println(l.size());

    }

    @Test
    public void test36() {
        Animal animal = new Dog();
        Dog dog = (Dog) animal;
        dog.name = "1";
        System.out.println(dog.name);
    }


    @Test
    public void test37() {
        AtomicInteger count = new AtomicInteger();
        IntStream.range(0, 10).forEach((k) -> {
            List<String> list = Lists.newArrayList();
            IntStream.range(0, 274).forEach((i) -> list.add(String.valueOf(i)));
            List<List<String>> listFormSet = ListUtil.instance.getListGroup(list, 20, count.incrementAndGet());
            System.out.println(listFormSet);
        });
    }

    @Test
    public void test38() throws InterruptedException {
        IntStream.range(0, 100).forEach((i) -> {
            try {
                System.out.println("-");
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        for (int i = 0; i < 10; i++) {
            System.out.println("-");
            Thread.sleep(1000);
        }
    }

    @Test
    public void test39() {
        List<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5, 6, 6, 6, 6);
        List<Integer> listTemp = Lists.newArrayList();

        for (int i = 0; i < list.size(); i++) {
            Integer k = list.get(i);
            try {
                if (k == 1 || k == 2 || k == 6) {
                    throw new RuntimeException();
                }
                listTemp.add(k);
            } catch (Throwable e) {
                list.remove(i);
                i--;
            }
        }

        System.out.println(list);
        System.out.println(listTemp);

    }

    @Test
    public void test40() {
        List<List<String>> listList = Lists.newArrayList();
        List<String> list1 = Lists.newArrayList("1", "1", "1", "1", "1");
        List<String> list2 = Lists.newArrayList("1", "1", "1", "1", "1", "1");
        listList.add(list1);
        listList.add(list2);
        System.out.println(ListUtil.instance.getListGroup(list1, 5, 0));
        System.out.println(ListUtil.instance.getListGroup(list2, 5, 0));

//        InvokeTask.newInstance(() ->
//                System.out.println(ListSubUtil.instance.getListGroup(list2, 5, 0))).start();
    }

    @Test
    public void test41() {
        List<Integer> collect = IntStream.range(0, 10).boxed().collect(Collectors.toList());
        Integer integer = collect.stream().findFirst().orElse(100);
        System.out.println(integer);
    }

    @Test
    public void test42() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Map<String, Object> map = Maps.newHashMap();
//        map.put("type", "a");
        map.put("id", "abcd");
        Bean bean = JacksonUtil.fromJson(JacksonUtil.toJson(map), Bean.class);
//        System.out.println(bean);

        Set<ConstraintViolation<Bean>> constraintViolations = validator.validate(bean);

        //如果ite中没有信息，就是正确的
        Iterator<ConstraintViolation<Bean>> ite = constraintViolations.iterator();
        while (ite.hasNext()) {
            System.out.println(ite.next().getMessage());
//            throw new RuntimeException(ite.next().getMessage());
        }
    }

    @Test
    public void test43() {
        DataChangeBean dataChangeBean = new DataChangeBean();
        System.out.println(hashKey(dataChangeBean));
        System.out.println(12 & hashKey(dataChangeBean));
        System.out.println(100 & hashKey(dataChangeBean));
//        System.out.println(hashKey((int)'s'));
//        System.out.println(hashKey("a"));
    }

    @Test
    public void test44() {
        HashMap hashMap = new HashMap();
        //Node[]:若hashCode一样，equals不一样，在添加时往链表添加，equals一样时，则时覆盖原有的k，当一个node的size>8时转为红黑树
        hashMap.put(new Node("A"), "haha");
        hashMap.put(new Node("A"), "haha");
        hashMap.put(new Node("A"), "haha");
        hashMap.put(new Node("A"), "haha");

        hashMap.put(new Node("A"), "haha");
        hashMap.put(new Node("A"), "haha");
        hashMap.put(new Node("A"), "haha");
        hashMap.put(new Node("A"), "haha");
        hashMap.put(new Node("A"), "haha");
        hashMap.put(new Node("A"), "haha");
        hashMap.put(new Node("A"), "haha");
        hashMap.put(new Node("A"), "haha");
        hashMap.put(new Node("A"), "haha");

        System.out.println(hashMap);
    }

    @Test
    public void test45() {
        Set<Integer> strings = Sets.newConcurrentHashSet();
        IntStream.range(0, 4).forEach(strings::add);
        for (Integer k : strings) {
            if (k == 1) {
//                strings.remove(k);
            }
        }
        Iterator<Integer> iterator = strings.iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            if (next == 1) {
                strings.remove(next);
            }
        }
    }

    @Test
    public void test46() {
        HashMap map = new HashMap();
        map.put(1, 1);
        System.out.println(map.size());
    }

    @Test
    public void test47() throws IntrospectionException {
        BeanInfo beanInfo = Introspector.getBeanInfo(Node.class);
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            System.out.println(propertyDescriptor);
        }
    }

    @Test
    public void test48() throws IntrospectionException {
        String id = "100";
        String md5Hex = DigestUtils.md5Hex(id);
        String s = md5Hex.substring(md5Hex.length() - 3);
        int num = Integer.valueOf(s, 16);
        int route = num % 3;
        System.out.println(route);
    }

    @Test
    public void test49() {
        Class kClazz = Car.KOENIGSEGG.getClass();
        Field[] fields = kClazz.getFields();
        for (Field field : fields) {
            if (Modifier.isPublic(field.getModifiers()) &&
                    Modifier.isStatic(field.getModifiers()) &&
                    Modifier.isFinal(field.getModifiers())) {
                System.out.println(field.getName() + "元素的修饰符为public static final");
            }
        }
    }

    @Test
    public void test50() {
        //创建入参创建对应的实例
        CarFactory.ICar iCar = CarFactory.ICarImpl.valueOf(Car.KOENIGSEGG.name());

        //用KOENIGSEGG这个对象去调用printCatName方法
        Assert.assertSame("科尼赛克价格：1.0E9",
                iCar.printCatName(CarFactory.Car.KOENIGSEGG));
    }

    @Test
    public void test51(){
        String id=UUID.randomUUID().toString()+"_index";
        System.out.println(id.endsWith("_index"));
        System.out.println(id.matches(".*_index$"));
        System.out.println(id);

        System.out.println(GsonUtil.toJson(Car.KOENIGSEGG.toString()));
    }

    @Test
    public void test52(){
        System.out.println(MD5.create().digestHex("1234567890"));
    }


    @Test public void test53(){
        EnumBean enumBean = new EnumBean();
        enumBean.setE1(E1.A);
        enumBean.setE2(E2.D);


        Student student = new Student();
        System.out.println(GsonUtil.toJson(student));

        Gson gson = new Gson();
        System.out.println(gson.toJson(student));


        String json=GsonUtil.toJson(enumBean);
        System.out.println(json);

        String json1= FastjsonUtil.toJson(enumBean);
        System.out.println(json1);

        String json2= JacksonUtil.toJson(enumBean);
        System.out.println(json2);
    }

    @Test public void test54(){
        System.out.println(
                GsonUtil.fromJson("{\"A\":{\"code\":1,\"name\":\"A_Name\"}}",E1.class)
        );
    }

    @Test public void test55(){
        Double d=22.33;
        System.out.println(FastjsonUtil.fromJson(FastjsonUtil.toJson(d),BigDecimal.class));
    }

    @Test public void test56(){
        // -Dspring.profiles.active=ceshi
        System.out.println(System.getProperty("profiles"));
    }
    @Test public void test57(){

        Assert.assertTrue(ObjectUtils.isEmpty(null));
        Assert.assertTrue(ObjectUtils.isEmpty(Lists.newArrayList()));
        Assert.assertTrue(ObjectUtils.isEmpty(Maps.newHashMap()));
        Assert.assertTrue(ObjectUtils.isEmpty(new int[]{}));
        Assert.assertTrue(ObjectUtils.isEmpty(""));
    }
    @Test public void test58(){
        Long currentTime = System.currentTimeMillis();
        // 30 分钟
        Long thirtyMinutes = 30*60*1000L;
        System.out.println(System.currentTimeMillis());
        // 当前时间之前30分钟
        System.out.println(currentTime-thirtyMinutes);
    }
    @Test public void test59() throws InterruptedException, IOException {
        int len=100000000;
        int[] batchLineArray = new int[len];
        Random random = new Random();
        IntStream.range(0,len).forEach(e->{
            batchLineArray[e]= random.nextInt(10000000);
        });
        Arrays.parallelSort(batchLineArray);
        System.out.println("qq");
//        System.out.println(Arrays.toString(batchLineArray));
        IntStream.range(0,10).forEach(e->{
            System.out.println("start:"+batchLineArray[e]);
        });
        System.out.println("end:"+batchLineArray[len-1]);
        System.in.read();
    }
    @Test public void test60(){
        Map<String,Integer> map = new HashMap<>();
        map.put("a:",1);
        map.put("b:",2);
        map.put("c:",3);
        map.put("d:",4);
        map.put("e:",5);

        Set<Map.Entry<String, Integer>> entries = map.entrySet();
        for(Map.Entry<String, Integer> entry:entries){
            System.out.println(entry.getValue());
            System.out.println(entry.getKey());
        }
    }
    @Test public void test61(){
        StringBuilder stringBuffer=new StringBuilder();
        stringBuffer.append("111");
        System.out.println(stringBuffer.length());
        System.out.println(stringBuffer.toString());
        stringBuffer.delete(0,stringBuffer.length());
        System.out.println(stringBuffer.toString());

    }
    @Test public void test62(){
        System.out.println(39201390214L >> 10); // 39201390214L / 2^10

        System.out.println(3920139022L&((1<<10)-1)); // 39201390214L / 2^10
        System.out.println(3920139022L%1024); // 39201390214L / 2^10
    }
    @Test public void test63() throws IOException {
        /**
         * abc
         * 123
         *
         */
        RandomAccessFile file=new RandomAccessFile("/Users/didi/Documents/test/test_a.txt","r");
        System.out.println("line:"+file.length());
        System.out.println("pointer:"+file.getFilePointer());
        // 移动光标
        file.seek(0);
        System.out.println(file.getFilePointer());
        String line;
        while ((line=file.readLine())!=null){
            System.out.println("readLine:"+line);
        }
    }

    @Test public void test64() throws IOException {
        // 太慢了
        RandomAccessFile file=new RandomAccessFile("/Users/didi/Documents/test/test_big_file_sort/test_5000w.txt","r");
        String line;
        long start = System.currentTimeMillis();
        int i=0;
        while ((line=file.readLine())!=null){
            i++;
            System.out.println("readLine:"+line);
        }
        System.out.println("i:"+i);
        System.out.println("duration:"+(System.currentTimeMillis()-start));
    }


    @Test public void test65(){
        // 5000w:4s
        try (BufferedReader br = new BufferedReader(new FileReader("/Users/didi/Documents/test/test_big_file_sort/test_5000w.txt"))) {
            // 按行读取字符串
            long start = System.currentTimeMillis();
            LineIterator iterator = new LineIterator(br);
            int count=0;
            List<String> list=Lists.newArrayList();
            while (iterator.hasNext()) {
                String line = iterator.nextLine();
                list.add(line);
                if(count%10000==0){
                    System.out.println("----list.size:"+list.size());
                    list.clear();
                    System.out.println("----count:"+count);
                    System.out.println("----list.size:"+list.size());
                }
//                System.out.println(line);
                count++;
            }
            System.out.println("count:"+count);
            System.out.println("duration:"+(System.currentTimeMillis()-start));
        } catch (Exception e) {
        }
    }

    @Test public void test66(){
        File file = new File("/Users/didi/Documents/test/test_big_file_sort_01/test_split_725.txt");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Test public void test67() throws InterruptedException {
        StopWatch stopWatch=new StopWatch("1");
        stopWatch.start("111");
        TimeUnit.SECONDS.sleep(2);
        stopWatch.stop();
        stopWatch.shortSummary();

        stopWatch.start("222");
        TimeUnit.SECONDS.sleep(2);
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
    }
    @Test public void test68(){
        org.apache.commons.lang3.time.StopWatch stopWatch = new org.apache.commons.lang3.time.StopWatch();
        stopWatch.start();

    }
    @Test public void test69(){
        System.out.println(false&&false||true);
    }
    @Test public void test70(){
        try {
            throw new RuntimeException();
        }finally {
            System.out.println("----");
        }

//        System.out.println("end");
    }
    @Test public void test71(){
        System.out.println((int)'A');
        System.out.println((int)'a');
        System.out.println((int)'0');
        System.out.println((int)'9');
        System.out.println((int)' ');
    }
    @Test public void test(){}

}

