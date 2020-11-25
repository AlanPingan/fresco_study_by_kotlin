package com.project.alan.frescolearningbykotlin.kotlin.observer.chainobserver;

/**
 * Created by Alan on 2020/10/22.
 */

class ObservableCreate<T> extends Observable<T> {
    ObservableOnSubscribe<T> source;

    public ObservableCreate(ObservableOnSubscribe<T> source) {
        this.source = source;
    }

    @Override
    public void subScribe(Observer observer) {
        super.subScribe(observer);
    }

    //如果订阅功能要实现，在这里面就应该通知observer消息更新了
    @Override
    protected void subScribeActual(Observer observer) {
        // observer.onNext();
        CreateEmitter<T> p = new CreateEmitter<>(observer);
        observer.onSubscribe();
        //把发射器和被观察者绑定在一起（完成订阅）
        source.subscribe(p);


    }

    static final class CreateEmitter<T> implements Emitter<T> {

        Observer<T> observer;

        public CreateEmitter(Observer<T> observer) {
            this.observer = observer;
        }

        @Override
        public void onNext(T t) {
            observer.onNext(t);
        }

        @Override
        public void onError(Throwable e) {
            observer.onError(e);
        }

        @Override
        public void onComplete() {
            observer.onComplete();
        }
    }
}
