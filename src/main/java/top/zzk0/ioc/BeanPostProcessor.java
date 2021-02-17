package top.zzk0.ioc;

public interface BeanPostProcessor {

    Object postProcessBeforeInitialization(String name, Object bean) throws Exception;
    Object postProcessAfterInitialization(String name, Object bean) throws Exception;
}
