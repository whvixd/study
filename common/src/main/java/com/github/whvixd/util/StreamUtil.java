package com.github.whvixd.util;

import lombok.experimental.UtilityClass;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wangzhx on 2018/10/12.
 */
@UtilityClass
public class StreamUtil {

    /**
     * 读取流的内容
     *
     * @param inputStream
     * @return
     * @throws IOException
     */
    public String readStream2String(final InputStream inputStream) {
        StringBuilder stringBuffer = new StringBuilder();

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
            String content;
            while (Objects.nonNull(content = bufferedReader.readLine())) {
                stringBuffer.append(String.format("%s\n", content));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return stringBuffer.toString();
    }

    public String readByteStream2String(final InputStream inputStream) throws IOException {
        byte[] bytes = new byte[1024];
        int len;
        StringBuilder sb = new StringBuilder();
        while ((len = inputStream.read(bytes)) != -1) {
            sb.append(new String(bytes, 0, len, "UTF-8"));
        }
        inputStream.close();
        return sb.toString();
    }

    /**
     * 向输出流中写信息
     *
     * @param outputStream
     * @param message
     */
    public void writeStreamWithString(final OutputStream outputStream, final String message) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream))) {
            bufferedWriter.write(message);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 读取文件中的英文字母
     *
     * @param fileName
     * @return
     * @throws IOException
     */
    public String readFileWithEnglish(String fileName) throws IOException {
        String fileContent = IOUtils.toString(StreamUtil.class.getClassLoader().getResourceAsStream(fileName));
        StringBuilder stringBuffer = new StringBuilder();
        Pattern compile = Pattern.compile("[a-zA-Z]+");
        Matcher matcher = compile.matcher(fileContent);
        while (matcher.find()) {
            stringBuffer.append(matcher.group()).append("\t");
        }
        return stringBuffer.toString();
    }
}
