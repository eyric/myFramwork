package com.levin.spring.mvc.framework.context;

import com.levin.spring.mvc.framework.annotation.Autowired;
import com.levin.spring.mvc.framework.annotation.Controller;
import com.levin.spring.mvc.framework.annotation.Service;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ Author: levin
 * @ date: 2018/2/24 下午10:30
 * @ description:
 */
public class ApplicationContext {

    private Map<String, Object> instanceMapping = new ConcurrentHashMap<>();

    private Properties config = new Properties();

    //类似于内部的配置信息，我们在外面是看不到的
    //我们能够看到的只有ioc容器  getBean方法来间接调用的
    private List<String> classCache = new ArrayList<>();

    public ApplicationContext(String location) {

        InputStream is;
        try {
            //1、定位
            is = this.getClass().getClassLoader().getResourceAsStream(location);

            //2、载入
            config.load(is);

            //3、注册，找出所有class
            String packageName = config.getProperty("scanPackage");
            doRegister(packageName);


            //4、实例化需要ioc的对象(就是加了@Service，@Controller)，只要循环class了
            doCreateBean();

            //5、注入
            populate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 把符合条件所有的class全部找出来，注册到缓存里面去
     *
     * @param packageName 包名
     */
    private void doRegister(String packageName) {
        URL resource = this.getClass().getClassLoader().getResource("/" + packageName.replaceAll("\\.", "/"));
        if (resource == null || resource.getFile() == null) {
//            throw new NullPointerException("the resource or its file is null!");
            return;
        }
        File dir = new File(resource.getFile());
        File[] files = dir.listFiles();
        if (files == null) {
//            throw new NullPointerException("there is no file in the resource directory!");
            return;
        }
        for (File file : files) {
            //如果是一个文件夹，继续递归
            if (file.isDirectory()) {
                doRegister(packageName + "." + file.getName());
            } else {
                classCache.add(packageName + "." + file.getName().replace(".class", "").trim());
            }
        }
    }

    private void doCreateBean() {
        //检查看有没有注册信息,注册信息里面保存了所有的class名字
        //BeanDefinition 保存了类的名字，也保存类和类之间的关系(Map/list/Set/ref/parent)
        //processArray
        if (classCache.size() == 0) {
            return;
        }

        try {

            for (String className : classCache) {

                Class<?> clazz = Class.forName(className);

                //判断只要加了相关注解（@Service，@Controller）的都要初始化
                if (clazz.isAnnotationPresent(Controller.class)) {
                    //默认类名首字母小写
                    String id = lowerFirstChar(clazz.getSimpleName());
                    instanceMapping.put(id, clazz.newInstance());
                } else if (clazz.isAnnotationPresent(Service.class)) {
                    Service service = clazz.getAnnotation(Service.class);

                    //如果设置了自定义名字，就优先使用自定义名字
                    String id = service.value();
                    if (!"".equals(id.trim())) {
                        instanceMapping.put(id, clazz.newInstance());
                        continue;
                    }

                    //如果是空的，就用默认规则类名首字母小写，如果是接口可以根据类型匹配
                    Class<?>[] interfaces = clazz.getInterfaces();
                    //如果这个类实现了接口，就用接口的类型作为id
                    for (Class<?> anInterface : interfaces) {
                        instanceMapping.put(anInterface.getSimpleName(), clazz.newInstance());
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void populate() {
        //首先判断ioc容器中是否为空
        if (instanceMapping.isEmpty()) {
            return;
        }

        for (Map.Entry<String, Object> entry : instanceMapping.entrySet()) {
            //把所有的属性全部取出来，包括私有属性
            Field[] fields = entry.getValue().getClass().getDeclaredFields();

            for (Field field : fields) {
                if (!field.isAnnotationPresent(Autowired.class)) {
                    continue;
                }
                Autowired autowired = field.getAnnotation(Autowired.class);

                String id = autowired.value().trim();
                //如果id为空，也就是说，自己没有设置，默认根据类型来注入
                if ("".equals(id)) {
                    id = field.getType().getName();
                }

                //开发私有变量属性
                field.setAccessible(true);

                try {
                    field.set(entry.getValue(), instanceMapping.get(id));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 将首字母小写
     *
     * @param str 原字符串
     * @return 转换后的字符串
     */
    private String lowerFirstChar(String str) {
        char[] chars = str.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }

    public Object getBean(String beanName) {
        return instanceMapping.getOrDefault(beanName, null);
    }

    public Map<String, Object> getAll() {
        return instanceMapping;
    }

    public Properties getConfig() {
        return config;
    }
}
