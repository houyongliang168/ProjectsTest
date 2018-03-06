package com.example.houyongliang.rxjavaexercisedemo;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.Notification;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.BiPredicate;
import io.reactivex.functions.BooleanSupplier;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by houyongliang on 2018/2/8.
 */

public class RxjavaSingleTools {
    private static RxjavaSingleTools mSingleTools = null;
    private static final String TAG = "Rxjava";
    private RxjavaSingleTools(){}
    public static RxjavaSingleTools getInstance() {
      if (mSingleTools == null) {
        synchronized (RxjavaSingleTools.class) {
            if (mSingleTools == null) {
                mSingleTools = new RxjavaSingleTools();
            }
        }
        }
        return mSingleTools;
    }
    /*Rxjava 测试---  基本功能*/
    public void RxJavaTestOne() {
        //步骤1：创建被观察者 Observable $ 生产事件
        // 即 顾客入饭店 - 坐下餐桌 - 点菜

        //  1. 创建被观察者 Observable 对象
        Observable.create(new ObservableOnSubscribe<Integer>() {
            // 2. 在复写的subscribe（）里定义需要发送的事件
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        }).subscribe(new Observer<Integer>() {
            // 2. 通过通过订阅（subscribe）连接观察者和被观察者
            // 3. 创建观察者 & 定义响应事件的行为
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "开始采用subscribe连接");
            }

            @Override
            public void onNext(Integer value) {
                Log.d(TAG, "对Next事件" + value + "作出响应");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "对Error事件作出响应");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "对Complete事件作出响应");
            }
        });

        /*

        @SchedulerSupport(SchedulerSupport.NONE)
        public static <T> Observable<T> create(ObservableOnSubscribe<T> source) {
        ObjectHelper.requireNonNull(source, "source is null");
        return RxJavaPlugins.onAssembly(new ObservableCreate<T>(source));



    }
*/

    }

    /*Rxjava 测试2 ---  不同的 事件接收 细节 observable .subscribe 的处理*/
    public void RxJavaTestTwo() {
        //步骤1：创建被观察者 Observable $ 生产事件
        // 即 顾客入饭店 - 坐下餐桌 - 点菜

        //  1. 创建被观察者 Observable 对象
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            // 2. 在复写的subscribe（）里定义需要发送的事件
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        });
/*---------------------------------------------------------------------------------------*/
        /*创建观察者*/
//        public final Disposable subscribe() {}
        // 表示观察者不对被观察者发送的事件作出任何响应（但被观察者还是可以继续发送事件）

/*        public final Disposable subscribe(Consumer<? super T> onNext) {}
        // 表示观察者只对被观察者发送的Next事件作出响应
        public final Disposable subscribe(Consumer<? super T> onNext, Consumer<? super Throwable> onError) {}
        // 表示观察者只对被观察者发送的Next事件 & Error事件作出响应

        public final Disposable subscribe(Consumer<? super T> onNext, Consumer<? super Throwable> onError, Action onComplete) {}
        // 表示观察者只对被观察者发送的Next事件、Error事件 & Complete事件作出响应

        public final Disposable subscribe(Consumer<? super T> onNext, Consumer<? super Throwable> onError, Action onComplete, Consumer<? super Disposable> onSubscribe) {}
        // 表示观察者只对被观察者发送的Next事件、Error事件 、Complete事件 & onSubscribe事件作出响应

        public final void subscribe(Observer<? super T> observer) {}
        // 表示观察者对被观察者发送的任何事件都作出响应*/
/*---------------------------------------------------------------------------------------*/
        /*对不同事件的接收处理*/

        //1 表示观察者不对被观察者发送的事件作出任何响应（但被观察者还是可以继续发送事件）
        Disposable subscribe = observable.subscribe();
        //2.  表示观察者只对被观察者发送的Next事件作出响应  Consumer 翻译为消费
        Disposable subscribe1 = observable.subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {

            }
        });
        //3 表示观察者只对被观察者发送的Next事件 & Error事件作出响应
        Disposable subscribe2 = observable.subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {

            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

            }
        });
        //4. 表示观察者只对被观察者发送的Next事件、Error事件 & Complete事件作出响应
        observable.subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {

            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

            }
        }, new Action() {
            @Override
            public void run() throws Exception {

            }
        });
        //5 表示观察者只对被观察者发送的Next事件、Error事件 、Complete事件 & onSubscribe事件作出响应
        observable.subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {

            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

            }
        }, new Action() {
            @Override
            public void run() throws Exception {

            }
        }, new Consumer<Disposable>() {
            @Override
            public void accept(Disposable disposable) throws Exception {

            }
        });
        //6 表示观察者对被观察者发送的任何事件都作出响应*/
        observable.subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer value) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }

    /*Rxjava 测试---  取消订阅的一种方式*/
    public void RxJavaTestThree() {
        //步骤1：创建被观察者 Observable $ 生产事件
        // 即 顾客入饭店 - 坐下餐桌 - 点菜

        //  1. 创建被观察者 Observable 对象
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            // 2. 在复写的subscribe（）里定义需要发送的事件
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        });
        //观察者对象
        Observer<Integer> observer = new Observer<Integer>() {
            // 2. 通过通过订阅（subscribe）连接观察者和被观察者
            // 3. 创建观察者 & 定义响应事件的行为

            // 1. 定义Disposable类变量
            private Disposable mDisposable;

            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "开始采用subscribe连接");
                mDisposable = d;
            }

            @Override
            public void onNext(Integer value) {
                Log.d(TAG, "对Next事件" + value + "作出响应");
                if (value == 2) {
                    // 设置在接收到第二个事件后切断观察者和被观察者的连接
                    mDisposable.dispose();
                    Log.d(TAG, "已经切断了连接：" + mDisposable.isDisposed());
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "对Error事件作出响应");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "对Complete事件作出响应");
            }
        };

        /*订阅*/
        observable.subscribe(observer);

    }


    /*Rxjava 操作符练习*/
    /*Rxjava 创建操作符练习*/
    public void rxjavaCreateTest() {

        /**
         * 1. 通过creat（）创建被观察者 Observable 对象
         */
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            // 传入参数： ObservableOnSubscribe 对象
            // 当 Observable 被订阅时，subscribe() 的方法会自动被调用，即事件序列就会依照设定依次被触发
            // 即观察者会依次调用对应事件的复写方法从而响应事件
            // 从而实现由被观察者向观察者的事件传递 & 被观察者调用了观察者的回调方法 ，即观察者模式

            /**
             * 2. 在复写的subscribe（）里定义需要发送的事件
             */

            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                // 通过 ObservableEmitter类对象 产生 & 发送事件
                // ObservableEmitter类介绍
                // a. 定义：事件发射器
                // b. 作用：定义需要发送的事件 & 向观察者发送事件

                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();


            }
        });
        Observer<Integer> observer = new Observer<Integer>() {
            // 以下步骤仅为展示一个完整demo，可以忽略
            // 3. 通过通过订阅（subscribe）连接观察者和被观察者
            // 4. 创建观察者 & 定义响应事件的行为
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "开始采用subscribe连接");
            }
            // 默认最先调用复写的 onSubscribe（）

            @Override
            public void onNext(Integer value) {
                Log.d(TAG, "接收到了事件" + value);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "对Error事件作出响应");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "对Complete事件作出响应");
            }

        };
        observable.subscribe(observer);


    }    /*Rxjava 创建操作符练习*/

    public void rxjavaJustTest() {

        // 1. 创建时传入整型1、2、3、4
        // 在创建后就会发送这些对象，相当于执行了onNext(1)、onNext(2)、onNext(3)、onNext(4)
        // 快速创建 被观察者对象（Observable） & 发送
        Observable<Bean> observable = Observable.just(new Bean(1), new Bean(2), new Bean(3));
        Observer<Bean> observer = new Observer<Bean>() {
            // 以下步骤仅为展示一个完整demo，可以忽略
            // 3. 通过通过订阅（subscribe）连接观察者和被观察者
            // 4. 创建观察者 & 定义响应事件的行为
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "开始采用subscribe连接");
            }

            @Override
            public void onNext(Bean value) {
                Log.d(TAG, "接收到了事件" + value);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "对Error事件作出响应");
            }

            // 默认最先调用复写的 onSubscribe（）
            @Override
            public void onComplete() {
                Log.d(TAG, "对Complete事件作出响应");
            }

        };
        observable.subscribe(observer);


    }

    /*数组*/
    public void rxjavaFromArrayTest() {
        //与 just 类似 调用相同的 方法  RxJavaPlugins.onAssembly(new ObservableFromArray<T>(items));
        // 1. 创建时传入整型1、2、3、4
        // 在创建后就会发送这些对象，相当于执行了onNext(1)、onNext(2)、onNext(3)、onNext(4)
        //数组元素遍历
        Bean[] items = {new Bean(1), new Bean(2), new Bean(3)};
        Observable<Bean> observable = Observable.fromArray(items);
        Observer<Bean> observer = new Observer<Bean>() {
            // 以下步骤仅为展示一个完整demo，可以忽略
            // 3. 通过通过订阅（subscribe）连接观察者和被观察者
            // 4. 创建观察者 & 定义响应事件的行为
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "开始采用 fromArray --subscribe连接");
            }

            @Override
            public void onNext(Bean value) {
                Log.d(TAG, "接收到了事件" + value);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "对Error事件作出响应");
            }

            // 默认最先调用复写的 onSubscribe（）
            @Override
            public void onComplete() {
                Log.d(TAG, "对Complete事件作出响应");
            }

        };
        observable.subscribe(observer);


    }

    /*集合*/
    public void rxjavaFromIterableTest() {
        //与 just 类似 调用相同的 方法  RxJavaPlugins.onAssembly(new ObservableFromArray<T>(items));
        // 1. 创建时传入整型1、2、3、4
        // 在创建后就会发送这些对象，相当于执行了onNext(1)、onNext(2)、onNext(3)、onNext(4)
        List<Bean> list = new ArrayList<>();
        list.add(new Bean(1));
        list.add(new Bean(2));
        list.add(new Bean(3));

        Observable<Bean> observable = Observable.fromIterable(list);
        Observer<Bean> observer = new Observer<Bean>() {
            // 以下步骤仅为展示一个完整demo，可以忽略
            // 3. 通过通过订阅（subscribe）连接观察者和被观察者
            // 4. 创建观察者 & 定义响应事件的行为
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "开始采用 FromIterable --subscribe连接");
            }

            @Override
            public void onNext(Bean value) {
                Log.d(TAG, "接收到了事件" + value);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "对Error事件作出响应");
            }

            // 默认最先调用复写的 onSubscribe（）
            @Override
            public void onComplete() {
                Log.d(TAG, "对Complete事件作出响应");
            }

        };
        observable.subscribe(observer);


    }

    /*其他*/
    public void rxjavaOtherTest() {
        //与 just 类似 调用相同的 方法  RxJavaPlugins.onAssembly(new ObservableFromArray<T>(items));
        // 1. 创建时传入整型1、2、3、4
        // 在创建后就会发送这些对象，相当于执行了onNext(1)、onNext(2)、onNext(3)、onNext(4)
        List<Bean> list = new ArrayList<>();
        list.add(new Bean(1));
        list.add(new Bean(2));
        list.add(new Bean(3));

//        Observable<Bean> observable = Observable.empty();
//        Observable<Bean> observable = Observable.error(new TimeoutException());
        Observable<Bean> observable = Observable.never();
        Observer<Bean> observer = new Observer<Bean>() {
            // 以下步骤仅为展示一个完整demo，可以忽略
            // 3. 通过通过订阅（subscribe）连接观察者和被观察者
            // 4. 创建观察者 & 定义响应事件的行为
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "开始采用 empty --subscribe连接");
            }

            @Override
            public void onNext(Bean value) {
                Log.d(TAG, "接收到了事件" + value);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "对Error事件作出响应");
            }

            // 默认最先调用复写的 onSubscribe（）
            @Override
            public void onComplete() {
                Log.d(TAG, "对Complete事件作出响应");
            }

        };
        observable.subscribe(observer);
