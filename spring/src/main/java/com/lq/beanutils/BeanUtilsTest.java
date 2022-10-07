package com.lq.beanutils;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.junit.jupiter.api.Test;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.util.StopWatch;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author LQ
 * @date 2020/10/29 10:05
 */
public class BeanUtilsTest {

    /**
     * org.springframework.beans.BeanUtils
     *
     * @param personDO
     * @param times
     */
    private void mappingBySpringBeanUtils(PersonDO personDO, int times) {
        StopWatch stopwatch = new StopWatch();
        stopwatch.start();
        for (int i = 0; i < times; i++) {
            PersonDTO personDTO = new PersonDTO();
            org.springframework.beans.BeanUtils.copyProperties(personDO, personDTO);
        }
        stopwatch.stop();
        System.out.println("mappingBySpringBeanUtils cost :" + stopwatch.
                getTotalTimeMillis());
    }

    /**
     * Cglib BeanCopier
     *
     * @param personDO
     * @param times
     */
    private void mappingByCglibBeanCopier(PersonDO personDO, int times) {
        StopWatch stopwatch = new StopWatch();
        stopwatch.start();
        for (int i = 0; i < times; i++) {
            PersonDTO personDTO = new PersonDTO();
            BeanCopier copier = BeanCopier.create(PersonDO.class, PersonDTO.
                    class, false);
            copier.copy(personDO, personDTO, null);
        }
        stopwatch.stop();
        System.out.println("mappingByCglibBeanCopier cost :" + stopwatch.
                getTotalTimeMillis());
    }

    /**
     * Apache BeanUtils
     *
     * @param personDO
     * @param times
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    private void mappingByApacheBeanUtils(PersonDO personDO, int times)
            throws InvocationTargetException, IllegalAccessException {
        StopWatch stopwatch = new StopWatch();
        stopwatch.start();
        for (int i = 0; i < times; i++) {
            PersonDTO personDTO = new PersonDTO();
            BeanUtils.copyProperties(personDTO, personDO);
        }
        stopwatch.stop();
        System.out.println("mappingByApacheBeanUtils cost :" + stopwatch.
                getTotalTimeMillis());
    }

    /**
     * Apache PropertyUtils
     *
     * @param personDO
     * @param times
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     */
    private void mappingByApachePropertyUtils(PersonDO personDO, int times)
            throws InvocationTargetException, IllegalAccessException,
            NoSuchMethodException {
        StopWatch stopwatch = new StopWatch("mappingByApachePropertyUtils");
        stopwatch.start();
        for (int i = 0; i < times; i++) {
            PersonDTO personDTO = new PersonDTO();
            PropertyUtils.copyProperties(personDTO, personDO);
        }
        stopwatch.stop();
        System.out.println("mappingByApachePropertyUtils cost :" + stopwatch.
                prettyPrint());
    }


    public static void main(String[] args)
            throws InvocationTargetException, IllegalAccessException,
            NoSuchMethodException {
        PersonDO personDO = new PersonDO();
        personDO.setName("LQ");
        personDO.setAge(26);
        personDO.setBirthday(new Date());
        personDO.setId(1);
        BeanUtilsTest mapperTest = new BeanUtilsTest();
        mapperTest.mappingBySpringBeanUtils(personDO, 100);
        mapperTest.mappingBySpringBeanUtils(personDO, 1000);
        mapperTest.mappingBySpringBeanUtils(personDO, 10000);
        mapperTest.mappingBySpringBeanUtils(personDO, 100000);
        mapperTest.mappingBySpringBeanUtils(personDO, 1000000);
        mapperTest.mappingByCglibBeanCopier(personDO, 100);
        mapperTest.mappingByCglibBeanCopier(personDO, 1000);
        mapperTest.mappingByCglibBeanCopier(personDO, 10000);
        mapperTest.mappingByCglibBeanCopier(personDO, 100000);
        mapperTest.mappingByCglibBeanCopier(personDO, 1000000);
        mapperTest.mappingByApachePropertyUtils(personDO, 100);
        mapperTest.mappingByApachePropertyUtils(personDO, 1000);
        mapperTest.mappingByApachePropertyUtils(personDO, 10000);
        mapperTest.mappingByApachePropertyUtils(personDO, 100000);
        mapperTest.mappingByApachePropertyUtils(personDO, 1000000);
        mapperTest.mappingByApacheBeanUtils(personDO, 100);
        mapperTest.mappingByApacheBeanUtils(personDO, 1000);
        mapperTest.mappingByApacheBeanUtils(personDO, 10000);
        mapperTest.mappingByApacheBeanUtils(personDO, 100000);

        mapperTest.mappingByApacheBeanUtils(personDO, 1000000);
    }

    @Test
    void testStopWatch() throws InterruptedException {
        StopWatch stopWatch = new StopWatch("testStopWatch");
        stopWatch.start();
        TimeUnit.SECONDS.sleep(5);
        stopWatch.stop();

        System.out.println(stopWatch.getTotalTimeSeconds());
    }
}
