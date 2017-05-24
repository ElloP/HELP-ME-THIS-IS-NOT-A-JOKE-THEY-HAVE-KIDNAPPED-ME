package com.helpme.app.maybetest;

import com.helpme.app.utils.maybe.Just;
import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.utils.maybe.Nothing;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kopa on 2017-05-23.
 */
public class MaybeTest {
    private static class Container {
        private int value;

        public Container(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void testIsJust() {
        assert (new Just<>(1).isJust());
    }

    @Test
    public void testIsNothing() {
        assert (new Nothing<>().isNothing());
    }

    @Test
    public void testNullJust() {
        exception.expect(NullPointerException.class);
        new Just<>(null);
    }

    @Test
    public void testGetNullValue() {
        exception.expect(NullPointerException.class);
        new Nothing<>().getValue();
    }

    @Test
    public void testMaybeRunJust() {
        Container test = new Container(1);
        Maybe<Container> maybe = new Just<>(test);
        maybe.run(c -> c.setValue(3));
        assert (test.getValue() == 3);
    }

    @Test
    public void testMaybeRunNothing() {
        Maybe<Boolean> maybe = new Nothing<>();
        maybe.run(t -> {
            assert false;
        });
    }

    @Test
    public void testMaybeChainJust() {
        Maybe<Boolean> maybe0 = new Just<>(true);
        Maybe<Integer> maybe1 = maybe0.chain(t -> t ? 1 : 4);
        assert (maybe1.isJust() && maybe1.getValue() == 1);
    }

    @Test
    public void testMaybeChainNothing() {
        Maybe<Boolean> maybe = new Nothing<>();
        assert (maybe.chain(t -> true).isNothing());
    }

    @Test
    public void testMaybeCheckJust() {
        Maybe<Integer> maybe = new Just<>(3);
        assert (maybe.check(i -> i == 3));
    }

    @Test
    public void testMaybeCheckNothing() {
        Maybe<Integer> maybe = new Nothing<>();
        assert (!maybe.check(i -> true));
    }

    @Test
    public void testMaybeCheckNothingDefaultTrue() {
        Maybe<Integer> maybe = new Nothing<>();
        assert (maybe.check(i -> false, true));
    }

    @Test
    public void testMaybeWrapNull() {
        assert (Maybe.wrap(null).isNothing());
    }

    @Test
    public void testMaybeWrapValue() {
        assert (Maybe.wrap(1).isJust());
    }

    @Test
    public void testMaybeWrapJust() {
        Maybe<Boolean> maybe0 = new Just<>(true);
        Maybe<Boolean> maybe1 = Maybe.wrap(maybe0);
        assert (maybe1.isJust() && maybe1.getValue());
    }

    @Test
    public void testMaybeWrapNothing() {
        Maybe<Boolean> maybe0 = new Nothing<>();
        Maybe<Boolean> maybe1 = Maybe.wrap(maybe0);
        assert (maybe1.isNothing());
    }

    @Test
    public void testMaybeEqualsJust() {
        assert (new Just<>(3).equals(new Just<>(3)));
    }

    @Test
    public void testMaybeNotEqualsJust() {
        assert (!new Just<>(3).equals(new Just<>(4)));
    }

    @Test
    public void testMaybeEqualsNothing() {
        assert (new Nothing<>().equals(new Nothing<>()));
    }

    @Test
    public void testHashCode() {
        assert (new Just<>("test").hashCode() == "test".hashCode());
    }

    @Test
    public void testMaybeEqualsJustNothing() {
        Maybe<Integer> just = new Just<>(5);
        Maybe<Integer> nothing = new Nothing<>();
        assert (!just.equals(nothing));
    }

    @Test
    public void testMaybeCast() {
        Integer element = 3;
        List<Maybe<Integer>> maybe0 = new ArrayList<>();
        maybe0.add(new Just<>(element));
        maybe0.add(new Nothing<>());
        List<Maybe<Object>> maybe1 = Maybe.cast(maybe0);

        assert (maybe1.get(0).isJust() && maybe1.get(0).getValue().equals(element) && maybe1.get(1).isNothing());
    }

}