//        <-- empty()  -->
//// 该方法创建的被观察者对象发送事件的特点：仅发送Complete事件，直接通知完成
//                Observable observable1=Observable.empty();
//// 即观察者接收后会直接调用onCompleted（）
//
//<-- error()  -->
//// 该方法创建的被观察者对象发送事件的特点：仅发送Error事件，直接通知异常
//// 可自定义异常
//                Observable observable2=Observable.error(new RuntimeException())
//// 即观察者接收后会直接调用onError（）
//
//                <-- never()  -->
//// 该方法创建的被观察者对象发送事件的特点：不发送任何事件
//                Observable observable3=Observable.never();
//// 即观察者接收后什么都不调用


    }

    /*延迟创建*/
    public static Bean i;

    public void rxjavaDelateTest() {
        //defer（）
        //直到有观察者（Observer ）订阅时，才动态创建被观察者对象（Observable） & 发送事件
        //动态创建被观察者对象（Observable） & 获取最新的Observable对象数据
        List<Bean> list = new ArrayList<>();
        list.add(new Bean(1));
        list.add(new Bean(2));
        list.add(new Bean(3));
//         <-- 1. 第1次对i赋值 ->>
        i = new Bean(1);
        Observable<Bean> observable =
                Observable.defer(new Callable<ObservableSource<? extends Bean>>() {
                    @Override
                    public ObservableSource<? extends Bean> call() throws Exception {
                        return Observable.just(i);
                    }
                });
        i = new Bean(2);
        Observer<Bean> observer = new Observer<Bean>() {
            // 以下步骤仅为展示一个完整demo，可以忽略
            // 3. 通过通过订阅（subscribe）连接观察者和被观察者
            // 4. 创建观察者 & 定义响应事件的行为
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "开始采用 empty --subscribe连接");
            }

            @Override
            public void onNext(Bean value) {
                Log.d(TAG, "接收到了事件" + value);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "对Error事件作出响应");
            }

            // 默认最先调用复写的 onSubscribe（）
            @Override
            public void onComplete() {
                Log.d(TAG, "对Complete事件作出响应");
            }

        };
        observable.subscribe(observer);


    }

    public void rxjavaDelateTimerTest() {
        //timer（）
        // 延迟指定时间后，发送1个数值0（Long类型）


        Observable<Long> observable =
                Observable.timer(2, TimeUnit.SECONDS);

        Observer<Long> observer = new Observer<Long>() {
            // 以下步骤仅为展示一个完整demo，可以忽略
            // 3. 通过通过订阅（subscribe）连接观察者和被观察者
            // 4. 创建观察者 & 定义响应事件的行为
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "开始采用 empty --subscribe连接");
            }

            @Override
            public void onNext(Long value) {
                Log.d(TAG, "接收到了事件" + value);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "对Error事件作出响应");
            }

            // 默认最先调用复写的 onSubscribe（）
            @Override
            public void onComplete() {
                Log.d(TAG, "对Complete事件作出响应");
            }

        };
        observable.subscribe(observer);


    }

    public void rxjavaDelateIntervalTest() {
        //interval（）发送的事件序列 = 从0开始、无限递增1的的整数序列

        // 参数说明：
        // 参数1 = 第1次延迟时间；
        // 参数2 = 间隔时间数字；
        // 参数3 = 时间单位；

        // 该例子发送的事件序列特点：延迟3s后发送事件，每隔2秒产生1个数字（从0开始递增1，无限个）
        Observable<Long> observable =
                Observable.interval(3, 2, TimeUnit.SECONDS);

        Observer<Long> observer = new Observer<Long>() {
            // 以下步骤仅为展示一个完整demo，可以忽略
            // 3. 通过通过订阅（subscribe）连接观察者和被观察者
            // 4. 创建观察者 & 定义响应事件的行为
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "开始采用 empty --subscribe连接");
            }

            @Override
            public void onNext(Long value) {
                Log.d(TAG, "接收到了事件" + value);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "对Error事件作出响应");
            }

            // 默认最先调用复写的 onSubscribe（）
            @Override
            public void onComplete() {
                Log.d(TAG, "对Complete事件作出响应");
            }

        };
        observable.subscribe(observer);
        // 注：interval默认在computation调度器上执行
// 也可自定义指定线程调度器（第3个参数）：interval(long,TimeUnit,Scheduler)


    }
    public void rxjavaDelateIntervalRangeTest() {
        //intervalRange（）：每隔指定时间 就发送 事件，可指定发送的数据的数量

        // 参数说明：
        // 参数1 = 事件序列起始点；
        // 参数2 = 事件数量；
        // 参数3 = 第1次事件延迟发送时间；
        // 参数4 = 间隔时间数字；
        // 参数5 = 时间单位


        // 2. 第1次延迟3s发送，之后每隔2秒产生1个数字（从0开始递增1，无限个）
        Observable<Long> observable =
                Observable.intervalRange(2,10,3, 2, TimeUnit.SECONDS);

        Observer<Long> observer = new Observer<Long>() {
            // 以下步骤仅为展示一个完整demo，可以忽略
            // 3. 通过通过订阅（subscribe）连接观察者和被观察者
            // 4. 创建观察者 & 定义响应事件的行为
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "开始采用 empty --subscribe连接");
            }

            @Override
            public void onNext(Long value) {
                Log.d(TAG, "接收到了事件" + value);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "对Error事件作出响应");
            }

            // 默认最先调用复写的 onSubscribe（）
            @Override
            public void onComplete() {
                Log.d(TAG, "对Complete事件作出响应");
            }

        };
        observable.subscribe(observer);
        // 注：interval默认在computation调度器上执行
