package com.github.whvixd;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.github.whvixd.message.InvokeTask;
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
import com.google.gson.reflect.TypeToken;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.Test;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.stringtemplate.v4.ST;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.net.SocketTimeoutException;
import java.text.NumberFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
public class TestTmp {
    @Data
    @AllArgsConstructor
    static class Student {

        @NotNull(message = "name不为空")
        private String name;
        private int age;
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


    enum Tenum {
        A {
            public void v() {
                System.out.println("A");
            }
        };

        public void v() {
            System.out.println("v()");
        }
    }

    @Data
    public static class C {
        private List<C> cs;

        public boolean hasC() {
            return !CollectionUtils.isEmpty(cs);
        }
    }

    public void recursion(C c) {
        if (c == null) {
            return;
        }

        boolean f = c.hasC();
        if (f) {
            List<C> cs = c.getCs();
            cs.forEach(c1 -> {
                if (c1.hasC()) {
                    recursion(c1);
                    return;
                }
                System.out.println("hava c");
            });
        }

    }

    Map<String, Object> getObject(String id, Map<String, Map<String, Object>> cache, Function<String, Map> function) {
        Map<String, Object> obj = cache.get(id);
        if (obj == null) {
            obj = function.apply(id);
            if (obj != null) {
                cache.putIfAbsent(id, obj);
                return obj;
            }
            System.out.println("对象为空！");
        }
        return obj;
    }


    void test() {
        Map<String, Map<String, Object>> cache = Maps.newHashMap();
        cache.put("1", Maps.newHashMap());

        getObject("1", Maps.newHashMap(), (id) ->
                getObject("1", Maps.newHashMap(), (i) ->
                        cache.get("1"))
        );
    }

    public static void main(String[] args) {


        Config.getConfig("", iConfig -> {
            iConfig.getName();
        });
    }

    static class Config {
        static void getConfig(String name, IConfigLisener iConfigLisener) {
        }
    }

    interface IConfigLisener {
        void change(IConfig iConfig);
    }

    class IConfig {
        private String name = "config";

        public String getName() {
            return this.name;
        }
    }

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

        com.github.whvixd.testUtil.Student student = com.github.whvixd.testUtil.Student.getInstance();
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
        com.github.whvixd.testUtil.Student student = com.github.whvixd.testUtil.Student.getInstance();
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
            List<List<String>> listFormSet = ListSubUtil.instance.getListGroup(list, 20, count.incrementAndGet());
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
        System.out.println(ListSubUtil.instance.getListGroup(list1, 5, 0));

        InvokeTask.newInstance(() ->
                System.out.println(ListSubUtil.instance.getListGroup(list2, 5, 0))).start();
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
        Bean bean = JacksonUtil.fromJson(JacksonUtil.toJson(map), Bean.class);
        System.out.println(bean);

        Set<ConstraintViolation<Bean>> constraintViolations = validator.validate(bean);
        //如果ite中没有信息，就是正确的
        Iterator<ConstraintViolation<Bean>> ite = constraintViolations.iterator();
        while (ite.hasNext()) {
            System.out.println(ite.next().getMessage());
        }
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


}

