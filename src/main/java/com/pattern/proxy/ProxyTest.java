/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: ProxyTest
 * Author:   martin
 * Date:     2018/5/17 14:37
 * Description:
 *          静态代理和动态代理的区别
 *              【1】 冗余。由于代理对象要与目标对象实现一致的接口，会产生过多的代理类。
 *              【2】 不易维护。一旦接口增加方法，目标对象与代理对象都要进行修改。
 *              【3】 静态代理在编译时就已经实现，编译完成后代理类是一个实际的class文件
 *                  而动态代理是在运行时动态生成的，即编译完成后没有实际的class文件，而是在运行时动态生成类字节码，并加载到JVM中
 *          动态代理和cglib代理的区别
 *              使用动态代理的对象必须实现一个或多个接口
 *              使用cglib代理的对象则无需实现接口，达到代理类无侵入。
 *
 *          总结:
 *              静态代理实现较简单，只要代理对象对目标对象进行包装，即可实现增强功能，但静态代理只能为一个目标对象服务，如果目标对象过多，则会产生很多代理类。
 *              JDK动态代理需要目标对象实现业务接口，代理类只需实现InvocationHandler接口。
 *              动态代理生成的类为 class com.sun.proxy.$Proxy4，
 *              cglib代理生成的类为class com.cglib.UserDao$$EnhancerByCGLIB$$552188b6。
 *              静态代理在编译时产生class字节码文件，可以直接使用，效率高。
 *              动态代理必须实现InvocationHandler接口，通过反射代理方法，比较消耗系统性能，但可以减少代理类的数量，使用更灵活。
 *              cglib代理无需实现接口，通过生成类字节码实现代理，比反射稍快，不存在性能问题，但cglib会继承目标对象，需要重写方法，所以目标对象不能为final类。
 * Histoy:
 * <author>          <time>          <version>          <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.pattern.proxy;
//静态代理+动态代理+Cglib代理 测试
public class ProxyTest {

    public static void main(String[] args) throws Throwable{

        //【1】 静态代理 测试
        UserService userService2 = new UserServiceImpl();
        UserProxyServiceImpl userProxyService = new UserProxyServiceImpl(userService2);
        System.out.println(userProxyService.getClass());  //输出静态代理对象信息
        userProxyService.add();

        // 【2】 动态代理 测试
        // 实例化目标对象
        UserService userService = new UserServiceImpl();
        // 实例化InvocationHandler
        MyInvocationHandler invocationHandler = new MyInvocationHandler(userService);
        // 根据目标对象生成代理对象(强制转换Object成代理对象【$Proxy11 extends Proxy  implements UserService 】 )
        UserService proxy = (UserService) invocationHandler.getProxy();
        System.out.println(proxy.getClass());  //输出动态代理对象信息
        // 调用代理对象($Proxy11)的方法
        proxy.add();

        // 【3】 Cglib代理 测试
        //目标对象
        CglibUserServiceImpl target = new CglibUserServiceImpl();
        System.out.println(target.getClass());
        //代理对象
        CglibUserServiceImpl cglibProxy = (CglibUserServiceImpl) new CglibProxyFactory(target).getProxyInstance();
        System.out.println(cglibProxy.getClass());
        //执行代理对象方法
        cglibProxy.add();

    }

}