// 也可自定义指定线程调度器（第3个参数）：interval(long,TimeUnit,Scheduler)


    }
    public void rxjavaDelateRangeTest() {
        //Range（）：连续发送 1个事件序列，可指定范围  不支持long 只支持 int


//        a. 发送的事件序列 = 从0开始、无限递增1的的整数序列
//        b. 作用类似于intervalRange（），但区别在于：无延迟发送事件

        // 参数说明：
        // 参数1 = 事件序列起始点；
        // 参数2 = 事件数量；
        // 注：若设置为负数，则会抛出异常
        Observable<Integer> observable =
                Observable.range(3,10);

        Observer<Integer> observer = new Observer<Integer>() {
            // 以下步骤仅为展示一个完整demo，可以忽略
            // 3. 通过通过订阅（subscribe）连接观察者和被观察者
            // 4. 创建观察者 & 定义响应事件的行为
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "开始采用 empty --subscribe连接");
            }

            @Override
            public void onNext(Integer value) {
                Log.d(TAG, "接收到了事件" + value);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "对Error事件作出响应");
            }

            // 默认最先调用复写的 onSubscribe（）
            @Override
            public void onComplete() {
                Log.d(TAG, "对Complete事件作出响应");
            }

        };
        observable.subscribe(observer);

    }
    public void rxjavaDelateRangeLongTest() {
        //rangeLong（）：连续发送 1个事件序列，可指定范围  不支持long 只支持 int
//        作用：类似于range（），区别在于该方法支持数据类型 = Long

//        a. 发送的事件序列 = 从0开始、无限递增1的的整数序列
//        b. 作用类似于intervalRange（），但区别在于：无延迟发送事件

        // 参数说明：
        // 参数1 = 事件序列起始点；
        // 参数2 = 事件数量；
        // 注：若设置为负数，则会抛出异常
        Observable<Long> observable =
                Observable.rangeLong(2,10);

        Observer<Long> observer = new Observer<Long>() {
            // 以下步骤仅为展示一个完整demo，可以忽略
            // 3. 通过通过订阅（subscribe）连接观察者和被观察者
            // 4. 创建观察者 & 定义响应事件的行为
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "开始采用 empty --subscribe连接");
            }

            @Override
            public void onNext(Long value) {
                Log.d(TAG, "接收到了事件" + value);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "对Error事件作出响应");
            }

            // 默认最先调用复写的 onSubscribe（）
            @Override
            public void onComplete() {
                Log.d(TAG, "对Complete事件作出响应");
            }

        };
        observable.subscribe(observer);

    }


    //    变换操作符 练习
    //变换操作符 两种操作 ： 变换顺序 和 变换事件


    public void rxjavaMapTest() {
        //     map
        // 对 被观察者发送的每个事件都通过 指定的函数 处理，从而变换成另外一种事件   1对1
        // 采用RxJava基于事件流的链式操作
        Observable.create(new ObservableOnSubscribe<Integer>() {

            // 1. 被观察者发送事件 = 参数为整型 = 1、2、3
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);

            }
            // 2. 使用Map变换操作符中的Function函数对被观察者发送的事件进行统一变换：整型变换成字符串类型
        }).map(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) throws Exception {
                return "使用 Map变换操作符 将事件" + integer + "的参数从 整型" + integer + " 变换成 字符串类型" + integer;
            }
        }).subscribe(new Consumer<String>() {

            // 3. 观察者接收事件时，是接收到变换后的事件 = 字符串类型
            @Override
            public void accept(String s) throws Exception {
                Log.d(TAG, s);
            }
        });

    }

    public void rxjavaFlatMapTest() {
        //     flatMap
        // 将被观察者发送的事件序列进行 拆分 & 单独转换，再合并成一个新的事件序列，最后再进行发送  可以1对多
        //        注：新合并生成的事件序列顺序是无序的，即 与旧序列发送事件的顺序无关
        // 采用RxJava基于事件流的链式操作
        Observable.create(new ObservableOnSubscribe<Integer>() {

            // 1. 被观察者发送事件 = 参数为整型 = 1、2、3
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);

            }
            // 2. 使用Map变换操作符中的Function函数对被观察者发送的事件进行统一变换：整型变换成字符串类型
        }).flatMap(new Function<Integer, ObservableSource<Bean>>() {
            @Override
            public ObservableSource<Bean> apply(Integer integer) throws Exception {
                final List<Bean> list = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    list.add(new Bean("我是事件 " + integer + "拆分后的子事件" + i, i));
                    // 通过flatMap中将被观察者生产的事件序列先进行拆分，再将每个事件转换为一个新的发送三个String事件
                    // 最终合并，再发送给被观察者
                }


                return Observable.fromIterable(list);
            }
        }).subscribe(new Consumer<Bean>() {

            // 3. 观察者接收事件时，是接收到变换后的事件 = 字符串类型
            @Override
            public void accept(Bean s) throws Exception {
                Log.d(TAG, s.toString());
            }
        });

    }

    public void rxjavaConcatMapTest() {
        //     ConcatMap
        // 将被观察者发送的事件序列进行 拆分 & 单独转换，再合并成一个新的事件序列，最后再进行发送  可以1对多
        //类似FlatMap（）操作符         与FlatMap（）的 区别在于：拆分 & 重新合并生成的事件序列 的顺序 = 被观察者旧序列生产的顺序
        // 采用RxJava基于事件流的链式操作
        Observable.create(new ObservableOnSubscribe<Integer>() {

            // 1. 被观察者发送事件 = 参数为整型 = 1、2、3
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);

            }
            // 2. 使用Map变换操作符中的Function函数对被观察者发送的事件进行统一变换：整型变换成字符串类型
        }).concatMap(new Function<Integer, ObservableSource<Bean>>() {
            @Override
            public ObservableSource<Bean> apply(Integer integer) throws Exception {
                final List<Bean> list = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    list.add(new Bean("我是事件 " + integer + "拆分后的子事件" + i, i));
                    // 通过flatMap中将被观察者生产的事件序列先进行拆分，再将每个事件转换为一个新的发送三个String事件
                    // 最终合并，再发送给被观察者
                }


                return Observable.fromIterable(list);
            }
        }).subscribe(new Consumer<Bean>() {

            // 3. 观察者接收事件时，是接收到变换后的事件 = 字符串类型
            @Override
            public void accept(Bean s) throws Exception {
                Log.d(TAG, s.toString());
            }
        });

    }

    public void rxjavaBuferTest() {
        //     Buffer
//        定期从 被观察者（Obervable）需要发送的事件中 获取一定数量的事件 & 放到缓存区中，最终发送
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .buffer(3, 2)// 设置缓存区大小 & 步长
                // 缓存区大小 = 每次从被观察者中获取的事件数量
                // 步长 = 每次获取新事件的数量
                .subscribe(new Consumer<List<Integer>>() {
                    @Override
                    public void accept(List<Integer> stringList) throws Exception {
                        Log.d(TAG, " 缓存区里的事件数量 = " +  stringList.size());
                        for (Integer value : stringList) {
                            Log.d(TAG, " 事件 = " + value);
                        }

                    }
                });

    }
    //组合/合并操作符
    //没有时间限制的是 concat
    public void rxjavaConcatTest() {
        // concat（）：组合多个被观察者（≤4个）一起发送数据
        // 注：串行执行
//        Observable.concat(Observable.just(1, 2, 3),
//                Observable.just(4, 5, 6),
//                Observable.just(7, 8, 9),
//                Observable.just(10, 11, 12))
//                .subscribe(new Observer<Integer>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                        Log.d(TAG, "开始连接"  );
//                    }
//
//                    @Override
//                    public void onNext(Integer value) {
//                        Log.d(TAG, "接收到了事件"+ value  );
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.d(TAG, "对Error事件作出响应");
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        Log.d(TAG, "对Complete事件作出响应");
//                    }
//                });

// concatArray（）：组合多个被观察者一起发送数据（可＞4个）
        // 注：串行执行
        Observable.concatArray(Observable.just(1, 2, 3),
                Observable.just(4, 5, 6),
                Observable.just(7, 8, 9),
                Observable.just(10, 11, 12),
                Observable.just(13, 14, 15))
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "开始了连接" );
                    }

                    @Override
                    public void onNext(Integer value) {
                        Log.d(TAG, "接收到了事件"+ value  );
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "对Complete事件作出响应");
                    }
                });

    }
    //有时间处理的是Merge
    public void rxjavaMergeTest() {
        // merge（）：组合多个被观察者（＜4个）一起发送数据
        // 注：合并后按照时间线并行执行

        Observable.merge(
                Observable.intervalRange(0, 3, 1, 1, TimeUnit.SECONDS), // 从0开始发送、共发送3个数据、第1次事件延迟发送时间 = 1s、间隔时间 = 1s
                Observable.intervalRange(2, 3, 1, 1, TimeUnit.SECONDS)) // 从2开始发送、共发送3个数据、第1次事件延迟发送时间 = 1s、间隔时间 = 1s
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long value) {
                        Log.d(TAG, "接收到了事件"+ value  );
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "对Complete事件作出响应");
                    }
                });

