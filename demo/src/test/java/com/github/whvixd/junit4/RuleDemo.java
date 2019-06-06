package com.github.whvixd.junit4;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.AssumptionViolatedException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.*;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

/**
 * 参考 https://blog.csdn.net/fanxiaobin577328725/article/details/78407199 | http://ju.outofmemory.cn/entry/75607
 * <p>
 * 1. TemporaryFolder：创建新文件，并在测试后删除
 * 2. ExternalResource：例如，启动和停止服务器
 * 3. ErrorCollector： 在一个测试方法中收集多个错误,抛出异常时，程序不会停止运行，直到最后，会将所有搜集的异常抛出
 * 4. Verifier：如果对象状态不正确，则测试失败
 * 5. TestWatcher： 在方法执行期间添加事件的逻辑
 * 6. TestName： 记住在方法中使用的测试名称
 * 7. Timeout：使测试在设定的时间后失败
 * 8. ExpectedException：抛出异常的灵活断言
 * <p>
 * Created by wangzhx on 2019/1/29.
 */
public class RuleDemo {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    @Rule
    public TestName testName = new TestName();
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();
    @Rule
    public ErrorCollector errorCollector = new ErrorCollector();
    @Rule
    public Verifier verifier = new Verifier() {
        /**
         * 每个case执行之前先进行校验
         */
        @Override
        public void verify() {
            if (Objects.isNull(collectionString) || collectionString.size() == 0) {
                throw new RuntimeException("collectionString size is null");
            }
        }
    };

    @Rule
    public TestWatcher testWatcher = new TestWatcher() {
        @Override
        public Statement apply(Statement base, Description description) {
            System.out.println("watch apply.");
            return super.apply(base, description);
        }

        @Override
        protected void succeeded(Description description) {
            watchedLog += description.getDisplayName() + " " + "success!\n";
            System.out.println(watchedLog);
            super.succeeded(description);
        }

        @Override
        protected void failed(Throwable e, Description description) {
            watchedLog += description.getDisplayName() + " " + e.getClass().getSimpleName() + "\n";
            System.out.println(watchedLog);
            super.failed(e, description);
        }

        @Override
        protected void skipped(AssumptionViolatedException e, Description description) {
            watchedLog += description.getDisplayName() + " " + e.getClass().getSimpleName() + "\n";
            System.out.println(watchedLog);
            super.skipped(e, description);
        }

        @Override
        protected void skipped(org.junit.internal.AssumptionViolatedException e, Description description) {
            System.out.println(watchedLog);
            super.skipped(e, description);
        }

        @Override
        protected void starting(Description description) {
            System.out.println(watchedLog);
            super.starting(description);
        }

        @Override
        protected void finished(Description description) {
            System.out.println(watchedLog);
            super.finished(description);
        }
    };

    @Rule
    public Timeout timeout = Timeout.seconds(2);

    private List<String> collectionString = Lists.newArrayList(" ");
    private String watchedLog = "testWatcher: ";


    @Test
    public void throwsIllegalArgumentExceptionIfIconIsNull() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Negative value not allowed");

        ThrowException classToBeTested = new ThrowException();
        classToBeTested.methodToBeTest();
        System.out.println(testName.getMethodName());//throwsIllegalArgumentExceptionIfIconIsNull
    }

    @Test
    public void temporaryFolder() throws IOException {
        File createdFolder = folder.newFolder("@rule");
        File createdFile = folder.newFile("test.txt");

        assertTrue(createdFile.exists());
        assertTrue(createdFolder.exists());

    }

    @Test
    public void errorCollector() {
        System.out.println("Hello");

        try {
            Assert.assertTrue(false);
        } catch (Throwable e) {
            errorCollector.addError(e);
        }

        Integer out = errorCollector.checkSucceeds(() -> {
            Assert.assertTrue(true);//异常 结果不能返回。无异常 返回结果
            return 1;
        });
        System.out.println(out);

        System.out.println("World");
    }

    @Test
    public void verifier() {
        collectionString.add("c");

    }

    @Test
    public void testWatcher() {
//        fail();
    }

    @Test
    public void timeout() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
    }

    class ThrowException {
        void methodToBeTest() {
            throw new IllegalArgumentException("Negative value not allowed");
        }
    }

}
