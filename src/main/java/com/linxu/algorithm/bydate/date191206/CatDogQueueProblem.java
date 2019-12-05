package com.linxu.algorithm.bydate.date191206;

/**
 * @author linxu
 * @date 2019/12/5
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 猫狗队列问题：
 * 1、调用add方法，可以将cat或者dog放入队列中
 * 2、调用pollAll方法，可以将所有实例按照FIFO顺序依次弹出
 * 3、调用pollDog方法，可以将DOG实例按照FIFO依次弹出
 * 4、调用pollCar方法，可以将cat实例按照FIFO依次弹出
 * 5、调用isEmpty，判断所有是否为空
 * 6、调用isEmptyDog判断还有没有DOG实例
 * 7、调用isEmptyCat判断...
 */
public class CatDogQueueProblem {
    public static void main(String[] args) {
        CatDogQueue catDogQueue = new CatDogQueue();
        Dog dog = new Dog();
        Dog dog1 = new Dog();
        Dog dog2 = new Dog();

        Cat cat = new Cat();
        Cat cat1 = new Cat();
        Cat cat2 = new Cat();
        //----

        catDogQueue.add(dog);
        catDogQueue.add(dog2);
        catDogQueue.add(cat);
        catDogQueue.add(cat1);
        catDogQueue.add(cat2);
        catDogQueue.add(dog1);

        System.out.println(catDogQueue.isEmpty());

    }
}