// mergeArray（） = 组合4个以上的被观察者一起发送数据，此处不作过多演示，类似concatArray（）


    }
    public void rxjavaConcatDelayError(){
        //concatDelayError（） / mergeDelayError（）
        //情景:第1个被观察者发送Error事件后，第2个被观察者则不会继续发送事件
        //使用 concat 和 concatArrayDelayError 的区别在于 concat 有错误 就会终止，而 concatArrayDelayError 则会 所有都走完 才会终止
        Observable.concatArrayDelayError(
                Observable.create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {

                        emitter.onNext(1);
                        emitter.onNext(2);
                        emitter.onNext(3);
                        emitter.onError(new NullPointerException()); // 发送Error事件，因为无使用concatDelayError，所以第2个Observable将不会发送事件
                        emitter.onComplete();
                    }
                }),
                Observable.just(4, 5, 6))
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(Integer value) {
                        Log.d(TAG, "接收到了事件"+ value  );
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "对Complete事件作出响应");
                    }
                });



    }

    public void rxjavaZipTest(){
        //ZIP  合并 多个被观察者（Observable）发送的事件，生成一个新的事件序列（即组合过后的事件序列），并最终发送
//        事件组合方式 = 严格按照原先事件序列 进行对位合并
//                最终合并的事件数量 = 多个被观察者（Observable）中数量最少的数量

//        <-- 创建第1个被观察者 -->
        Observable<Integer> observable1 = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                Log.d(TAG, "被观察者1发送了事件1");
                emitter.onNext(1);
                // 为了方便展示效果，所以在发送事件后加入2s的延迟
                Thread.sleep(1000);

                Log.d(TAG, "被观察者1发送了事件2");
                emitter.onNext(2);
                Thread.sleep(1000);

                Log.d(TAG, "被观察者1发送了事件3");
                emitter.onNext(3);
                Thread.sleep(1000);

                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io()); // 设置被观察者1在工作线程1中工作

//<-- 创建第2个被观察者 -->
        Observable<String> observable2 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                Log.d(TAG, "被观察者2发送了事件A");
                emitter.onNext("A");
                Thread.sleep(1000);

                Log.d(TAG, "被观察者2发送了事件B");
                emitter.onNext("B");
                Thread.sleep(1000);

                Log.d(TAG, "被观察者2发送了事件C");
                emitter.onNext("C");
                Thread.sleep(1000);

                Log.d(TAG, "被观察者2发送了事件D");
                emitter.onNext("D");
                Thread.sleep(1000);

                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.newThread());// 设置被观察者2在工作线程2中工作
        // 假设不作线程控制，则该两个被观察者会在同一个线程中工作，即发送事件存在先后顺序，而不是同时发送

//<-- 使用zip变换操作符进行事件合并 -->
// 注：创建BiFunction对象传入的第3个参数 = 合并后数据的数据类型
        Observable.zip(observable1, observable2, new BiFunction<Integer, String, String>() {
            @Override
            public String apply(Integer integer, String string) throws Exception {
                return  integer + string;
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe");
            }

            @Override
            public void onNext(String value) {
                Log.d(TAG, "最终接收到的事件 =  " + value);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete");
            }
        });


    }

    public void rxjavaCombineLatest(){
        Observable.combineLatest(
                Observable.just(1L, 2L, 3L,4L), // 第1个发送数据事件的Observable
                Observable.intervalRange(0, 3, 1, 1, TimeUnit.SECONDS), // 第2个发送数据事件的Observable：从0开始发送、共发送3个数据、第1次事件延迟发送时间 = 1s、间隔时间 = 1s
                new BiFunction<Long, Long, Long>() {
                    @Override
                    public Long apply(Long o1, Long o2) throws Exception {
                        // o1 = 第1个Observable发送的最新（最后）1个数据
                        // o2 = 第2个Observable发送的每1个数据
                        Log.e(TAG, "合并的数据是： "+ o1 + " "+ o2);
                        return o1 + o2;
                        // 合并的逻辑 = 相加
                        // 即第1个Observable发送的最后1个数据 与 第2个Observable发送的每1个数据进行相加
                    }
                }).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long s) throws Exception {
                Log.e(TAG, "合并的结果是： "+s);
            }
        });
//        combineLatestDelayError（）
//        作用类似于concatDelayError（） / mergeDelayError（） ，即错误处理，此处不作过多描述

    }
    public void rxjavaReduceTest(){
        //reduce 把被观察者需要发送的事件聚合成1个事件 & 发送
        Observable.just(1,2,3,4)
                .reduce(new BiFunction<Integer, Integer, Integer>() {
                    // 在该复写方法中复写聚合的逻辑
                    @Override
                    public Integer apply(@NonNull Integer s1, @NonNull Integer s2) throws Exception {
                        Log.e(TAG, "本次计算的数据是： "+s1 +" 乘 "+ s2);
                        return s1 * s2;
                        // 本次聚合的逻辑是：全部数据相乘起来
                        // 原理：第1次取前2个数据相乘，之后每次获取到的数据 = 返回的数据x原始下1个数据每
                    }
                }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(@NonNull Integer s) throws Exception {
                Log.e(TAG, "最终计算的结果是： "+s);

            }
        });


    }

    public void rxjavaCollectTest(){
        //collect（） 将被观察者Observable发送的数据事件收集到一个数据结构里
        Observable.just(1, 2, 3 ,4, 5, 6)

                .collect(new Callable<ArrayList<Integer>>() {
                    //// 1. 创建数据结构（容器），用于收集被观察者发送的数据
                    @Override
                    public ArrayList<Integer> call() throws Exception {
                        return new ArrayList<Integer>();
                    }
                    // 2. 对发送的数据进行收集
                }, new BiConsumer<ArrayList<Integer>, Integer>() {
                    @Override
                    public void accept(ArrayList<Integer> integers, Integer integer) throws Exception {
                        // 参数说明：list = 容器，integer = 后者数据
                        integers.add(integer);
                        // 对发送的数据进行收集
                    }
                }).subscribe(new Consumer<ArrayList<Integer>>() {
            @Override
            public void accept(@NonNull ArrayList<Integer> s) throws Exception {
                Log.e(TAG, "本次发送的数据是： "+s);

            }
        });



    }

    public void rxjavaStartWith(){
        //startWith（） / startWithArray（）
//        在一个被观察者发送事件前，追加发送一些数据
        // 注：追加数据顺序 = 后调用先追加
//        Observable.just(4, 5, 6)
//                .startWith(0)  // 追加单个数据 = startWith()
//                .startWithArray(1, 2, 3) // 追加多个数据 = startWithArray()
//                .subscribe(new Observer<Integer>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                    }
//
//                    @Override
//                    public void onNext(Integer value) {
//                        Log.d(TAG, "接收到了事件"+ value  );
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.d(TAG, "对Error事件作出响应");
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        Log.d(TAG, "对Complete事件作出响应");
//                    }
//                });

//        在一个被观察者发送事件前，追加发送被观察者 & 发送数据
        Observable.just(4, 5, 6)
                .startWith(Observable.just(1, 2, 3))
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer value) {
                        Log.d(TAG, "接收到了事件"+ value  );
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "对Complete事件作出响应");
                    }
                });





    }

    public void rxjavaCountTest(){
        // 注：返回结果 = Long类型
        Observable.just(1, 2, 3, 4)
                .count()
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Log.e(TAG, "发送的事件数量 =  "+aLong);

                    }
                });

    }


    //功能性 操作符
    public void rxjavaSubscribe() {
        // 前者 = 被观察者（observable）；后者 = 观察者（observer 或 subscriber） 完成接口回调机制
//<-- 1. 分步骤的完整调用 -->
//  步骤1： 创建被观察者 Observable 对象
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        });

// 步骤2：创建观察者 Observer 并 定义响应事件行为
        Observer<Integer> observer = new Observer<Integer>() {
            // 通过复写对应方法来 响应 被观察者
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "开始采用subscribe连接");
            }
            // 默认最先调用复写的 onSubscribe（）

            @Override
            public void onNext(Integer value) {
                Log.d(TAG, "对Next事件" + value + "作出响应");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "对Error事件作出响应");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "对Complete事件作出响应");
            }
        };


        // 步骤3：通过订阅（subscribe）连接观察者和被观察者
        observable.subscribe(observer);


