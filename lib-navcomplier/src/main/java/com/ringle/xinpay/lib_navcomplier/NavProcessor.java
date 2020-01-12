package com.ringle.xinpay.lib_navcomplier;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.auto.service.AutoService;
import com.ringle.xinpay.lib_navanotation.navigation.FragmentDestination;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.annotation.Annotation;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import javax.tools.FileObject;
import javax.tools.JavaFileManager;
import javax.tools.StandardLocation;


/**
 * create by 岑胜德
 * on 2020/1/6
 * 说明:
 */
@AutoService(Processor.class)
@SupportedAnnotationTypes("com.ringle.xinpay.lib_navanotation.navigation.FragmentDestination")
public class NavProcessor extends AbstractProcessor {
    private Messager messager;
    private Filer filer;
    private static final String OUTPUT_FILE_NAME = "destination.json";

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        messager = processingEnvironment.getMessager();
        filer = processingEnvironment.getFiler();
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
//        messager=roundEnvironment.
        Set<? extends Element> fragmentElements = roundEnvironment.getElementsAnnotatedWith(FragmentDestination.class);
        HashMap<String, Object> jsonObjectMap = null;
        if (!fragmentElements.isEmpty()) {
            jsonObjectMap = handleDestination(fragmentElements, FragmentDestination.class);
        }
        FileOutputStream fos = null;
        OutputStreamWriter writer = null;
        try {
            FileObject resource = filer.createResource(StandardLocation.CLASS_OUTPUT,"", OUTPUT_FILE_NAME);
            String resPath = resource.toUri().getPath();
            messager.printMessage(Diagnostic.Kind.NOTE, "resPath:" + resPath);
            String appPath = resPath.substring(0, resPath.indexOf("app") + 4);
            String assetsPath = appPath + "src/main/assets/";
            messager.printMessage(Diagnostic.Kind.NOTE, "assetsPath:" + assetsPath);
            File file = new File(assetsPath);
            if (!file.exists()) {
                file.mkdirs();
            }
            File outPutFile = new File(file, OUTPUT_FILE_NAME);
            if (outPutFile.exists()) {
                outPutFile.delete();
            }
            outPutFile.createNewFile();
            String content = JSON.toJSONString(jsonObjectMap);
            fos = new FileOutputStream(outPutFile);
            writer = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
            writer.write(content);
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (writer!=null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos!=null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

    private HashMap<String, Object> handleDestination(Set<? extends Element> elements, Class<? extends Annotation> annotationClass) {
        HashMap<String, Object> destMap = new HashMap<>();
        elements.forEach(element -> {
            TypeElement typeElement = (TypeElement) element;
            String path = null;
            String clazName = typeElement.getQualifiedName().toString();
            int id = Math.abs(clazName.hashCode());
            boolean needLogin = false;
            boolean asStarter = false;
            boolean isFragment = false;
            Annotation annotation = typeElement.getAnnotation(annotationClass);
            if (annotation instanceof FragmentDestination) {
                isFragment = true;
                FragmentDestination dest = (FragmentDestination) annotation;
                path = dest.path();
                needLogin = dest.needLogin();
                asStarter = dest.asStarter();
            }
            if (destMap.containsKey(path)) {
                messager.printMessage(Diagnostic.Kind.ERROR, "不同的Destination不允许使用相同的path:");
            } else {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", id);
                jsonObject.put("path", path);
                jsonObject.put("needLogin", needLogin);
                jsonObject.put("asStarter", asStarter);
                jsonObject.put("isFragment", isFragment);
                jsonObject.put("clazName", clazName);
                destMap.put(path, jsonObject);
            }
        });
        return destMap;
    }
}
