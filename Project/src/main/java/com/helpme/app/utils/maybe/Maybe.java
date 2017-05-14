package com.helpme.app.utils.maybe;

import com.helpme.app.utils.functions.IAction;
import com.helpme.app.utils.functions.ICheck;
import com.helpme.app.utils.functions.IFunction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kopa on 2017-04-20.
 */
public abstract class Maybe<T> {
    private final T value;

    protected Maybe(){
        this.value = null;
    }

    protected Maybe(T value){
        if(value == null){
            throw new NullPointerException("Trying to instantiate Maybe with null");
        }
        this.value = value;
    }

    public T getValue(){
        return value;
    }

    public boolean isJust(){
        return value != null;
    }

    public boolean isNothing(){
        return value == null;
    }



    public void run(IAction<T> action){
        if(isJust()){
            action.apply(value);
        }
    }

    public <R> Maybe<R> chain(IFunction<T, R> function){
        if(isJust()){
            return Maybe.wrap(function.apply(value));
        }
        return new Nothing<>();
    }

    public boolean check(ICheck<T> check){
        return check(check, false);
    }

    public boolean check(ICheck<T> check, boolean ifNothing){
        if(isJust()){
            return check.apply(value);
        }
        return ifNothing;
    }

    public static <T> Maybe<T> wrap(T value){
        if(value == null){
            return new Nothing<>();
        }
        return new Just<>(value);
    }

    public static <T extends Y, Y> Maybe<Y> wrap(Maybe<T> maybe){
        if(maybe.isJust()){
            return new Just<>(maybe.getValue());
        }
        return new Nothing<>();
    }

    public static <T extends Y, Y> List<Maybe<Y>> cast(List<Maybe<T>> maybe){
        List<Maybe<Y>> list = new ArrayList<Maybe<Y>>(){
            {
                for(Maybe<T> element : maybe){
                    add(Maybe.wrap(element));
                }
            }
        };
        return list;
    }
}