//
//        <-- Observable.subscribe(Subscriber) 的内部实现 -->
//
//        public Subscription subscribe(Subscriber subscriber) {
//            subscriber.onStart();
//            // 在观察者 subscriber抽象类复写的方法 onSubscribe.call(subscriber)，用于初始化工作
//            // 通过该调用，从而回调观察者中的对应方法从而响应被观察者生产的事件
//            // 从而实现被观察者调用了观察者的回调方法 & 由被观察者向观察者的事件传递，即观察者模式
//            // 同时也看出：Observable只是生产事件，真正的发送事件是在它被订阅的时候，即当 subscribe() 方法执行时
//        }


    }

    //线程调度
    CompositeDisposable c = new CompositeDisposable();

    public void rxjavaSubscribeOnObserveOn() {
        //指定 被观察者 （Observable） / 观察者（Observer） 的工作线程类型。
        //线程调度器（ Scheduler ）
        // 步骤1：创建被观察者 Observable & 发送事件
        // 在主线程创建被观察者 Observable 对象
        // 所以生产事件的线程是：主线程

        // Observable.subscribeOn（Schedulers.Thread）：指定被观察者 发送事件的线程（传入RxJava内置的线程类型）
        // Observable.observeOn（Schedulers.Thread）：指定观察者 接收 & 响应事件的线程（传入RxJava内置的线程类型）

        // 步骤3：通过订阅（subscribe）连接观察者和被观察者
//        observable.subscribeOn(Schedulers.newThread()) // 1. 指定被观察者 生产事件的线程
//                .observeOn(AndroidSchedulers.mainThread())  // 2. 指定观察者 接收 & 响应事件的线程
//                .subscribe(observer); // 3. 最后再通过订阅（subscribe）连接观察者和被观察者


        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {

                Log.d(TAG, " 被观察者 Observable的工作线程是: " + Thread.currentThread().getName());
                // 打印验证
                emitter.onNext(1);
                emitter.onComplete();
            }
        });

// 步骤2：创建观察者 Observer 并 定义响应事件行为
        // 在主线程创建观察者 Observer 对象
        // 所以接收 & 响应事件的线程是：主线程
        Observer<Integer> observer = new Observer<Integer>() {

            @Override
            public void onSubscribe(Disposable d) {
//                if(isFinishing()){
//                    d.dispose();
//                    d.isDisposed();
//                }
//                c.add(d);
//               c.clear();
                Log.d(TAG, "开始采用subscribe连接");
                Log.d(TAG, " 观察者 Observer的工作线程是: " + Thread.currentThread().getName());
                // 打印验证

            }

            @Override
            public void onNext(Integer value) {
                Log.d(TAG, "对Next事件" + value + "作出响应");
//                tv.setText("对Next事件"+ value +"作出响应");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "对Error事件作出响应");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "对Complete事件作出响应");
            }
        };

        // 步骤3：通过订阅（subscribe）连接观察者和被观察者
        observable.subscribeOn(Schedulers.io()).subscribeOn(AndroidSchedulers.mainThread())// 1. 指定被观察者 生产事件的线程  第一次有效
                .observeOn(AndroidSchedulers.mainThread())// 2. 指定观察者 接收 & 响应事件的线程 // 第一次指定观察者线程 = 主线程
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG, "第一次观察者Observer的工作线程是： " + Thread.currentThread().getName());
                    }
                })
                .observeOn(Schedulers.newThread())// 第二次指定观察者线程 = 新的工作线程
                .subscribe(observer);// 3. 最后再通过订阅（subscribe）连接观察者和被观察者
        // 注：
// 1. 整体方法调用顺序：观察者.onSubscribe（）> 被观察者.subscribe（）> 观察者.doOnNext（）>观察者.onNext（）>观察者.onComplete()
// 2. 观察者.onSubscribe（）固定在主线程进行


    }

    public void rxjavaDelay() {
        //延迟操作 delay（）  使得被观察者延迟一段时间再发送事件
//        // 1. 指定延迟时间
//// 参数1 = 时间；参数2 = 时间单位
//        delay(long delay,TimeUnit unit)
//
//// 2. 指定延迟时间 & 调度器
//// 参数1 = 时间；参数2 = 时间单位；参数3 = 线程调度器
//        delay(long delay,TimeUnit unit,mScheduler scheduler)
//
//// 3. 指定延迟时间  & 错误延迟
//// 错误延迟，即：若存在Error事件，则如常执行，执行后再抛出错误异常
//// 参数1 = 时间；参数2 = 时间单位；参数3 = 错误延迟参数
//        delay(long delay,TimeUnit unit,boolean delayError)
//
//// 4. 指定延迟时间 & 调度器 & 错误延迟
//// 参数1 = 时间；参数2 = 时间单位；参数3 = 线程调度器；参数4 = 错误延迟参数
//        delay(long delay,TimeUnit unit,mScheduler scheduler,boolean delayError): 指定延迟多长时间并添加调度器，错误通知可以设置是否延迟

        Observable.just(1, 2, 3)
                .delay(3, TimeUnit.SECONDS) // 延迟3s再发送，由于使用类似，所以此处不作全部展示
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer value) {
                        Log.d(TAG, "接收到了事件" + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "对Complete事件作出响应");
                    }
                });


    }

    public void rxjavaDo() {
        //在事件的生命周期中操作
        //在事件发送 & 接收的整个生命周期过程中进行操作
        //如发送事件前的初始化、发送事件后的回调请求等
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onError(new Throwable("发生错误了"));
            }
        })
                // 1. 当Observable每发送1次数据事件就会调用1次
                .doOnEach(new Consumer<Notification<Integer>>() {
                    @Override
                    public void accept(Notification<Integer> integerNotification) throws Exception {
                        Log.d(TAG, "doOnEach: " + integerNotification.getValue());
                    }
                })
                // 2. 执行Next事件前调用
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG, "doOnNext: " + integer);
                    }
                })
                // 3. 执行Next事件后调用
                .doAfterNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG, "doAfterNext: " + integer);
                    }
                })
                // 4. Observable正常发送事件完毕后调用
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.e(TAG, "doOnComplete: ");
                    }
                })
                // 5. Observable发送错误事件时调用
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d(TAG, "doOnError: " + throwable.getMessage());
                    }
                })
                // 6. 观察者订阅时调用
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        Log.e(TAG, "doOnSubscribe: ");
                    }
                })
                // 7. Observable发送事件完毕后调用，无论正常发送完毕 / 异常终止
                .doAfterTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.e(TAG, "doAfterTerminate: ");
                    }
                })
                // 8. 最后执行
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.e(TAG, "doFinally: ");
                    }
                })
                .doOnTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.e(TAG, "doOnTerminate: ");
                    }
                })
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer value) {
                        Log.d(TAG, "接收到了事件" + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "对Complete事件作出响应");
                    }
                });


    }

    public void rxjavaError() {
        //可捕获在它之前发生的异常
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onError(new Throwable("发生错误了"));
            }
        })
                .onErrorReturn(new Function<Throwable, Integer>() {
                    @Override
                    public Integer apply(@NonNull Throwable throwable) throws Exception {
                        // 捕捉错误异常
                        Log.e(TAG, "在onErrorReturn处理了错误: " + throwable.toString());

                        return 666;
                        // 发生错误事件后，发送一个"666"事件，最终正常结束
                    }
                })
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer value) {
                        Log.d(TAG, "接收到了事件" + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "对Complete事件作出响应");
                    }
                });


    }

    public void rxjavaonErrorResumeNext() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onError(new Throwable("发生错误了"));
            }
        })
                .onErrorResumeNext(new Function<Throwable, ObservableSource<? extends Integer>>() {
                    @Override
                    public ObservableSource<? extends Integer> apply(@NonNull Throwable throwable) throws Exception {

                        // 1. 捕捉错误异常
                        Log.e(TAG, "在onErrorReturn处理了错误: " + throwable.toString());

                        // 2. 发生错误事件后，发送一个新的被观察者 & 发送事件序列
                        return Observable.just(11, 22);

                    }
                })
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer value) {
                        Log.d(TAG, "接收到了事件" + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "对Complete事件作出响应");
                    }
                });

    }

    public void rxjavaOnExceptionResumeNext() {
        //onExceptionResumeNext 不会出现错误提示 ，直接提供 被观察者处理
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onError(new Exception("发生错误了"));
            }
        })
                .onExceptionResumeNext(new Observable<Integer>() {
                    @Override
                    protected void subscribeActual(Observer<? super Integer> observer) {
                        observer.onNext(11);
                        observer.onNext(22);
                        observer.onComplete();
                    }
                })
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer value) {
                        Log.d(TAG, "接收到了事件" + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "对Complete事件作出响应");
                    }
                });


    }

    public void rxjavaRetry() {
//        <-- 1. retry（） -->
//// 作用：出现错误时，让被观察者重新发送数据
//// 注：若一直错误，则一直重新发送
//
//<-- 2. retry（long time） -->
//// 作用：出现错误时，让被观察者重新发送数据（具备重试次数限制
//// 参数 = 重试次数
//
//<-- 3. retry（Predicate predicate） -->
//// 作用：出现错误后，判断是否需要重新发送数据（若需要重新发送& 持续遇到错误，则持续重试）
//// 参数 = 判断逻辑
//
//<--  4. retry（new BiPredicate<Integer, Throwable>） -->
//// 作用：出现错误后，判断是否需要重新发送数据（若需要重新发送 & 持续遇到错误，则持续重试
//// 参数 =  判断逻辑（传入当前重试次数 & 异常错误信息）
//
//<-- 5. retry（long time,Predicate predicate） -->
//// 作用：出现错误后，判断是否需要重新发送数据（具备重试次数限制
//// 参数 = 设置重试次数 & 判断逻辑
//        Observable.create(new ObservableOnSubscribe<Integer>() {
//            @Override
//            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
//                e.onNext(1);
//                e.onNext(2);
//                e.onError(new Exception("发生错误了"));
//                e.onNext(3);
//            }
//        })
//                .retry() // 遇到错误时，让被观察者重新发射数据（若一直错误，则一直重新发送
//                .subscribe(new Observer<Integer>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//                    @Override
//                    public void onNext(Integer value) {
//                        Log.d(TAG, "接收到了事件"+ value  );
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.d(TAG, "对Error事件作出响应");
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        Log.d(TAG, "对Complete事件作出响应");
//                    }
//                });

//        Observable.create(new ObservableOnSubscribe<Integer>() {
//            @Override
//            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
//                e.onNext(1);
//                e.onNext(2);
//                e.onError(new Exception("发生错误了"));
//                e.onNext(3);
//            }
//        })
//                .retry(3) // 设置重试次数 = 3次
//                .subscribe(new Observer<Integer>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//                    @Override
//                    public void onNext(Integer value) {
//                        Log.d(TAG, "接收到了事件"+ value  );
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.d(TAG, "对Error事件作出响应");
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        Log.d(TAG, "对Complete事件作出响应");
//                    }
//                });


//        Observable.create(new ObservableOnSubscribe<Integer>() {
//            @Override
//            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
//                e.onNext(1);
//                e.onNext(2);
//                e.onError(new Exception("发生错误了"));
//                e.onNext(3);
//            }
//        })
//                // 拦截错误后，判断是否需要重新发送请求
//                .retry(new Predicate<Throwable>() {
//                    @Override
//                    public boolean test(@NonNull Throwable throwable) throws Exception {
//                        // 捕获异常
//                        Log.e(TAG, "retry错误: "+throwable.toString());
//
//                        //返回false = 不重新重新发送数据 & 调用观察者的onError结束
//                        //返回true = 重新发送请求（若持续遇到错误，就持续重新发送）
//                        return true;
//                    }
//                })
//                .subscribe(new Observer<Integer>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//                    @Override
//                    public void onNext(Integer value) {
//                        Log.d(TAG, "接收到了事件"+ value  );
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.d(TAG, "对Error事件作出响应");
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        Log.d(TAG, "对Complete事件作出响应");
//                    }
//                });
//    <--  4. retry（new BiPredicate<Integer, Throwable>） -->
// 作用：出现错误后，判断是否需要重新发送数据（若需要重新发送 & 持续遇到错误，则持续重试
// 参数 =  判断逻辑（传入当前重试次数 & 异常错误信息）

        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onError(new Exception("发生错误了"));
                e.onNext(3);
            }
        })

                // 拦截错误后，判断是否需要重新发送请求
                .retry(new BiPredicate<Integer, Throwable>() {
                    @Override
                    public boolean test(@NonNull Integer integer, @NonNull Throwable throwable) throws Exception {
                        // 捕获异常
                        Log.e(TAG, "异常错误 =  " + throwable.toString());

                        // 获取当前重试次数
                        Log.e(TAG, "当前重试次数 =  " + integer);

                        //返回false = 不重新重新发送数据 & 调用观察者的onError结束
                        //返回true = 重新发送请求（若持续遇到错误，就持续重新发送）
                        return true;
                    }
                })
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer value) {
                        Log.d(TAG, "接收到了事件" + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "对Complete事件作出响应");
                    }
                });

