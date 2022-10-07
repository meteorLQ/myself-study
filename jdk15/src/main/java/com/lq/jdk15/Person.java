package com.lq.jdk15;

/**
 * JEP 360:Sealed Classes(Preview)密封的类和接口（预览）
 *
 * @author LQ
 * @date 2020/10/22 16:36
 */
public sealed class Person permits Teacher, Student, Worker {
}

final class Teacher extends Person {
}

sealed class Student extends Person permits PostgraduateStudent, MiddleSchoolStudent {
}

final class PostgraduateStudent extends Student {
}

final class MiddleSchoolStudent extends Student {
}

non-sealed class Worker extends Person {
}