//        具体使用类似于retry（Predicate predicate），唯一区别：返回 true 则不重新发送数据事件。此处不作过多描述


    }

    public void rxjavaRetryUntil() {
        //retryUntil  出现错误后，判断是否需要重新发送数据
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onError(new Exception("发生错误了"));
                e.onNext(3);
            }
        })

                // 拦截错误后，判断是否需要重新发送请求
                .retryUntil(new BooleanSupplier() {
                    @Override
                    public boolean getAsBoolean() throws Exception {

                        // ture 不做处理  false 一直发送


                        return true;
                    }
                })
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer value) {
                        Log.d(TAG, "接收到了事件" + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "对Complete事件作出响应");
                    }
                });
//  Observable.create(new ObservableOnSubscribe<Integer>() {
//            @Override
//            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
//                e.onNext(1);
//                e.onNext(2);
//                e.onError(new Exception("发生错误了"));
//                e.onNext(3);
//            }
//        })
//
//                // 拦截错误后，判断是否需要重新发送请求
//                .retry(new Predicate<Throwable>() {
//                    @Override
//                    public boolean test(Throwable throwable) throws Exception {
//                        Log.d(TAG,"throwable"+throwable.getMessage().toString());
//                        //false 不发送，true 发送
//                        return true;
//                    }
//                })
//                .subscribe(new Observer<Integer>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//                    @Override
//                    public void onNext(Integer value) {
//                        Log.d(TAG, "接收到了事件"+ value  );
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.d(TAG, "对Error事件作出响应");
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        Log.d(TAG, "对Complete事件作出响应");
//                    }
//                });


    }

    public void rxjavaRetryWhen() {
        //RetryWhen  遇到错误时，将发生的错误传递给一个新的被观察者（Observable），并决定是否需要重新订阅原始被观察者（Observable）& 发送事件
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onError(new Exception("发生错误了"));
                e.onNext(3);
            }
        })
                // 遇到error事件才会回调
                .retryWhen(new Function<Observable<Throwable>, ObservableSource<?>>() {

                    @Override
                    public ObservableSource<?> apply(@NonNull Observable<Throwable> throwableObservable) throws Exception {
                        // 参数Observable<Throwable>中的泛型 = 上游操作符抛出的异常，可通过该条件来判断异常的类型
                        // 返回Observable<?> = 新的被观察者 Observable（任意类型）
                        // 此处有两种情况：
                        // 1. 若 新的被观察者 Observable发送的事件 = Error事件，那么 原始Observable则不重新发送事件：
                        // 2. 若 新的被观察者 Observable发送的事件 = Next事件 ，那么原始的Observable则重新发送事件：
                        return throwableObservable.flatMap(new Function<Throwable, ObservableSource<?>>() {
                            @Override
                            public ObservableSource<?> apply(@NonNull Throwable throwable) throws Exception {

                                // 1. 若返回的Observable发送的事件 = Error事件，则原始的Observable不重新发送事件
                                // 该异常错误信息可在观察者中的onError（）中获得
//                                return Observable.error(new Throwable("retryWhen终止啦"));
                                return Observable.just(1);

                                // 2. 若返回的Observable发送的事件 = Next事件，则原始的Observable重新发送事件（若持续遇到错误，则持续重试）
                                // return Observable.just(1);
                            }
                        });

                    }
                })
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer value) {
                        Log.d(TAG, "接收到了事件" + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "对Error事件作出响应" + e.toString());
                        // 获取异常错误信息
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "对Complete事件作出响应");
                    }
                });


    }

    public void rxjavaRepeat() {
        //重复不断地发送被观察者事件
        // 不传入参数 = 重复发送次数 = 无限次
//        repeat（）；
        // 传入参数 = 重复发送次数有限
//        repeatWhen（Integer int ）；

        // 具体使用
        Observable.create(new ObservableOnSubscribe<Integer>() {

            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
//                e.onError(new Throwable("错误了的"));
                e.onNext(4);
            }
        })
//                .repeat(3) // 重复创建次数 =- 3次
//                .repeat()
                .repeatWhen(new Function<Observable<Object>, ObservableSource<?>>() {
                                @Override
                                // 在Function函数中，必须对输入的 Observable<Object>进行处理，这里我们使用的是flatMap操作符接收上游的数据
                                public ObservableSource<?> apply(@NonNull Observable<Object> objectObservable) throws Exception {
                                    // 将原始 Observable 停止发送事件的标识（Complete（） /  Error（））转换成1个 Object 类型数据传递给1个新被观察者（Observable）
                                    // 以此决定是否重新订阅 & 发送原来的 Observable
                                    // 此处有2种情况：
                                    // 1. 若新被观察者（Observable）返回1个Complete（） /  Error（）事件，则不重新订阅 & 发送原来的 Observable
                                    // 2. 若新被观察者（Observable）返回其余事件，则重新订阅 & 发送原来的 Observable
                                    return objectObservable.flatMap(new Function<Object, ObservableSource<?>>() {
                                        @Override
                                        public ObservableSource<?> apply(@NonNull Object throwable) throws Exception {

                                            // 情况1：若新被观察者（Observable）返回1个Complete（） /  Error（）事件，则不重新订阅 & 发送原来的 Observable
//                                            return Observable.empty();
                                            // Observable.empty() = 发送Complete事件，但不会回调观察者的onComplete（）

                                            return Observable.error(new Throwable("不再重新订阅事件"));
                                            // 返回Error事件 = 回调onError（）事件，并接收传过去的错误信息.不会回调观察者的 onError 方法。

                                            // 情况2：若新被观察者（Observable）返回其余事件，则重新订阅 & 发送原来的 Observable
//                                             return Observable.just(1);
                                            // 仅仅是作为1个触发重新订阅被观察者的通知，发送的是什么数据并不重要，只要不是Complete（） /  Error（）事件
                                        }
                                    });

                                }
                            }
                )


                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "开始采用subscribe连接");
                    }

                    @Override
                    public void onNext(Integer value) {
                        Log.d(TAG, "接收到了事件" + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "对Error事件作出响应:"+e.toString());
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "对Complete事件作出响应");
                    }

                });


    }


    //过滤操作符 过滤 / 筛选 被观察者（Observable）发送的事件 & 观察者 （Observer）接收的事件
    //1。 根据 指定条件 过滤事件
    public void rxjavaFiter() {
        //过滤 特定条件的事件
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                // 1. 发送5个事件
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onNext(4);
                emitter.onNext(5);
            }

            // 2. 采用filter（）变换操作符
        }).filter(new Predicate<Integer>() {
            // 根据test()的返回值 对被观察者发送的事件进行过滤 & 筛选
            // a. 返回true，则继续发送
            // b. 返回false，则不发送（即过滤）
            @Override
            public boolean test(Integer integer) throws Exception {
                return integer > 3;
                // 本例子 = 过滤了整数≤3的事件
            }
        }).subscribe(new Observer<Integer>() {

            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "开始采用subscribe连接");
            }

            @Override
            public void onNext(Integer value) {
                Log.d(TAG, "过滤后得到的事件是：" + value);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "对Error事件作出响应");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "对Complete事件作出响应");
            }
        });


    }

    public void rxjavaOfType() {
        //过滤 特定数据类型的数据
        Observable.just(1, "Carson", 3, "Ho", 5)
                .ofType(Integer.class) // 筛选出 整型数据
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG, "获取到的整型事件元素是： " + integer);
                    }
                });

    }

    public void rxjavaSkip() {
        //跳过某个事件   Skip 前面几个  slipLast  后面几个
        // 使用1：根据顺序跳过数据项
        Observable.just(1, 2, 3, 4, 5)
                .skip(1) // 跳过正序的前1项
                .skipLast(2) // 跳过正序的后2项
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG, "获取到的整型事件元素是： " + integer);
                    }
                });

//// 使用2：根据时间跳过数据项
//        // 发送事件特点：发送数据0-5，每隔1s发送一次，每次递增1；第1次发送延迟0s
        Observable.intervalRange(5, 5, 1, 1, TimeUnit.SECONDS)
                .skip(1, TimeUnit.SECONDS) // 跳过第1s发送的数据
                .skipLast(1, TimeUnit.SECONDS) // 跳过最后1s发送的数据
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long along) throws Exception {
                        Log.d(TAG, "获取到的整型事件元素是： " + along);
                    }
                });

    }

    public void rxjavaDistinct() {
        // 使用1：过滤事件序列中重复的事件
        Observable.just(1, 2, 3, 1, 2)
                .distinct()
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG, "不重复的整型事件元素是： " + integer);
                    }
                });

        // 使用2：过滤事件序列中 连续重复的事件
        // 下面序列中，连续重复的事件 = 3、4
        Observable.just(1, 2, 3, 1, 2, 3, 3, 4, 4)
                .distinctUntilChanged()
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG, "不连续重复的整型事件元素是： " + integer);
                    }
                });

    }

    //2.根据 指定事件数量 过滤事件
    public void rxjavaTake() {
        //指定观察者最多能接收到的事件数量
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                // 1. 发送5个事件
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onNext(4);
                emitter.onNext(5);
            }

            // 采用take（）变换操作符
            // 指定了观察者只能接收2个事件
        }).take(2)
                .subscribe(new Observer<Integer>() {

                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "开始采用subscribe连接");
                    }

                    @Override
                    public void onNext(Integer value) {
                        Log.d(TAG, "过滤后得到的事件是：" + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "对Complete事件作出响应");
                    }
                });

// 实际上，可理解为：被观察者还是发送了5个事件，只是因为操作符的存在拦截了3个事件，最终观察者接收到的是2个事件
        //takeLast（）                指定观察者只能接收到被观察者发送的最后几个事件

        Observable.just(1, 2, 3, 4, 5)
                .takeLast(3) //指定观察者只能接受被观察者发送的3个事件
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "开始采用subscribe连接");
                    }

                    @Override
                    public void onNext(Integer value) {
                        Log.d(TAG, "过滤后得到的事件是：" + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "对Complete事件作出响应");
                    }
                });


    }

    //根据 指定时间 过滤事件  通过设置指定的时间，仅发送在该时间内的事件
    public void rxjavaThrottleFirst() {
        //在某段时间内，只发送该段时间内第1次事件 / 最后1次事件
//        <<- 在某段时间内，只发送该段时间内第1次事件 ->>
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                // 隔段事件发送时间
                e.onNext(1);
                Thread.sleep(500);

                e.onNext(2);
                Thread.sleep(400);

                e.onNext(3);
                Thread.sleep(300);

                e.onNext(4);
                Thread.sleep(300);

                e.onNext(5);
                Thread.sleep(300);

                e.onNext(6);
                Thread.sleep(400);

                e.onNext(7);
                Thread.sleep(300);
                e.onNext(8);

                Thread.sleep(300);
                e.onNext(9);

                Thread.sleep(300);
                e.onComplete();
            }
        }).throttleFirst( 1, TimeUnit.SECONDS)//每1秒中采用数据
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "开始采用subscribe连接");
                    }

                    @Override
                    public void onNext(Integer value) {
                        Log.d(TAG, "接收到了事件"+ value  );
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "对Complete事件作出响应");
                    }
                });


//<<- 在某段时间内，只发送该段时间内最后1次事件 ->>
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                // 隔段事件发送时间
                e.onNext(1);
                Thread.sleep(500);

                e.onNext(2);
                Thread.sleep(400);

                e.onNext(3);
                Thread.sleep(300);

                e.onNext(4);
                Thread.sleep(300);

                e.onNext(5);
                Thread.sleep(300);

                e.onNext(6);
                Thread.sleep(400);

                e.onNext(7);
                Thread.sleep(300);
                e.onNext(8);

                Thread.sleep(300);
                e.onNext(9);

                Thread.sleep(300);
                e.onComplete();
            }
        }).throttleLast(1, TimeUnit.SECONDS)//每1秒中采用数据
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "开始采用subscribe连接");
                    }

                    @Override
                    public void onNext(Integer value) {
                        Log.d(TAG, "接收到了事件"+ value  );
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "对Complete事件作出响应");
                    }
                });



    }

    public void rxjavaSample(){
        //在某段时间内，只发送该段时间内最新（最后）1次事件
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                // 隔段事件发送时间
                e.onNext(1);
                Thread.sleep(500);

                e.onNext(2);
                Thread.sleep(400);

                e.onNext(3);
                Thread.sleep(300);

                e.onNext(4);
                Thread.sleep(300);

                e.onNext(5);
                Thread.sleep(300);

                e.onNext(6);
                Thread.sleep(400);

                e.onNext(7);
                Thread.sleep(300);
                e.onNext(8);

                Thread.sleep(300);
                e.onNext(9);

                Thread.sleep(300);
                e.onComplete();
            }
        }).sample(1, TimeUnit.SECONDS)//每1秒中采用数据
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "开始采用subscribe连接");
                    }

                    @Override
                    public void onNext(Integer value) {
                        Log.d(TAG, "接收到了事件"+ value  );
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "对Complete事件作出响应");
                    }
                });

    }

    public void rxjavaThrottleWithTimeout(){
        //throttleWithTimeout （） / debounce（）
        //发送数据事件时，若2次发送事件的间隔＜指定时间，就会丢弃前一次的数据，直到指定时间内都没有新数据发射时才会发送后一次的数据

        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                // 隔段事件发送时间
                e.onNext(1);
                Thread.sleep(500);
                e.onNext(2); // 1和2之间的间隔小于指定时间1s，所以前1次数据（1）会被抛弃，2会被保留
                Thread.sleep(1500);  // 因为2和3之间的间隔大于指定时间1s，所以之前被保留的2事件将发出
                e.onNext(3);
                Thread.sleep(1500);  // 因为3和4之间的间隔大于指定时间1s，所以3事件将发出
                e.onNext(4);
                Thread.sleep(500); // 因为4和5之间的间隔小于指定时间1s，所以前1次数据（4）会被抛弃，5会被保留
                e.onNext(5);
                Thread.sleep(500); // 因为5和6之间的间隔小于指定时间1s，所以前1次数据（5）会被抛弃，6会被保留
                e.onNext(6);
                Thread.sleep(1500); // 因为6和Complete实践之间的间隔大于指定时间1s，所以之前被保留的6事件将发出

                e.onComplete();
            }
        }).debounce(1, TimeUnit.SECONDS)//每1秒中采用数据
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer value) {
                        Log.d(TAG, "接收到了事件"+ value  );
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "对Complete事件作出响应");
                    }
                });



    }
    //根据 指定事件位置 过滤事件
    public void rxjavafirstElement(){
        //firstElement（） / lastElement（） 仅选取第1个元素 / 最后一个元素
        // 获取第1个元素
        Observable.just(1, 2, 3, 4, 5)
                .firstElement()
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept( Integer integer) throws Exception {
                        Log.d(TAG,"获取到的第一个事件是： "+ integer);
                    }
                });

// 获取最后1个元素
        Observable.just(1, 2, 3, 4, 5)
                .lastElement()
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept( Integer integer) throws Exception {
                        Log.d(TAG,"获取到的最后1个事件是： "+ integer);
                    }
                });


    }

    public void rxjavaelementAt(){
        //elementAt（） 指定接收某个元素（通过 索引值 确定）
        //注：允许越界，即获取的位置索引 ＞ 发送事件序列长度

        // 使用1：获取位置索引 = 2的 元素
        // 位置索引从0开始
        Observable.just(1, 2, 3, 4, 5)
                .elementAt(2)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept( Integer integer) throws Exception {
                        Log.d(TAG,"获取到的事件元素是： "+ integer);
                    }
                });

// 使用2：获取的位置索引 ＞ 发送事件序列长度时，设置默认参数
        Observable.just(1, 2, 3, 4, 5)
                .elementAt(6,10)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept( Integer integer) throws Exception {
                        Log.d(TAG,"获取到的事件元素是： "+ integer);
                    }
                });


    }

    public void rxjavaelementAtOrError(){
        //在elementAt（）的基础上，当出现越界情况（即获取的位置索引 ＞ 发送事件序列长度）时，即抛出异常
        Observable.just(1, 2, 3, 4, 5)
                .elementAtOrError(6)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG, "获取到的事件元素是： " + integer);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d(TAG, "Throwable： " + throwable.toString());
                    }
                });


    }

    /*布尔操作符*/
    public void rxjavaAll() {
        //判断发送的每项数据是否都满足 设置的函数条件
        //若满足，返回 true；否则，返回 false
        Observable.just(1, 2, 3, 4, 5, 6)
                .all(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        return (integer <= 5);
                        // 该函数用于判断Observable发送的10个数据是否都满足integer<=10
                    }
                }).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                Log.d(TAG, "result is " + aBoolean);
                // 输出返回结果
            }

        });

    }

    public void rxjavatakeWhile() {
        //判断发送的每项数据是否满足 设置函数条件

        //若发送的数据满足该条件，则发送该项数据；否则不发

        // 1. 每1s发送1个数据 = 从0开始，递增1，即0、1、2、3
        Observable.interval(1, TimeUnit.SECONDS)
                // 2. 通过takeWhile传入一个判断条件
                .takeWhile(new Predicate<Long>() {
                    @Override
                    public boolean test(Long integer) throws Exception {
                        return (integer < 3);
                        // 当发送的数据满足<3时，才发送Observable的数据
                    }
                }).subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(Long value) {
                Log.d(TAG, "发送了事件 " + value);
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        });
    }

    public void rxjavaskipWhile() {
//        判断发送的每项数据是否满足 设置函数条件  逻辑与 takeWhile 相反

//        直到该判断条件 = false时，才开始发送Observable的数据
        // 1. 每隔1s发送1个数据 = 从0开始，每次递增1
        Observable.interval(1, TimeUnit.SECONDS)
                // 2. 通过skipWhile（）设置判断条件
                .skipWhile(new Predicate<Long>() {
                    @Override
                    public boolean test(Long aLong) throws Exception {
                        return (aLong < 5);
                        // 直到判断条件不成立 = false = 发射的数据≥5，才开始发送数据
                    }
                }).subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(Long value) {
                Log.d(TAG, "发送了事件 " + value);
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        });

    }

    public void rxjavatakeUntil() {
        //执行到某个条件时，停止发送事件  包含该事件
        // 1. 每1s发送1个数据 = 从0开始，递增1，即0、1、2、3
//        Observable.interval(1, TimeUnit.SECONDS)
//                // 2. 通过takeUntil的Predicate传入判断条件
//                .takeUntil(new Predicate<Long>(){
//                    @Override
//                    public boolean test( Long integer) throws Exception {
//                        return (integer>3);
//                        // 返回true时，就停止发送事件
//                        // 当发送的数据满足>3时，就停止发送Observable的数据
//                    }
//                }).subscribe(new Observer<Long>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//            }
//
//            @Override
//            public void onNext(Long value) {
//                Log.d(TAG,"发送了事件 "+ value);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//            }
//
//            @Override
//            public void onComplete() {
//                Log.d(TAG,"onComplete");
//            }
//        });
        //该判断条件也可以是Observable，即 等到 takeUntil（） 传入的Observable开始发送数据，（原始）第1个Observable的数据停止发送数据
        // （原始）第1个Observable：每隔1s发送1个数据 = 从0开始，每次递增1
        Observable.interval(1, TimeUnit.SECONDS)
                // 第2个Observable：延迟5s后开始发送1个Long型数据
                .takeUntil(Observable.timer(5, TimeUnit.SECONDS))
                //当第 5s 时，第2个 Observable 开始发送数据，于是（原始）第1个 Observable 停止发送数据
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "开始采用subscribe连接");
                    }

                    @Override
                    public void onNext(Long value) {
                        Log.d(TAG, "接收到了事件" + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "对Complete事件作出响应");
                    }

                });


    }

    public void rxjavaskipUntil() {
        //等到 skipUntil（） 传入的Observable开始发送数据，（原始）第1个Observable的数据才开始发送数据
        // （原始）第1个Observable：每隔1s发送1个数据 = 从0开始，每次递增1
        //5s后（ skipUntil（） 传入的Observable开始发送数据），（原始）第1个Observable的数据才开始发送
        Observable.interval(1, TimeUnit.SECONDS)
                // 第2个Observable：延迟5s后开始发送1个Long型数据
                .skipUntil(Observable.timer(5, TimeUnit.SECONDS))
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "开始采用subscribe连接");
                    }

                    @Override
                    public void onNext(Long value) {
                        Log.d(TAG, "接收到了事件" + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "对Complete事件作出响应");
                    }

                });
    }

    public void rxjavaSequenceEqual() {
//        判定两个Observables需要发送的数据是否相同

//        若相同，返回 true；否则，返回 false
        Observable.sequenceEqual(
                Observable.just(4, 5, 6),
                Observable.just(4, 5, 6)
        )
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        Log.d(TAG, "2个Observable是否相同：" + aBoolean);
                        // 输出返回结果
                    }
                });

    }

    public void rxjavacontains() {
//        判断发送的数据中是否包含指定数据
//
//        若包含，返回 true；否则，返回 false
//        内部实现 = exists（）

        Observable.just(1, 2, 3, 4, 5, 6)
                .contains(4)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        Log.d(TAG, "result is " + aBoolean);
                        // 输出返回结果
                    }

                });


    }

    public void rxjavaisEmpty() {
//        判断发送的数据是否为空  整体是否为空？

//        若为空，返回 true；否则，返回 false

        Observable.just(1+"", 2+"", 3+"",  5+"", 6+"")
                .isEmpty() // 判断发送的数据中是否为空
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        Log.d(TAG,"result is "+ aBoolean);
                        // 输出返回结果
                    }
                });
    }

    public void rxjavaamb(){
        //当需要发送多个 Observable时，只发送 先发送数据的Observable的数据，而其余 Observable则被丢弃。
        // 设置2个需要发送的Observable & 放入到集合中
        List<ObservableSource<Integer>> list= new ArrayList<>();
        // 第1个Observable延迟1秒发射数据
        list.add( Observable.just(1,2,3).delay(1,TimeUnit.SECONDS));
        // 第2个Observable正常发送数据
        list.add( Observable.just(4,5,6));

        // 一共需要发送2个Observable的数据
        // 但由于使用了amba（）,所以仅发送先发送数据的Observable
        // 即第二个（因为第1个延时了）
        Observable.amb(list).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.e(TAG, "接收到了事件 "+integer);
            }
        });
    }

    public void rxjavadefaultIfEmpty(){
        //在不发送任何有效事件（ Next事件）、仅发送了 Complete 事件的前提下，发送一个默认值
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                // 不发送任何有效事件
                //  e.onNext(1);
//                  e.onNext(2);

                // 仅发送Complete事件
                e.onComplete();
            }
        }).defaultIfEmpty(10) // 若仅发送了Complete事件，默认发送 值 = 10
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "开始采用subscribe连接");
                    }

                    @Override
                    public void onNext(Integer value) {
                        Log.d(TAG, "接收到了事件"+ value  );
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "对Complete事件作出响应");
                    }
                });
    }

}
